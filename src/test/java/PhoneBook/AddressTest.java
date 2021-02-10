package PhoneBook;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AddressTest {

    Address address;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        address = new Address("Bukhara", "Dil", "3");
    }

    @Test
    void setCity() {
        address.setCity("Moscow");
        Assertions.assertEquals("Moscow", address.getCity());
    }

    @Test
    void setIncorrectCity() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> address.setCity("moscow"));
    }


    @Test
    void setStreet() {
        address.setStreet("Тверская");
        Assertions.assertEquals("Тверская", address.getStreet());
    }

    @Test
    void setIncorrectStreet() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> address.setStreet("тв"));
    }

    @Test
    void getCountry() {
        Assertions.assertEquals("Россия", address.getCountry());
    }

    @Test
    void getHome() {
        Assertions.assertEquals("3", address.getHome());
    }

    @Test
    void getStreet() {
        Assertions.assertEquals("Dil", address.getStreet());
    }

    @Test
    void getCity() {
        Assertions.assertEquals("Bukhara", address.getCity());
    }

    @Test
    void testToString() {
        Assertions.assertEquals("Россия, Bukhara, Dil, 3",address.toString());
    }
}