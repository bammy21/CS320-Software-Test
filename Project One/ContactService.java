import java.util.Map;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.UUID;

public class ContactService {
    private final Map<String, Contact> contacts = new HashMap<>();

    /** Add an existing Contact, enforcing unique ID. */
    public void addContact(Contact contact) {
        if (contact == null) {
            throw new IllegalArgumentException("contact cannot be null");
        }
        String id = contact.getContactId();
        if (contacts.containsKey(id)) {
            throw new IllegalArgumentException("Contact ID already exists: " + id);
        }
        contacts.put(id, contact);
    }

    /** Convenience: create and add a Contact while generating a unique 10-char ID. Returns the ID. */
    public String addContact(String firstName, String lastName, String phone, String address) {
        String id;
        do {
            id = UUID.randomUUID().toString().replace("-", "").substring(0, 10);
        } while (contacts.containsKey(id));
        Contact c = new Contact(id, firstName, lastName, phone, address);
        contacts.put(id, c);
        return id;
    }

    public void deleteContact(String contactId) {
        if (contactId == null || !contacts.containsKey(contactId)) {
            throw new NoSuchElementException("No contact with id " + contactId);
        }
        contacts.remove(contactId);
    }

    private Contact getOrThrow(String contactId) {
        Contact c = contacts.get(contactId);
        if (c == null) {
            throw new NoSuchElementException("No contact with id " + contactId);
        }
        return c;
    }

    public void updateFirstName(String contactId, String firstName) {
        getOrThrow(contactId).setFirstName(firstName);
    }

    public void updateLastName(String contactId, String lastName) {
        getOrThrow(contactId).setLastName(lastName);
    }

    public void updatePhone(String contactId, String phone) {
        getOrThrow(contactId).setPhone(phone);
    }

    public void updateAddress(String contactId, String address) {
        getOrThrow(contactId).setAddress(address);
    }

    /** Optional getter to help with testing/inspection. */
    public Contact getContact(String contactId) {
        return getOrThrow(contactId);
    }

    /** Optional size for testing. */
    public int size() {
        return contacts.size();
    }
}