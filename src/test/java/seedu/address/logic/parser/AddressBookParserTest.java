package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ArchiveCommand;
import seedu.address.logic.commands.AssignCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.ConfirmClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteModuleCommand;
import seedu.address.logic.commands.DeleteTaskCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.HistoryCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ManualCommand;
import seedu.address.logic.commands.MarkCommand;
import seedu.address.logic.commands.ProgressCommand;
import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.commands.TaskCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.commands.UnmarkCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.ModuleCodeContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentId;
import seedu.address.model.person.StudentIdContainsKeywordsPredicate;
import seedu.address.model.person.Task;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        assertEquals(new AddCommand(person), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_deleteByIndex() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteCommand(new Index[]{INDEX_FIRST_PERSON}), command);
    }

    @Test
    public void parseCommand_deleteById() throws Exception {
        String id = "A0000000Z";
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " i/" + id);
        assertEquals(new DeleteCommand(new StudentId(id)), command);
    }

    @Test
    public void parseCommand_deleteModule() throws Exception {
        String mod = "CS2100";
        ModuleCodeContainsKeywordsPredicate pred =
                new ModuleCodeContainsKeywordsPredicate(Collections.singletonList(mod));
        DeleteModuleCommand command = (DeleteModuleCommand) parser.parseCommand(
                DeleteModuleCommand.COMMAND_WORD + " m/" + mod);
        assertEquals(new DeleteModuleCommand(pred), command);
    }

    @Test
    public void parseCommand_deleteTaskById() throws Exception {
        String id = "A0000000Z";
        String idx = "1";
        DeleteTaskCommand command = (DeleteTaskCommand) parser.parseCommand(
                DeleteTaskCommand.COMMAND_WORD + " i/" + id + " idx/" + idx);
        assertEquals(new DeleteTaskCommand(new StudentId(id), INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_deleteTaskByModuleCode() throws Exception {
        String mod = "CS2100";
        String taskName = "task";
        DeleteTaskCommand command = (DeleteTaskCommand) parser.parseCommand(
                DeleteTaskCommand.COMMAND_WORD + " m/" + mod + " tn/" + taskName);
        assertEquals(new DeleteTaskCommand(new ModuleCode(mod), new Task(taskName)), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_findName() throws Exception {
        List<String> keywords = Arrays.asList("Foo", "Bar", "Baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " n/" + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_findId() throws Exception {
        String id = "A0000000Z";
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " i/" + id);
        assertEquals(new FindCommand(new StudentIdContainsKeywordsPredicate(Collections.singletonList(id))), command);
    }

    @Test
    public void parseCommand_findModuleCode() throws Exception {
        String mod = "CS2100";
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " m/" + mod);
        assertEquals(new FindCommand(new ModuleCodeContainsKeywordsPredicate(Collections.singletonList(mod))), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_assignById() throws Exception {
        String id = "A0000000Z";
        String taskName = "task";
        AssignCommand command = (AssignCommand) parser.parseCommand(
                AssignCommand.COMMAND_WORD + " i/" + id + " tn/" + taskName);
        assertEquals(new AssignCommand(new StudentId(id), new Task(taskName)), command);
    }

    @Test
    public void parseCommand_assignByMod() throws Exception {
        String mod = "CS2100";
        String taskName = "task";
        AssignCommand command = (AssignCommand) parser.parseCommand(
                AssignCommand.COMMAND_WORD + " m/" + mod + " tn/" + taskName);
        assertEquals(new AssignCommand(new ModuleCode(mod), new Task(taskName)), command);
    }

    @Test
    public void parseCommand_mark() throws Exception {
        String id = "A0000000Z";
        String idx = "1";
        MarkCommand command = (MarkCommand) parser.parseCommand(
                MarkCommand.COMMAND_WORD + " i/" + id + " idx/" + idx);
        assertEquals(new MarkCommand(new StudentId(id), INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_unmark() throws Exception {
        String id = "A0000000Z";
        String idx = "1";
        UnmarkCommand command = (UnmarkCommand) parser.parseCommand(
                UnmarkCommand.COMMAND_WORD + " i/" + id + " idx/" + idx);
        assertEquals(new UnmarkCommand(new StudentId(id), INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_task() throws Exception {
        String id = "A0000000Z";
        TaskCommand command = (TaskCommand) parser.parseCommand(
                TaskCommand.COMMAND_WORD + " i/" + id);
        assertEquals(new TaskCommand(new StudentId(id)), command);
    }

    @Test
    public void parseCommand_progress() throws Exception {
        String mod = "CS2100";
        String taskName = "task";
        ProgressCommand command = (ProgressCommand) parser.parseCommand(
                ProgressCommand.COMMAND_WORD + " m/" + mod + " tn/" + taskName);
        assertEquals(new ProgressCommand(new ModuleCode(mod), new Task(taskName)), command);
    }

    @Test
    public void parseCommand_manual() throws Exception {
        ManualCommand command = (ManualCommand) parser.parseCommand(
                ManualCommand.COMMAND_WORD + " " + "add");
        assertEquals(new ManualCommand(AddCommand.COMMAND_WORD), command);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_archive() throws Exception {
        assertTrue(parser.parseCommand(ArchiveCommand.COMMAND_WORD) instanceof ArchiveCommand);
        assertTrue(parser.parseCommand(ArchiveCommand.COMMAND_WORD + " 3") instanceof ArchiveCommand);
    }

    @Test
    public void parseCommand_sort() throws Exception {
        assertTrue(parser.parseCommand(SortCommand.COMMAND_WORD) instanceof SortCommand);
        assertTrue(parser.parseCommand(SortCommand.COMMAND_WORD + " 3") instanceof SortCommand);
    }

    @Test
    public void parseCommand_history() throws Exception {
        assertTrue(parser.parseCommand(HistoryCommand.COMMAND_WORD) instanceof HistoryCommand);
        assertTrue(parser.parseCommand(HistoryCommand.COMMAND_WORD + " 3") instanceof HistoryCommand);
    }

    @Test
    public void parseCommand_undo() throws Exception {
        assertTrue(parser.parseCommand(UndoCommand.COMMAND_WORD) instanceof UndoCommand);
        assertTrue(parser.parseCommand(UndoCommand.COMMAND_WORD + " 3") instanceof UndoCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class,
                MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }

    @Test
    public void parseConfirmCommand_success() throws Exception {
        assertTrue(parser.parseConfirmCommand(ConfirmClearCommand.COMMAND_WORD) instanceof ConfirmClearCommand);
        assertTrue(parser.parseConfirmCommand(ConfirmClearCommand.COMMAND_WORD + " 3")
                instanceof ConfirmClearCommand);
    }

    @Test
    public void parseConfirmCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseConfirmCommand(""));
    }

    @Test
    public void parseConfirmCommand_invalidInput_throwsParseException() {
        assertThrows(ParseException.class,
                MESSAGE_UNKNOWN_COMMAND, () -> parser.parseConfirmCommand("unknownCommand"));
    }
}
