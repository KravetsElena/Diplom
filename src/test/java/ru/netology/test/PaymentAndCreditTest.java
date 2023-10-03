package ru.netology.test;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import org.junit.jupiter.api.*;
import ru.netology.page.StartPage;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.SQLHelper.cleanDatabase;

public class PaymentAndCreditTest {
    @BeforeEach
    void setup() {
        open("http://localhost:8080");
    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @AfterEach
    public void teardrop() {
        cleanDatabase();
    }


    @Test
    @Order(1-1)
    @DisplayName("Should All Fields Valid with Approved card PaymentGate")
    void ShouldAllFieldsValidApprovedCardPaymentGate() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentGate = start.buy();

        paymentGate.setCardNumber(DataHelper.getApprovedCard());
        paymentGate.setMonth(DataHelper.getMonth(0));
        paymentGate.setYear(DataHelper.getYear(0));
        paymentGate.setHolder(DataHelper.getValidName());
        paymentGate.setCVC(DataHelper.getValidCVC());
        paymentGate.buttonContinueClick();
        paymentGate.checkNotificationOk();
        SQLHelper.expectedPaymentStatus("APPROVED");
    }

    @Test
    @Order(1-2)
    @DisplayName("Should Holder field with hyphen with Approved card and rest fields valid PaymentGate")
    void ShouldHolderFieldHyphenApprovedCardAndRestFieldsValidPaymentGate() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getApprovedCard());
        paymentPage.setMonth(DataHelper.getMonth(0));
        paymentPage.setYear(DataHelper.getYear(0));
        paymentPage.setHolder(DataHelper.getValidNameWithDash());
        paymentPage.setCVC(DataHelper.getValidCVC());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationOk();
        SQLHelper.expectedPaymentStatus("APPROVED");
    }

    @Test
    @Order(1-3)
    @DisplayName("Should field Year +5 to current with Approved card and rest fields valid PaymentGate")
    void ShouldFieldYearUp5ToCurrentApprovedCardAndRestFieldsValidPaymentGate() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getApprovedCard());
        paymentPage.setMonth(DataHelper.getMonth(0));
        paymentPage.setYear(DataHelper.getYear(5));
        paymentPage.setHolder(DataHelper.getValidName());
        paymentPage.setCVC(DataHelper.getValidCVC());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationOk();
        SQLHelper.expectedPaymentStatus("APPROVED");

    }

    @Test  // баг
    @Order(1-4)
    @DisplayName("Should DECLINED Card and all fields valid PaymentGate")
    void ShouldDeclinedCardAndAllFieldsValidPaymentGate() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentGate = start.buy();

        paymentGate.setCardNumber(DataHelper.getDeclinedCard());
        paymentGate.setMonth(DataHelper.getMonth(0));
        paymentGate.setYear(DataHelper.getYear(0));
        paymentGate.setHolder(DataHelper.getValidName());
        paymentGate.setCVC(DataHelper.getValidCVC());
        paymentGate.buttonContinueClick();
        paymentGate.checkNotificationError();
        SQLHelper.expectedPaymentStatus("DECLINED");
    }

    @Test
    @Order(2-1)
    @DisplayName("Should Incomplete field Card and rest fields valid PaymentGate")
    void ShouldIncompleteFieldCardAndRestFieldsValidPaymentGate() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentGate = start.buy();

        paymentGate.setCardNumber(DataHelper.getInvalidCard());
        paymentGate.setMonth(DataHelper.getMonth(0));
        paymentGate.setYear(DataHelper.getYear(0));
        paymentGate.setHolder(DataHelper.getValidName());
        paymentGate.setCVC(DataHelper.getValidCVC());
        paymentGate.buttonContinueClick();
        paymentGate.checkNotification("Неверный формат");
    }

    @Test // баг
    @Order(2-2)
    @DisplayName("Should Field Holder in Cyrillic with Approved card and rest fields valid PaymentGate")
    void ShouldFieldHolderCyrillicWithApprovedCardAndRestFieldsValidPaymentGate() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentGate = start.buy();

        paymentGate.setCardNumber(DataHelper.getApprovedCard());
        paymentGate.setMonth(DataHelper.getMonth(0));
        paymentGate.setYear(DataHelper.getYear(0));
        paymentGate.setHolder(DataHelper.getInvalidNameRu());
        paymentGate.setCVC(DataHelper.getValidCVC());
        paymentGate.buttonContinueClick();
        paymentGate.checkNotification("Неверный формат");
    }

    @Test  // баг
    @Order(2-3)
    @DisplayName("Should Field Holder Incomplete Name with Approved card and rest fields valid PaymentGate")
    void ShouldFieldHolderIncompleteNameWithApprovedCardAndRestFieldsValidPaymentGate() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentGate = start.buy();

        paymentGate.setCardNumber(DataHelper.getApprovedCard());
        paymentGate.setMonth(DataHelper.getMonth(0));
        paymentGate.setYear(DataHelper.getYear(0));
        paymentGate.setHolder(DataHelper.getIncompleteName());
        paymentGate.setCVC(DataHelper.getValidCVC());
        paymentGate.buttonContinueClick();
        paymentGate.checkNotification("Неверный формат");
    }

    @Test
    @Order(2-4)
    @DisplayName("Should Field Month one symbol with Approved card and rest fields valid PaymentGate")
    void ShouldFieldMonthOneSymbolWithApprovedCardAndRestFieldsValidPaymentGate() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentGate = start.buy();

        paymentGate.setCardNumber(DataHelper.getApprovedCard());
        paymentGate.setMonth(DataHelper.getInValidMonth());
        paymentGate.setYear(DataHelper.getYear(0));
        paymentGate.setHolder(DataHelper.getValidName());
        paymentGate.setCVC(DataHelper.getValidCVC());
        paymentGate.buttonContinueClick();
        paymentGate.checkNotification("Неверный формат");
    }

    @Test
    @Order(2-5)
    @DisplayName("Should Field year Before Limit with Approved card and rest fields valid PaymentGate")
    void ShouldFieldYearBeforeLimitWithApprovedCardAndRestFieldsValidPaymentGate() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentGate = start.buy();

        paymentGate.setCardNumber(DataHelper.getApprovedCard());
        paymentGate.setMonth(DataHelper.getMonth(0));
        paymentGate.setYear(DataHelper.getYear(-1));
        paymentGate.setHolder(DataHelper.getValidName());
        paymentGate.setCVC(DataHelper.getValidCVC());
        paymentGate.buttonContinueClick();
        paymentGate.checkNotification("Истёк срок действия карты");
    }

    @Test
    @Order(2-6)
    @DisplayName("Should Field year Over Limit with Approved card and rest fields valid PaymentGate")
    void ShouldFieldYearOverLimitWithApprovedCardAndRestFieldsValidPaymentGate() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentGate = start.buy();

        paymentGate.setCardNumber(DataHelper.getApprovedCard());
        paymentGate.setMonth(DataHelper.getMonth(0));
        paymentGate.setYear(DataHelper.getYear(6));
        paymentGate.setHolder(DataHelper.getValidName());
        paymentGate.setCVC(DataHelper.getValidCVC());
        paymentGate.buttonContinueClick();
        paymentGate.checkNotification("Неверно указан срок действия карты");
    }

    @Test
    @Order(2-7)
    @DisplayName("Should Field Invalid CVC with Approved card and rest fields valid PaymentGate")
    void ShouldFieldInvalidCVCWithApprovedCardAndRestFieldsValidPaymentGate() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentGate = start.buy();

        paymentGate.setCardNumber(DataHelper.getApprovedCard());
        paymentGate.setMonth(DataHelper.getMonth(0));
        paymentGate.setYear(DataHelper.getYear(0));
        paymentGate.setHolder(DataHelper.getValidName());
        paymentGate.setCVC(DataHelper.getInvalidCVC());
        paymentGate.buttonContinueClick();
        paymentGate.checkNotification("Неверный формат");
    }
    @Test
    @Order(2-8)
    @DisplayName("Should Field Invalid Month with Approved card and rest fields valid PaymentGate")
    void ShouldFieldInvalidMonthWithApprovedCardAndRestFieldsValidPaymentGate() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentGate = start.buy();

        paymentGate.setCardNumber(DataHelper.getApprovedCard());
        paymentGate.setMonth(DataHelper.getMonth(1));
        paymentGate.setYear(DataHelper.getYear(0));
        paymentGate.setHolder(DataHelper.getValidName());
        paymentGate.setCVC(DataHelper.getValidCVC());
        paymentGate.buttonContinueClick();
        paymentGate.checkNotification("Неверно указан срок действия карты");
    }

    @Test
    @Order(2-9)
    @DisplayName("Should Field Card null rest fields valid PaymentGate")
    void ShouldFieldCardNullRestFieldsValidPaymentGate() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentGate = start.buy();

        paymentGate.setMonth(DataHelper.getMonth(0));
        paymentGate.setYear(DataHelper.getYear(0));
        paymentGate.setHolder(DataHelper.getValidName());
        paymentGate.setCVC(DataHelper.getValidCVC());
        paymentGate.buttonContinueClick();
        paymentGate.checkNotification("Неверный формат");
    }

    @Test
    @Order(2-10)
    @DisplayName("Should Field Month null with Approved rest fields valid PaymentGate")
    void ShouldFieldMonthNullWithApprovedRestFieldsValidPaymentGate() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentGate = start.buy();

        paymentGate.setCardNumber(DataHelper.getApprovedCard());
        paymentGate.setYear(DataHelper.getYear(0));
        paymentGate.setHolder(DataHelper.getValidName());
        paymentGate.setCVC(DataHelper.getValidCVC());
        paymentGate.buttonContinueClick();
        paymentGate.checkNotification("Неверный формат");
    }

    @Test
    @Order(2-11)
    @DisplayName("Should Field Year null with Approved rest fields valid PaymentGate")
    void ShouldFieldYearNullWithApprovedRestFieldsValidPaymentGate() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentGate = start.buy();

        paymentGate.setCardNumber(DataHelper.getApprovedCard());
        paymentGate.setMonth(DataHelper.getMonth(0));
        paymentGate.setHolder(DataHelper.getValidName());
        paymentGate.setCVC(DataHelper.getValidCVC());
        paymentGate.buttonContinueClick();
        paymentGate.checkNotification("Неверный формат");
    }

    @Test
    @Order(2-12)
    @DisplayName("Should Field Holder null with Approved rest fields valid PaymentGate")
    void ShouldFieldHolderNullWithApprovedRestFieldsValidPaymentGate() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentGate = start.buy();

        paymentGate.setCardNumber(DataHelper.getApprovedCard());
        paymentGate.setMonth(DataHelper.getMonth(0));
        paymentGate.setYear(DataHelper.getYear(0));
        paymentGate.setCVC(DataHelper.getValidCVC());
        paymentGate.buttonContinueClick();
        paymentGate.checkNotification("Поле обязательно для заполнения");
    }

    @Test
    @Order(2-13)
    @DisplayName("Should Field CVC null with Approved rest fields valid PaymentGate")
    void ShouldFieldCVCNullWithApprovedRestFieldsValidPaymentGate() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentGate = start.buy();

        paymentGate.setCardNumber(DataHelper.getApprovedCard());
        paymentGate.setMonth(DataHelper.getMonth(0));
        paymentGate.setYear(DataHelper.getYear(0));
        paymentGate.setHolder(DataHelper.getValidName());
        paymentGate.buttonContinueClick();
        paymentGate.checkNotification("Поле обязательно для заполнения");
    }

    @Test
    @Order(3-1)
    @DisplayName("Should All Fields Valid with Approved card CreditGate")
    void ShouldAllFieldsValidWithApprovedCardCreditGate() {
        StartPage start = new StartPage();
        start.startPage();
        var creditGate = start.buyInCredit();

        creditGate.setCardNumber(DataHelper.getApprovedCard());
        creditGate.setMonth(DataHelper.getMonth(0));
        creditGate.setYear(DataHelper.getYear(0));
        creditGate.setHolder(DataHelper.getValidName());
        creditGate.setCVC(DataHelper.getValidCVC());
        creditGate.buttonContinueClick();
        creditGate.checkNotificationOk();
        SQLHelper.expectedCreditStatus("APPROVED");
    }

    @Test  ///???????
    @Order(3-2)
    @DisplayName("Should Holder field with hyphen with Approved card and rest fields valid CreditGate")
    void ShouldHolderFieldHyphenApprovedCardAndRestFieldsValidCreditGate() {
        StartPage start = new StartPage();
        start.startPage();
        var creditGate = start.buyInCredit();

        creditGate.setCardNumber(DataHelper.getApprovedCard());
        creditGate.setMonth(DataHelper.getMonth(0));
        creditGate.setYear(DataHelper.getYear(0));
        creditGate.setHolder(DataHelper.getValidNameWithDash());
        creditGate.setCVC(DataHelper.getValidCVC());
        creditGate.buttonContinueClick();
        creditGate.checkNotificationOk();
        SQLHelper.expectedCreditStatus("APPROVED");
    }

    @Test
    @Order(3-3)
    @DisplayName("Should field Year +5 to current with Approved card and rest fields valid CreditGate")
    void ShouldFieldYearUp5ToCurrentApprovedCardAndRestFieldsValidCreditGate() {
        StartPage start = new StartPage();
        start.startPage();
        var creditGate = start.buyInCredit();

        creditGate.setCardNumber(DataHelper.getApprovedCard());
        creditGate.setMonth(DataHelper.getMonth(0));
        creditGate.setYear(DataHelper.getYear(5));
        creditGate.setHolder(DataHelper.getValidName());
        creditGate.setCVC(DataHelper.getValidCVC());
        creditGate.buttonContinueClick();
        creditGate.checkNotificationOk();
        SQLHelper.expectedCreditStatus("APPROVED");

    }

    @Test  // баг
    @Order(3-4)
    @DisplayName("Should DECLINED Card and all fields valid CreditGate")
    void ShouldDeclinedCardAndAllFieldsValidCreditGate() {
        StartPage start = new StartPage();
        start.startPage();
        var creditGate = start.buyInCredit();

        creditGate.setCardNumber(DataHelper.getDeclinedCard());
        creditGate.setMonth(DataHelper.getMonth(0));
        creditGate.setYear(DataHelper.getYear(0));
        creditGate.setHolder(DataHelper.getValidName());
        creditGate.setCVC(DataHelper.getValidCVC());
        creditGate.buttonContinueClick();
        creditGate.checkNotificationError();
        SQLHelper.expectedCreditStatus("DECLINED");
    }

    @Test
    @Order(4-1)
    @DisplayName("Should Incomplete field Card and rest fields valid CreditGate")
    void ShouldIncompleteFieldCardAndRestFieldsValidCreditGate() {
        StartPage start = new StartPage();
        start.startPage();
        var creditGate = start.buyInCredit();

        creditGate.setCardNumber(DataHelper.getInvalidCard());
        creditGate.setMonth(DataHelper.getMonth(0));
        creditGate.setYear(DataHelper.getYear(0));
        creditGate.setHolder(DataHelper.getValidName());
        creditGate.setCVC(DataHelper.getValidCVC());
        creditGate.buttonContinueClick();
        creditGate.checkNotification("Неверный формат");
    }

    @Test   // баг
    @Order(4-2)
    @DisplayName("Should Field Holder in Cyrillic with Approved card and rest fields valid CreditGate")
    void ShouldFieldHolderCyrillicWithApprovedCardAndRestFieldsValidCreditGate() {
        StartPage start = new StartPage();
        start.startPage();
        var creditGate = start.buyInCredit();

        creditGate.setCardNumber(DataHelper.getApprovedCard());
        creditGate.setMonth(DataHelper.getMonth(0));
        creditGate.setYear(DataHelper.getYear(0));
        creditGate.setHolder(DataHelper.getInvalidNameRu());
        creditGate.setCVC(DataHelper.getValidCVC());
        creditGate.buttonContinueClick();
        creditGate.checkNotification("Неверный формат");
    }

    @Test  // баг
    @Order(4-3)
    @DisplayName("Should Field Holder Incomplete Name with Approved card and rest fields valid CreditGate")
    void ShouldFieldHolderIncompleteNameWithApprovedCardAndRestFieldsValidCreditGate() {
        StartPage start = new StartPage();
        start.startPage();
        var creditGate = start.buyInCredit();

        creditGate.setCardNumber(DataHelper.getApprovedCard());
        creditGate.setMonth(DataHelper.getMonth(0));
        creditGate.setYear(DataHelper.getYear(0));
        creditGate.setHolder(DataHelper.getIncompleteName());
        creditGate.setCVC(DataHelper.getValidCVC());
        creditGate.buttonContinueClick();
        creditGate.checkNotification("Неверный формат");
    }

    @Test
    @Order(4-4)
    @DisplayName("Should Field Month one symbol with Approved card and rest fields valid CreditGate")
    void ShouldFieldMonthOneSymbolWithApprovedCardAndRestFieldsValidCreditGate() {
        StartPage start = new StartPage();
        start.startPage();
        var creditGate = start.buyInCredit();

        creditGate.setCardNumber(DataHelper.getApprovedCard());
        creditGate.setMonth(DataHelper.getInValidMonth());
        creditGate.setYear(DataHelper.getYear(0));
        creditGate.setHolder(DataHelper.getValidName());
        creditGate.setCVC(DataHelper.getValidCVC());
        creditGate.buttonContinueClick();
        creditGate.checkNotification("Неверный формат");
    }

    @Test
    @Order(4-5)
    @DisplayName("Should Field year Before Limit with Approved card and rest fields valid CreditGate")
    void ShouldFieldYearBeforeLimitWithApprovedCardAndRestFieldsValidCreditGate() {
        StartPage start = new StartPage();
        start.startPage();
        var creditGate = start.buyInCredit();

        creditGate.setCardNumber(DataHelper.getApprovedCard());
        creditGate.setMonth(DataHelper.getMonth(0));
        creditGate.setYear(DataHelper.getYear(-1));
        creditGate.setHolder(DataHelper.getValidName());
        creditGate.setCVC(DataHelper.getValidCVC());
        creditGate.buttonContinueClick();
        creditGate.checkNotification("Истёк срок действия карты");
    }

    @Test
    @Order(4-6)
    @DisplayName("Should Field year Over Limit with Approved card and rest fields valid CreditGate")
    void ShouldFieldYearOverLimitWithApprovedCardAndRestFieldsValidCreditGate() {
        StartPage start = new StartPage();
        start.startPage();
        var creditGate = start.buyInCredit();

        creditGate.setCardNumber(DataHelper.getApprovedCard());
        creditGate.setMonth(DataHelper.getMonth(0));
        creditGate.setYear(DataHelper.getYear(6));
        creditGate.setHolder(DataHelper.getValidName());
        creditGate.setCVC(DataHelper.getValidCVC());
        creditGate.buttonContinueClick();
        creditGate.checkNotification("Неверно указан срок действия карты");
    }

    @Test
    @Order(4-7)
    @DisplayName("Should Field Invalid CVC with Approved card and rest fields valid CreditGate")
    void ShouldFieldInvalidCVCWithApprovedCardAndRestFieldsValidCreditGate() {
        StartPage start = new StartPage();
        start.startPage();
        var creditGate = start.buyInCredit();

        creditGate.setCardNumber(DataHelper.getApprovedCard());
        creditGate.setMonth(DataHelper.getMonth(0));
        creditGate.setYear(DataHelper.getYear(0));
        creditGate.setHolder(DataHelper.getValidName());
        creditGate.setCVC(DataHelper.getInvalidCVC());
        creditGate.buttonContinueClick();
        creditGate.checkNotification("Неверный формат");
    }
    @Test
    @Order(4-8)
    @DisplayName("Should Field Invalid Month with Approved card and rest fields valid CreditGate")
    void ShouldFieldInvalidMonthWithApprovedCardAndRestFieldsValidCreditGate() {
        StartPage start = new StartPage();
        start.startPage();
        var creditGate = start.buyInCredit();

        creditGate.setCardNumber(DataHelper.getApprovedCard());
        creditGate.setMonth(DataHelper.getMonth(1));
        creditGate.setYear(DataHelper.getYear(0));
        creditGate.setHolder(DataHelper.getValidName());
        creditGate.setCVC(DataHelper.getValidCVC());
        creditGate.buttonContinueClick();
        creditGate.checkNotification("Неверно указан срок действия карты");
    }

    @Test
    @Order(4-9)
    @DisplayName("Should Field Card null rest fields valid CreditGate")
    void ShouldFieldCardNullRestFieldsValidCreditGate() {
        StartPage start = new StartPage();
        start.startPage();
        var creditGate = start.buyInCredit();

        creditGate.setMonth(DataHelper.getMonth(0));
        creditGate.setYear(DataHelper.getYear(0));
        creditGate.setHolder(DataHelper.getValidName());
        creditGate.setCVC(DataHelper.getValidCVC());
        creditGate.buttonContinueClick();
        creditGate.checkNotification("Неверный формат");
    }

    @Test
    @Order(4-10)
    @DisplayName("Should Field Month null with Approved rest fields valid CreditGate")
    void ShouldFieldMonthNullWithApprovedRestFieldsValidCreditGate() {
        StartPage start = new StartPage();
        start.startPage();
        var creditGate = start.buyInCredit();

        creditGate.setCardNumber(DataHelper.getApprovedCard());
        creditGate.setYear(DataHelper.getYear(0));
        creditGate.setHolder(DataHelper.getValidName());
        creditGate.setCVC(DataHelper.getValidCVC());
        creditGate.buttonContinueClick();
        creditGate.checkNotification("Неверный формат");
    }

    @Test
    @Order(4-11)
    @DisplayName("Should Field Year null with Approved rest fields valid CreditGate")
    void ShouldFieldYearNullWithApprovedRestFieldsValidCreditGate() {
        StartPage start = new StartPage();
        start.startPage();
        var creditGate = start.buyInCredit();

        creditGate.setCardNumber(DataHelper.getApprovedCard());
        creditGate.setMonth(DataHelper.getMonth(0));
        creditGate.setHolder(DataHelper.getValidName());
        creditGate.setCVC(DataHelper.getValidCVC());
        creditGate.buttonContinueClick();
        creditGate.checkNotification("Неверный формат");
    }

    @Test
    @Order(4-12)
    @DisplayName("Should Field Holder null with Approved rest fields valid CreditGate")
    void ShouldFieldHolderNullWithApprovedRestFieldsValidCreditGate() {
        StartPage start = new StartPage();
        start.startPage();
        var creditGate = start.buyInCredit();

        creditGate.setCardNumber(DataHelper.getApprovedCard());
        creditGate.setMonth(DataHelper.getMonth(0));
        creditGate.setYear(DataHelper.getYear(0));
        creditGate.setCVC(DataHelper.getValidCVC());
        creditGate.buttonContinueClick();
        creditGate.checkNotification("Поле обязательно для заполнения");
    }

    @Test
    @Order(4-13)
    @DisplayName("Should Field CVC null with Approved rest fields valid CreditGate")
    void ShouldFieldCVCNullWithApprovedRestFieldsValidCreditGate() {
        StartPage start = new StartPage();
        start.startPage();
        var creditGate = start.buyInCredit();

        creditGate.setCardNumber(DataHelper.getApprovedCard());
        creditGate.setMonth(DataHelper.getMonth(0));
        creditGate.setYear(DataHelper.getYear(0));
        creditGate.setHolder(DataHelper.getValidName());
        creditGate.buttonContinueClick();
        creditGate.checkNotification("Поле обязательно для заполнения");
    }

}
