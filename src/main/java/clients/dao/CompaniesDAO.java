package clients.dao;

import clients.beans.Company;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface CompaniesDAO {
    public boolean isCompanyExists(String email, String password);

    //CRUD
    public void addCompany(Company company);

    public void updateCompany(Company company);

    public void deleteCompany(int  companyId);

    public List<Company> getAllCompanies(String sql, Map<Integer, Object> values);

    public Company getOneCompany(int companyId);
}
