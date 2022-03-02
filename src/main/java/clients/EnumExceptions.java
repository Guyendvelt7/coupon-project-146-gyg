package clients;

/**
 * keys to exceptions
 */
public enum EnumExceptions {

    NAME_EXIST("Cannot add existing name."),
    EMAIL_EXIST("Cannot add existing email."),
    INVALID_EMAIL("Invalid email."),
    ID_NOT_EXIST("Cannot update/remove, invalid ID."),
    INVALID_PASSWORD("Invalid password."),
    COUPON_TITLE_EXIST("Cannot add coupon, title already exist."),
    COUPON_PURCHASED("Cannot purchase this item, already in your list."),
    EMPTY_RESULT_SET("no data in result set, the result set does not! have next"),
    RESULT_SET_DATA_PROBLEM("problem in the result set data, sql context"),
    NO_COMPANIES("there are no companies in the system"),
    NO_COMPANY("there is no company with this id in the system"),
    CUSTOMER_ALREADY_EXIST("the customer already exist in the system"),
    NO_COUPONS("you don't have coupons purchased"),
    NO_COUPONS_BY_CATEGORY("you don't have coupons at this category"),
    NO_COUPONS_BY_PRICE("you don't have coupons under this price"),
    COMPANY_ALREADY_EXIST("the company is already exist in the system"),
    ID_COMPANY_ALREADY_EXIST("the id company is already exist in the system"),
    NO_COUPONS_COMPANY("company doesn't have coupons"),
    COMPANY_IS_NOT_EXIST("the company is not exist in the system"),
    CATEGORY_IS_NOT_EXIST("not such category"),
    No_COUPON("THERE IS NO COUPON WITH THIS ID");
    ;

    private String message;

    private EnumExceptions(String message){
        setMessage(message);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
