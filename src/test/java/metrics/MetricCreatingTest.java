package metrics;

import FMadmin.users.CreateNewMetricForm;
import FMadmin.users.GlobalMetricsPage;
import base.TestBase;
import configs.Credentials;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import pages.DashboardHomePage;
import pages.LoginPage;

public class MetricCreatingTest extends TestBase {
    @DisplayName("Creating global metric")
    @ParameterizedTest(name = "Run: {index} - value: {arguments}")
    @CsvFileSource(files = {"src/test/resources/FMuser/newMetrics.csv"}, numLinesToSkip = 1)
    public void testCreatingGlobalTextMetric(String name, String code, String question){
        DashboardHomePage dashboardHomePage = authorize(Credentials.fundManagerCreds());
        GlobalMetricsPage globalMetricsPage = dashboardHomePage.openGlobalMetricsPage();
        Assertions.assertTrue(globalMetricsPage.isNewMetricButtonDisplayed(), "Global metrics page is not opened");

        CreateNewMetricForm metricForm = globalMetricsPage.clickNewMetricButton();
        metricForm.setParameters(name, code);
        metricForm.selectTextMetricType();
        metricForm.enterQuestionMetricType(question);
        int actualNumOfMetrics = metricForm.clickCreateMetric();
        Assertions.assertEquals(metricForm.getNumOfMetrics(), actualNumOfMetrics, "Expected and actual num of metrics don't match");

        LoginPage loginPage = logout();
        Assertions.assertTrue(loginPage.getEmailInput(), "You didn't log out");

    }
}
