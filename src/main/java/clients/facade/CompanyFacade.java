package clients.facade;

import clients.CustomExceptions;
import clients.beans.Category;
import clients.beans.Company;
import clients.beans.Coupon;
import clients.dbDao.CouponsDBDAO;
import org.checkerframework.checker.units.qual.C;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CompanyFacade extends ClientFacade  {

    private int companyId;

    public CompanyFacade(int companyId) {
        this.companyId = companyId;
    }

    @Override
    public boolean login(String email, String password) throws CustomExceptions {
        return false;
    }

public void addCoupon(Coupon coupon) throws SQLException {
    myCoupons.addCoupon(coupon);
}

public void updateCoupon(Coupon coupon) throws SQLException {
        myCoupons.updateCoupon(coupon);
}

public void deleteCoupon(int couponId) throws SQLException {
        myCoupons.deleteCoupon(couponId);
}

public ArrayList<Coupon>getCompanyCoupons() throws SQLException {
       return CouponsDBDAO.getCouponsByCompanyId(this.companyId);
    }

public List<Coupon>getCompanyCoupons(Category category) throws SQLException {
        return  getCompanyCoupons().stream()
            .filter(item->item.getCategory().equals(category))
            .collect(Collectors.toList());
    }

public List<Coupon>getCompanyCoupons(double maxPrice) throws SQLException {
        return getCompanyCoupons().stream()
        .filter(item->item.getPrice()<=maxPrice).collect(Collectors.toList());
}

public Company getCompanyDetails() throws SQLException {
        return myCompanies.getOneCompany(companyId);
}
}
