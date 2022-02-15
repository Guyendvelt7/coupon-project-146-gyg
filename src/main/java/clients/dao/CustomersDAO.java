package clients.dao;

import clients.beans.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CustomersDAO {

    public boolean isCustomerExist(String name, String password) throws SQLException;

    public void addCustomer(Customer customer) throws SQLException;

    public void updateCustomer(Customer customer) throws SQLException;

    public void deleteCustomer(int customerID) throws SQLException;

    public List<Customer> getAllCustomers() throws SQLException, InterruptedException;

    public Customer getOneCustomer(int customerID) throws SQLException, InterruptedException;
}
