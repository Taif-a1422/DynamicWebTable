package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.FluentWebTablePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebTableTest {

    @DataProvider(name = "records")
    public Object[][] getData() {
        return new Object[][] {
                {"Ali Hassan", "28", "Canada"},
                {"Nora Salem", "33", "Australia"},
        };
    }

    @Test(dataProvider = "records")
    public void testAddRecord(String name, String age, String country) {
        WebDriver driver = new ChromeDriver();
        FluentWebTablePage tablePage = new FluentWebTablePage(driver);

        tablePage.openPage()
                .setName(name)
                .setAge(age)
                .setCountry(country)
                .clickAddRecord();

        Assert.assertTrue(tablePage.isRecordAdded(name, age, country), "❌ Record not found in table!");

        driver.quit();
    }
}
