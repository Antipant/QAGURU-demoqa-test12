package ru.antipant;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.*;
import java.time.*;
import java.time.format.TextStyle;
import java.util.Locale;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormTests {

        @BeforeAll
        static void setUp () {
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        }

        @Test
        void fillFormTest () {
        String firstName = "Alexander";
        String lastName = "Pushkin";
        String email = "ap@gmail.com";
        String gender = "Male";
        String userNumber = "9999999999";
        LocalDate date = LocalDate.of(1900, 1, 1);
        String month = Month.of(date.getMonthValue()).getDisplayName(TextStyle.FULL_STANDALONE, Locale.forLanguageTag("en"));
        String subjectsInput = "Computer Science";
        String hobby = "Sports";
        String imgPath = "img/Pushkin.jpg";
        String currentAddress = "A N L Colony, Lucknow, Lucknow, UTTAR PRADESH, 226004";
        SelenideElement stateCityWrapper = $("#stateCity-wrapper");
        String state = "Uttar Pradesh";
        String city = "Lucknow";

        open("/automation-practice-form");

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $("#genterWrapper").$(byText(gender)).click();
        $("#userNumber").setValue(userNumber);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").selectOption(String.valueOf(date.getYear()));
        $("[aria-label$='" + month + " " + date.getDayOfMonth() + "st, " + date.getYear() + "']").click();
        $("#subjectsInput").setValue(subjectsInput).pressEnter();
        $("#hobbiesWrapper").$(byText(hobby)).click();
        $("#uploadPicture").uploadFromClasspath(imgPath);
        $("#currentAddress").setValue(currentAddress);
        stateCityWrapper.$(byText("Select State")).click();
        stateCityWrapper.$(byText(state)).click();
        stateCityWrapper.$(byText("Select City")).click();
        stateCityWrapper.$(byText(city)).click();
        $("#submit").click();

        //проверка
        $(".table-responsive").shouldHave(
                text(firstName + " " + lastName),
                text(email),
                text(gender),
                text(userNumber),
                text(date.getDayOfMonth() + " " + month + "," + date.getYear()),
                text(subjectsInput),
                text(hobby),
                text(imgPath.substring(7)),
                text(currentAddress),
                text(state + " " + city)
        );
    }

}
