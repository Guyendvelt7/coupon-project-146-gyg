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
        try {
            values.put(1, company.getName());
            values.put(2, company.getEmail());
            values.put(3, company.getPassword());
            DBTools.runQuery(DBManager.ADD_COMPANY, values);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCompany(Company company) {
        Map<Integer, Object> values = new HashMap<>();
        try {
            values.put(1, company.getName());
            values.put(2, company.getEmail());
            values.put(3, company.getPassword());
            values.put(4, company.getId());
            DBTools.runQuery(DBManager.UPDATE_COMPANY, values);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCompany(int companyId) {
        Map<Integer, Object> values = new HashMap<>();
        try {
            values.put(1, companyId);
            DBTools.runQuery(DBManager.DELETE_COMPANY, values);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        } catch (SQLException | InterruptedException err) {
            System.out.println(err.getMessage());
        }
        return allCompanies;
    }

    @Override
    public ArrayList<Company> getAllCompanies(String sql, Map<Integer, Object> values) {
        ArrayList<Company> companies = new ArrayList<>();

        try {
            ResultSet resultSet = DBTools.runQueryForResult(sql, values);
            while (resultSet.next()) {
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
    public Company getOneCompany(int companyId) throws SQLException {
        Company company = null;
        Connection connection = null;
        ArrayList<Coupon> coupons = CouponsDBDAO.getCouponsByCompanyId(companyId);
        try {
            connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DBManager.GET_SINGLE_COMPANY);
            preparedStatement.setInt(1, companyId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                company = new Company(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        coupons
                );
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().restoreConnection(connection);
        }
        return company;
    }

    @Override
    public ArrayList<Coupon> getCompanyCoupons(int companyId) throws SQLException {
        Map<Integer, Object> value = new HashMap<>();
        value.put(1, companyId);
        return couponsDBDAO.getCoupons(DBManager.GET_COUPONS_BY_COMPANIES, value);
    }
}
