package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.*;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.*;
import seedu.address.model.tag.Tag;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeletePropertyToSellCommand extends Command {

    public static final String COMMAND_WORD = "delSell";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the property a person initially wanted to sell. \n"
            + "Person is identified by the index number used in the displayed person list.\n"
            + "Property-to-sell is identified by the index number used in the "
            + "displayed properties list a person wants to sell. \n"
            + "Parameters: PERSON_INDEX (must be a positive integer) PROPSELL_INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1" + " 2";

    public static final String MESSAGE_DELETE_PROPERTY_TO_SELL_SUCCESS = "Deleted Property-to-sell: %1$s";

    private final Index personIndex;
    private final Index propertyToSellIndex;
    public DeletePropertyToSellCommand(Index personIndex, Index propertyToSellIndex) {
        this.personIndex = personIndex;
        this.propertyToSellIndex = propertyToSellIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (personIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        // Gets the person whose property data needs to be modified
        Person personToEdit = lastShownList.get(personIndex.getZeroBased());
        // Gets the sellingProperties of the person
        List<Property> sellingPropertiesToModify = personToEdit.getSellingProperties();
        // Removes the property based on the index specified by the user.
        sellingPropertiesToModify.remove(propertyToSellIndex.getZeroBased());

        // Create a new EditSellerDescriptor
        EditSellerDescriptor editSellerDescriptor = new EditSellerDescriptor();
        // update the sellingProperties field of the editSellerDescriptor
        editSellerDescriptor.setSellingProperties(sellingPropertiesToModify);

        // Creates the new Edited Person with the updated list of sellingProperties
        Person editedPerson = createEditedSeller(personToEdit, editSellerDescriptor);

//        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
//            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
//        }

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_DELETE_PROPERTY_TO_SELL_SUCCESS,
                Messages.propertyFormat(editedPerson)));
    }

    /**
     * Creates and returns a {@code Seller} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedSeller(Person sellerToEdit, EditSellerDescriptor editSellerDescriptor) {
        assert sellerToEdit != null;

        Name updatedName = editSellerDescriptor.getName().orElse(sellerToEdit.getName());
        Phone updatedPhone = editSellerDescriptor.getPhone().orElse(sellerToEdit.getPhone());
        Email updatedEmail = editSellerDescriptor.getEmail().orElse(sellerToEdit.getEmail());
        Address updatedAddress = editSellerDescriptor.getAddress().orElse(sellerToEdit.getAddress());
        Set<Tag> updatedTags = editSellerDescriptor.getTags().orElse(sellerToEdit.getTags());
        List<Property> updatedSellingProperties = editSellerDescriptor.getSellingProperties()
                .orElse(sellerToEdit.getSellingProperties());
        List<Property> updatedBuyingProperties = editSellerDescriptor.getBuyingProperties()
                .orElse(sellerToEdit.getBuyingProperties());

        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags,
                updatedSellingProperties, updatedBuyingProperties);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditSellerDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private Set<Tag> tags;
        private List<Property> sellingProperties;
        private List<Property> buyingProperties;

        public EditSellerDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditSellerDescriptor(EditSellerDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setTags(toCopy.tags);
            setSellingProperties(toCopy.sellingProperties);
            setBuyingProperties(toCopy.buyingProperties);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, tags);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }
        public void setSellingProperties(List<Property> sellingProperties) {
            this.sellingProperties = (sellingProperties != null) ? new ArrayList<>(sellingProperties) : null;
        }
        public Optional<List<Property>> getSellingProperties() {
            return Optional.ofNullable(sellingProperties);
        }
        public void setBuyingProperties(List<Property> buyingProperties) {
            this.buyingProperties = (buyingProperties != null) ? new ArrayList<>(buyingProperties) : null;
        }
        public Optional<List<Property>> getBuyingProperties() {
            return Optional.ofNullable(sellingProperties);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditSellerDescriptor otherEditPersonDescriptor)) {
                return false;
            }

            return Objects.equals(name, otherEditPersonDescriptor.name)
                    && Objects.equals(phone, otherEditPersonDescriptor.phone)
                    && Objects.equals(email, otherEditPersonDescriptor.email)
                    && Objects.equals(address, otherEditPersonDescriptor.address)
                    && Objects.equals(tags, otherEditPersonDescriptor.tags)
                    && Objects.equals(sellingProperties, otherEditPersonDescriptor.sellingProperties)
                    && Objects.equals(buyingProperties, otherEditPersonDescriptor.buyingProperties);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("phone", phone)
                    .add("email", email)
                    .add("address", address)
                    .add("tags", tags)
                    .add("buying properties", buyingProperties)
                    .add("selling properties", sellingProperties)
                    .toString();
        }

    }
}
