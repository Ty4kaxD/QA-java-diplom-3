package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPageObject {
    private final WebDriver driver;


    //поле ввода имени
    private final By name = By.xpath("//label[text()='Имя']/../input");;

    //поле ввода Email
    private final By email = By.xpath("//label[text()='Email']/../input");

    //поле ввода пароля
    private final By password = By.xpath("//label[text()='Пароль']/../input");

    //кнопка зарегистрироваться
    private final By registerButton = By.xpath(".//button[text() = 'Зарегистрироваться']");

    //кнопка "Войти"
    private final By signIn = By.linkText("Войти");

    //сообщение о некорректном пароле
    private final By errorMessage = By.xpath(".//p[text() = 'Некорректный пароль']");

    //заголовок "Регистрация"
    private final By text = By.xpath(".//h2[text() = 'Регистрация']");

    public RegisterPageObject(WebDriver driver) {
        this.driver = driver;
    }

    @Step("ввод имени в поле")
    public void setName(String userName) {
        driver.findElement(name).sendKeys(userName);
    }

    @Step("ввод Email в поле")
    public void setEmail(String userEmail) {
        driver.findElement(email).sendKeys(userEmail);
    }

    @Step("ввод пароля в поле")
    public void setPassword(String userPassword) {
        driver.findElement(password).sendKeys(userPassword);
    }

    @Step("клик по кнопке Зарегистрироваться")
    public void clickToRegisterButton() {
        driver.findElement(registerButton).click();
    }

    @Step("клик по кнопке Войти")
    public void clickToSignIn() {
        driver.findElement(signIn).click();
    }

    @Step("получение текста сообщения об ошибке")
    public String checkErrorMessage() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
        return driver.findElement(errorMessage).getText();
    }

    @Step("ожидание загрузки страницы")
    public void waitForLoad() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(text));
    }
}
