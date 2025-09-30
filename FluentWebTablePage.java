package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class FluentWebTablePage {
    private WebDriver driver;

    public FluentWebTablePage(WebDriver driver) {
        this.driver = driver;
    }

    public FluentWebTablePage openPage() {
        driver.get("https://claruswaysda.github.io/addRecordWebTable.html");
        return this;
    }

    public FluentWebTablePage setName(String name) {
        driver.findElement(By.id("nameInput")).clear();
        driver.findElement(By.id("nameInput")).sendKeys(name);
        return this;
    }

    public FluentWebTablePage setAge(String age) {
        driver.findElement(By.id("ageInput")).clear();
        driver.findElement(By.id("ageInput")).sendKeys(age);
        return this;
    }

    public FluentWebTablePage setCountry(String country) {
        Select select = new Select(driver.findElement(By.id("countrySelect")));
        select.selectByVisibleText(country);
        return this;
    }

    public FluentWebTablePage clickAddRecord() {
        driver.findElement(By.xpath("//button[text()='Add Record']")).click();
        return this;
    }

    public boolean isRecordAdded(String name, String age, String country) {
        List<WebElement> rows = driver.findElements(By.xpath("//table/tbody/tr"));

        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.size() >= 3 &&
                    cells.get(0).getText().equals(name) &&
                    cells.get(1).getText().equals(age) &&
                    cells.get(2).getText().equals(country)) {
                return true;
            }
        }
        return false;
    }

    public FluentWebTablePage deleteRecord(String name) {
        List<WebElement> rows = driver.findElements(By.xpath("//table/tbody/tr"));
        for (WebElement row : rows) {
            WebElement nameCell = row.findElement(By.xpath("./td[1]"));
            if (nameCell.getText().equals(name)) {
                row.findElement(By.xpath(".//button[text()='Delete']")).click();
                break;
            }
        }
        return this;
    }
}
