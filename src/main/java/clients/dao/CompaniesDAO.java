package clients.dao;

import clients.CustomExceptions;
import clients.beans.Company;
import clients.beans.Coupon;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface CompaniesDAO {

    public boolean isCompanyExists(String email, String password);

    //CRUD
    public void addCompany(Company company) throws CustomExceptions;

    public void updateCompany(Company company);

    public void deleteCompany(int  companyId);

    public List<Company> getAllCompanies();

    public Company getOneCompany(int companyId);
}