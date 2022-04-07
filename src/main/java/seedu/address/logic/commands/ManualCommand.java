package seedu.address.logic.commands;

import seedu.address.commons.core.ManualMessages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Display the format for a specified command and a short description for a particular command.
 */

public class ManualCommand extends Command {

    public static final String COMMAND_WORD = "manual";

    private final String commandName;

    public ManualCommand(String commandName) {
        this.commandName = commandName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {

        switch (commandName) {

        case (""):
            return new CommandResult(ManualMessages.MANUAL_MESSAGE_ALL_COMMANDS);

        case AddCommand.COMMAND_WORD:
            return new CommandResult(ManualMessages.MANUAL_MESSAGE_ADD_COMMAND);

        case DeleteCommand.COMMAND_WORD:
            return new CommandResult(ManualMessages.MANUAL_MESSAGE_DELETE_COMMAND);

        case DeleteModuleCommand.COMMAND_WORD:
            return new CommandResult(ManualMessages.MANUAL_MESSAGE_DELETE_MODULE_COMMAND);

        case FindCommand.COMMAND_WORD:
            return new CommandResult(ManualMessages.MANUAL_MESSAGE_FIND_COMMAND);

        case EditCommand.COMMAND_WORD:
            return new CommandResult(ManualMessages.MANUAL_MESSAGE_EDIT_COMMAND);

        case ClearCommand.COMMAND_WORD:
            return new CommandResult(ManualMessages.MANUAL_MESSAGE_CLEAR_COMMAND);

        case ListCommand.COMMAND_WORD:
            return new CommandResult(ManualMessages.MANUAL_MESSAGE_LIST_COMMAND);

        case ManualCommand.COMMAND_WORD:
            return new CommandResult(ManualMessages.MANUAL_MESSAGE_MANUAL_COMMAND);

        case HelpCommand.COMMAND_WORD:
            return new CommandResult(ManualMessages.MANUAL_MESSAGE_HELP_COMMAND);

        case ExitCommand.COMMAND_WORD:
            return new CommandResult(ManualMessages.MANUAL_MESSAGE_EXIT_COMMAND);

        case TaskCommand.COMMAND_WORD:
            return new CommandResult(ManualMessages.MANUAL_MESSAGE_TASK_COMMAND);

        case MarkCommand.COMMAND_WORD:
            return new CommandResult(ManualMessages.MANUAL_MESSAGE_MARK_COMMAND);

        case UnmarkCommand.COMMAND_WORD:
            return new CommandResult(ManualMessages.MANUAL_MESSAGE_UNMARK_COMMAND);

        case ArchiveCommand.COMMAND_WORD:
            return new CommandResult(ManualMessages.MANUAL_MESSAGE_ARCHIVE_COMMAND);

        case AssignCommand.COMMAND_WORD:
            return new CommandResult(ManualMessages.MANUAL_MESSAGE_ASSIGN_COMMAND);

        case DeleteTaskCommand.COMMAND_WORD:
            return new CommandResult(ManualMessages.MANUAL_MESSAGE_DELETE_TASK_COMMAND);

        case ProgressCommand.COMMAND_WORD:
            return new CommandResult(ManualMessages.MANUAL_MESSAGE_PROGRESS_COMMAND);

        case HistoryCommand.COMMAND_WORD:
            return new CommandResult(ManualMessages.MANUAL_MESSAGE_HISTORY_COMMAND);

        case UndoCommand.COMMAND_WORD:
            return new CommandResult(ManualMessages.MANUAL_MESSAGE_UNDO_COMMAND);

        case SortCommand.COMMAND_WORD:
            return new CommandResult(ManualMessages.MANUAL_MESSAGE_SORT_COMMAND);

        default:
            throw new CommandException(ManualMessages.MANUAL_MESSAGE_UNKNOWN_COMMANDS);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ManualCommand // instanceof handles nulls
                && commandName.equals(((ManualCommand) other).commandName)); // state check
    }
}
