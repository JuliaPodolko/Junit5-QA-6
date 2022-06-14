package guru.qa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class HomeWork {
    @BeforeAll
    static void setUP () {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
    }
    @BeforeEach
    void setMod () {
        open ("/text-box");
    }
    @AfterEach
    void close(){
        Selenide.closeWebDriver();
    }
    @CsvSource (value = {
            "Anna Petrova | anna@test.ru",
            "Maria Ivanova | maria@test.ru"
    },
    delimiter = '|')
    @ParameterizedTest(name="Проверка заполнения формы {0}")
    void paramFillForm(String fullName, String userEmail) {
        $("#userName").setValue(fullName); // Name
        $("#userEmail").setValue(userEmail); // Электронная почта
        $("#submit").click();
        $("#output").shouldHave(
                text(fullName),
                text(userEmail));
    }
    @Disabled
    @Test
    void fillFormTest () {
        String fullName = "Julia Podolko";
        String userEmail = "Julia@yandex.ru";
        $("#userName").setValue(fullName); // Name
        $("#userEmail").setValue(userEmail); // Электронная почта
        $("#submit").click();
        $("#output").shouldHave(
                text(fullName),
                text(userEmail));
    }
    static Stream<Arguments> methodSourceExampleTest() {
        return  Stream.of(
                Arguments.of("Anna Petrova", "anna@test.ru"),
                Arguments.of("Maria Ivanova", "maria@test.ru")
        );
    }
    @MethodSource("methodSourceExampleTest")
    @ParameterizedTest
    void methodSourceExampleTest(String fullName, String userEmail) {
        $("#userName").setValue(fullName); // Name
        $("#userEmail").setValue(userEmail); // Электронная почта
        $("#submit").click();
        $("#output").shouldHave(
                text(fullName),
                text(userEmail));
    }
    @ValueSource(strings = {
            "Anna Petrova",
            "Maria Ivanova"
    })
    @ParameterizedTest(name="Проверка заполнения формы {0}")
    void fillFormTest (String fullName) {
        String userEmail = "test@test.ru";
        $("#userName").setValue(fullName); // Name
        $("#userEmail").setValue(userEmail); // Электронная почта
        $("#submit").click();
        $("#output").shouldHave(
                text(fullName),
                text(userEmail));
    }
}
