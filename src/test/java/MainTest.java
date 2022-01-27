import io.qameta.allure.Allure;
import net.jodah.failsafe.internal.util.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.AllureUtils;
import utils.WebDriverSingleton;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.Duration;

import static org.openqa.selenium.Keys.ENTER;

@DisplayName("Домашнее задание на позицию QA Automation в Smartech")
public class MainTest {
    WebDriverWait wait = new WebDriverWait(WebDriverSingleton.getDriver(), Duration.ofSeconds(5));
    @BeforeEach
    public void setUp() {

    }

    @AfterEach
    public void tearDown() {

        WebDriverSingleton.getDriver().quit();
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

                WebDriverSingleton.getDriver().findElement(By.id("text"))
                        .sendKeys("расчет расстояний между городами", ENTER);

                AllureUtils.screenshotAfterStep();
            });

        Allure.step("Среди результатов поиска пользователь ищет результат с сайта «avtodispetcher.ru» (в течение 5 секунд)", () -> {
            AllureUtils.screenshotBeforeStep();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[href='https://www.avtodispetcher.ru/distance/']")));

            AllureUtils.screenshotAfterStep();
        });

        Allure.step("Найдя нужный результат с этого сайта – пользовать кликает на данном результате и переходит на сайт www.avtodispetcher.ru/distance/ ", () -> {
            AllureUtils.screenshotBeforeStep();

            WebDriverSingleton.getDriver().
                    findElement(By.cssSelector("a[href='https://www.avtodispetcher.ru/distance/']")).click();

            AllureUtils.screenshotAfterStep();
        });

        Allure.step("Пользователь убеждается что попал на нужный сайт", () -> {
            AllureUtils.screenshotBeforeStep();

            String currentTab = WebDriverSingleton.getDriver().getWindowHandle();
            for (String tab : WebDriverSingleton.getDriver().getWindowHandles()) {
                if (!tab.equals(currentTab)) {
                    WebDriverSingleton.getDriver().switchTo().window(tab);
                }
            }
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[href='/distance/']")));

            AllureUtils.screenshotAfterStep();
        });

        Allure.step("Пользователь вводит следующие значения в поля для расчета (в течении 10 секунд):" +
                "Поле «Откуда» - «Тула»" +
                "Поле «Куда» - «Санкт-Петербург»" +
                "Поле «Расход топлива» - «9»" +
                "Поле «Цена топлива» - «46»", () -> {

            AllureUtils.screenshotBeforeStep();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='from']")))
                    .sendKeys("Тула");

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='to']")))
                    .sendKeys("Санкт-Петербург");

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='fc']")))
                    .clear();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='fc']")))
                    .sendKeys("9");

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='fp']")))
                    .clear();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='fp']")))
                    .sendKeys("46");

            AllureUtils.screenshotAfterStep();
        });

        Allure.step("Пользователь нажимает кнопку «Рассчитать» ", () -> {
            AllureUtils.screenshotBeforeStep();

            WebDriverSingleton.getDriver().findElement(By.cssSelector("input[value='Рассчитать']")).click();

            AllureUtils.screenshotAfterStep();
        });

        Allure.step("Пользователь проверяет что рассчитанное расстояние = 897 км, а стоимость топлива = 3726 руб.", () -> {
            AllureUtils.screenshotBeforeStep();

            String totalDistance = WebDriverSingleton.getDriver().findElement(By.cssSelector("span[id='totalDistance']")).getText();
            assertEquals(totalDistance, "897");

            String fuelPrice = WebDriverSingleton.getDriver().findElement(By.cssSelector("div[class='fuel_calculator fuelCalculator'")).getText();

            AllureUtils.screenshotAfterStep();
        });

        Allure.step("Пользователь кликает на «Изменить маршрут» ", () -> {
            AllureUtils.screenshotBeforeStep();

            WebElement elm = WebDriverSingleton.getDriver().findElement(By.cssSelector("a[class='is-extra-closed']"));
            ((JavascriptExecutor)WebDriverSingleton.getDriver()).executeScript("arguments[0].scrollIntoView(true);",elm);
            elm.click();

            AllureUtils.screenshotAfterStep();
        });

        Allure.step("В открывшейся форме в поле «Через города» вводит «Великий Новгород» (тратит на это 5 секунд)", () -> {
            AllureUtils.screenshotBeforeStep();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='v']")))
                    .sendKeys("Великий Новгород");

            AllureUtils.screenshotAfterStep();
        });

        Allure.step("Пользователь снова нажимает «Рассчитать", () -> {
            AllureUtils.screenshotBeforeStep();

            WebElement elm = WebDriverSingleton.getDriver().findElement(By.cssSelector("div[class='submit_button']"));
            ((JavascriptExecutor)WebDriverSingleton.getDriver()).executeScript("arguments[0].scrollIntoView(true);",elm);
            elm.click();
            Thread.sleep(10000);

            AllureUtils.screenshotAfterStep();
        });

        Allure.step("Пользователь снова нажимает «Рассчитать", () -> {
            AllureUtils.screenshotBeforeStep();

            WebElement elm = WebDriverSingleton.getDriver().findElement(By.cssSelector("span[id='totalDistance']"));
            ((JavascriptExecutor)WebDriverSingleton.getDriver()).executeScript("arguments[0].scrollIntoView(true);",elm);
            Thread.sleep(20000);

            AllureUtils.screenshotAfterStep();
        });
        }
    }
