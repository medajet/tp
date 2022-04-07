package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_HANDLE_BOB;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PersonTest {
    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSamePerson(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSamePerson(null));

        // same id, all other attributes different -> returns true
        Person editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB)
                .withModuleCode(VALID_MODULE_CODE_BOB)
                .withPhone(VALID_PHONE_BOB).withTelegramHandle(VALID_TELEGRAM_HANDLE_BOB)
                .withEmail(VALID_EMAIL_BOB)
                .build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // different id, all other attributes same -> returns false
        editedAlice = new PersonBuilder(ALICE).withStudentId(VALID_ID_BOB).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // id differs in case, all other attributes same -> returns false
        Person editedBob = new PersonBuilder(BOB).withStudentId(VALID_ID_BOB.toLowerCase()).build();
        assertFalse(BOB.isSamePerson(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Person aliceCopy = new PersonBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // copy of object (unmarked tasks) -> returns true
        Person newAliceCopy = aliceCopy.getCopy();
        assertTrue(aliceCopy.equals(newAliceCopy));

        // copy of object (marked tasks) -> returns true
        Person bensonCopy = new PersonBuilder(BENSON).build();
        Person newBensonCopy = bensonCopy.getCopy();
        assertTrue(bensonCopy.equals(newBensonCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different person -> returns false
        assertFalse(ALICE.equals(BOB));

        // different id -> returns false
        Person editedAlice = new PersonBuilder(ALICE).withStudentId(VALID_ID_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different name -> returns false
        editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different moduleCode -> returns false
        editedAlice = new PersonBuilder(ALICE).withModuleCode(VALID_MODULE_CODE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different telegramHandle -> returns false
        editedAlice = new PersonBuilder(ALICE).withTelegramHandle(VALID_TELEGRAM_HANDLE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // null checks for phone
        editedAlice = new PersonBuilder(ALICE).withPhone((String) null).build();
        Person editedAliceCopy = editedAlice.getCopy();
        assertFalse(ALICE.equals(editedAlice));
        assertFalse(editedAlice.equals(ALICE));
        assertTrue(editedAlice.equals(editedAliceCopy));

        // null checks for telegram handle
        editedAlice = new PersonBuilder(ALICE).withTelegramHandle((String) null).build();
        editedAliceCopy = editedAlice.getCopy();
        assertFalse(ALICE.equals(editedAlice));
        assertFalse(editedAlice.equals(ALICE));
        assertTrue(editedAlice.equals(editedAliceCopy));

        // null checks for email
        editedAlice = new PersonBuilder(ALICE).withEmail((String) null).build();
        editedAliceCopy = editedAlice.getCopy();
        assertFalse(ALICE.equals(editedAlice));
        assertFalse(editedAlice.equals(ALICE));
        assertTrue(editedAlice.equals(editedAliceCopy));
    }
}
