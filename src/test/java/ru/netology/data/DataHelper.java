package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.time.LocalDate;

public class DataHelper {

    private static Faker faker = new Faker(new Locale("en"));
    private static Faker fakerRu = new Faker(new Locale("ru"));


    public static cardNumber getApprovedCard() {  /// дейст валид карта
        cardNumber cardNumber = new cardNumber("4444 4444 4444 4441");
        return cardNumber;
    }

    public static cardNumber getDeclinedCard() {   /// недейст валид карта
        cardNumber cardNumber = new cardNumber("4444 4444 4444 4442");
        return cardNumber;
    }

    public static cardNumber getInvalidCard() {    /// невалид карта
        cardNumber cardNumber = new cardNumber("4444 4444 4444 44");
        return cardNumber;
    }

    public static String getValidMonth() {    /// валид месяц
        return LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getInValidMonth() {    /// невалид месяц 1-9
        return LocalDate.now().format(DateTimeFormatter.ofPattern("M"));
    }

    public static String getPreviousMonth() {    ///невалид месяц текущ -1
        return LocalDate.now().minusMonths(1).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getValidYear() {  /// валид год текущ
        return LocalDate.now().format(DateTimeFormatter.ofPattern("YY"));
    }

    public static String getValidYearPlus5() {  /// валид год 23-28
        return LocalDate.now().plusYears(5).format(DateTimeFormatter.ofPattern("YY"));
    }

    public static String getInvalidYearBeforeLimit() {  /// невалид год текущ -1
        return LocalDate.now().minusYears(1).format(DateTimeFormatter.ofPattern("YY"));

    }

    public static String getInvalidYearOverLimit() {  ///невалид год текущ+6
        return LocalDate.now().plusYears(6).format(DateTimeFormatter.ofPattern("YY"));
    }

    public static String getValidName() {     // валид владелец
        String name = faker.name().firstName() + " " + faker.name().lastName();
        return name;
    }

    public static String getValidNameWithDash() {  // валид владелец с дефисом
        String name = faker.name().firstName() + " " + faker.name().lastName() + "-" + faker.name().lastName();
        return name;
    }

    public static String getInvalidNameRu() {  // невалид владелец кирилиц
        String name = faker.name().firstName() + " " + faker.name().lastName();
        return name;
    }

    public static String getIncompleteName() { //невадил владелец имя
        String name = faker.name().firstName();
        return name;
    }

    public static String getValidCVC() {  // валид СVV
        return faker.numerify("###");
    }

    public static String getInvalidCVC() {  // невалид CVV
        return faker.numerify("##");
    }

    @Value

    public static class cardNumber {
        String card;
    }

}

