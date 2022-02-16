package clients.db;

import java.security.PublicKey;

public class DBManager {

    public static final String URL = "jdbc:mysql://localhost:3306";
    public static final String SQL_USER = "root";
    public static final String SQL_PASS = "12345678";


    public static final String CREATE_COMPANY_TABLE = "CREATE TABLE `coupon_project`.`companies` (" +
            "`id` INT NOT NULL AUTO_INCREMENT," +
            "`name` VARCHAR(45) NOT NULL," +
            "`email` VARCHAR(45) NOT NULL," +
            "`password` VARCHAR(45) NOT NULL," +
            "PRIMARY KEY (`id`));";

    public static final String CREATE_CUSTOMER_TABLE = "CREATE TABLE `coupon_project`.`customers` (" +
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

    public static final String CREATE_CUSTOMER_VS_COUPONS_TABLE = "CREATE TABLE `coupon_project`.`customers_vs_coupons` (" +
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


    public static final String CREATE_NEW_COUPON = "INSERT INTO `coupon_project`.`coupons` " +
            "(`company_id`, `category_id`, `title`, `description`, " +
            "`start_date`, `end_date`, `amount`, `price`, `image`)" +
            "VALUES (?,?,?,?,?,?,?,?,?);";

    public static final String UPDATE_COUPON = "UPDATE `coupon_project`.`coupons` " +
            "SET company_id=?, category_id=?, title=?, description=?, " +
            "start_date=?, end_date=?, amount=?, price=?, image=?" +
            "WHERE id=?;";

    public static final String DELETE_COUPON = "DELETE FROM `coupon_project`.`coupons` WHERE id=?;";

    public static final String GET_ALL_COUPONS = "SELECT * FROM `coupon_project`.`coupons`;";

    public static final String GET_ONE_COUPON = "SELECT * FROM `coupon_project`.`coupons` WHERE id=?;";

    public static final String ADD_PURCHASED_COUPON = "INSERT INTO `coupon_project`.`customers_vs_coupons` " +
            "(`customer_id`, `coupon_id`) VALUES (?,?);";

    public static final String DELETE_PURCHASED_COUPON = "DELETE FROM `coupon_project`.`customers_vs_coupons` " +
            "(`customer_id`, `coupon_id`) VALUES (?,?);";

    public static final String GET_COUPONS_BY_COMPANIES = "SELECT * FROM `coupon_project`.`coupons` WHERE company_id=?;";

    public static final String ADD_COMPANY = "INSERT INTO `coupon_project`.`companies` ( `name`, `email`, `password`) VALUES (?,?,?);";

    public static final String UPDATE_COMPANY = "UPDATE `coupon_project`.`companies` SET `name` = ?, `email` = ?, `password` = ? WHERE (`id` = ?);";

    public static final String DELETE_COMPANY = "DELETE FROM `coupon_project`.`companies` WHERE (`id` = ?);";

  public static final String GET_ALL_COMPANIES = "SELECT* FROM `coupon_project`.`companies`;";

    public static final String GET_SINGLE_COMPANY = "SELECT* FROM `coupon_project`.`companies` WHERE (`id` = ?);";

    public static final String COUNT_BY_PASS_AND_EMAIL = "SELECT COUNT(*) FROM `coupon_project`.`companies` WHERE `email` = ? and `password` = ?";

    public static final String ADD_CUSTOMER = "INSERT INTO `coupon_project`.`customers` ( `firstName`, `lastName`, `email`, `password`) VALUES (?,?,?,?);";

  public static final String UPDATE_CUSTOMER = "UPDATE `coupon_project`.`customer` SET `firstName` = ?, `lastName` = ?, `email` = ? `password` = ? WHERE (`id` = ?);";

  public static final String DELETE_CUSTOMER = "DELETE FROM `coupon_project`.`customers` WHERE (`id` = ?);";

  public static final String GET_ONE_CUSTOMER = "SELECT * FROM `coupon_project`.`customers` WHERE (`id` = ?);";

  public static final String GET_ALL_CUSTOMERS = "SELECT * FROM `coupon_project`.`customers`;";

  public static final String IS_CUSTOMER_EXISTS = "SELECT COUNT(*) FROM `coupon_project`.`customers` WHERE `email` = ? and `password` = ?";



}

//Todo: CREATE_NEW_COMPANY
//Todo: CREATE_NEW_CUSTOMER
