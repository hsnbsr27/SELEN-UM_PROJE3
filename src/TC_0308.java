import Utility.BaseDriver;
import Utility.MyFunc;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.time.Duration;

public class TC_0308 extends BaseDriver {
/*  AC08:
- https://www.e-junkie.com/ ana sayfasında "Nasıl Çalışır" düğmesini bulun ve tıklayın.
- URL'nin "https://www.e-junkie.com/" olduğunu doğrulayın.
- Açılan YouTube penceresindeki videonun oynatılmasını başlatın.
- Pencereyi 10 saniye sonra kapatın.    */
    @Test
    public void TC_308() throws AWTException {
        driver.get("https://shopdemo.fatfreeshop.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='E-commerce by E-junkie']")));
        WebElement eJunkie = driver.findElement(By.xpath("//a[text()='E-commerce by E-junkie']"));
        eJunkie.click();
        MyFunc.wait(5);

        wait.until(ExpectedConditions.urlContains("https://www.e-junkie.com/"));

        WebElement howItWorks = driver.findElement(By.cssSelector("[onclick='toggleYoutubeModal(true)']"));
        howItWorks.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='14-Day FREE Trial']")));
        Actions action = new Actions(driver);
        action.moveToLocation(1, 1).click().build().perform();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//h2[text()='14-Day FREE Trial']")));

        for (int i = 1; i <= 29; i++) {
            action.keyDown(Keys.TAB).build().perform();
            action.keyUp(Keys.TAB).build().perform();
        }
        action.keyDown(Keys.ENTER).build().perform();
        action.keyUp(Keys.ENTER).build().perform();

        MyFunc.wait(10);

        WebElement closeVideo = driver.findElement(By.xpath("//button[@onclick='toggleYoutubeModal(false)']"));
        closeVideo.click();
        
        tearDown();
    }
}
