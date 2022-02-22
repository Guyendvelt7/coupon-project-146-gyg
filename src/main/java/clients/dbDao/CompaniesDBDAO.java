package clients.dbDao;

import clients.CustomExceptions;
import clients.beans.Company;
import clients.beans.Coupon;
import clients.dao.CompaniesDAO;
import clients.db.ConnectionPool;
import clients.db.DBManager;
import clients.db.DBTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompaniesDBDAO implements CompaniesDAO {
    CouponsDBDAO couponsDBDAO;
    //todo: add exceptions
    //todo: ConnectionPool connectionPool;

    @Override
    public boolean isCompanyExists(String email, String password) {
        Map<Integer, Object> values = new HashMap<>();
        try {
            values.put(1, email);
            values.put(2, password);
            ResultSet resultSet = DBTools.runQueryForResult(DBManager.COUNT_BY_PASS_AND_EMAIL, values);
            resultSet.next();
            return (resultSet.getInt(1) == 1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public void addCompany(Company company) {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, company.getName());
        values.put(2, company.getEmail());
        values.put(3, company.getPassword());
        DBTools.runQuery(DBManager.ADD_COMPANY, values);
    }

    @Override
    public void updateCompany(Company company) {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, company.getName());
        values.put(2, company.getEmail());
        values.put(3, company.getPassword());
        values.put(4, company.getId());
        DBTools.runQuery(DBManager.UPDATE_COMPANY, values);
    }

    @Override
    public void deleteCompany(int companyId) {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, companyId);
        DBTools.runQuery(DBManager.DELETE_COMPANY, values);
    }
  
    @Override
    public ArrayList<Company> getAllCompanies() {
        ArrayList<Company> allCompanies = new ArrayList<>();
        ResultSet resultSet = null;
        try {
            resultSet = DBTools.runQueryForResult(DBManager.GET_ALL_COMPANIES);
            while (resultSet.next()) {
                ArrayList<Coupon> coupons = new ArrayList<>();
                Company company = new Company(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        CouponsDBDAO.getCouponsByCompanyId(resultSet.getInt("id"))
                );
                allCompanies.add(company);
            }
        } catch (SQLException | CustomExceptions err) {
            System.out.println(err.getMessage());
        }
        return allCompanies;
    }



    @Override
    public Company getOneCompany(int companyId) {
        Company company = null;
        ArrayList<Coupon> coupons = null;
        Map<Integer,Object> map= new HashMap<>();
        map.put(1,companyId);
        try {
            coupons = CouponsDBDAO.getCouponsByCompanyId(companyId);
            ResultSet resultSet = DBTools.runQueryForResult(DBManager.GET_SINGLE_COMPANY,map);
            assert resultSet != null;
            if (resultSet.next()) {
                company = new Company(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        coupons
                );
                coupons = CouponsDBDAO.getCouponsByCompanyId(companyId);
            }
        } catch (SQLException | CustomExceptions e) {
            e.printStackTrace();
        }
        return company;
    }

    public List<Coupon> getCompanyCoupons (int companyId){
        Map<Integer, Object> value = new HashMap<>();
        value.put(1, companyId);
        try {
            return couponsDBDAO.getCoupons(DBManager.GET_COUPONS_BY_COMPANIES, value);
        } catch (CustomExceptions e) {
            e.printStackTrace();
            return null;
        }
    }
}
