package SeleniumTest;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MailLoginTest {

    private WebDriver webDriver;
    private String url;
    private String user;
    private String password;

    @Before
    public void setUpBrowser() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        webDriver = new ChromeDriver();
        url = "https://www.wp.pl/";
        user = "testowyUzytkownik";
        password = "testoweHaslo";
    }

    @Test
    public void loginWrong() {
        webDriver.get(url);
        webDriver.findElement(By.partialLinkText("POCZTA")).click();
        WebElement loginUsername = webDriver.findElement(By.name("login_username"));
        loginUsername.clear();
        loginUsername.sendKeys(user);
        WebElement passwordUser = webDriver.findElement(By.name("password"));
        passwordUser.clear();
        passwordUser.sendKeys(password);
        webDriver.findElement(By.id("btnSubmit")).click();

        Assert.assertTrue(webDriver.getPageSource().contains("Niestety podany login lub hasło jest błędne"));
    }

    @After
    public void closeBrowser() {
        webDriver.quit();
    }
}

