package clients.dao;

import clients.beans.Company;

import java.util.ArrayList;

public interface CompaniesDAO {
    public boolean isCompanyExists(String email, String password);

    //CRUD
    public void addCompany(Company company);

    public void updateCompany(Company company);

    public void deleteCompany(int  companyId);

    public ArrayList<Company> getAllCompanies();

    public Company getOneCompany(int companyId);
}
