package clients.beans;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 * Coupon class
 * @author Yoav, Guy and Geri
 */
public class Coupon {
    private int id;
    private int companyId;
    private Category category;
    private String title;
    private String description;
    private Date startDate;
    private Date endDate;
    private int amount;
    private double price;
    private String image;
    private Scanner sc = new Scanner(System.in);

    /**
     *Coupon constructor
     * @param id coupon ID
     * @param companyId refers to coupons company ownership
     * @param category refers to coupons category type
     * @param title coupons name
     * @param description message about coupon discount
     * @param startDate coupon start date
     * @param endDate coupon end date
     * @param amount defines number of coupons available for purchase
     * @param price coupons purchase value
     * @param image visual of coupon description
     */

    public Coupon(int id, int companyId, Category category, String title, String description, Date startDate, Date endDate, int amount, double price, String image) {
        this.id = id;
        this.companyId = companyId;
        this.category = category;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
        this.price = price;
        this.image = image;
    }


    public Coupon(int companyId, Category category, String title, String description, Date startDate, Date endDate, int amount, double price, String image) {
        this.companyId = companyId;
        this.category = category;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
        this.price = price;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy");
        System.out.println("set start date of coupon by format dd/MM/yyyy");
        String startDateString = sc.next();
        try {
            startDate = (Date) sdf.parse(startDateString);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy");
        System.out.println("set end date of coupon by format dd/MM/yyyy");
        String endDateString = sc.next();
        try {
            endDate = (Date) sdf.parse(endDateString);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        this.endDate = endDate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "id=" + id +
                ", companyId=" + companyId +
                ", category=" + category +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", amount=" + amount +
                ", price=" + price +
                ", image='" + image + '\'' +
                '}';
    }
}
