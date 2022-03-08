package clients.dao;

import clients.exceptions.CustomExceptions;
import clients.beans.Company;

import java.util.List;

/**
 * @author Yoav Hachmon, Guy Endvelt and Gery Glazer
 * 03.2022
 */
/**
 * interface: implemented by CompanyDBDAO
 */
public interface CompaniesDAO {

    public boolean isCompanyExists(String email, String password);

    /**
     *CRUD
     */

    public void addCompany(Company company) throws CustomExceptions;

    public void updateCompany(Company company) throws CustomExceptions;

    public void deleteCompany(int  companyId) throws CustomExceptions;

    public List<Company> getAllCompanies() throws CustomExceptions;

    public Company getOneCompany(int companyId) throws CustomExceptions;
}