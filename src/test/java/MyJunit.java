import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MyJunit {
    WebDriver driver;

    @BeforeAll
    public void Setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }

    @Test
    public void browse() throws InterruptedException {

        driver.get("https://www.digitalunite.com/practice-webform-learners");
        driver.findElement(By.id("onetrust-accept-btn-handler")).click();

        driver.findElement(By.id("edit-name")).sendKeys("Shazzadul Islam Fahim");
        driver.findElement(By.id("edit-number")).sendKeys("+8801752007136");
        driver.findElement(By.cssSelector("[for=edit-agnew-20-30]")).click();
        driver.findElement(By.id("edit-date")).sendKeys("06/30/1998");
        driver.findElement(By.id("edit-email")).sendKeys("shazzadulislamfahim@gmail.com");
        driver.findElement(By.id("edit-tell-us-a-bit-about-yourself-")).sendKeys("My name is Shazzadul Islam Fahim. I am a SQA engineer.Currently working at SkyTech Solutions.");
        driver.findElement(By.id("edit-uploadocument-upload")).sendKeys(System.getProperty("user.dir") + "./src/test/resources/Image.jpg");
        Thread.sleep(3000);
        WebElement element = driver.findElement(By.id("edit-age"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().build().perform();
        WebElement submit = driver.findElement(By.className("webform-button--submit"));
        actions.moveToElement(submit).click().build().perform();
        String actual = "Thank you for your submission!";
        String expected = driver.findElement(By.id("block-pagetitle-2")).getText();
        Assertions.assertTrue(expected.contains(actual));

    }


    @AfterAll
    public void quit() {
//        driver.quit();
    }
}
