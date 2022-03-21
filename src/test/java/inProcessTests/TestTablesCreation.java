package inProcessTests;

import clients.db.DBManager;
import clients.db.DBTools;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestTablesCreation {

    @Test
    public void initTest() {
//        DBTools.runQuery(DBManager.CREATE_CATEGORIES_TABLE);
//        DBTools.runQuery(DBManager.CREATE_COMPANY_TABLE);
//        DBTools.runQuery(DBManager.CREATE_CUSTOMER_TABLE);
//        DBTools.runQuery(DBManager.CREATE_COUPONS_TABLE);
//        DBTools.runQuery(DBManager.CREATE_CUSTOMER_VS_COUPONS_TABLE);

        DBTools.runQuery(DBManager.DROP_CUSTOMER_VS_COUPONS_TABLE);
        DBTools.runQuery(DBManager.DROP_COUPONS_TABLE);
        DBTools.runQuery(DBManager.DROP_COMPANY_TABLE);
        DBTools.runQuery(DBManager.DROP_CUSTOMERS_TABLE);

        DBTools.runQuery(DBManager.CREATE_COMPANY_TABLE);
        DBTools.runQuery(DBManager.CREATE_CUSTOMER_TABLE);
        DBTools.runQuery(DBManager.CREATE_COUPONS_TABLE);
        DBTools.runQuery(DBManager.CREATE_CUSTOMER_VS_COUPONS_TABLE);
    }

}
