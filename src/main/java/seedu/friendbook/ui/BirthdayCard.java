package seedu.friendbook.ui;

import java.util.logging.Logger;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.friendbook.commons.core.LogsCenter;
import seedu.friendbook.logic.commands.exceptions.CommandException;
import seedu.friendbook.model.person.Person;

public class BirthdayCard extends UiPart<Region> {

    private static final String FXML = "BirthdayListCard.fxml";

    public final Person person;
    private final Logger logger = LogsCenter.getLogger(BirthdayCard.class);

    @FXML
    private HBox cardPane;
    @FXML
    private ImageView friendImage;
    @FXML
    private Label name;
    @FXML
    private Label dob;
    @FXML
    private Label age;
    @FXML
    private Label daysToBirthday;
    @FXML
    private CheckBox reminderCheckBox;

    private SetRemindExecutor setRemindExecutor;


    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public BirthdayCard(Person person, int displayedIndex, SetRemindExecutor setRemindExecutor) {
        super(FXML);
        this.person = person;
        this.setRemindExecutor = setRemindExecutor;

        //TODO: to update friendpicture
        name.setText(person.getName().fullName);
        age.setText("Currently " + String.valueOf(person.getAge()) + " Years Old");
        dob.setText(person.getBirthday().getActualDate());
        daysToBirthday.setText(String.valueOf(person.getDaysToRemainingBirthday()));
        reminderCheckBox.setSelected(person.getReminder().getBooleanValue());

        reminderCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                // update person model store in newvale
                Person updatedFriend = Person.newInstance(person);
                updatedFriend.getReminder().setReminder(newValue);
                try {
                    setRemindExecutor.execute(person, updatedFriend);
                } catch (CommandException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FriendListCard)) {
            return false;
        }

        // state check
        FriendListCard card = (FriendListCard) other;
        return person.equals(card.person);
    }

    /**
     * Represents a function that can execute commands.
     */
    @FunctionalInterface
    public interface SetRemindExecutor {
        void execute(Person oldPerson, Person updatedPerson) throws CommandException;
    }
}
