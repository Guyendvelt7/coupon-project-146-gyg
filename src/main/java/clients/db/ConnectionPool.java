package clients.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Stack;
/**
 * @author Yoav Hacmon, Guy Endvelt and Gery Glazer
 * 03.2022
 */

/**
 * ConnectionPool class: a pool of connections that allow interaction with the database
 * if no connection is available request for connection is put on hold
 */
public class ConnectionPool {

    private static final int NUMBER_OF_CONNECTIONS = 10;
    private static ConnectionPool instance = null;
    private final Stack<Connection> connections = new Stack<>();

    /**
     * SINGLETON constructor.
     * private, internal use only
     * initialize communication to database
     */
    private ConnectionPool() {
        //todo: delete s.out before project submit
        System.out.println("Connection created...");
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DBManager.URL, DBManager.SQL_USER, DBManager.SQL_PASS);
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
        connections.push(connection);
    }

    /**
     * close all database communication
     */
    public void closeAllConnections() {
        synchronized (connections) {
            while (connections.size() < NUMBER_OF_CONNECTIONS) {
                try {
                    connections.wait();
                } catch (InterruptedException err) {
                    System.out.println(err.getMessage());
                }
            }
            connections.removeAllElements();
        }
    }

    /**
     * create one instance of ConnectionPool class only
     * uses synchronized to the entire class to prevent use of other methods while this is in process
     *
     * @return connection to database
     */
    public static ConnectionPool getInstance() {
        if (instance == null) {
            synchronized (ConnectionPool.class) {
                if (instance == null) {
                    instance = new ConnectionPool();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() {
        synchronized (connections) {
            if (connections.isEmpty()) {
                try {
                    connections.wait();
                } catch (InterruptedException err) {
                    System.out.println(err.getMessage());
                }
            }
            return connections.pop();
        }
    }

    public void restoreConnection(Connection connection) {
        synchronized (connections) {
            connections.push(connection);
            connections.notify();
        }
    }
}
