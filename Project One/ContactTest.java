import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContactTest {

    @Test
    void constructor_validatesAllFields() {
        Contact c = new Contact("abc123", "John", "Doe", "1234567890", "123 Main Street");
        assertEquals("abc123", c.getContactId());
        assertEquals("John", c.getFirstName());
        assertEquals("Doe", c.getLastName());
        assertEquals("1234567890", c.getPhone());
        assertEquals("123 Main Street", c.getAddress());
    }

    @Test
    void id_cannotBeNullOrLongerThan10() {
        assertThrows(IllegalArgumentException.class, () -> new Contact(null, "A", "B", "1234567890", "Addr"));
        assertThrows(IllegalArgumentException.class, () -> new Contact("this-id-is-too-long", "A", "B", "1234567890", "Addr"));
    }

    @Test
    void firstName_constraints() {
        Contact c = new Contact("id1", "John", "Doe", "1234567890", "Addr");
        assertThrows(IllegalArgumentException.class, () -> c.setFirstName(null));
        assertThrows(IllegalArgumentException.class, () -> c.setFirstName("abcdefghijkl")); // 12 chars > 10
    }

    @Test
    void lastName_constraints() {
        Contact c = new Contact("id1", "John", "Doe", "1234567890", "Addr");
        assertThrows(IllegalArgumentException.class, () -> c.setLastName(null));
        assertThrows(IllegalArgumentException.class, () -> c.setLastName("abcdefghijkl"));
    }

    @Test
    void phone_mustBeExactly10Digits() {
        Contact c = new Contact("id1", "John", "Doe", "1234567890", "Addr");
        assertThrows(IllegalArgumentException.class, () -> c.setPhone(null));
        assertThrows(IllegalArgumentException.class, () -> c.setPhone("123456789"));   // 9 digits
        assertThrows(IllegalArgumentException.class, () -> c.setPhone("12345678901")); // 11 digits
        assertThrows(IllegalArgumentException.class, () -> c.setPhone("12345abc90"));  // non-digit
    }

    @Test
    void address_constraints() {
        Contact c = new Contact("id1", "John", "Doe", "1234567890", "Addr");
        assertThrows(IllegalArgumentException.class, () -> c.setAddress(null));
        assertThrows(IllegalArgumentException.class, () -> c.setAddress("a".repeat(31))); // 31 > 30
    }

    @Test
    void id_isImmutable() {
        Contact c = new Contact("abc123", "John", "Doe", "1234567890", "Addr");
        // There is no setter: compile-time guarantee; just assert value remains
        assertEquals("abc123", c.getContactId());
    }
}