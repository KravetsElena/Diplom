package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selectors.byXpath;

public class CreditGate {
        private final SelenideElement heading = $$(".heading").findBy(Condition.text("Кредит по данным карты"));
        SelenideElement form = $(".form");
        private SelenideElement cardNumber = form.$("input[placeholder='0000 0000 0000 0000']");
        private final SelenideElement months = form.$("input[placeholder='08']");
        private final SelenideElement years = form.$("input[placeholder='22']");
        private final SelenideElement holder = $(byXpath("/html/body/div[1]/div/form/fieldset/div[3]/span/span[1]/span/span/span[2]/input"));
        private final SelenideElement cvc = form.$("input[placeholder='999']");
        private final SelenideElement button = form.$$("button").findBy(Condition.text("Продолжить"));
        private final SelenideElement notificationOk = $(".notification_status_ok");
        private final SelenideElement notificationError = $(".notification_status_error");

        public void PaymentPage() {
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
        public void checkNotificationError(){
            notificationError.shouldBe(Condition.visible,Duration.ofSeconds(25)).shouldHave(Condition.exactText("Ошибка! Банк отказал в проведении операции."));
        }
        public void checkNotificationInvalidFormat() {
            $(".input__sub").shouldBe(Condition.visible).shouldHave(Condition.exactText("Неверный формат"));
        }
        public void checkNotificationInput(){
            $(".input__sub").shouldBe(Condition.visible).shouldHave(Condition.exactText("Поле обязательно для заполнения"));
        }
        public void checkNotificationWrongDateCard() {
            $(".input__sub").shouldBe(Condition.visible).shouldHave(Condition.exactText("Неверно указан срок действия карты"));
        }
        public void checkNotificationErrorDateCard() {
            $(".input__sub").shouldBe(Condition.visible).shouldHave(Condition.exactText("Истёк срок действия карты"));
        }
        public void checkNotificationErrorLanguageHolder(){
            $(".input__sub").shouldBe(Condition.visible).shouldHave(Condition.exactText("Неверный формат"));
        }
}
