package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeletePropertyToSellCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

/**
 * Parses input arguments and creates a new DeletePropertyToSellCommand object
 */
public class DeletePropertyToSellCommandParser implements Parser<DeletePropertyToSellCommand> {

    // Users should provide at least 2 Indexes
    public static final int MINIMUM_INDEX_COUNT = 2;

    /**
     * Parses the given {@code String} of arguments in the context of the DeletePropertyToSellCommand
     * and returns a DeletePropertyToSellCommand object for execution. Rejects any integers that are
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeletePropertyToSellCommand parse(String args) throws ParseException {
        try {
            Index personIndex = ParserUtil.parseFirstIndex(args, MINIMUM_INDEX_COUNT);
            Index propertyToSellIndex = ParserUtil.parseSecondIndex(args, MINIMUM_INDEX_COUNT);
            return new DeletePropertyToSellCommand(personIndex, propertyToSellIndex);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeletePropertyToSellCommand.MESSAGE_USAGE), pe);
        }
    }

}
