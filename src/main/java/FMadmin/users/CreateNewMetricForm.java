package FMadmin.users;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class CreateNewMetricForm {
    private WebDriver driver;
    private WebDriverWait wait;
    private By metricName = By.id("name");
    private By metricCode = By.id("accountCode");
    private By textCheckbox = By.cssSelector("input[value='TEXT']");
    private By textAreaQuestion = By.id("typeConfig_question");
    private By createGlobalMetricButton = By.xpath("//span[text()='Create metric']");
    private By metricNamesInRow = By.xpath("//div[@class='MetricName_metric__1hDU5']");
    private By metricCodesInRow = By.cssSelector("td[class='ant-table-cell']");
    private By metricRow = By.cssSelector("tr[class='ant-table-row ant-table-row-level-0']");


    public CreateNewMetricForm(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void setParameters(String name, String code){
        driver.findElement(metricName).sendKeys(changeNameIfMetricAlreadyExist(name));
        driver.findElement(metricCode).sendKeys(changeCodeIfMetricAlreadyExist(code));
    }

    public void selectTextMetricType(){
        driver.findElement(textCheckbox).click();
    }

    public void enterQuestionMetricType(String question){
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(textAreaQuestion)));
        driver.findElement(textAreaQuestion).sendKeys(question);
    }

    public int clickCreateMetric(){
        int before = getNumOfMetrics();
        driver.findElement(createGlobalMetricButton).click();
        wait.until(ExpectedConditions.numberOfElementsToBe(metricRow, before + 1));
        return driver.findElements(metricRow).size();
    }

    public int getNumOfMetrics(){
        return driver.findElements(metricRow).size();
    }

    //Each metric must have unique name and code
    private String changeNameIfMetricAlreadyExist(String name){
        for(String element : getExistedMetricsNames()){
            if(element.equalsIgnoreCase(name)) {
                name = replaceSymbolInMetric(name);
                break;
            }
        }
        return name;
    }

    private String changeCodeIfMetricAlreadyExist(String code){
        for(String element : getExistedMetricsCodes()){
            if(element.equalsIgnoreCase(code)){
                code = replaceSymbolInMetric(code);
                break;
            }
        }
        return code;
    }

    private List<String> getExistedMetricsNames() {
        List<WebElement> elements = driver.findElements(metricNamesInRow);
        return elements.stream().map(e->e.getText()).collect(Collectors.toList());
    }
    private List<String> getExistedMetricsCodes() {
        List<WebElement> elements = driver.findElements(metricCodesInRow);
        return elements.stream().map(e->e.getText()).collect(Collectors.toList());
    }

    private String replaceSymbolInMetric(String metric) {
        Random random = new Random();
        String newMetric = metric.replace(metric.charAt(0), (char) (random.nextInt('z' - 'a') + 'a'));
        if (newMetric.equalsIgnoreCase(metric)) {
            replaceSymbolInMetric(metric);
        }
        return newMetric;
    }

    //Assertions
    public boolean getCreateGlobalMetricButton() { return driver.findElement(createGlobalMetricButton).isDisplayed(); }
}
