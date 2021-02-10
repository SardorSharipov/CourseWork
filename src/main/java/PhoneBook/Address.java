package PhoneBook;

import java.util.Objects;

public class Address {
    private String city;
    private final String country;
    private String street;
    private final String home;

    public Address(String city, String street, String home) {
        // Пока что только Россия
        this.country = "Россия";
        setCity(city);
        setStreet(street);
        this.home = home;
    }

    /**
     * @param city - город
     * Входные параметры - Слово должно начинаться с заглавной буквы,
     *                     кол-во букв минимум 2, принимаются только лат и рус буквы
     */
    public void setCity(String city) {
        if (!city.matches("^[A-Z][a-z]+$") && !city.matches("^[А-Я][а-я]+$"))
            throw new IllegalArgumentException("Название города введено неправильно. Пример \"Москва\"");
        else this.city = city;
    }
    /**
     * @param street - улица
     * Входные параметры - Слово должно начинаться с заглавной буквы,
     *                     кол-во букв минимум 2, принимаются только лат и рус буквы
     */
    public void setStreet(String street) {
        if (!street.matches("^[A-Z][a-z]+$") && !street.matches("^[А-Я][а-я]+$"))
            throw new IllegalArgumentException("Название улицы введено неправильно. Пример \"Тверская\"");
        else this.street = street;
    }

    public String getCountry() {
        return country;
    }

    public String getHome() {
        return home;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return city.equalsIgnoreCase(address.city) && country.equalsIgnoreCase(address.country) &&
                street.equalsIgnoreCase(address.street) && home.equalsIgnoreCase(address.home);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, country, street, home);
    }

    @Override
    public String toString() {
        return country + ", " + city + ", " + street + ", " + home;
    }
}
