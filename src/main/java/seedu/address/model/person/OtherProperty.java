package seedu.address.model.person;

import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents an Other Property in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class OtherProperty extends Property {

    public static final String MESSAGE_CONSTRAINTS = "Other Property names should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+"; // Checks if the Other Property name is alphanumeric

    public OtherProperty(PostalCode postalCode, UnitNumber unitNumber, Price price, Set<Tag> tags) {
        super(postalCode, unitNumber, price, tags);
    }

    /**
    * Returns true if a given string is a valid Other Property name.
    */
    public static boolean isValidOtherPropertyName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object otherProperty) {
        if (!super.equals(otherProperty)) {
            return false;  // Check the parent class's equality (postalCode)
        }
        if (!(otherProperty instanceof Bto)) {
            return false;  // Ensure `other` is an instance of the same subclass
        }
        return true;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
