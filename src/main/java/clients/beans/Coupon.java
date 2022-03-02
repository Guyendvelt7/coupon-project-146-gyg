package clients.beans;

import java.sql.Date;

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
    /**
     * default C`tor
     */
    public Coupon() {
    }

    /**
     *Coupon constructor
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


    public Coupon(int id, int companyId, Category category, String title, String description, int when2Start, int when2End, int amount, double price, String image) {
        this.id=id;
        this.companyId = companyId;
        this.category = category;
        this.title = title;
        this.description = description;
        setStartDate(when2Start);
        setEndDate(when2End);
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

    public void setStartDate(int whenToStart) {
        this.startDate = new Date(System.currentTimeMillis()+ 24L *60*60*1000*whenToStart);
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(int hawManyDays) {
        this.endDate = new Date(System.currentTimeMillis()+ 24L *60*60*1000*hawManyDays);
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
