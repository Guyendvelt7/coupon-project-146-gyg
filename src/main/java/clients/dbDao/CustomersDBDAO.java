package clients.dbDao;

import clients.beans.Coupon;
import clients.beans.Customer;
import clients.dao.CustomersDAO;
import clients.db.DBManager;
import clients.db.DBTools;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomersDBDAO implements CustomersDAO {
    //todo: ConnectionPool connectionPool
    //todo: add exceptions

    @Override
    public boolean isCustomerExist(String name, String password) throws SQLException {
        Map<Integer,Object> values = new HashMap<>();
        values.put(1,name);
        values.put(2,password);
       ResultSet resultSet =  DBTools.runQueryForResult(DBManager.IS_CUSTOMER_EXISTS,values);
        assert resultSet != null;
        resultSet.next();
       return resultSet.getInt("1") == 1;
    }

    @Override
    public void addCustomer(Customer customer) throws SQLException {
    Map<Integer,Object> values = new HashMap<>();
    values.put(1,customer.getFirstName());
    values.put(2,customer.getLastName());
    values.put(3,customer.getEmail());
    values.put(4,customer.getPassword());
    DBTools.runQuery(DBManager.ADD_CUSTOMER,values);
    }

    @Override
    public void updateCustomer(Customer customer) throws SQLException {
        Map<Integer,Object> values = new HashMap<>();
        values.put(1,customer.getFirstName());
        values.put(2,customer.getLastName());
        values.put(3,customer.getEmail());
        values.put(4,customer.getPassword());
        DBTools.runQuery(DBManager.UPDATE_CUSTOMER,values);
    }

    @Override
    public void deleteCustomer(int customerID) throws SQLException {
        Map<Integer,Object> values = new HashMap<>();
        values.put(1,customerID);
        DBTools.runQuery(DBManager.DELETE_CUSTOMER,values);
    }

    @Override
    public List<Customer> getAllCustomers() throws SQLException, InterruptedException {
        ResultSet customerResultSet = DBTools.runQueryForResult(DBManager.GET_ALL_CUSTOMERS);
        List<Customer> allCustomers = new ArrayList<>();
        while(customerResultSet.next()){
            allCustomers.add(new Customer(
                    customerResultSet.getInt("id"),
                    customerResultSet.getString("firstName"),
                    customerResultSet.getString("lastName"),
                    customerResultSet.getString("email"),
                    customerResultSet.getString("password"),
                    CouponsDBDAO.getCouponsByCompanyId(customerResultSet.getInt("id"))
            ));
        }
        return allCustomers;
    }

    @Override
    public Customer getOneCustomer(int customerID) throws SQLException {
        Map<Integer,Object> values = new HashMap<>();
        values.put(1,customerID);
       ResultSet resultSet = DBTools.runQueryForResult(DBManager.GET_ONE_CUSTOMER,values);
        assert resultSet != null;
        resultSet.next();
        return new Customer(
                resultSet.getInt("id"),
                resultSet.getString("firstName"),
                resultSet.getString("lastName"),
                resultSet.getString("email"),
                resultSet.getString("password"),
                CouponsDBDAO.getCouponsByCompanyId(customerID)
        );
    }

}
