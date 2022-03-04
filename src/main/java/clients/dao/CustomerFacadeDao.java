package clients.dao;

import clients.exceptions.CustomExceptions;
import clients.beans.Category;
import clients.beans.Coupon;
import clients.beans.Customer;

import java.util.List;
/**
 * @author Yoav Chachmon, Guy Endvelt and Gery Glazer
 *  * 03.2022
 */
/**
 * interface: implemented by CustomerFacade
 */
public interface CustomerFacadeDao {

    void purchaseCoupon(Coupon coupon);

    List<Coupon> getCustomerCoupons() throws CustomExceptions;

    List<Coupon> getCustomerCoupons(Category category) throws CustomExceptions;

    List<Coupon> getCustomerCoupons(double maxPrice) throws CustomExceptions;

    Customer getCustomerDetails();
}
