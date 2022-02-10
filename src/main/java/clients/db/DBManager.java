package clients.db;

public class DBManager {

    public static final String URL = "jdbc:mysql://localhost:3306";
    public static final String SQL_USER = "root";
    public static final String SQL_PASS = "12345678";


    public static final String CREATE_COMPANY_TABLE =    "CREATE TABLE `coupon_project`.`companies` (" +
      "`id` INT NOT NULL AUTO_INCREMENT," +
      "`name` VARCHAR(45) NOT NULL," +
      "`email` VARCHAR(45) NOT NULL," +
      "`password` VARCHAR(45) NOT NULL," +
      "PRIMARY KEY (`id`));";

  public static final String CREATE_CUSTOMER_TABLE =   "CREATE TABLE `coupon_project`.`customers` (" +
      " `id` INT NOT NULL AUTO_INCREMENT," +
      "`first_name` VARCHAR(45) NOT NULL," +
      "`last_name` VARCHAR(45) NOT NULL," +
      "`email` VARCHAR(45) NOT NULL," +
      "`password` VARCHAR(45) NOT NULL," +
      "PRIMARY KEY (`id`));";

  public static final String CREATE_CATEGORIES_TABLE = "CREATE TABLE `coupon_project`.`categories` ( " +
      "`id` INT NOT NULL AUTO_INCREMENT," +
      "`name` VARCHAR(45) NOT NULL," +
      "PRIMARY KEY (`id`));";

  public static final String CREATE_COUPONS_TABLE = "CREATE TABLE `coupon_project`.`coupons` (" +
      "`id` INT NOT NULL AUTO_INCREMENT," +
      "`company_id` INT NOT NULL," +
      "`category_id` INT NOT NULL," +
      "`title` VARCHAR(45) NOT NULL," +
      "`description` VARCHAR(45) NOT NULL," +
      "`start_date` DATE NOT NULL," +
      "`end_date` DATE NOT NULL," +
      "`amount` INT NOT NULL," +
      "`price` DOUBLE NOT NULL," +
      "`image` VARCHAR(45) NOT NULL," +
      "PRIMARY KEY (`id`)," +
      "INDEX `company_id_idx` (`company_id` ASC) VISIBLE," +
      "INDEX `category_id_idx` (`category_id` ASC) VISIBLE," +
      "CONSTRAINT `company_id`" +
      "FOREIGN KEY (`company_id`)" +
      "REFERENCES `coupon_project`.`companies` (`id`)" +
      "ON DELETE NO ACTION" +
      "ON UPDATE NO ACTION," +
      "CONSTRAINT `category_id`" +
      "FOREIGN KEY (`category_id`)" +
      "REFERENCES `coupon_project`.`categories` (`id`)" +
      "ON DELETE NO ACTION" +
      "ON UPDATE NO ACTION);";

  public static final String CREATE_CUSTOMER_COUPONS_TABLE =  "CREATE TABLE `coupon_project`.`customers_coupons` (" +
    "`customer_id` INT NOT NULL," +
    "`coupon_id` INT NOT NULL," +
    "PRIMARY KEY (`customer_id`, `coupon_id`)," +
    "INDEX `coupon_id_idx` (`coupon_id` ASC) VISIBLE," +
    "CONSTRAINT `customer_id`" +
    "FOREIGN KEY (`customer_id`)" +
    "REFERENCES `coupon_project`.`customers` (`id`)" +
    "ON DELETE NO ACTION" +
    "ON UPDATE NO ACTION," +
    "CONSTRAINT `coupon_id`" +
    "FOREIGN KEY (`coupon_id`)" +
    "REFERENCES `coupon_project`.`coupons` (`id`)" +
    "ON DELETE NO ACTION" +
    "ON UPDATE NO ACTION);";

}
