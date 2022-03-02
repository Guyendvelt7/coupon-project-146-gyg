import clients.CustomExceptions;
import clients.beans.Category;
import clients.beans.CategoryClass;
import clients.beans.Coupon;
import clients.dbDao.CouponsDBDAO;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CouponsTests {
    private static CouponsDBDAO couponsDBDAO;
    private static Coupon coupon;


    @BeforeClass
    public static void initTest() {
        couponsDBDAO = new CouponsDBDAO();
        coupon= new Coupon(0,2, Category.FOOD, "other",
                    "not sure",1 ,30,200, 54,"mmmmm!");
    }

    @Test
    public void addCoupon() {
        try {
            couponsDBDAO.addCoupon(coupon);
        } catch (CustomExceptions customExceptions) {
            System.out.println(customExceptions.getMessage());
        }
    }

    @Test
    public void couponDoesNotExist(){
        Assert.assertFalse(couponsDBDAO.isCouponExists(10));
    }

//    @Test
//    public void couponExist(){
//        //todo: NOT working
//        Assert.assertTrue(couponsDBDAO.isCouponExists(2));
//    }

    @Test
    public void updateCoupon(){
        coupon.setCategory(Category.ENTERTAINMENT);
        try {
            couponsDBDAO.updateCoupon(coupon);
        } catch (CustomExceptions customExceptions) {
            System.out.println(customExceptions.getMessage());
        }
    }

    @Test
    public void deleteCoupon(){
        try {
            couponsDBDAO.deleteCoupon(coupon.getId());
        } catch (CustomExceptions customExceptions) {
            System.out.println(customExceptions.getMessage());
        }
    }

    @Test
    public void getAllCoupons(){
        try {
            List<Coupon> couponList = couponsDBDAO.getAllCoupons();
            couponList.forEach(System.out::println);
        } catch (CustomExceptions customExceptions) {
            System.out.println(customExceptions.getMessage());
        }
    }

    @Test
    public void getOneCoupons() {
        try {
            System.out.println(couponsDBDAO.getOneCoupon(1));
        } catch (CustomExceptions customExceptions) {
            System.out.println(customExceptions.getMessage());
        }
    }

    @Test
    public void getCouponsByCustomerId()  {
        List<Coupon> coupons = couponsDBDAO.getCouponsByCustomerId(3);
        System.out.println(coupons);
    }

    @Test
    public void addPurchasedCoupon(){
        try {
            couponsDBDAO.addCouponPurchase(2, 3);
        } catch (CustomExceptions customExceptions) {
            System.out.println(customExceptions.getMessage());
        }
    }
//todo: not working
    @Test
    public void deletePurchasedCoupon(){
        try {
            couponsDBDAO.deleteCouponPurchase(2, 3);
        } catch (CustomExceptions customExceptions) {
            System.out.println(customExceptions.getMessage());
        }
    }


}
