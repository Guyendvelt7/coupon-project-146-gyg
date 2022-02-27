package clients.dao;

import clients.CustomExceptions;
import clients.beans.Customer;

import java.util.List;

public interface CustomersDAO {

    public boolean isCustomerExist(String name, String password);

    public void addCustomer(Customer customer) throws CustomExceptions;

    public void updateCustomer(Customer customer);

    public void deleteCustomer(int customerID);

    public List<Customer> getAllCustomers() throws CustomExceptions;

    public Customer getOneCustomer(int customerID);
}
