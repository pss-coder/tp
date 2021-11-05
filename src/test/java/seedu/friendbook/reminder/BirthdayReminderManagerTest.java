package seedu.friendbook.reminder;

import static org.junit.jupiter.api.Assertions.assertNull;
import static seedu.friendbook.testutil.Assert.assertThrows;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.friendbook.logic.Logic;
import seedu.friendbook.logic.LogicManager;
import seedu.friendbook.model.Model;
import seedu.friendbook.model.ModelManager;
import seedu.friendbook.storage.JsonFriendBookStorage;
import seedu.friendbook.storage.JsonUserPrefsStorage;
import seedu.friendbook.storage.StorageManager;


public class BirthdayReminderManagerTest {
    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private BirthdayReminder birthdayReminder;

    @BeforeEach
    public void setUp() {
        JsonFriendBookStorage friendBookStorage =
                new JsonFriendBookStorage(temporaryFolder.resolve("friendBook.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(friendBookStorage, userPrefsStorage);
        Logic logic = new LogicManager(model, storage);
        birthdayReminder = new BirthdayReminderManager(logic.getFilteredPersonListSortedByBirthday());
    }

    @Test
    public void execute_invalidBirthdayList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new BirthdayReminderManager(null));
    }

    @Test
    public void execute_invalidBirthdayReminderTask_expectNullValue() {
        BirthdayReminderManager birthdayReminderManager =
                new BirthdayReminderManager(model.getFilteredPersonListSortedByBirthday());
        assertNull(birthdayReminderManager.createTask().getValue());
    }
}