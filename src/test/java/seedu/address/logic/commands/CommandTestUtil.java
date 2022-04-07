package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM_HANDLE;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_ID_AMY = "A1111111Z";
    public static final String VALID_ID_BOB = "A2222222Z";
    public static final String VALID_ID_ANDY = "A0000001Z";
    public static final String VALID_NAME_AMY = "Amy";
    public static final String VALID_NAME_BOB = "Bob";
    public static final String VALID_NAME_ANDY = "Andy";
    public static final String VALID_MODULE_CODE_AMY = "CS1111";
    public static final String VALID_MODULE_CODE_BOB = "CS2222";
    public static final String VALID_MODULE_CODE_ANDY = "CS2101";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_PHONE_ANDY = "10000000";
    public static final String VALID_TELEGRAM_HANDLE_AMY = "amyyy";
    public static final String VALID_TELEGRAM_HANDLE_BOB = "bobbb";
    public static final String VALID_TELEGRAM_HANDLE_ANDY = "andyyy";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_EMAIL_ANDY = "andyyy@u.nus.edu";
    public static final String VALID_TASK_AMY = "Task A";
    public static final String VALID_TASK_BOB = "Task B";
    public static final String VALID_INDEX = "1";
    public static final String VALID_INDEX_TWO = "2";
    public static final String VALID_INDEX_THREE = "3";
    public static final String VALID_INDEX_MULTIPLE = "1 2 3";
    public static final String VALID_TASK_NAME = "Valid Task";

    public static final String ID_DESC_AMY = " " + PREFIX_ID + VALID_ID_AMY;
    public static final String ID_DESC_BOB = " " + PREFIX_ID + VALID_ID_BOB;
    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String MODULE_CODE_DESC_AMY = " " + PREFIX_MODULE_CODE + VALID_MODULE_CODE_AMY;
    public static final String MODULE_CODE_DESC_BOB = " " + PREFIX_MODULE_CODE + VALID_MODULE_CODE_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String TELEGRAM_HANDLE_DESC_AMY = " " + PREFIX_TELEGRAM_HANDLE + VALID_TELEGRAM_HANDLE_AMY;
    public static final String TELEGRAM_HANDLE_DESC_BOB = " " + PREFIX_TELEGRAM_HANDLE + VALID_TELEGRAM_HANDLE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String TASK_DESC_AMY = " " + PREFIX_TASK_NAME + VALID_TASK_AMY;
    public static final String TASK_DESC_BOB = " " + PREFIX_TASK_NAME + VALID_TASK_BOB;
    public static final String INDEX_DESC = " " + PREFIX_INDEX + VALID_INDEX;
    public static final String TASK_NAME_DESC = " " + PREFIX_TASK_NAME + VALID_TASK_NAME;

    public static final String INVALID_ID_DESC = " " + PREFIX_ID + "A&&&&&&&Z"; // '&' not allowed in studentId
    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_MODULE_CODE_DESC = " " + PREFIX_MODULE_CODE
            + "CS&&&&"; // '&' not allowed in moduleCode
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_TELEGRAM_HANDLE_DESC = " " + PREFIX_TELEGRAM_HANDLE
            + "xxx"; // telegramHandles should have 5 to 32 characters
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_TASK_DESC = " " + PREFIX_TASK_NAME + "*"; // '*' not allowed in task name
    public static final String INVALID_COMMAND_NAME = "poop";
    public static final String INVALID_INDEX = " " + PREFIX_INDEX + "index"; // alphabets and symbols not allowed
    public static final String INVALID_TASKNAME_DESC = " " + PREFIX_TASK_NAME + "T@sk"; // '@' not allowed in names

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withStudentId(VALID_ID_AMY).withName(VALID_NAME_AMY)
                .withModuleCode(VALID_MODULE_CODE_AMY).withPhone(VALID_PHONE_AMY)
                .withTelegramHandle(VALID_TELEGRAM_HANDLE_AMY).withEmail(VALID_EMAIL_AMY).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withStudentId(VALID_ID_BOB).withName(VALID_NAME_BOB)
                .withModuleCode(VALID_MODULE_CODE_BOB).withPhone(VALID_PHONE_BOB)
                .withTelegramHandle(VALID_TELEGRAM_HANDLE_BOB).withEmail(VALID_EMAIL_BOB).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
                                            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
                                            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

}
