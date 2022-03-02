package clients.dbDao;

import clients.CustomExceptions;
import clients.EnumExceptions;
import clients.beans.Company;
import clients.beans.Coupon;
import clients.dao.CompaniesDAO;
import clients.db.DBManager;
import clients.db.DBTools;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Yoav Chachmon, Guy Endvelt and Gery Glazer
 */
public class CompaniesDBDAO implements CompaniesDAO {
    CouponsDBDAO couponsDBDAO;


    public boolean isCompanyExistsById(int id) {
        Map<Integer, Object> values = new HashMap<>();
        try {
            values.put(1, id);
            ResultSet resultSet = DBTools.runQueryForResult(DBManager.COUNT_COMPANY_BY_ID, values);
            assert resultSet != null;
            resultSet.next();
            return (resultSet.getInt(1) == 1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean isCompanyExistsByName(String name) {
        Map<Integer, Object> values = new HashMap<>();
        try {
            values.put(1, name);
            ResultSet resultSet = DBTools.runQueryForResult(DBManager.COUNT_COMPANY_BY_NAME, values);
            assert resultSet != null;
            resultSet.next();
            return (resultSet.getInt(1) == 1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean isCompanyExistsByEmail(String email) {
        Map<Integer, Object> values = new HashMap<>();
        try {
            values.put(1, email);
            ResultSet resultSet = DBTools.runQueryForResult(DBManager.COUNT_COMPANY_BY_NAME, values);
            assert resultSet != null;
            resultSet.next();
            return (resultSet.getInt(1) == 1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

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
    public void addCompany(Company company) throws CustomExceptions {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, company.getName());
        values.put(2, company.getEmail());
        values.put(3, company.getPassword());
        if (isCompanyExistsByName(company.getName())) {
            throw new CustomExceptions(EnumExceptions.COMPANY_NAME_ALREADY_EXIST);
        } else if (isCompanyExistsByEmail(company.getEmail())) {
            throw new CustomExceptions(EnumExceptions.COMPANY_EMAIL_ALREADY_EXIST);
        } else {
            DBTools.runQuery(DBManager.ADD_COMPANY, values);
        }
    }

    @Override
    public void updateCompany(Company company) throws CustomExceptions {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, company.getName());
        values.put(2, company.getEmail());
        values.put(3, company.getPassword());
        if (!isCompanyExistsById(company.getId())){
            throw new CustomExceptions(EnumExceptions.ID_NOT_EXIST);
        }else if (isCompanyExistsByName(company.getName())){
            throw new CustomExceptions(EnumExceptions.COMPANY_NAME_ALREADY_EXIST);
        }else if(isCompanyExistsByEmail(company.getEmail())){
            throw new CustomExceptions(EnumExceptions.COMPANY_EMAIL_ALREADY_EXIST);
        }else {
            DBTools.runQuery(DBManager.UPDATE_COMPANY, values);
        }
    }

    @Override
    public void deleteCompany(int companyId) throws CustomExceptions {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, companyId);
        if (getOneCompany(companyId) == null) {
            throw new CustomExceptions(EnumExceptions.ID_NOT_EXIST);
        }
        DBTools.runQuery(DBManager.DELETE_COMPANY, values);
        List<Coupon> companyCoupons = getCompanyCoupons(companyId);
        for (Coupon item : companyCoupons) {
            couponsDBDAO.deleteCoupon(item.getId());
        }
    }

    @Override
    public List<Company> getAllCompanies() throws CustomExceptions {
        List<Company> allCompanies = new ArrayList<>();
        Map<Integer, Object> param = new HashMap<>();
        ResultSet resultSet = DBTools.runQueryForResult(DBManager.GET_ALL_COMPANIES, param);
        try {
            while (true) {
                assert resultSet != null;
                if (!resultSet.next()) break;
                Company company = new Company(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        couponsDBDAO.getCouponsByCompanyId(resultSet.getInt("id"))
                );
                allCompanies.add(company);
            }
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
        return allCompanies;
    }

    @Override
    public Company getOneCompany(int companyId) throws CustomExceptions {
        if (isCompanyExistsById(companyId)) {
            Company company = null;
            List<Coupon> coupons = null;
            Map<Integer, Object> map = new HashMap<>();
            map.put(1, companyId);
            try {
                coupons = couponsDBDAO.getCouponsByCompanyId(companyId);
                ResultSet resultSet = DBTools.runQueryForResult(DBManager.GET_SINGLE_COMPANY, map);
                assert resultSet != null;
                if (resultSet.next()) {
                    company = new Company(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("email"),
                            resultSet.getString("password"),
                            coupons
                    );
                    coupons = couponsDBDAO.getCouponsByCompanyId(companyId);
                }
            } catch (SQLException err) {
                System.out.println(err.getMessage());
            }
            return company;
        } else {
            throw new CustomExceptions(EnumExceptions.NO_COMPANY);
        }
    }

    public List<Coupon> getCompanyCoupons(int companyId) {
        Map<Integer, Object> value = new HashMap<>();
        value.put(1, companyId);
        try {
            if (couponsDBDAO.getCouponsByCompanyId(companyId) == null) {
                throw new CustomExceptions(EnumExceptions.NO_COUPONS_COMPANY);
            }
            return couponsDBDAO.getCouponsByCompanyId(companyId);
        } catch (CustomExceptions customExceptions) {
            System.out.println(customExceptions.getMessage());
            return null;
        }
    }
}
