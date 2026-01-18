import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.idividualsPages.DisabilityAssessmentServicePage;
import pages.idividualsPages.IndividualLandingPage;
import pages.idividualsPages.IndividualLoginPage;
import pages.idividualsPages.IndividualServicesPage;

import java.time.Duration;

public class TestRunner {
    WebDriver driver;
    IndividualLoginPage individualLoginPage ;
    IndividualLandingPage individualLandingPage;
    IndividualServicesPage individualServicesPage;
    DisabilityAssessmentServicePage disabilityAssessmentServicePage;


    TestData testData;

    @BeforeClass
    public void setup() {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        options.setAcceptInsecureCerts(true);
        driver = new EdgeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        individualLoginPage = new IndividualLoginPage(driver);
        individualLandingPage = new IndividualLandingPage(driver);
        individualServicesPage = new IndividualServicesPage(driver);
        disabilityAssessmentServicePage = new DisabilityAssessmentServicePage(driver);

        testData = new TestData();
    }


    @Test (priority = 0)
    public  void login () throws InterruptedException {

        individualLoginPage.navigateToLoginPage(testData.individualLoginPageURL);
        individualLoginPage.changeLanguageToEnglish();
        individualLoginPage.enterIndividualNationalID(testData.individualNationalID);
        individualLoginPage.clickOnLoginMock();

        Assert.assertTrue(
                driver.getCurrentUrl().contains("landing"),
                "Expected URL to contain 'landing' but found: " + driver.getCurrentUrl()
        );
    }


    @Test(priority = 1, dependsOnMethods = "login")
    public  void landingPage () throws InterruptedException {

        individualLandingPage.clickOnServices();

    }

    @Test(priority = 2, dependsOnMethods = "landingPage")
    public  void servicesPage () throws InterruptedException {

        individualServicesPage.clickOnMedicalAssessmentRequestCard();

    }

    @Test(priority = 3, dependsOnMethods = "servicesPage")
    public  void  disabilityAssessmentServicePage () throws InterruptedException {

        disabilityAssessmentServicePage.clickOnStartServiceButton();

    }

}
