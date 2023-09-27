package ru.netology.page;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
public class StartPage {
        private final SelenideElement heading = $(".heading_size_l").shouldBe(Condition.text("Путешествие дня"));
        private final SelenideElement buttonBuy = $$("button").findBy(Condition.text("Купить"));
        private final SelenideElement buttonBuyInCredit = $$("button").findBy(Condition.text("Купить в кредит"));


        public void startPage() {
            heading.shouldBe(Condition.visible);
        }

        public PaymentGate buy(){
            buttonBuy.click();
            return new PaymentGate();
        }


        public CreditGate buyInCredit() {
            buttonBuyInCredit.click();
            return new CreditGate();
        }
    }

