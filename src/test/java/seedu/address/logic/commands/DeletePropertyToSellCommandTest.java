package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Property;
import seedu.address.testutil.PropertyBuilder;
public class DeletePropertyToSellCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Index indexOfPersonWithProperty = Index.fromOneBased(1);
    private final Index indexOfPersonWithoutProperty = Index.fromOneBased(2);
    private final Index indexOfFirstProperty = Index.fromOneBased(1);

    private final Index invalidPersonIndex = Index.fromOneBased(1000);
//    private final Index invalidPropertyIndex = Index.fromOneBased(-1);

    @Test
    public void execute_validModel_success() throws Exception {
//        DeletePropertyToSellCommand command = new DeletePropertyToSellCommand(indexOfPersonWithProperty,
//                indexOfFirstProperty);
//        CommandResult result = command.execute(model);
//        assertEquals(DeletePropertyToSellCommand.MESSAGE_DELETE_PROPERTY_TO_SELL_SUCCESS,
//                result.getFeedbackToUser());
    }

}
