package pages.entitiesPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;

public class EntitiesInboxPage extends BasePage {

    public EntitiesInboxPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    private final By AssessmentRequests = By.xpath("//dga-navigation-group-item[2]");

    public void clickOnDisabilityAssessmentRequests(){
        click(driver.findElement(AssessmentRequests));
        wait.until(ExpectedConditions.urlContains("requests"));
        driver.navigate().refresh();
    }


}
