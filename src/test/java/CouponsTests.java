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
        coupon= new Coupon(0,3, Category.FOOD, "Shtuchim",
                    "Sorry, it`s a mistake", new Date(2022, 4, 1),
                    new Date(2022, 6, 30), 150, 10, "Kvech!");
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

    @Test
    public void couponExist(){
        Assert.assertTrue(couponsDBDAO.isCouponExists(coupon.getId()));
    }

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




}
