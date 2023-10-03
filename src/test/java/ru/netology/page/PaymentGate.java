package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PaymentGate {

    private final SelenideElement heading = $$(".heading").findBy(Condition.text("Оплата по карте"));
    SelenideElement form = $(".form");
    private SelenideElement cardNumber = form.$("input[placeholder='0000 0000 0000 0000']");
    private final SelenideElement months = form.$("input[placeholder='08']");
    private final SelenideElement years = form.$("input[placeholder='22']");
    private final SelenideElement holder = $$(".input").find(exactText("Владелец")).$(".input__control");
    private final SelenideElement cvc = form.$("input[placeholder='999']");
    private final SelenideElement button = form.$$("button").findBy(Condition.text("Продолжить"));
    private final SelenideElement notificationOk = $(".notification_status_ok");
    private final SelenideElement notificationError = $(".notification_status_error");


    public PaymentGate() {
        heading.shouldBe(Condition.visible);
    }

    public void setCardNumber(DataHelper.cardNumber number) {
        cardNumber.setValue(String.valueOf(number));
    }

    public void setMonth(String month) {
        months.setValue(month);
    }

    public void setYear(String year) {
        years.setValue(year);
    }

    public void setHolder(String hold) {
        holder.setValue(hold);
    }

    public void setCVC(String Cvc) {
        cvc.setValue(Cvc);
    }

    public void buttonContinueClick() {
        button.click();
    }

    public void checkNotificationOk() {
        notificationOk.shouldBe(Condition.visible, Duration.ofSeconds(25)).shouldHave(Condition.exactText("Успешно Операция одобрена Банком."));
    }

    public void checkNotificationError() {
        notificationError.shouldBe(Condition.visible, Duration.ofSeconds(25)).shouldHave(Condition.exactText("Ошибка! Банк отказал в проведении операции."));
    }

    public void checkNotification(String text) {
        $(".input__sub").shouldBe(Condition.visible).shouldHave(Condition.exactText(text));
    }


}
