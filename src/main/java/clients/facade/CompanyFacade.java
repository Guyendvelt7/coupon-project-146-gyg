package clients.facade;

import clients.exceptions.CustomExceptions;
import clients.exceptions.EnumExceptions;
import clients.beans.Category;
import clients.beans.Company;
import clients.beans.Coupon;
import clients.dbDao.CouponsDBDAO;

import java.util.List;
import java.util.stream.Collectors;
/**
 * @author Yoav Chachmon, Guy Endvelt and Gery Glazer
 * 03.2022
 */

/**
 * incorporation of all accessible methods to a company
 */
public class CompanyFacade extends ClientFacade {
    private int companyId;
    private CouponsDBDAO couponsDBDAO;

    /**
     * company constructor
     *
     * @param companyId
     */
    public CompanyFacade(int companyId) {
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
        //todo: check if customerFacade login method is better
        String pas = null;
        String mail = null;
        try {
            pas = companiesDBDAO.getOneCompany(companyId).getPassword();
            mail = companiesDBDAO.getOneCompany(companyId).getEmail();
        } catch (CustomExceptions customExceptions) {
            System.out.println(customExceptions.getMessage());
        }
        return email.equals(mail) && password.equals(pas);
    }

    public void addCoupon(Coupon coupon) {
        List<Coupon> couponList;
        try {
            couponList = companiesDBDAO.getCompanyCoupons(companyId).stream()
                    .filter(item -> item.getTitle().equals(coupon.getTitle())).collect(Collectors.toList());
            if (couponList.size() == 0) {
                couponsDBDAO.addCoupon(coupon);
            }
        } catch (CustomExceptions e) {
            System.out.println(EnumExceptions.COUPON_TITLE_EXIST);
        }
    }

    public void updateCoupon(Coupon coupon) {
        try {
            couponsDBDAO.updateCoupon(coupon);
        } catch (CustomExceptions e) {
            System.out.println(EnumExceptions.ID_NOT_EXIST);
        }
    }

    public void deleteCoupon(int couponId) {
        try {
            couponsDBDAO.deleteCoupon(couponId);
        } catch (CustomExceptions e) {
            System.out.println(EnumExceptions.ID_NOT_EXIST);
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
        } catch (CustomExceptions e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
