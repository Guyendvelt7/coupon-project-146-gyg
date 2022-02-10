package clients.dbDao;

import clients.beans.Customer;
import clients.dao.CustomersDAO;

import java.util.ArrayList;

public class CustomersDBDAO implements CustomersDAO {
    //todo: ConnectionPool connectionPool
    //todo: add exceptions

    @Override
    public boolean isCustomerExist(String name, String password) {
        //todo: check Cheat Sheet boolean Query

        return false;
    }

    @Override
    public void addCustomer(Customer customer) {

    }

    @Override
    public void updateCustomer(Customer customer) {

    }

    @Override
    public void deleteCustomer(int customerID) {

    }

    @Override
    public ArrayList<Customer> getAllCustomers() {
        return null;
    }

    @Override
    public Customer getOneCustomer(int customerID) {
        return null;
    }
}
