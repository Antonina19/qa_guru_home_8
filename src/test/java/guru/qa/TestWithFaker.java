package guru.qa;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Configuration.startMaximized;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestWithFaker {
    Faker faker = new Faker();
    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String email = faker.internet().emailAddress();

    @BeforeAll
    static void setup() {
        baseUrl = "https://demoqa.com";
        startMaximized = true;
    }

    @Test
    void positiveFillTest() {
        open("/automation-practice-form");
        $("#firstName").setValue(firstName); // имя
        $("#lastName").setValue(lastName); // фамилия
        $("#userEmail").setValue(email); // почта
        $("[name=gender][value=Female]").parent().click();
        $("#userNumber").setValue("0123456789"); // номер телефона
        $("#subjectsInput").setValue("Biology").pressEnter(); // предметы
        $("#currentAddress").setValue("Street1"); // адрес

        $("#submit").click(); // клик по кнопке

        // сравнение введенных данных

        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(text(firstName + " " + lastName));
        $("tbody").$(byText("Student Email")).parent().shouldHave(text(email));
        $("tbody").$(byText("Gender")).parent().shouldHave(text("Female"));
        $("tbody").$(byText("Mobile")).parent().shouldHave(text("0123456789"));
        $("tbody").$(byText("Subjects")).parent().shouldHave(text("Biology"));
        $("tbody").$(byText("Address")).parent().shouldHave(text("Street1"));

    }

}
