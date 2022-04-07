package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Email;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.StudentId;
import seedu.address.model.person.TelegramHandle;

public class JsonAdaptedPersonTest {
    private static final String INVALID_ID = "A@@@@@@@Z";
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_MODULE_CODE = "CS@@@@";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_TELEGRAM_HANDLE = "r@";
    private static final String INVALID_EMAIL = "example.com";

    private static final String VALID_ID = BENSON.getStudentId().toString();
    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_MODULE_CODE = BENSON.getModuleCode().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_TELEGRAM_HANDLE = BENSON.getTelegramHandle().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidStudentId_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(INVALID_ID, VALID_NAME, VALID_MODULE_CODE,
                        VALID_PHONE, VALID_TELEGRAM_HANDLE, VALID_EMAIL, null);
        String expectedMessage = StudentId.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullStudentId_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(null, VALID_NAME, VALID_MODULE_CODE,
                VALID_PHONE, VALID_TELEGRAM_HANDLE, VALID_EMAIL, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, StudentId.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_ID, INVALID_NAME, VALID_MODULE_CODE,
                        VALID_PHONE, VALID_TELEGRAM_HANDLE, VALID_EMAIL, null);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_ID, null, VALID_MODULE_CODE,
                VALID_PHONE, VALID_TELEGRAM_HANDLE, VALID_EMAIL, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidModuleCode_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_ID, VALID_NAME, INVALID_MODULE_CODE,
                        VALID_PHONE, VALID_TELEGRAM_HANDLE, VALID_EMAIL, null);
        String expectedMessage = ModuleCode.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullModuleCode_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_ID, VALID_NAME, null,
                VALID_PHONE, VALID_TELEGRAM_HANDLE, VALID_EMAIL, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ModuleCode.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_ID, VALID_NAME, VALID_MODULE_CODE,
                        INVALID_PHONE, VALID_TELEGRAM_HANDLE, VALID_EMAIL, null);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTelegramHandle_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_ID, VALID_NAME, VALID_MODULE_CODE,
                        VALID_PHONE, INVALID_TELEGRAM_HANDLE, VALID_EMAIL, null);
        String expectedMessage = TelegramHandle.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_ID, VALID_NAME, VALID_MODULE_CODE,
                        VALID_PHONE, VALID_TELEGRAM_HANDLE, INVALID_EMAIL, null);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

}
