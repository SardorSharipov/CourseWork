package PhoneBook;

import org.junit.jupiter.api.Assertions;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

class ContactTest {
    Contact contact;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        var phone = new ArrayList<String>();
        phone.add("+79037277667");
        var email = new ArrayList<String>();
        email.add("sardor@mail.ru");
        contact = new Contact("Sardor", "Sharipov", "Ut",
                new Address("Bukhara", "Dilkusho", "3"),
                "07.12.2000", phone, email);
    }

    @org.junit.jupiter.api.Test
    void getName() {
        Assertions.assertEquals("sardor", contact.getName());
        Assertions.assertNotEquals("Sardor", contact.getName());
    }

    @org.junit.jupiter.api.Test
    void getSurname() {
        Assertions.assertEquals("sharipov", contact.getSurname());
        Assertions.assertNotEquals("Sharipov", contact.getSurname());
    }

    @org.junit.jupiter.api.Test
    void getMiddleName() {
        Assertions.assertEquals("ut", contact.getMiddleName());
        Assertions.assertNotEquals("Ut", contact.getMiddleName());
    }

    @org.junit.jupiter.api.Test
    void getAddress() {
        Assertions.assertEquals("Россия, Bukhara, Dilkusho, 3", contact.getAddress().toString());
    }

    @org.junit.jupiter.api.Test
    void getEmail() {
        Assertions.assertEquals("sardor@mail.ru", contact.getEmail().get(0));
        Assertions.assertEquals(1, contact.getEmail().size());
    }

    @org.junit.jupiter.api.Test
    void getPhones() {
        Assertions.assertEquals("+79037277667", contact.getPhones().get(0));
        Assertions.assertEquals(1, contact.getPhones().size());
    }

    @org.junit.jupiter.api.Test
    void getBirthday() {
        Assertions.assertEquals("07.12.2000",
                contact.getBirthday().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
    }

    @org.junit.jupiter.api.Test
    void setCorrectName() {
        contact.setName("Serik");
        Assertions.assertEquals("serik", contact.getName());
    }

    @org.junit.jupiter.api.Test
    void setInCorrectName() throws IllegalArgumentException {
        Assertions.assertThrows(IllegalArgumentException.class, () -> contact.setName("serik"));
    }

    @org.junit.jupiter.api.Test
    void setCorrectSurName() {
        contact.setSurname("Shar");
        Assertions.assertEquals("shar", contact.getSurname());
    }

    @org.junit.jupiter.api.Test
    void setInCorrectSurName() throws IllegalArgumentException {
        Assertions.assertThrows(IllegalArgumentException.class, () -> contact.setSurname("sharipov"));
    }

    @org.junit.jupiter.api.Test
    void setMiddleName() {
        contact.setMiddleName("Rodriges");
        Assertions.assertEquals("rodriges", contact.getMiddleName());
    }

    @org.junit.jupiter.api.Test
    void setPhones() {
        var phone = new ArrayList<String>();
        phone.add("+79037277669");
        phone.add("89037277668");
        contact.setPhones(phone);
        Assertions.assertEquals(2, contact.getPhones().size());
    }

    @org.junit.jupiter.api.Test
    void setIncorrectPhones() {
        var phone = new ArrayList<String>();
        phone.add("+89037277667");
        Assertions.assertThrows(IllegalArgumentException.class, () -> contact.setPhones(phone));
    }


    @org.junit.jupiter.api.Test
    void setBirthday() {
    }

    @org.junit.jupiter.api.Test
    void setEmail() {
        List<String> email = new ArrayList<String>();
        email.add("sardor@mail.ru");
        email.add("sardor@mail.com");
        contact.setEmail(email);
        Assertions.assertEquals(2, contact.getEmail().size());
    }

    @org.junit.jupiter.api.Test
    void setIncorrectEmail() {
        var email = new ArrayList<String>();
        email.add("sardormail.ru");
        Assertions.assertThrows(IllegalArgumentException.class, () -> contact.setEmail(email));
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        Assertions.assertEquals(
                "Contact{name='Sardor', " +
                        "surname='Sharipov', middleName='Ut', " +
                        "address='Россия, Bukhara, Dilkusho, 3'," +
                        " birthday='2000-12-07', phones='+79037277667', " +
                        "email='sardor@mail.ru'}",
                contact.toString());
    }

    @org.junit.jupiter.api.Test
    void toStringForCsv() {
        Assertions.assertEquals("Sardor|Sharipov|Ut|Россия|Bukhara|Dilkusho|3|07.12.2000" +
                "|+79037277667|sardor@mail.ru", contact.toStringForCsv());
    }
}