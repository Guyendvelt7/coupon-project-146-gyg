package clients.facade;

import clients.EnumExceptions;
import clients.CustomExceptions;
import clients.beans.Company;
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

    public boolean login(String email, String password) throws CustomExceptions {
        return this.email.equals(email) && this.password.equals(password);

    }

    public void addCompany(Company company) throws SQLException {
        this.companiesDBDAO.addCompany(company);
    }

    public void updateCompany (Company company) throws SQLException {
        this.companiesDBDAO.updateCompany(company);
    }

    public void deleteCompany (int companyID) throws SQLException {
        this.companiesDBDAO.deleteCompany(companyID);
        //delete coupons - cascade sql
    }

    public List<Company> getAllCompanies(){
        return this.companiesDBDAO.getAllCompanies();
    }

    public Company getOneCompany (int companyID) throws SQLException {
        Company comp = null;
        comp = this.companiesDBDAO.getOneCompany(companyID);
        comp.setCoupons(this.companiesDBDAO.getCompanyCoupons(companyID));
        return comp;

    }

//    throw new CustomExceptions(EnumExceptions.INVALID_EMAIL);
//    throw new CustomExceptions(EnumExceptions.INVALID_PASSWORD);
}
