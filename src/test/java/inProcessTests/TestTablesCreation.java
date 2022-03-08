package inProcessTests;

import clients.db.DBManager;
import clients.db.DBTools;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestTablesCreation {
@Test
    public void initTest(){
        assertTrue(DBTools.runQuery(DBManager.CREATE_COUPONS_TABLE));
        assertTrue(DBTools.runQuery(DBManager.CREATE_CUSTOMER_VS_COUPONS_TABLE));
        assertTrue(DBTools.runQuery(DBManager.CREATE_CATEGORIES_TABLE));
        assertTrue(DBTools.runQuery(DBManager.CREATE_COMPANY_TABLE));
        assertTrue(DBTools.runQuery(DBManager.CREATE_CUSTOMER_TABLE));
    }
}
