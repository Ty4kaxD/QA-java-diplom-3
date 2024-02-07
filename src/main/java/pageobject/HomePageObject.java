package pageobject;

import constants.Constants;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePageObject {

    private final WebDriver driver;
    //кнопка "Личный кабинет"
    private final By personalAccountButton = By.linkText("Личный Кабинет");
    //Кнопка "Войти в аккаунт"
    private By logInAccountButton = By.xpath("//button[text()='Войти в аккаунт']");
    //кнопка перехода к разделу "Булки"
    private final By buns = By.xpath(".//span[text()= 'Булки']");
    //кнопка перехода к разделу "Соусы"
    private final By sauce = By.xpath(".//span[text()= 'Соусы']");
    //кнопка перехода к разделу "Начинки"
    private final By filling = By.xpath(".//span[text()= 'Начинки']");
    //кнопка "Оформить заказ"
    private final By makeOrder = By.xpath(".//button[text() = 'Оформить заказ']");
    private final By currentMenu = By.xpath("//div[contains(@class,'tab_tab__1SPyG tab_tab_type_current__2BEPc')]");

    public HomePageObject(WebDriver driver) {
        this.driver = driver;
    }

    @Step("открываем сайт")
    public void open() {
        driver.get(Constants.SITE);
    }

    @Step("клик по кнопке Личный кабинет")
    public void clickToPersonalAccountButton() {
        driver.findElement(personalAccountButton).click();
    }

    @Step("клик по кнопке Войти в аккаунт")
    public void clickToSignIn() {
        driver.findElement(logInAccountButton).click();
    }

    @Step("клик по разделу Булки в конструкторе")
    public void clickToBuns() {
        driver.findElement(buns).click();
    }

    @Step("клик по разделу Соусы в конструкторе")
    public void clickToSauce() {
        driver.findElement(sauce).click();
    }

    @Step("клик по разделу Начинки в конструкторе")
    public void clickToFilling() {
        driver.findElement(filling).click();
    }

    @Step("ожидание загрузки страницы")
    public void waitForLoadPage() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(personalAccountButton));
    }

    @Step("ожидание появление кнопки Оформить заказ")
    public void waitForLoadMakeOrderButton() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(makeOrder));
    }

    @Step("проверка что выбрана нажатая секция меню")
    public String getTextFromSelectedMenu() {
        return driver.findElement(currentMenu).getText();
    }
    @Step("проверка что отображается кнопка Оформить заказ")
    public boolean isOrderButtonDisplayed() {
        WebElement pageWindowElement = driver.findElement(makeOrder);
        return pageWindowElement.isDisplayed();
    }
}
