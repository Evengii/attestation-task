package pages;

import configs.Configs;
import configs.Credentials;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By emailInput = By.id("email");
    private By passwordInput = By.id("password");
    private By loginButton = By.id("loginButton");
    private By termsOfUseSign = By.id("terms-of-use");
    private By acceptTermsButton = By.xpath("//button[@class='ant-btn ant-btn-primary']");

    public LoginPage(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
    }

    public void setCredentials(Credentials credentials){
        driver.findElement(emailInput).sendKeys(credentials.login);
        driver.findElement(passwordInput).sendKeys(credentials.password);
    }

    public DashboardHomePage clickLoginButton(){
        driver.findElement(loginButton).click();
        return new DashboardHomePage(driver, wait);
    }

    public boolean getEmailInput(){
        return driver.findElement(emailInput).isDisplayed();
    }
}
