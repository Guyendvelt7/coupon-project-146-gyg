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
        this.companiesDAO.addCompany(company);
    }

    public void updateCompany (Company company) throws SQLException {
        this.companiesDAO.updateCompany(company);
    }

    public void deleteCompany (int companyID) throws SQLException {
        this.companiesDAO.deleteCompany(companyID);
        //delete coupons - cascade sql
    }

    public ArrayList<Company> getAllCompanies(){
        return this.companiesDAO.getAllCompanies();
    }

    public Company getOneCompany (int companyID) throws SQLException {
        Company comp = null;
        comp = this.companiesDAO.getOneCompany(companyID);
        comp.setCoupons(this.companiesDAO.getCompanyCoupons(companyID));
        return comp;

    }

//    throw new CustomExceptions(EnumExceptions.INVALID_EMAIL);
//    throw new CustomExceptions(EnumExceptions.INVALID_PASSWORD);
}
