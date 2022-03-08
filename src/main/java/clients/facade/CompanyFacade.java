package clients.facade;

import clients.beans.Category;
import clients.beans.Company;
import clients.beans.Coupon;
import clients.db.DBManager;
import clients.db.DBTools;
import clients.dbDao.CompaniesDBDAO;
import clients.exceptions.CustomExceptions;
import clients.exceptions.EnumExceptions;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
/**
 * @author Yoav Hachmon, Guy Endvelt and Gery Glazer
 * 03.2022
 */

/**
 * incorporation of all accessible methods to a company
 */
public class CompanyFacade extends ClientFacade {
    private int companyId;
    private CompaniesDBDAO companiesDBDAO = new CompaniesDBDAO();

    /**
     * Empty company constructor

     */
    public CompanyFacade() {
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    /**
     * login verification
     *
     * @param email    input by user
     * @param password input by user
     * @return verification result
     */
    @Override
    public boolean login(String email, String password) {
        if (companiesDBDAO.isCompanyExists(email, password)) {
            Map<Integer, Object> values = new HashMap<>();
            values.put(1, email);
            values.put(2, password);
            ResultSet resultSet = DBTools.runQueryForResult(DBManager.GET_COMPANY_ID_BY_EMAIL_AND_PASSWORD, values);
            int id = 0;
            try {
                resultSet.next();
                id = resultSet.getInt("id");
            } catch (SQLException err) {
                System.out.println(err.getMessage());
            }
            setCompanyId(id);
            return true;
        }
        return false;
    }

    public void addCoupon(Coupon coupon) throws CustomExceptions{
        if (coupon.getCompanyId() == companyId) {
            List<Coupon> couponList;
            couponList = couponsDBDAO.getCouponsByCompanyId(companyId).stream()
                    .filter(item -> item.getTitle().equals(coupon.getTitle())).collect(Collectors.toList());
            if (couponList.size() == 0) {
                couponsDBDAO.addCoupon(coupon);
            }else {
                throw new CustomExceptions(EnumExceptions.COUPON_TITLE_EXIST);
            }
        }else {
            throw new CustomExceptions(EnumExceptions.NO_COUPONS);
        }
    }

    public void updateCoupon(Coupon coupon) throws CustomExceptions {
        if (coupon.getCompanyId() == companyId) {
            if (!couponsDBDAO.isCouponExists(coupon.getId())) {
                throw new CustomExceptions(EnumExceptions.ID_NOT_EXIST);
            } else if (coupon.getCompanyId() != companyId) {
                throw new CustomExceptions(EnumExceptions.COMPANY_DOES_NOT_HAVE_THIS_COUPON);
            } else {
                couponsDBDAO.updateCoupon(coupon);
            }
        }else {
            throw new CustomExceptions(EnumExceptions.NO_COUPONS);
        }
    }

    public void deleteCoupon(int couponId) throws CustomExceptions {
        if (couponsDBDAO.getOneCoupon(couponId).getCompanyId()==companyId) {
            try {
                couponsDBDAO.deleteCoupon(couponId);
            } catch (CustomExceptions customExceptions) {
                System.out.println(customExceptions.getMessage());
            }
        }else {
            throw new CustomExceptions(EnumExceptions.NO_COUPONS);
        }
    }

    public List<Coupon> getCompanyCoupons() {
        return couponsDBDAO.getCouponsByCompanyId(this.companyId);
    }

    public List<Coupon> getCompanyCoupons(Category category) {
        return getCompanyCoupons().stream()
                .filter(item -> item.getCategory().equals(category))
                .collect(Collectors.toList());
    }

    public List<Coupon> getCompanyCoupons(double maxPrice) {
        return getCompanyCoupons().stream()
                .filter(item -> item.getPrice() <= maxPrice).collect(Collectors.toList());
    }

    public Company getCompanyDetails() {
        try {
            return companiesDBDAO.getOneCompany(companyId);
        } catch (CustomExceptions customExceptions) {
            System.out.println(customExceptions.getMessage());
            return null;
        }
    }
}
