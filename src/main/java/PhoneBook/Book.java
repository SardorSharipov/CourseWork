package PhoneBook;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Book {

    /**
     * Хранилище данных
     */
    private final String PATH;

    /**
     * Телефонная книжка
     */
    private final Set<Contact> book;

    public Book(String path) throws FileNotFoundException, IllegalArgumentException {
        PATH = path;
        book = new HashSet<>();
        readBook();
    }

    /**
     * @param startWith - слог с которого начинается слово
     * @return лист всех совпадений слога в ФИО
     */
    public List<Contact> searchByName(String startWith) {
        String startWithToLower = startWith.toLowerCase();
        return book.stream()
                .filter(x -> x.getSurname().startsWith(startWithToLower) ||
                        x.getName().startsWith(startWithToLower) ||
                        x.getMiddleName().startsWith(startWithToLower))
                .collect(Collectors.toList());
    }

    /**
     * @param phoneSomeNumbers - пару цифр для поиска совпадений в телефонной книге
     * @return лист всех совпадений в телефонных номерах
     */
    public Map<String, List<Contact>> searchByPhoneAndGroupCity(String phoneSomeNumbers) {
        return book.stream()
                .filter(x -> x.getPhones().stream().anyMatch(y -> y.contains(phoneSomeNumbers)))
                .collect(Collectors.groupingBy(c -> c.getAddress().getCity()));
    }

    public boolean addContact(Contact contact) {
        return book.add(contact);
    }

    public boolean removeContact(Contact contact) {
        return book.remove(contact);
    }

    /**
     * Метод для записи всей телефонной книжки в csv
     */
    public void saveBook() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(PATH, true))) {
            book.stream()
                    .map(Contact::toStringForCsv)// Метод для записи в сsv, чтоб красивая табличка была
                    .forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
        book.clear();
    }

    /**
     * Метод для чтения все с файла data.csv
     *
     * @throws FileNotFoundException - кидается когда нет такого файла
     * @throws IllegalArgumentException - кидается когда ошибки в контактах
     */
    public void readBook() throws FileNotFoundException, IllegalArgumentException {
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split("\\|");
                records.add(Arrays.asList(values));
            }
            new FileWriter(PATH, false).close();
        } catch (IOException e) {
            throw new FileNotFoundException("Файл считать не получилось :(...");
        }
        csvIntoBook(records);
    }

    /**
     * @param records - все строки и столбцы csv файла которые должны преобразовать в список контактов
     * @throws IllegalArgumentException - что то наверно поменяли в файле и контакты обратно несовместимы
     */
    private void csvIntoBook(List<List<String>> records) throws IllegalArgumentException {
        int err = 0;
        for (var contact : records) {
            if (contact.size() < 10) {// 10 столбцов должно быть
                ++err;
                continue;
            }
            List<String> phones = new ArrayList<>();
            List<String> email = new ArrayList<>();
            for (var detail : contact) {
                if (detail.contains("+")) phones.addAll(Arrays.asList(detail.split(",")));
                else if (detail.contains("@")) email.addAll(Arrays.asList(detail.split(",")));
            }
            try {
                Contact pureContact = new Contact(
                        contact.get(0), contact.get(1), contact.get(2),
                        new Address(contact.get(4), contact.get(5), contact.get(6)),
                        contact.get(7), phones, email);
                if (!addContact(pureContact)) ++err;
            } catch (IllegalArgumentException ignored) {
                ++err;
            }

        }
        if (err > 0) throw new IllegalArgumentException("In " + err + "contact(s) caught an exception");
    }

    /**
     * @param separator - разделитель
     * @param book      - книга телефонная
     * @return строку всех контактов с заданным разделителем
     */
    public String stringCollectionOfContacts(String separator, Collection<Contact> book) {
        StringBuilder arrayOfStrings = new StringBuilder();
        for (var c : book) {
            arrayOfStrings.append(c);
            arrayOfStrings.append(separator);
        }
        return arrayOfStrings.toString();
    }

    /**
     * метод преобразует мапу в строку, что все ключи и значения были красиво выведены
     * * @param groups - мапа
     */
    public String stringMapOfContacts(Map<String, List<Contact>> groups) {
        StringBuilder arrayOfStrings = new StringBuilder();
        for (var c : groups.entrySet()) {
            arrayOfStrings.append(c.getKey());
            arrayOfStrings.append(System.lineSeparator()).append("\t");
            arrayOfStrings
                    .append(stringCollectionOfContacts(
                            System.lineSeparator() + "\t", c.getValue())
                    );
            arrayOfStrings.append(System.lineSeparator());
        }
        return arrayOfStrings.toString();
    }

    @Override
    public String toString() {
        return "Book -> " + stringCollectionOfContacts(System.lineSeparator(), book);
    }
}
