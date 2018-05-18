package SeleniumTest;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;

public class LandingPageUserDataTest {

    private WebDriver webDriver;
    private String url;
    private String userName;
    private String emailAdress;
    private String telephoneNumber;

    @Before
    public void setUpBrowser() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        webDriver = new ChromeDriver();
        url = "http://e-portfel24.pl/kontakt/strona-przechwytujaca/";
        userName = "Jan Kowalski";
        emailAdress = "jan.kowalski88@onet.pl";
        telephoneNumber = "506321123";
    }

    @Test
    public void saveUser() {
        webDriver.get(url);
        WebElement name = webDriver.findElement(By.name("imie"));
        name.clear();
        name.sendKeys(userName);
        WebElement adresEmail = webDriver.findElement(By.name("adres-email"));
        adresEmail.clear();
        adresEmail.sendKeys(emailAdress);
        WebElement telefon = webDriver.findElement(By.name("telefon"));
        telefon.clear();
        telefon.sendKeys(telephoneNumber);
        webDriver.findElement(By.name("acceptance-254")).click();
        webDriver.findElement(By.xpath("//input[@value='Zapisz się']")).click();

        Assert.assertTrue(webDriver.findElement(By.xpath("//input[@value='Zapisz się']")).isEnabled());

        File screenshootFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshootFile, new File("C:/SeleniumScreenShot/LandingPageUserDataTest.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @After
    public void closeBrowser() {
        webDriver.quit();
    }
}

