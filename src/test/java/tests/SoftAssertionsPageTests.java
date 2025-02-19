package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;

public class SoftAssertionsPageTests {
    @BeforeAll
    static void configurations() {
        Configuration.baseUrl = "https://github.com";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void shouldFindCodeForJUnitOnSoftAssertionsPageTest() {
        open("");
        $(".search-input").click();
        $("#query-builder-test").setValue("Selenide").pressEnter();
        $$(".prc-Link-Link-85e08").findBy(Condition.text("selenide/selenide")).click();
        webdriver().shouldHave(url("https://github.com/selenide/selenide"));
        $("#wiki-tab").click();
        $(byXpath("//button[contains(text(), 'Show 3 more pages')]")).click();
        $(".wiki-rightbar").shouldHave(Condition.text("SoftAssertions"));
        $("#wiki-pages-box").$(withText("SoftAssertions")).click();
        $("#wiki-wrapper").shouldHave(Condition.text("""
                @ExtendWith({SoftAssertsExtension.class})
                class Tests {
                  @Test
                  void test() {
                    Configuration.assertionMode = SOFT;
                    open("page.html");

                    $("#first").should(visible).click();
                    $("#second").should(visible).click();
                  }
                }"""));
    }
}
