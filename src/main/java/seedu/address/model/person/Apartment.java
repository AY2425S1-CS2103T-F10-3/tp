package seedu.address.model.person;

import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents an Apartment in the address book. A subclass of Property class
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Apartment extends Property {

    public static final String MESSAGE_CONSTRAINTS = "Apartment names should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+"; // Checks if the Apartment name is alphanumeric

    /**
     * Constructs a {@code Apartment}.
     *
     * @param postalCode A valid apartment postalCode.
     * @param unitNumber A valid unit number.
     * @param price A valid price.
     */
    public Apartment(PostalCode postalCode, UnitNumber unitNumber, Price price, Set<Tag> tags) {
        super(postalCode, unitNumber, price, tags);
    }

    /**
     * Returns true if a given string is a valid Apartment name.
     */
    public static boolean isValidApartmentName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object otherApartment) {
        if (!super.equals(otherApartment)) {
            return false;
        }
        if (!(otherApartment instanceof Bto)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
