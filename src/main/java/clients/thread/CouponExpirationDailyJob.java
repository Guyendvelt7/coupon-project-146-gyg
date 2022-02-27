package clients.thread;

import clients.CustomExceptions;
import clients.EnumExceptions;
import clients.beans.Coupon;
import clients.dbDao.CouponsDBDAO;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class CouponExpirationDailyJob implements Runnable {
    private CouponsDBDAO myCoupons;
    private boolean quit = false;

    public CouponExpirationDailyJob(CouponsDBDAO myCoupons) {
        this.myCoupons = myCoupons;
    }

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
                System.out.println(EnumExceptions.ID_NOT_EXIST);
            }
        }

    }
    public void stop(){
        this.quit = true;
    }



}
