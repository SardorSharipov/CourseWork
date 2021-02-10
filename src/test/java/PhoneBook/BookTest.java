package PhoneBook;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


class BookTest {

    Book book;

    @BeforeEach
    void setUp() throws FileNotFoundException {
        book = new Book("src/test/java/resources/testWithoutChangingData.csv");
    }

    @AfterEach
    void setDown() {
        book.saveBook();
    }

    @Test
    void searchByName() {
        var contacts = book.searchByName("ша");
        Assertions.assertEquals(1, contacts.size());
        var exp = new ArrayList<>();
        List<String> phones = new ArrayList<>();
        phones.add("+79037207667");
        phones.add("+79037226776");
        List<String> email = new ArrayList<>();
        email.add("serik@example.com");
        email.add("sardor@example.ru");
        exp.add(new Contact("Шарипов", "Сардор", "Уткирович"
                , new Address("Москва", "Тверская", "3к4"), "07.12.2000", phones, email));
        Assertions.assertEquals(exp.get(0), contacts.get(0));
    }

    @Test
    void searchByPhoneAndGroupCity() {
        var contacts = book.searchByPhoneAndGroupCity("207");
        Assertions.assertEquals(1, contacts.size());
        var exp = new ArrayList<>();
        List<String> phones = new ArrayList<>();
        phones.add("+79037207667");
        phones.add("+79037226776");
        List<String> email = new ArrayList<>();
        email.add("serik@example.com");
        email.add("sardor@example.ru");
        exp.add(new Contact("Шарипов", "Сардор", "Уткирович"
                , new Address("Москва", "Тверская", "3к4"), "07.12.2000", phones, email));
        Assertions.assertEquals(exp.get(0), contacts.get("Москва").get(0));
    }

    @Test
    void stringCollectionOfContacts() {
        var act = book.stringCollectionOfContacts(System.lineSeparator(), book.searchByName("са"));
        var exp = "Contact{name='Штырба', surname='Самуил', middleName='Акрабатович', address='Россия, " +
                "Москва, Коломенская, 1к11', birthday='1121-12-09', phones='+79037121234,+78932426776', email='shtyrba@example.com,samuil@example.ru'}" + System.lineSeparator() +
                "Contact{name='Шарипов', surname='Сардор', middleName='Уткирович', address='Россия, " +
                "Москва, Тверская, 3к4', birthday='2000-12-07', phones='+79037207667,+79037226776', email='serik@example.com,sardor@example.ru'}" + System.lineSeparator();

        Assertions.assertEquals(exp, act);
    }

    @Test
    void testToString() {
        var exp = "Book -> Contact{name='Штырба', surname='Самуил', middleName='Акрабатович', address='Россия, " +
                "Москва, Коломенская, 1к11', birthday='1121-12-09', phones='+79037121234,+78932426776', email='shtyrba@example.com,samuil@example.ru'}" + System.lineSeparator() +
                "Contact{name='Рамон', surname='Антонио', middleName='Залипинос', address='Россия, " +
                "Москва, Арбатская, 2к8', birthday='2000-11-08', phones='+79037201234,+78957226776', email='antonio@example.com,ramon@example.ru'}"+ System.lineSeparator() +
                "Contact{name='Шарипов', surname='Сардор', middleName='Уткирович', address='Россия, " +
                "Москва, Тверская, 3к4', birthday='2000-12-07', phones='+79037207667,+79037226776', email='serik@example.com,sardor@example.ru'}"+ System.lineSeparator() +
                "Contact{name='Solo', surname='Ramzess', middleName='Pasha', address='Россия, " +
                "Москва, Павелецкая, 11', birthday='1121-10-09', phones='+79037121211,+78932426326', email='solo@example.com,ramzess666@example.ru'}"+ System.lineSeparator();
        Assertions.assertEquals(exp, book.toString());
    }
}