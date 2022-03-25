package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's matriculation number in TAPA.
 * Guarantees: immutable; is valid as declared in {@link #isValidId(String)}
 */
public class StudentId {
    public static final String MESSAGE_CONSTRAINTS = "Matriculation number should only contain alphanumeric "
            + "characters and should not be blank";
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String id;

    /**
     * Constructs a {@code StudentId}
     *
     * @param id A valid id that represents the student's matriculation number.
     */
    public StudentId(String id) {
        requireNonNull(id);
        checkArgument(isValidId(id), MESSAGE_CONSTRAINTS);
        this.id = id;
    }

    /**
     * Returns true if a given string is a valid id.
     *
     * @param test the string that is being tested.
     * @return a boolean value (true/false) depending if the id is valid.
     */
    public static boolean isValidId(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return id;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentId // instanceof handles nulls
                && id.equals(((StudentId) other).id)); // state check
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
