package clients.dao;

import clients.exceptions.CustomExceptions;
import clients.beans.Customer;

import java.util.List;
/**
 * @author Yoav Chachmon, Guy Endvelt and Gery Glazer
 *  * 03.2022
 */
/**
 * interface: implemented by CustomerDBDAO
 */
public interface CustomersDAO {

    public boolean isCustomerExist(String name, String password);

    /**
     *CRUD
     */

    public void addCustomer(Customer customer) throws CustomExceptions;

    public void updateCustomer(Customer customer) throws CustomExceptions;

    public void deleteCustomer(int customerID) throws CustomExceptions;

    public List<Customer> getAllCustomers() throws CustomExceptions;

    public Customer getOneCustomer(int customerID) throws CustomExceptions;
}
