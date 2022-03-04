package clients.facade;

import clients.exceptions.CustomExceptions;
import clients.beans.Company;
import clients.beans.Customer;

import java.util.ArrayList;
import java.util.List;
/**
 * @author Yoav Chachmon, Guy Endvelt and Gery Glazer
 * 03.2022
 */

/**
 * incorporation of all accessible methods to an administrator
 */
public class AdminFacade extends ClientFacade {
    private final String email;
    private final String password;
    private boolean isLoginCorrect;

    /**
     * administrator constructor
     * set e-mail and password (hard coded)
     */
    public AdminFacade() {
        this.email = "admin@admin.com";
        this.password = "admin";
    }

    /**
     * login verification
     *
     * @param email    input by user
     * @param password input by user
     * @return verification result
     */
    @Override
    public boolean login(String email, String password) {
        return this.email.equals(email) && this.password.equals(password);
    }

    public void addCompany(Company company) {
        try {
            this.companiesDBDAO.addCompany(company);
        } catch (CustomExceptions e) {
            System.out.println(e.getMessage());
            ;
        }
    }

    public void updateCompany(Company company) {
        try {
            this.companiesDBDAO.updateCompany(company);
        } catch (CustomExceptions customExceptions) {
            System.out.println(customExceptions.getMessage());
        }
    }

    public void deleteCompany(int companyID) {
        try {
            this.companiesDBDAO.deleteCompany(companyID);
        } catch (CustomExceptions e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Company> getAllCompanies() {
        try {
            return this.companiesDBDAO.getAllCompanies();
        } catch (CustomExceptions e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Company getOneCompany(int companyID) {
        Company comp = null;
        try {
            comp = this.companiesDBDAO.getOneCompany(companyID);

            assert comp != null;
            comp.setCoupons(this.companiesDBDAO.getCompanyCoupons(companyID));
        } catch (CustomExceptions e) {
            System.out.println(e.getMessage());
            ;
        }
        return comp;
    }

    public void addCustomer(Customer customer) {
        try {
            this.customersDBDAO.addCustomer(customer);
        } catch (CustomExceptions customExceptions) {
            System.out.println(customExceptions.getMessage());
        }
    }

    public void updateCustomer(Customer customer) {
        try {
            this.customersDBDAO.updateCustomer(customer);
        } catch (CustomExceptions customExceptions) {
            System.out.println(customExceptions.getMessage());
        }
    }

    public void deleteCustomer(int customerID) {
        try {
            this.customersDBDAO.deleteCustomer(customerID);
        } catch (CustomExceptions customExceptions) {
            System.out.println(customExceptions.getMessage());
        }
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customerList = new ArrayList<>();
        try {
            customerList = this.customersDBDAO.getAllCustomers();
            return customerList;
        } catch (CustomExceptions customExceptions) {
            System.out.println(customExceptions.getMessage());
            return null;
        }
    }

    public Customer getOneCustomer(int customerID) {
        Customer customer = null;
        try {
            customer = this.customersDBDAO.getOneCustomer(customerID);
            return customer;
        } catch (CustomExceptions customExceptions) {
            System.out.println(customExceptions.getMessage());
            return null;
        }
    }
}
