package inProcessTests;

import clients.db.DBManager;
import clients.db.DBTools;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestTablesCreation {



    @Test
    public void initTest() {
        System.out.println("Creating Schema");
        assertTrue(DBTools.runQuery(DBManager.CREATED_DB));
        System.out.println("Creating category table table");
        assertTrue(DBTools.runQuery(DBManager.CREATE_CATEGORIES_TABLE));
        System.out.println("Creating company table");
        assertTrue(DBTools.runQuery(DBManager.CREATE_COMPANY_TABLE));
        System.out.println("Creating customer table");
        assertTrue(DBTools.runQuery(DBManager.CREATE_CUSTOMER_TABLE));
        System.out.println("Creating coupon table");
        assertTrue(DBTools.runQuery(DBManager.CREATE_COUPONS_TABLE));
        System.out.println("Creating customerVsCoupon table");
        assertTrue(DBTools.runQuery(DBManager.CREATE_CUSTOMER_VS_COUPONS_TABLE));
    }
}
