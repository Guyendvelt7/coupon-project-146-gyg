package clients;

import clients.beans.Category;
import clients.beans.Company;
import clients.beans.Coupon;
import clients.db.DBManager;
import clients.db.DBTools;
import clients.dbDao.CompaniesDBDAO;
import clients.dbDao.CouponsDBDAO;
import com.google.common.annotations.VisibleForTesting;
import org.checkerframework.checker.units.qual.C;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Tester {
   public static void main(String[] args) throws CustomExceptions {
//      DBTools.runQuery(DBManager.CREATED_DB);
//      DBTools.runQuery(DBManager.CREATE_COMPANY_TABLE);
//      DBTools.runQuery(DBManager.CREATE_COUPONS_TABLE);
//      DBTools.runQuery(DBManager.CREATE_CUSTOMER_TABLE);
//      DBTools.runQuery(DBManager.CREATE_CATEGORIES_TABLE);
//      DBTools.runQuery(DBManager.CREATE_CUSTOMER_VS_COUPONS_TABLE)

//      Company company = new Company(12,"gay's company","gay@org.com","123456",null);
      Coupon coupon = new Coupon(1,1, Category.FOOD,"MY COUPON","the best", new Date(LocalDate.now().toEpochDay()),new Date(LocalDate.now().toEpochDay()),3,20,"bla bla");
//     CompaniesDBDAO companiesDBDAO = new CompaniesDBDAO();
      CouponsDBDAO couponsDBDAO = new CouponsDBDAO();
      couponsDBDAO.addCoupon(coupon);
//      companiesDBDAO.deleteCompany(1);
// companiesDBDAO.addCompany(company);
//      System.out.println(companiesDBDAO.isCompanyExists("ger@org.com","12345"));
//      //System.out.println(companiesDBDAO.getAllCompanies());
////      company.setEmail("hjgbkbbi");
////      companiesDBDAO.updateCompany(company);
//      //System.out.println(companiesDBDAO.getOneCompany(1));

//       List<Category> categories = Arrays.stream(Category.values()).collect(Collectors.toList());
//       for (Category item:categories) {
//           addCategory(item);
//       }
   }

    public static void addCategory(Category category) {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, Category.valueOf(category.name()));
            DBTools.runQuery(DBManager.ADD_CATEGORY, values);

    }
    public static void deleteCategory(int categoryId){
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, categoryId);
        DBTools.runQuery(DBManager.DELETE_CATEGORY, values);
    }

}
