package PhoneBook;

import org.jetbrains.annotations.NotNull;


import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.time.LocalDate;
import java.util.Objects;


public class Contact {

    private String name;
    private String surname;
    private String middleName;
    private final Address address;
    private List<String> phones;
    private LocalDate birthday;
    private List<String> email;

    public Contact(@NotNull String name, @NotNull String surname,
                   @NotNull String middleName, @NotNull Address address,
                   @NotNull String birthday, @NotNull List<String> phones,
                   @NotNull List<String> email) throws IllegalArgumentException {
        setName(name);
        setSurname(surname);
        setMiddleName(middleName);
        this.address = address;
        setPhones(phones);
        setBirthday(birthday);
        setEmail(email);
    }

    public String getName() {
        return name.toLowerCase();
    }

    public String getSurname() {
        return surname.toLowerCase();
    }

    public String getMiddleName() {
        return middleName.toLowerCase();
    }

    public Address getAddress() {
        return address;
    }

    public List<String> getEmail() {
        return Collections.unmodifiableList(email);
    }

    public List<String> getPhones() {
        return Collections.unmodifiableList(phones);
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    /**
     * @param name - имя
     *             Проверяется формат. Слово должн начинаться с большой буквы, и минимум 2 буквы
     */
    public void setName(String name) {
        if (!name.matches("^[A-Z][a-z]+$") && !name.matches("^[А-Я][а-я]+$"))
            throw new IllegalArgumentException("Имя введено неправильно. Пример \"Александр\"");
        else this.name = name;
    }

    /**
     * @param surname - имя
     *                Проверяется формат. Слово должн начинаться с большой буквы, и минимум 2 буквы
     */
    public void setSurname(String surname) {
        if (!surname.matches("^[A-Z][a-z]+$") && !surname.matches("^[А-Я][а-я]+$"))
            throw new IllegalArgumentException("Фамилия введено неправильно. Пример \"Шарипов\"");
        else this.surname = surname;
    }

    /**
     * @param middleName - имя
     *                   Проверяется формат. Слово должн начинаться с большой буквы, и минимум 2 буквы
     */
    public void setMiddleName(String middleName) {
        if (!middleName.matches("^[A-Z][a-z]+$") && !middleName.matches("^[А-Я][а-я]+$"))
            throw new IllegalArgumentException("Отчество введено неправильно. Пример \"Александрович\"");
        else this.middleName = middleName;
    }

    /**
     * @param phones - телефонные номера
     *               Принимается только российские номера
     */
    public void setPhones(List<String> phones) {
        int errors = 0;
        this.phones = new ArrayList<>();
        for (String phone : phones) {
            if (phone.matches("\\+[7]\\d{10}"))
                this.phones.add(phone);
            else if (phone.matches("[8]\\d{10}")) {
                StringBuilder s = new StringBuilder();
                for (var c : phone.chars().skip(1).toArray()) {
                    s.append((char) c);
                }
                this.phones.add("+7" + s);
            } else errors++;
        }
        if (errors > 0) throw new IllegalArgumentException("Some phone numbers were out of format date format!");
    }

    public void setBirthday(String birthday) {
        if (birthday.matches("\\d{2}\\.\\d{2}\\.\\d{4}"))
            this.birthday = LocalDate.parse(birthday, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        else throw new IllegalArgumentException("Incorrect birthday date format!");
    }

    /**
     * @param email - электронные почты
     *              Формат - sss@example.com(ru)
     */
    public void setEmail(List<String> email) {
        int err = 0;
        this.email = new ArrayList<>();
        for (var e : email) {
            if (e.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z.-]+\\.[a-zA-Z]{2,6}$"))
                this.email.add(e);
            else ++err;
        }
        if (err > 0)
            throw new IllegalArgumentException("There were " + err + " incorrect email address format!");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact)) return false;
        Contact contact = (Contact) o;
        return name.equalsIgnoreCase(contact.name) &&
                surname.equalsIgnoreCase(contact.surname) &&
                middleName.equalsIgnoreCase(contact.middleName) &&
                address.equals(contact.address) &&
                birthday.equals(contact.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, middleName, address, birthday);
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", middleName='" + middleName + '\'' +
                ", address='" + address + '\'' +
                ", birthday='" + birthday + '\'' +
                ", phones='" + String.join(",", phones) + "'" +
                ", email='" + String.join(",", email) + "'" +
                '}';
    }

    public String toStringForCsv() {
        return name + "|" + surname + "|" + middleName + "|" + address.getCountry() + "|" +
                address.getCity() + "|" + address.getStreet() + "|" + address.getHome() + "|" +
                birthday.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + "|" +
                String.join(",", phones) + "|" + String.join(",", email);
    }
}
