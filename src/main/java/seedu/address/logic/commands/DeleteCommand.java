package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentIdContainsKeywordsPredicate;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the student identified by the index number used in the displayed student list "
            + "or matriculation number.\n"
            + "Parameters: INDEX (must be a positive integer) or STUDENT_ID\n"
            + "Example: " + COMMAND_WORD + " 1"
            + " or " + COMMAND_WORD + " " + PREFIX_ID + "A0123456Z\n";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "%s student(s) delete";

    private final Index targetIndex;
    private final StudentIdContainsKeywordsPredicate idPredicate;

    /**
     * Creates a DeleteCommand to delete the specified {@code Person} using the specified index.
     */
    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
        this.idPredicate = null;
    }

    /**
     * Creates a DeleteCommand to delete the specified {@code Person} using the specified student id.
     */
    public DeleteCommand(StudentIdContainsKeywordsPredicate idPredicate) {
        this.idPredicate = idPredicate;
        this.targetIndex = null;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        if (targetIndex != null) { // index was used for the command
            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
            Person personToDelete = lastShownList.get(targetIndex.getZeroBased());
            model.deletePerson(personToDelete);
            return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, 1));
        } else { // student id was used for the command
            assert idPredicate != null;
            model.updateFilteredPersonList(idPredicate);
            int numberOfDeletions = 0;
            if (model.getFilteredPersonList().size() > 0) {
                while (model.getFilteredPersonList().size() > 0) { // person with specified id exists
                    model.updateFilteredPersonList(idPredicate);
                    Person personToDelete = lastShownList.get(Index.fromZeroBased(0).getZeroBased());
                    model.deletePerson(personToDelete);
                    numberOfDeletions++;
                }
                model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
                return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, numberOfDeletions));
            }
             else {
                throw new CommandException(Messages.MESSAGE_NONEXISTENT_STUDENTID);
            }
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) { // short circuit if same object
            return true;
        } else {
            boolean isInstanceOf = other instanceof DeleteCommand;
            if (!isInstanceOf) { // instanceof handles nulls
                return false;
            }
            DeleteCommand commandToCompare = (DeleteCommand) other;
            if (this.idPredicate == null && this.targetIndex != null) { // only targetIndex present
                return targetIndex.equals(commandToCompare.targetIndex); // state check
            } else if (this.targetIndex == null && this.idPredicate != null) { // only idPredicate present
                return idPredicate.equals(commandToCompare.idPredicate); // state check
            } else {
                return false;
            }
        }
    }
}
