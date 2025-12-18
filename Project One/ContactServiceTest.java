import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.NoSuchElementException;

public class ContactServiceTest {

    @Test
    void addContact_generatesUniqueId_andStoresContact() {
        ContactService svc = new ContactService();
        String id1 = svc.addContact("Amy", "Adams", "1112223333", "1 First St");
        String id2 = svc.addContact("Bob", "Baker", "2223334444", "2 Second St");

        assertNotEquals(id1, id2);
        assertEquals(2, svc.size());
        assertEquals("Amy", svc.getContact(id1).getFirstName());
    }

    @Test
    void addExistingContact_enforcesUniqueId() {
        ContactService svc = new ContactService();
        Contact c1 = new Contact("fixedID", "Amy", "Adams", "1112223333", "1 First St");
        svc.addContact(c1);
        Contact c2 = new Contact("fixedID", "Bob", "Baker", "2223334444", "2 Second St");
        assertThrows(IllegalArgumentException.class, () -> svc.addContact(c2));
    }

    @Test
    void deleteContact_removesById() {
        ContactService svc = new ContactService();
        String id = svc.addContact("Amy", "Adams", "1112223333", "1 First St");
        svc.deleteContact(id);
        assertEquals(0, svc.size());
        assertThrows(NoSuchElementException.class, () -> svc.getContact(id));
    }

    @Test
    void updateFields_updatesOnlySpecifiedContact() {
        ContactService svc = new ContactService();
        String id = svc.addContact("Amy", "Adams", "1112223333", "1 First St");

        svc.updateFirstName(id, "Ava");
        svc.updateLastName(id, "Anders");
        svc.updatePhone(id, "9998887777");
        svc.updateAddress(id, "9 Ninth Ave");

        Contact updated = svc.getContact(id);
        assertAll(
            () -> assertEquals("Ava", updated.getFirstName()),
            () -> assertEquals("Anders", updated.getLastName()),
            () -> assertEquals("9998887777", updated.getPhone()),
            () -> assertEquals("9 Ninth Ave", updated.getAddress())
        );
    }

    @Test
    void updateOnMissingId_throws() {
        ContactService svc = new ContactService();
        assertThrows(NoSuchElementException.class, () -> svc.updateFirstName("nope", "X"));
        assertThrows(NoSuchElementException.class, () -> svc.deleteContact("nope"));
    }

    @Test
    void invalidUpdates_throwValidationErrors() {
        ContactService svc = new ContactService();
        String id = svc.addContact("Amy", "Adams", "1112223333", "1 First St");
        assertThrows(IllegalArgumentException.class, () -> svc.updatePhone(id, "short"));
        assertThrows(IllegalArgumentException.class, () -> svc.updateFirstName(id, "thisiswaytoolong"));
    }
}