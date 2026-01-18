package pages.idividualsPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;

public class IndividualServicesPage extends BasePage {


    public IndividualServicesPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    private By medicalAssessmentRequestCard = By.cssSelector("app-landing-page-card[iconname='userCircle']");

    public void clickOnMedicalAssessmentRequestCard() throws InterruptedException {
//        servicesButtonRoots.add(servicesButtonHost);
//        servicesButtonRoots.add(servicesButtonShadowRoot);
//        try {
//            getShadowRootElement(servicesButtonRoots).click();
//        } catch (NoSuchElementException | StaleElementReferenceException | TimeoutException e) {
//            getShadowRootElement(servicesButtonRoots).click();
//        }

        driver.findElement(medicalAssessmentRequestCard).click();
        wait.until(ExpectedConditions.urlContains("disability-assesment"));


    }

}
