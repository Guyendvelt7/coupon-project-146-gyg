package clients.facade;

import clients.EnumExceptions;
import clients.CustomExceptions;
import clients.beans.Company;
import clients.beans.Customer;
import clients.dao.CompaniesDAO;
import clients.dbDao.CompaniesDBDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminFacade extends ClientFacade {
    private final String email;
    private final String password;
    private boolean isLoginCorrect;

    public AdminFacade() {
        this.email = "admin@admin.com";
        this.password = "admin";
    }

    @Override

    public boolean login(String email, String password) {
        return this.email.equals(email) && this.password.equals(password);

    }

    public void addCompany(Company company) {
        try {
            this.companiesDBDAO.addCompany(company);
        } catch (CustomExceptions customExceptions) {
            System.out.println(customExceptions.getMessage());
        }
    }

    public void updateCompany(Company company) {
        this.companiesDBDAO.updateCompany(company);
    }

    public void deleteCompany(int companyID) {
        this.companiesDBDAO.deleteCompany(companyID);
    }

    public List<Company> getAllCompanies() {
        return this.companiesDBDAO.getAllCompanies();

        }

        public Company getOneCompany ( int companyID){
            Company comp = null;
            comp = this.companiesDBDAO.getOneCompany(companyID);
            comp.setCoupons(this.companiesDBDAO.getCompanyCoupons(companyID));
            return comp;
        }

        public void addCustomer (Customer customer){
            try {
                this.customersDBDAO.addCustomer(customer);
            } catch (CustomExceptions customExceptions) {
                System.out.println(customExceptions.getMessage());
            }
        }

        public void updateCustomer (Customer customer){
            this.customersDBDAO.updateCustomer(customer);
        }

        public void deleteCustomer ( int customerID){
            this.customersDBDAO.deleteCustomer(customerID);
        }

        public List<Customer> getAllCustomers () {
            List<Customer> customerList = new ArrayList<>();
            customerList = this.customersDBDAO.getAllCustomers();
            return customerList;
        }

        public Customer getOneCustomer ( int customerID){
            Customer customer = null;
            customer = this.customersDBDAO.getOneCustomer(customerID);
            return customer;
        }
    }
