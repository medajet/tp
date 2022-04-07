package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM_HANDLE;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.person.Person;

/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Person person) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(person);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Person person) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_ID + person.getStudentId().id + " ");
        sb.append(PREFIX_NAME + person.getName().fullName + " ");
        sb.append(PREFIX_MODULE_CODE + person.getModuleCode().moduleCode + " ");
        if (person.getPhone() != null) {
            sb.append(PREFIX_PHONE + person.getPhone().value + " ");
        }
        if (person.getTelegramHandle() != null) {
            sb.append(PREFIX_TELEGRAM_HANDLE + person.getTelegramHandle().telegramHandle + " ");
        }
        if (person.getEmail() != null) {
            sb.append(PREFIX_EMAIL + person.getEmail().value + " ");
        }

        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getStudentId().ifPresent(studentId -> sb.append(PREFIX_ID).append(studentId.id).append(" "));
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getModuleCode().ifPresent(
            moduleCode -> sb.append(PREFIX_MODULE_CODE).append(moduleCode.moduleCode).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getTelegramHandle().ifPresent(
            telegramHandle -> sb.append(PREFIX_TELEGRAM_HANDLE).append(telegramHandle.telegramHandle).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));

        return sb.toString();
    }
}
