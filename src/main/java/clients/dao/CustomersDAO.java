package clients.dao;

import clients.beans.Customer;

import java.util.ArrayList;

public interface CustomersDAO {

    public boolean isCustomerExist(String name, String password);

    public void addCustomer(Customer customer);

    public void updateCustomer(Customer customer);

    public void deleteCustomer(int customerID);

    public ArrayList<Customer> getAllCustomers();

    public Customer getOneCustomer(int customerID);
}
