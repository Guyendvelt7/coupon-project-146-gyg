package clients.dbDao;

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
            assert resultSet != null;
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
    public List<Company> getAllCompanies() {
        List<Company> companies = new ArrayList<>();

        try {
            ResultSet resultSet = DBTools.runQueryForResult(DBManager.GET_ALL_COMPANIES);
            while (true) {
                assert resultSet != null;
                if (!resultSet.next()) break;
                ArrayList<Coupon> coupons = new ArrayList<>();
                Company company = new Company(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        CouponsDBDAO.getCouponsByCompanyId(resultSet.getInt("id"))
                );
                companies.add(company);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return companies;
    }

    @Override
    public Company getOneCompany(int companyId){
        Company company = null;
        ArrayList<Coupon> coupons = null;
        Map<Integer,Object> map= new HashMap<>();
        map.put(1,companyId);
        try {
            coupons = CouponsDBDAO.getCouponsByCompanyId(companyId);
            ResultSet resultSet = DBTools.runQueryForResult(DBManager.GET_SINGLE_COMPANY,map);
            if (resultSet.next()) {
                company = new Company(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        coupons
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return company;
    }

    public List<Coupon> getCompanyCoupons (int companyId) throws SQLException {
        Map<Integer, Object> value = new HashMap<>();
        value.put(1, companyId);
        return couponsDBDAO.getCoupons(DBManager.GET_COUPONS_BY_COMPANIES, value);
    }
}
