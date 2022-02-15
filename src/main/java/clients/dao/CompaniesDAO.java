package clients.dao;

import clients.beans.Company;
import clients.beans.Coupon;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface CompaniesDAO {
    public boolean isCompanyExists(String email, String password) throws SQLException;

    //CRUD
    public void addCompany(Company company) throws SQLException;

    public void updateCompany(Company company) throws SQLException;

    public void deleteCompany(int  companyId) throws SQLException;

    public ArrayList<Company> getAllCompanies();

    public ArrayList<Company> getAllCompanies(String sql, Map<Integer, Object> values) throws SQLException;

    public Company getOneCompany(int companyId) throws SQLException;

    public ArrayList<Coupon> getCompanyCoupons(int companyId) throws SQLException;
}
