import java.util.Objects;

public class Contact {
    private final String contactId;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;

    public Contact(String contactId, String firstName, String lastName, String phone, String address) {
        validateId(contactId);
        this.contactId = contactId;

        setFirstName(firstName);
        setLastName(lastName);
        setPhone(phone);
        setAddress(address);
    }

    private void validateId(String contactId) {
        if (contactId == null) {
            throw new IllegalArgumentException("contactId cannot be null");
        }
        if (contactId.length() > 10) {
            throw new IllegalArgumentException("contactId must be at most 10 characters");
        }
    }

    private static void requireNonNullAndMaxLen(String value, String field, int maxLen) {
        if (value == null) {
            throw new IllegalArgumentException(field + " cannot be null");
        }
        if (value.length() > maxLen) {
            throw new IllegalArgumentException(field + " must be at most " + maxLen + " characters");
        }
    }

    public String getContactId() {
        return contactId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        requireNonNullAndMaxLen(firstName, "firstName", 10);
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        requireNonNullAndMaxLen(lastName, "lastName", 10);
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (phone == null) {
            throw new IllegalArgumentException("phone cannot be null");
        }
        if (phone.length() != 10 || !phone.chars().allMatch(Character::isDigit)) {
            throw new IllegalArgumentException("phone must be exactly 10 digits");
        }
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        requireNonNullAndMaxLen(address, "address", 30);
        this.address = address;
    }
}