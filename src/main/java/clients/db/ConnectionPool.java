package clients.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Stack;

public class ConnectionPool {
    //todo: look for changes between projects

    private static final int NUMBER_OF_CONNECTIONS = 10;
    private static ConnectionPool instance=null;
    private  final Stack<Connection> connections = new Stack<>();

    private ConnectionPool() {
        System.out.println("Instance created...");
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DBManager.URL, DBManager.SQL_USER, DBManager.SQL_PASS);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        connections.push(connection);
    }

    public void closeAllConnections()  {
        synchronized (connections){
            while (connections.size()<NUMBER_OF_CONNECTIONS){
                try {
                    connections.wait();
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
            connections.removeAllElements();
        }
    }

    public static ConnectionPool getInstance() {
        //check if instance is null
        if(instance==null){
            //critical code, check that no other thread pass in same time
            synchronized (ConnectionPool.class){
                if (instance==null){
                    instance = new ConnectionPool();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() {
        synchronized (connections) {
            if (connections.isEmpty()) {
                //wait until a connection is available
                try {
                    connections.wait();
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
            return connections.pop();
        }
    }

    public void restoreConnection(Connection connection){
        synchronized (connections){
            connections.push(connection);
            //notify to waiting users that there is a connection available
            connections.notify();
        }
    }
}
