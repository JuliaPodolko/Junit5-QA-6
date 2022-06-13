package guru.qa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.codeborne.selenide.Condition.text;
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
    @AfterEach
    void close(){
        Selenide.closeWebDriver();
    }


}
