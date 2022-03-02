package clients;

/**
 * keys to exceptions
 */
public enum EnumExceptions {

    NAME_EXIST("Cannot add existing name."),
    EMAIL_EXIST("Cannot add existing email."),
    INVALID_EMAIL("Invalid email."),
    ID_NOT_EXIST("Invalid ID."),
    COUPON_TITLE_EXIST("Cannot add coupon, title already exist."),
    COUPON_PURCHASED("Cannot purchase this item, already in your list."),
    //INVALID ID?
    NO_COMPANY("There is no company with this ID in the system"),
    NO_COUPONS("You don't have coupons."),
    NO_COUPONS_BY_CATEGORY("You don't have coupons at this category"),
    NO_COUPONS_BY_PRICE("You don't have coupons under this price"),
    //NAME_EXIST?
    COMPANY_ALREADY_EXIST("The company already exist in the system"),
    //WTF?
    ID_COMPANY_ALREADY_EXIST("The id company is already exist in the system"),
    //NO_COUPONS
    NO_COUPONS_COMPANY("company doesn't have coupons"),
    //COUPON_PURCHASED??
    COUPON_ID_EXIST("This coupon already exist in Database"),
    CAN_NOT_CHANGE_NAME("you can't change company name"),
    CAN_NOT_CHANGE_ID("you can't change company id"),
    //NAME EXIST
    COMPANY_NAME_ALREADY_EXIST("Company name already exist"),
    //EMAIL_EXIST?
    COMPANY_EMAIL_ALREADY_EXIST("Company email already exist");

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
