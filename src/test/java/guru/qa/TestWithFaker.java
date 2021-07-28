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
import static io.qameta.allure.Allure.step;

public class TestWithFaker {
    Faker faker = new Faker();
    String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            email = faker.internet().emailAddress(),
            middleName = faker.name().nameWithMiddle(),
            curAddress = faker.address().fullAddress(),
            perAddress = faker.address().fullAddress();
    ;

    @BeforeAll
    static void setup() {
        baseUrl = "https://demoqa.com";
        startMaximized = true;
    }

    @Test
    void positiveFillTest() {
        step("Открываем форму регистрации студента - Student Registration Form", () -> {
            open("/automation-practice-form");
        });
        step("Прописываем случайное имя в поле First Name", () -> {
            $("#firstName").setValue(firstName);
        });
        step("Прописываем случайную фамилию в поле Last Name", () -> {
            $("#lastName").setValue(lastName);
        });
        step("Прописываем случайную электронную почту в поле email", () -> {
            $("#userEmail").setValue(email);
        });
        step("Выбираем пол - Female", () -> {
            $("[name=gender][value=Female]").parent().click();
        });
        step("Прописываем в поле Mobile значение - 0123456789", () -> {
            $("#userNumber").setValue("0123456789");
        });
        step("Прописываем и выбираем в поле Subject значение - Biology", () -> {
            $("#subjectsInput").setValue("Biology").pressEnter();
        });
        step("Прописываем случайный адрес в поле Current Address", () -> {
            $("#currentAddress").setValue(curAddress);
        });
        step("Нажимаем на кнопку Submit", () -> {
            $("#submit").scrollTo().click();
        });

        // сравнение введенных данных

        step("Проверяем, что на открытой форме появился текст Thanks for submitting the form", () -> {
            $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        });
        step("Проверяем, что веденные Имя и Фамилия на форме совпадают с полученным результатом", () -> {
            $(".table-responsive").shouldHave(text(firstName + " " + lastName));
        });
        step("Проверяяем, что веденный email на форме совпадает с полученным результатом", () -> {
            $("tbody").$(byText("Student Email")).parent().shouldHave(text(email));
        });
        step("Проверяем, что выбранный пол на форме совпадает с полученным результатом", () -> {
            $("tbody").$(byText("Gender")).parent().shouldHave(text("Female"));
        });
        step("Проверяем, что введенный номер телефона на форме совпадает с полученным результатом", () -> {
            $("tbody").$(byText("Mobile")).parent().shouldHave(text("0123456789"));
        });
        step("Проверяем, что выбранный предмет в поле Subject совпадает с полученным результатом", () -> {
            $("tbody").$(byText("Subjects")).parent().shouldHave(text("Biology"));
        });
        step("Проверяем, что веденный адрес (Current Address) " +
                "на форме совпадает с полученным результатом", () -> {
            $("tbody").$(byText("Address")).parent().shouldHave(text(curAddress));
        });
    }

    @Test
    void testTextBox() {
        step("Открываем форму Text Box", () -> {
            open("/text-box");
        });
        step("Прописываем случайный ФИО в поле Full Name", () -> {
            $("#userName").setValue(firstName + " " + middleName + " " + lastName);
        });
        step("Прописываем случайный email в поле Email", () -> {
            $("#userEmail").setValue(email);
        });
        step("Прописываем случайный адрес в поле Current Address", () -> {
            $("#currentAddress").setValue(curAddress);
        });
        step("Прописываем случайный адрес в поле Permanent Address", () -> {
            $("#permanentAddress").setValue(perAddress);
        });
        step("Нажимаем кнопку Submit", () -> {
            $("#submit").click();
        });

        step("Проверяем, что веденные Имя, Фамилия и Отчество" +
                " на форме совпадают с полученным результатом", () -> {
            $("#output").$("#name").shouldHave(text(firstName + " " + middleName + " " + lastName));
        });
        step("Проверяяем, что веденный email на форме совпадает с полученным результатом", () -> {
            $("#output").$("#email").shouldHave(text(email));
        });
        step("Проверяем, что веденный адрес (Current Address) " +
                "на форме совпадает с полученным результатом", () -> {
            $("#output").$("#currentAddress").shouldHave(text(curAddress));
        });
        step("Проверяем, что веденный адрес (Permanent Address) " +
                "на форме совпадает с полученным результатом", () -> {
            $("#output").$("#permanentAddress").shouldHave(text(perAddress));
        });
    }

}
