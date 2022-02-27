package clients;

import clients.db.DBManager;
import clients.db.DBTools;

public class Run {
    public static void main(String[] args) {
//        boolean allOK = DBTools.runQuery(DBManager.CREATE_COUPONS_TABLE);
//        System.out.println("Command run successfully: " + allOK);
//        allOK= DBTools.runQuery(DBManager.CREATE_CUSTOMER_VS_COUPONS_TABLE);
//        System.out.println("Command run successfully: " + allOK);
//        allOK=DBTools.runQuery(DBManager.CREATE_CATEGORIES_TABLE);
//        System.out.println("Command run successfully: " + allOK);
//        allOK=DBTools.runQuery(DBManager.CREATE_COMPANY_TABLE);
//        System.out.println("Command run successfully: " + allOK);
//        allOK=DBTools.runQuery(DBManager.CREATE_CUSTOMER_TABLE);
//        System.out.println("Command run successfully: " + allOK);

        final String ALTER_DROP_FK_COMPID_IN_COUPONS_TABLE = "ALTER TABLE `coupons_project`.`coupons`" +
                "drop CONSTRAINT `company_id`;";
        final String ALTER_ADD_NEW_FK_ONDELETE_CASCADE = "ALTER TABLE `coupons_project`.`coupons`" +
                "ADD CONSTRAINT `company_id`" +
                " FOREIGN KEY (`company_id`)" +
                " REFERENCES `coupons_project`.`companies` (`id`)" +
                " ON DELETE CASCADE" +
                " ON UPDATE CASCADE;";

        final String ALTER_DROP_FK_CATEGID_IN_COUPONS_TABLE = "ALTER TABLE `coupons_project`.`coupons`" +
                "drop CONSTRAINT `category_id`;";
        final String ALTER_ADD_NEW_FK_CATEGID_ONDELETE_CASCADE = "ALTER TABLE `coupons_project`.`coupons`" +
                "ADD CONSTRAINT `category_id`" +
                " FOREIGN KEY (`category_id`)" +
                " REFERENCES `coupons_project`.`categories` (`id`)" +
                " ON DELETE CASCADE" +
                " ON UPDATE CASCADE;";

        final String ALTER_DROP_FK_CUSTOMERID_IN_CUSTOMER_VS_COUPONS_TABLE =
                "ALTER TABLE `coupons_project`.`customers_vs_coupons`" +
                        "drop CONSTRAINT `customer_id`;";
        final String ALTER_ADD_NEW_FK_CUSTOMERID_ONDELETE_CASCADE =
                "ALTER TABLE `coupons_project`.`customers_vs_coupons`" +
                        "ADD CONSTRAINT `customer_id`" +
                        "FOREIGN KEY (`customer_id`)" +
                        " REFERENCES `coupons_project`.`customers` (`id`)" +
                        " ON DELETE CASCADE" +
                        " ON UPDATE CASCADE;";

        final String ALTER_DROP_FK_COUPONID_IN_CUSTOMER_VS_COUPONS_TABLE =
                "ALTER TABLE `coupons_project`.`customers_vs_coupons`" +
                        "drop CONSTRAINT `coupon_id`;";
        final String ALTER_ADD_NEW_FK_COUPONRID_ONDELETE_CASCADE =
                "ALTER TABLE `coupons_project`.`customers_vs_coupons`" +
                        "ADD CONSTRAINT `coupon_id`" +
                        " FOREIGN KEY (`coupon_id`)" +
                        " REFERENCES `coupons_project`.`coupons` (`id`)" +
                        " ON DELETE CASCADE" +
                        " ON UPDATE CASCADE;";

//        boolean allOK = DBTools.runQuery(ALTER_DROP_FK_COUPONID_IN_CUSTOMER_VS_COUPONS_TABLE);
//        System.out.println("Command run successfully: " + allOK);
//        allOK = DBTools.runQuery(ALTER_ADD_NEW_FK_COUPONRID_ONDELETE_CASCADE);
//        System.out.println("Command run successfully: " + allOK);
    }
}
