package pages.idividualsPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

public class DisabilityAssessmentRequestsPage extends BasePage {


    public DisabilityAssessmentRequestsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    private final By firstRequestNumber = By.xpath("//dga-table-row[1]//dga-table-data[@id='value_RequestsPage_RequestNumber']");

    public String getFirstRequestNumber(){
        return getText(driver.findElement(firstRequestNumber));
    }
}
