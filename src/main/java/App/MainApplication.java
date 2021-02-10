package App;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import PhoneBook.Address;
import PhoneBook.Book;
import PhoneBook.Contact;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class MainApplication {

    private static final Logger rootLogger = LogManager.getRootLogger();
    private static final Logger bookLogger = LogManager.getLogger(Book.class);
    private final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        //BasicConfigurator.configure();
        Book book = null;
        try {
            book = new Book("src/main/resources/data.csv");
            integrationMenu(book);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());// Ошибки в контактах, продолжить можно
            bookLogger.fatal("error message: " + e.getMessage());
            integrationMenu(book);
        } catch (IOException e) {
            bookLogger.fatal("error message: " + e.getMessage());
            System.out.println(e.getMessage());
        } finally {
            assert book != null;
            book.saveBook();
            LogManager.shutdown();
        }

    }

    private static void integrationMenu(Book book) {
        menu();
        int switchIndex;
        do {
            System.out.println("Введите цифру с диапозона [0-5] для выполнения действия");
            switchIndex = scanner.nextInt();
        } while (switchIndex < 0 || switchIndex > 5);
        switchMenu(switchIndex, book);
    }

    private static void switchMenu(int number, Book book) {
        String answer;
        switch (number) {
            case 0:
                return;
            case 1:
                System.out.println("Введите сочетание букв по которому хотите сделать поиск ФИО");
                answer = scanner.next();
                var listOfContacts = book.searchByName(answer);
                if (listOfContacts.size() == 0) System.out.println("Контактов не нашлось");
                else System.out.println(book.stringCollectionOfContacts(System.lineSeparator(), listOfContacts));
                integrationMenu(book);
                break;
            case 2:
                System.out.println("Введите сочетание цифр по которому хотите сделать поиск по телфонному номеру");
                answer = scanner.next();
                var mapOfContacts = book.searchByPhoneAndGroupCity(answer);
                if (mapOfContacts.size() == 0) System.out.println("Контактов не нашлось");
                else System.out.println(book.stringMapOfContacts(mapOfContacts));
                integrationMenu(book);
                break;
            case 3:
                deleteContact(book);
                integrationMenu(book);
                break;
            case 4:
                Contact contact = fillContact();
                bookLogger.info("BookLogger: input code");
                if (book.addContact(contact))
                    bookLogger.info("Contact is added " + contact);
                else
                    bookLogger.info("Contact is not added " + contact);
                integrationMenu(book);
                break;
            case 5:
                System.out.println(book);
                bookLogger.info("BookLogger: input code");
                bookLogger.info("code" + book);
                integrationMenu(book);
                break;
        }
    }

    /**
     * Метод удаляет контакт выбранныц пользователем
     * @param book - телефонная книга
     */
    private static void deleteContact(Book book) {
        System.out.println("Чтобы удалить контакт, " +
                "введите сочетание букв по которому хотите сделать поиск ФИО и найти нужный контакт");
        bookLogger.info("BookLogger: input code");
        bookLogger.info("code: " + "Чтобы удалить контакт, " +
                "введите сочетание букв по которому хотите сделать поиск ФИО и найти нужный контакт");

        String answer = scanner.next();
        rootLogger.info("RootLogger: input code");
        rootLogger.info("code: " + answer);

        var listOfContacts = book.searchByName(answer);
        bookLogger.info("BookLogger: input code");
        for (int i = 0; i < listOfContacts.size(); i++) {
            System.out.println(i + " " + listOfContacts.get(i));
            bookLogger.info("code: " + i + " " + listOfContacts.get(i));
        }
        if (listOfContacts.size() == 0) {
            System.out.println("Нет совпадений");
            return;
        }
        int contactIndex;
        do {
            bookLogger.info("BookLogger: input code");
            System.out.printf("Выберите индекс (от [0-%d]) нужного контакта\n", listOfContacts.size() - 1);
            bookLogger.info("code: " + "Выберите индекс (от [0-" + (listOfContacts.size() - 1) + "]) нужного контакта");

            rootLogger.info("RootLogger: input code");
            contactIndex = scanner.nextInt();
            rootLogger.info("code: " + answer);
        } while (contactIndex < 0 || contactIndex >= listOfContacts.size());
        bookLogger.info("BookLogger: input code");
        if (book.removeContact(listOfContacts.get(contactIndex)))
            bookLogger.info("Contact is removed " + listOfContacts.get(contactIndex));
        else
            bookLogger.info("Contact is not removed " + listOfContacts.get(contactIndex));
    }

    /**
     * Метод заполняет контакт
     * @return Новый контакт
     */
    private static Contact fillContact() {

        System.out.println("Если не будете заполнять как в инструкции, " +
                "в конце все равно выйдет ошибка и придется заново заполнять");

        System.out.println("Введите имя. Пример \"Александр Alex\"");
        bookLogger.info("BookLogger: input code");
        bookLogger.info("code: " + "Введите имя. Пример \"Александр Alex\"");
        String name = scanner.next();
        rootLogger.info("RootLogger: input code");
        rootLogger.info("code: " + name);

        System.out.println("Введите фамилию. Пример \"Шарипов Hazard\"");
        bookLogger.info("BookLogger: input code");
        bookLogger.info("code: " + "Введите фамилию. Пример \"Шарипов Hazard\"");
        String surname = scanner.next();
        rootLogger.info("RootLogger: input code");
        rootLogger.info("code: " + surname);

        System.out.println("Введите отчество. Пример \"Александрович Sue\"");
        bookLogger.info("BookLogger: input code");
        bookLogger.info("code: " + "Введите отчество. Пример \"Александрович Sue\"");
        String middleName = scanner.next();
        rootLogger.info("RootLogger: input code");
        rootLogger.info("code: " + middleName);

        System.out.println("Введите город. Пример \"Москва\"");
        bookLogger.info("BookLogger: input code");
        bookLogger.info("code: " + "Введите город. Пример \"Москва\"");
        String city = scanner.next();
        rootLogger.info("RootLogger: input code");
        rootLogger.info("code: " + city);

        System.out.println("Введите улицу. Пример \"Тверская\"");
        bookLogger.info("BookLogger: input code");
        bookLogger.info("code: " + "Введите улицу. Пример \"Тверская\"");
        String street = scanner.next();
        rootLogger.info("RootLogger: input code");
        rootLogger.info("code: " + street);

        System.out.println("Введите дом. Пример \"3k4\"");
        bookLogger.info("BookLogger: input code");
        bookLogger.info("code: " + "Введите дом. Пример \"3k4\"");
        String home = scanner.next();
        rootLogger.info("RootLogger: input code");
        rootLogger.info("code: " + home);

        System.out.println("Введите birthday. Пример \"06.11.2000 -> дд.мм.гггг\"");
        bookLogger.info("BookLogger: input code");
        bookLogger.info("code: " + "Введите birthday. Пример \"06.11.2000 -> дд.мм.гггг\"");
        String birthday = scanner.next();
        rootLogger.info("RootLogger: input code");
        rootLogger.info("code: " + birthday);

        System.out.println("Введите номера телефонов через запятую (без пробела). Пример \"+79037277667,89034567667\"");
        bookLogger.info("BookLogger: input code");
        bookLogger.info("code: " + "Введите номера телефонов через запятую (без пробела). " +
                "Пример \"+79037277667,89034567667\"");
        List<String> phones = Arrays.asList(scanner.next().split(",").clone());
        rootLogger.info("RootLogger: input code");
        rootLogger.info("code: " + String.join(" ", phones));

        System.out.println("Введите email через запятую (без пробела)(только латинские буквы). Пример \"sardorsharipov2000@mail.ru,sss@example.com\"");
        bookLogger.info("BookLogger: input code");
        bookLogger.info("code: " + "Введите email через запятую (без пробела)(только латинские буквы). " +
                "Пример \"sardorsharipov2000@mail.ru sss@example.com\"");
        List<String> email = Arrays.asList(scanner.next().split(",").clone());
        rootLogger.info("RootLogger: input code");
        rootLogger.info("code: " + String.join(" ", email));
        Contact contact;
        try {
            Address address = new Address(city, street, home);
            contact = new Contact(name, surname, middleName, address, birthday, phones, email);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            bookLogger.fatal("error message: " + e.getMessage());
            System.out.println("Заново заполняем");
            contact = fillContact();
        }
        return contact;
    }

    private static void menu() {
        String mes = "Нижеперечисленные действия доступны над телефонной книжкой:\n" +
                "1. Поиск по ФИО.\n" +
                "2. Поиск по телефонному номеру.\n" +
                "3. Удаление контакта.\n" +
                "4. Добавления контакта.\n" +
                "5. Вывод всех контактов.\n" +
                "0. Выход. Сохраняет всю телефонную книгу.\n";
        System.out.println(mes);
        bookLogger.info("BookLogger: input code");
        bookLogger.info("code: " + mes);
    }
}
