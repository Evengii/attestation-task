package pages;

import FMadmin.users.GlobalMetricsPage;
import FMadmin.users.ManageUsersPage;
import PCuser.library.LibraryPage;
import configs.Configs;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DashboardHomePage {
    private WebDriver driver;
    private WebDriverWait wait;
    // For FMs
    private By users = By.id("AdminMenu");

    //For PCs
    private By libraryButton = By.xpath("//li[text()='Library']");
    private By listOfLibraries = By.className("ant-table-thead");

    //Common
    private By profileButton = By.id("profileButton");
    private By logoutButton = By.id("logoutButton");
    private By metricsNavigationButton = By.id("metricsNavigationButton");

    public DashboardHomePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;

    }

    public ManageUsersPage openUsersPage(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Configs.TIMEOUT_SECONDS));
        driver.findElement(users).click();
        return new ManageUsersPage(driver, wait);
    }

    public LibraryPage openLibrary(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Configs.TIMEOUT_SECONDS));
        driver.findElement(libraryButton).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(listOfLibraries)));
        return new LibraryPage(driver, wait);
    }

    public GlobalMetricsPage openGlobalMetricsPage(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Configs.TIMEOUT_SECONDS));
        driver.findElement(metricsNavigationButton).click();
        return new GlobalMetricsPage(driver, wait);
    }

    public void clickProfileButton(){
        driver.findElement(profileButton).click();
    }

    public void clickLogoutButton(){
        driver.findElement(logoutButton).click();
    }
}
