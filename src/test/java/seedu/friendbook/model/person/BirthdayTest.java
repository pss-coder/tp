package seedu.friendbook.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.friendbook.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class BirthdayTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Birthday(null));
    }

    @Test
    public void constructor_invalidBirthday_throwsIllegalArgumentException() {
        String invalidBirthday = "1993/04/20";
        assertThrows(IllegalArgumentException.class, () -> new Birthday(invalidBirthday));
    }

    @Test
    public void isValidBirthday() {
        // null birthday
        assertThrows(NullPointerException.class, () -> Birthday.isValidBirthday(null));

        // invalid birthdays
        assertFalse(Birthday.isValidBirthday(""));
        assertFalse(Birthday.isValidBirthday("1993/04/20"));
        assertFalse(Birthday.isValidBirthday("1994/20/04"));
        assertFalse(Birthday.isValidBirthday("20/04/98"));
        assertFalse(Birthday.isValidBirthday("20/04/1998"));
        assertFalse(Birthday.isValidBirthday("26 Oct 1998"));
        assertFalse(Birthday.isValidBirthday("2022-04-15"));
        assertFalse(Birthday.isValidBirthday("2012-20-15"));
        assertFalse(Birthday.isValidBirthday("2022-02-33"));
        assertFalse(Birthday.isValidBirthday("2023-13-35"));

        // valid birthdays
        assertTrue(Birthday.isValidBirthday("1994-04-15"));
        assertTrue(Birthday.isValidBirthday("2021-03-25"));
        assertTrue(Birthday.isValidBirthday("2021-10-05"));
    }

    //TODO: update group that check will fail once the birthday passes (should we test this)
    @Test
    public void calculateAgeTest() {
        Birthday birthdayPassed = new Birthday("1994-05-20");
        Birthday birthdayUpcoming = new Birthday("1995-12-28");

        assertEquals(27, birthdayPassed.calculateAge());
        assertEquals(25, birthdayUpcoming.calculateAge());
    }

    //TODO: test for remaining days till birthday

}