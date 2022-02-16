package clients.dao;

import clients.beans.Company;
import clients.beans.Coupon;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface CompaniesDAO {
    public boolean isCompanyExists(String email, String password) ;

    //CRUD
    public void addCompany(Company company) ;

    public void updateCompany(Company company) ;

    public void deleteCompany(int  companyId) ;

    public ArrayList<Company> getAllCompanies() ;

    public Company getOneCompany(int companyId) ;

    public List<Coupon> getCompanyCoupons (int companyId);
}
