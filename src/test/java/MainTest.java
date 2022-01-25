import io.qameta.allure.Allure;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import utils.AllureUtils;
import utils.WebDriverSingleton;

@DisplayName("Домашнее задание на позицию QA Automation в Smartech")
public class MainTest {
    @BeforeEach
    public void setUp() {

    }

    @AfterEach
    public void tearDown() {
        WebDriverSingleton.getDriver().close();
    }

    @Test
    @DisplayName("Непосредственно тест")
    public void checkYandex() {
        Allure.step("Пользователь заходит на сайт Яндекс: www.yandex.ru", () -> {
            WebDriverSingleton.getDriver().get("https://www.yandex.ru");
            AllureUtils.screenshotAfterStep();
        });
        Allure.step("Вводит в поисковую строку фразу «расчет расстояний между городами» и запускает поиск", () -> {
            AllureUtils.screenshotBeforeStep();
            WebDriverSingleton.getDriver()
                    .findElement(By.className("input__control input__input mini-suggest__input"))
                    .sendKeys("расчет расстояний между городами");
            WebDriverSingleton.getDriver()
                    .findElement(By.className("button mini-suggest__button button_theme_search button_size_search i-bem button_js_inited"))
                    .click();
            AllureUtils.screenshotAfterStep();
        });
    }
}
