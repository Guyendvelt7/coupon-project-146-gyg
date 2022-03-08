package clients.thread;

import clients.exceptions.CustomExceptions;
import clients.exceptions.EnumExceptions;
import clients.beans.Coupon;
import clients.dbDao.CouponsDBDAO;
import java.time.LocalDate;
/**
 * @author Yoav Hachmon, Guy Endvelt and Gery Glazer
 * 03.2022
 */

/**
 * Daily task to check and remove expired coupons from database
 */
public class CouponExpirationDailyJob implements Runnable {
    private CouponsDBDAO myCoupons = new CouponsDBDAO();
    private boolean quit = false;

    public CouponExpirationDailyJob() {
    }
//todo: מימוש הט'רד צריך להתבמע בקלאס נוסף לבדוק ב-
//https://github.com/GeneralScar/CouponSystem/blob/fce8be6e2c09c9d50fb2c3d7a9bc3dcc8dbd411c/ProjectCoupun/src/infra/CouponSystem.java#L42
//use of daemon thread?
//check if thread isAlive()
    @Override
    public void run() {
        while (!quit) {
            try {
                for (Coupon item : myCoupons.getAllCoupons()) {
                    if (item.getEndDate().toLocalDate().isAfter(LocalDate.now())) {
                        myCoupons.deleteCoupon(item.getId());
                    }
                }
                Thread.sleep(1000 * 60 * 60 * 24);
            } catch (InterruptedException | CustomExceptions e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void stop(){
        this.quit = true;
    }



}
