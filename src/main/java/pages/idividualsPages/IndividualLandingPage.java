package pages.idividualsPages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

import java.util.ArrayList;

public class IndividualLandingPage extends BasePage {

    public IndividualLandingPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }


    String servicesButtonHost = "dga-navigation-group-item[class='default light hydrated']";
    String servicesButtonShadowRoot = "dga-box[aria-label='Services']";
    ArrayList<String> servicesButtonRoots = new ArrayList<>();




    public void clickOnServices() throws InterruptedException {
//        servicesButtonRoots.add(servicesButtonHost);
//        servicesButtonRoots.add(servicesButtonShadowRoot);
//        try {
//            getShadowRootElement(servicesButtonRoots).click();
//        } catch (NoSuchElementException | StaleElementReferenceException | TimeoutException e) {
//            getShadowRootElement(servicesButtonRoots).click();
//        }

        driver.navigate().to("http://portal.individual.namaa.sumerge.com/social-record/beneficiary-services");

    }


}
