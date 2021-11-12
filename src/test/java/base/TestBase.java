package base;

import configs.Configs;
import configs.Credentials;
import configs.Storage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.DashboardHomePage;
import pages.LoginPage;

public class TestBase {

    protected WebDriver driver;
    private WebDriverWait wait;
    private LoginPage loginPage;

    @BeforeEach
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver95.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Configs.TIMEOUT_SECONDS);
        driver.get(Configs.LOGIN_PAGE_URL);
    }

    public DashboardHomePage authorize(Credentials creds){
        loginPage = new LoginPage(driver, wait);
        loginPage.setCredentials(creds);
        return loginPage.clickLoginButton();
    }

    public LoginPage logout() {
        DashboardHomePage dashboardHomePage = new DashboardHomePage(driver, wait);
        dashboardHomePage.clickProfileButton();
        dashboardHomePage.clickLogoutButton();
        return new LoginPage(driver, wait);
    }

    @AfterEach
    public void tearDown(){
        driver.quit();
    }
}
