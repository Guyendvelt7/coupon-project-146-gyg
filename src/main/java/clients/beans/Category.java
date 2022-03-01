package clients.beans;

import java.util.Arrays;

public enum Category {
    /**
     * coupons categories
     */
    FOOD,
    ELECTRICITY,
    RESTAURANT,
    VACATION,
    ENTERTAINMENT,
    CLEANING_SUPPLIES,
    HOUSEHOLD_SUPPLIES,
    MEDICAL_AND_ADDITIVES,
    OUTDOOR,
    PERSONAL_CARE,
    PETS,
    OTHER;

public static Category getValue (String constant){
    return Arrays.stream(Category.values())
            .filter(item->item.name().equals(constant))
            .findFirst()
            .orElse(null);
}

}
