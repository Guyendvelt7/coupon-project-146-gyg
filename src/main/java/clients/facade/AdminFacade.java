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

    public void addCompany(Company company) throws SQLException, CustomExceptions {
        this.companiesDBDAO.addCompany(company);
    }

    public void updateCompany (Company company) throws SQLException, CustomExceptions {
        this.companiesDBDAO.updateCompany(company);
    }

    public void deleteCompany (int companyID) throws SQLException, CustomExceptions {
        this.companiesDBDAO.deleteCompany(companyID);
        //delete coupons - cascade sql
    }

    public ArrayList<Company> getAllCompanies()throws CustomExceptions{
        ArrayList<Company> companiesList = new ArrayList<>();
        companiesList = this.companiesDBDAO.getAllCompanies();
        return companiesList;
    }

    public Company getOneCompany (int companyID) throws SQLException, CustomExceptions {
        Company comp = null;
        comp = this.companiesDBDAO.getOneCompany(companyID);
        comp.setCoupons(this.companiesDBDAO.getCompanyCoupons(companyID));
        return comp;
    }

    public void addCustomer (Customer customer){
        this.customersDBDAO.addCustomer(customer);
    }

    public void updateCustomer(Customer customer){
        this.customersDBDAO.updateCustomer(customer);
    }

    public void deleteCustomer(int customerID) {
        this.customersDBDAO.deleteCustomer(customerID);
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customerList = new ArrayList<>();
        customerList = this.customersDBDAO.getAllCustomers();
        return customerList;
    }

    public Customer getOneCustomer(int customerID) {
        Customer customer = null;
        customer = this.customersDBDAO.getOneCustomer(customerID);
        return customer;
    }
}
