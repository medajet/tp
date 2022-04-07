package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ID_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ID_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TELEGRAM_HANDLE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INDEX_MULTIPLE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.model.person.StudentId;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteCommandParserTest {

    private DeleteCommandParser parser = new DeleteCommandParser();

    @Test
    public void parse_validIndex_returnsDeleteCommand() {
        assertParseSuccess(parser, VALID_INDEX, new DeleteCommand(new Index[]{INDEX_FIRST_PERSON}));
    }

    @Test
    public void parse_multipleValidIndex_returnsDeleteCommand() {
        assertParseSuccess(parser, VALID_INDEX_MULTIPLE, new DeleteCommand(
                new Index[]{INDEX_FIRST_PERSON, INDEX_SECOND_PERSON, INDEX_THIRD_PERSON}));
    }

    @Test
    public void parse_validStudentId_returnsDeleteCommand() {
        // valid student id
        DeleteCommand expectedDeleteCommand = new DeleteCommand(new StudentId(VALID_ID_BOB));

        // no white space before id
        assertParseSuccess(parser, ID_DESC_BOB, expectedDeleteCommand);

        // whitespace before id
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + ID_DESC_BOB, expectedDeleteCommand);
    }

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_nonemptyPreamble_throwsParseException() {
        assertParseFailure(parser, PREAMBLE_NON_EMPTY, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_multipleFields_throwsParseException() {
        // index + student id
        assertParseFailure(parser, PREAMBLE_WHITESPACE + VALID_INDEX + ID_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidFields_throwsParseException() {
        // invalid index
        assertParseFailure(parser, PREAMBLE_WHITESPACE + INVALID_INDEX, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteCommand.MESSAGE_USAGE));

        // invalid student id
        assertParseFailure(parser, PREAMBLE_WHITESPACE + INVALID_ID_DESC,
                StudentId.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_nonSupportedFields_throwsParseException() {
        // name
        assertParseFailure(parser, PREAMBLE_WHITESPACE + NAME_DESC_AMY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));

        // telegram handle
        assertParseFailure(parser, PREAMBLE_WHITESPACE + TELEGRAM_HANDLE_DESC_AMY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));

        // phone number
        assertParseFailure(parser, PREAMBLE_WHITESPACE + PHONE_DESC_AMY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));

        // email
        assertParseFailure(parser, PREAMBLE_WHITESPACE + EMAIL_DESC_AMY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }
}
