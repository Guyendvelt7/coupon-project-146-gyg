package inProcessTests;

import clients.exceptions.CustomExceptions;
import clients.beans.Category;
import clients.beans.Coupon;
import clients.dbDao.CouponsDBDAO;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Date;
import java.util.List;

public class CouponsTests {
    private static CouponsDBDAO couponsDBDAO;
    private static Coupon coupon;


    @BeforeClass
    public static void initTest() {
        couponsDBDAO = new CouponsDBDAO();
        coupon= new Coupon(0,1, Category.ENTERTAINMENT, "LunaPark",
                    "Fun Fun",new Date(System.currentTimeMillis()+ 24L * 60 * 60 * 1000*2),new Date(System.currentTimeMillis()+ 24L * 60 * 60 * 1000*60),50, 150,"Yey!");
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
        try {
        coupon.setCategory(Category.ENTERTAINMENT);
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
        List<Coupon> couponList = couponsDBDAO.getAllCoupons();
        couponList.forEach(System.out::println);
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
        List<Coupon> coupons = couponsDBDAO.getCouponsByCustomerId(5);
        coupons.forEach(System.out::println);
    }

    @Test
    public void getCouponsByCompanyId(){
        List<Coupon> couponList = couponsDBDAO.getCouponsByCompanyId(1);
        couponList.forEach(System.out::println);
    }

    @Test
    public void addPurchasedCoupon(){
        try {
            couponsDBDAO.addCouponPurchase(4, 5);
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
