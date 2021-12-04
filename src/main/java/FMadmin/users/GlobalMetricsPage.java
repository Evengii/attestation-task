package FMadmin.users;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GlobalMetricsPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By createNewMetricButton = By.id("newMetricBtn");

    public GlobalMetricsPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public CreateNewMetricForm clickNewMetricButton(){
        driver.findElement(createNewMetricButton).click();
        return new CreateNewMetricForm(driver, wait);
    }

    //Assertions
    public boolean isNewMetricButtonDisplayed(){
        return driver.findElement(createNewMetricButton).isDisplayed();
    }
}
