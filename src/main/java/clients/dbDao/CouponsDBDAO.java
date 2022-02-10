package clients.dbDao;

import clients.beans.Coupon;
import clients.dao.CouponsDAO;

import java.util.ArrayList;

public class CouponsDBDAO implements CouponsDAO {
    //todo: ConnectionPool connectionPool;
    //todo: add exceptions

    @Override
    public void addCoupon(Coupon coupon) {

    }

    @Override
    public void updateCoupon(Coupon coupon) {

    }

    @Override
    public void deleteCoupon(int couponID) {

    }

    @Override
    public ArrayList<Coupon> getAllCoupons() {
        return null;
    }

    @Override
    public Coupon getOneCoupon(int couponID) {
        return null;
    }

    @Override
    public void addCouponPurchase(int customerID, int couponID) {

    }

    @Override
    public void deleteCouponPurchase(int customerID, int couponID) {

    }
}
