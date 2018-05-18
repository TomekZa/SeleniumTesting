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

public class FormTest {

    private WebDriver webDriver;
    private String url;
    private String name;
    private String surname;
    private String emailAdress;
    private String telephoneNumber;

    @Before
    public void setUpBrowser() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        webDriver = new ChromeDriver();
        url = "http://e-portfel24.pl/";
        name = "Jan";
        surname = "Kowalski";
        emailAdress = "jan.kowalski88@onet.pl";
        telephoneNumber = "506321123";
    }

    @Test
    public void saveUser() {
        webDriver.get(url);
        WebElement nameUser = webDriver.findElement(By.name("imie"));
        nameUser.clear();
        nameUser.sendKeys(name);
        WebElement surnameUser = webDriver.findElement(By.name("nazwisko"));
        surnameUser.clear();
        surnameUser.sendKeys(surname);
        WebElement adresEmail = webDriver.findElement(By.name("email-klienta"));
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
            FileUtils.copyFile(screenshootFile, new File("C:/SeleniumScreenShot/FormTest.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @After
    public void closeBrowser() {
        webDriver.quit();
    }
}
