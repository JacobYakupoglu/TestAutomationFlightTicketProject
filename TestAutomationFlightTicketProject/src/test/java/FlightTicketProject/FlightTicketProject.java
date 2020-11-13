package FlightTicketProject;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FlightTicketProject
{
    WebDriver driver;

    //gecko driver ayaga kaldirir
    @BeforeTest
    public void driverFirefox()
    {
        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/src/main/resources/geckodriver");
        driver = new FirefoxDriver();
        driver.get("https://www.enuygun.com/ucak-bileti/");
    }

    @Test(priority = 1)
    public void searchDepartureCity()
    {
        driver.findElement(By.cssSelector("input#OriginInput")).sendKeys("istanbul");
        Actions pressEnterKey = new Actions(driver);
        pressEnterKey.moveToElement(driver.findElement(By.cssSelector("input#OriginInput"))).sendKeys(Keys.ENTER).build().perform();
        pressEnterKey.sendKeys(Keys.TAB).build().perform();
    }

    @Test(priority = 2)
    public void searchArricalCity()
    {
        driver.findElement(By.cssSelector("input#DestinationInput")).sendKeys("ankara");
        Actions pressEnterKey = new Actions(driver);
        pressEnterKey.moveToElement(driver.findElement(By.cssSelector("input#DestinationInput"))).sendKeys(Keys.ENTER).build().perform();
    }

    @Test(priority = 3)
    public void chooseDepartureDate()
    {
        driver.findElement(By.id("DepartureDate")).click();
        ((JavascriptExecutor) driver).executeScript("scroll(0, 250);");
        List<WebElement> departureDate = driver.findElements(By.xpath("//div[contains(@class,'_content')] "));
        int countDepartureDate = driver.findElements(By.xpath("//div[contains(@class,'_content')] ")).size();

        for (int i = 0; i < countDepartureDate; i++)
        {
            String text = driver.findElements(By.xpath("//div[contains(@class,'_content')] ")).get(i).getText();
            if (text.equalsIgnoreCase("20"))
            {
                driver.findElements(By.xpath("//div[contains(@class,'_content')] ")).get(i).click();
                break;
            }
        }
    }

    @Test(priority = 4)
    public void chooseArrivalDate()
    {
        driver.findElement(By.id("ReturnDate")).click();
        ((JavascriptExecutor) driver).executeScript("scroll(0, 250);");
        List<WebElement> arrivalDate = driver.findElements(By.xpath("//div[contains(@class,'_content')]"));
        int countArrivalDate = driver.findElements(By.xpath("//div[contains(@class,'_content')]")).size();

        for (int i = 0; i < countArrivalDate; i++)
        {
            String text = driver.findElements(By.xpath("//div[contains(@class,'_content')]")).get(i).getText();
            if (text.equalsIgnoreCase("29"))
            {
                driver.findElements(By.xpath("//div[contains(@class,'_content')]")).get(i).click();
                break;
            }
        }
    }

    @Test(priority = 5)
    public void checkTransitFiter()
    {
        driver.findElement(By.id("transitFilter")).click();
    }

    @Test(priority = 6)
    public void searchFlight()
    {
        driver.findElement(By.xpath("//span[contains(text(),'Ucuz bilet bul')]")).click();
    }

    @Test(priority = 7)
    public void waitForFlightSelection()
    {
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Test(priority = 8)
    public void chooseDepartureFlight()
    {
        ((JavascriptExecutor) driver).executeScript("scroll(0, 400);");
        List<WebElement> departureFlight = driver.findElements(By.xpath("//button[@class='action-select-btn tr btn btn-outline-success btn-sm']"));
        int countDepartureFlight = driver.findElements(By.xpath("//button[@class='action-select-btn tr btn btn-outline-success btn-sm']")).size();

        for (int i = 0; i < countDepartureFlight; i++)
        {
            if(i == 5)
            {
                driver.findElements(By.xpath("//button[@class='action-select-btn tr btn btn-outline-success btn-sm']")).get(i).click();
            }
        }
    }

    @Test(priority = 9)
    public void chooseArrivalFlight()
    {
        ((JavascriptExecutor) driver).executeScript("scroll(0, 200);");
        List<WebElement> arrivalFlight = driver.findElements(By.xpath("//button[@class='action-select-btn tr btn btn-outline-success btn-sm']"));
        int countArrivalFlight = driver.findElements(By.xpath("//button[@class='action-select-btn tr btn btn-outline-success btn-sm']")).size();

        for (int i = 0; i < countArrivalFlight; i++)
        {
            if(i == 18)
            {
                driver.findElements(By.xpath("//button[@class='action-select-btn tr btn btn-outline-success btn-sm']")).get(i).click();
            }
        }
    }

    @Test(priority = 10)
    public void enterContactInformation()
    {
        driver.findElement(By.cssSelector("input#contact_email")).sendKeys("eyupyakup@outlook.com");
        driver.findElement(By.cssSelector("input#contact_cellphone")).sendKeys("451111111");
    }

    @Test(priority = 11)
    public void enterPersonalInformation()
    {
        ((JavascriptExecutor) driver).executeScript("scroll(0, 200);");
        driver.findElement(By.cssSelector("input#firstName_0")).sendKeys("Ahmet");
        driver.findElement(By.cssSelector("input#lastName_0")).sendKeys("Ahmetoglu");
    }

    @Test(priority = 12)
    public void chooseBirthday()
    {
        Select s = new Select(driver.findElement(By.cssSelector("select#birthDateDay_0")));
        s.selectByIndex(8);
    }

    @Test(priority = 13)
    public void chooseBirthMonth()
    {
        Select s = new Select(driver.findElement(By.cssSelector("select#birthDateMonth_0")));
        s.selectByIndex(5);
    }

    @Test(priority = 14)
    public void chooseBirthYear()
    {
        Select s = new Select(driver.findElement(By.cssSelector("select#birthDateYear_0")));
        s.selectByValue("1984");
    }

    @Test(priority = 15)
    public void chooseCitizenship()
    {
        driver.findElement(By.cssSelector("input#publicId_0")).sendKeys("11261428724");
    }

    @Test(priority = 16)
    public void closeBrowser()
    {
        driver.close();
    }
}

