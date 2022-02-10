package clients.dbDao;

import clients.beans.Company;
import clients.dao.CompaniesDAO;

import java.util.ArrayList;

public class CompaniesDBDAO implements CompaniesDAO {
    //todo: add exceptions
    //todo: ConnectionPool connectionPool;

    @Override
    public boolean isCompanyExists(String email, String password) {

        //todo: check Cheat Sheet boolean Query
        return false;
    }

    @Override
    public void addCompany(Company company) {

    }

    @Override
    public void updateCompany(Company company) {

    }

    @Override
    public void deleteCompany(int companyId) {

    }

    @Override
    public ArrayList<Company> getAllCompanies() {
        return null;
    }

    @Override
    public Company getOneCompany(int companyId) {
        return null;
    }
}
