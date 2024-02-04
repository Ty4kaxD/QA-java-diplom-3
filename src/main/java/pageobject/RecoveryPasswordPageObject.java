package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RecoveryPasswordPageObject {

    private final WebDriver driver;

    public RecoveryPasswordPageObject(WebDriver driver) {
        this.driver = driver;
    }

    private final By signInButton = By.linkText("Войти");

    private final By text = By.xpath(".//h2[text() = 'Восстановление пароля']");

    @Step("клик по кнопке Войти")
    public void clickForSignInButton() {
        driver.findElement(signInButton).click();
    }
    @Step("ожидание загрузки страницы")
    public void waitForLoad() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(text));
    }
}
