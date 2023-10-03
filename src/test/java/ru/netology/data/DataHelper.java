package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.time.LocalDate;
import java.util.Random;

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

    public static String getMonth(int month) {    ///  месяц

        return LocalDate.now().minusMonths(month).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getInValidMonth() {    /// невалид месяц 1-9

        var month = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        return month[new Random().nextInt(month.length)];
    }

    public static String getYear( int years) {  /// год
        return LocalDate.now().plusYears(years).format(DateTimeFormatter.ofPattern("YY"));
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
        String name = fakerRu.name().firstName() + " " + fakerRu.name().lastName();
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

