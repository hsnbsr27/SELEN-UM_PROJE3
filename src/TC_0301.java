package src;

import Utility.BaseDriver;
import Utility.MyFunc;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
/*AC01:
- Kullanıcı, e-junkie demo sitesine ulaşabilmelidir.
- "Demo E-Kitabı Sepete Ekle" düğmesini bulun ve tıklayın.
- Demo e-kitabının başarıyla sepete eklenmiş olduğunu doğrulayın.
- "Promosyon Kodu Ekle" düğmesine tıklayın.
- Belirtilen alana geçersiz (rastgele) bir promosyon kodu girin.
- "Uygula" düğmesine tıklayın.
- "Geçersiz promosyon kodu" uyarı mesajının görüntülendiğini doğrulayın.    */
public class TC_0301 extends BaseDriver {
    @Test
    public void US_01() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://shopdemo.e-junkie.com/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.urlToBe("https://shopdemo.fatfreeshop.com/?"));

        WebElement addToCartBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"products\"]/div[1]/div/div[2]/a/div/div[2]/button")));
        addToCartBtn.click();

        WebElement iframeElement = driver.findElement(By.cssSelector("[class='EJIframeV3 EJOverlayV3']"));
        driver.switchTo().frame(iframeElement);

        WebElement addPrmCodeBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='Apply-Button Show-Promo-Code-Button']")));
        addPrmCodeBtn.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='Promo-Code-Value']")));
        // Ekranda promo code görünene kadar bekle
        Assert.assertTrue("Apply Promo code görülmedi", driver.findElement(By.cssSelector("[class='Promo-Code-Value']")).isDisplayed());
        // Ekranda promo code'un görüntülendiğini doğrula, görüntülenmezse ekrana hata mesajı yansıt

        WebElement invalidPromoCode1 = driver.findElement(By.cssSelector("[class='Promo-Code-Value']"));
        invalidPromoCode1.sendKeys("122524");
        MyFunc.wait(3);

        WebElement applyCodeBtn1 = driver.findElement(By.cssSelector("[class='Promo-Apply']"));
        applyCodeBtn1.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Invalid promo code']")));
        Assert.assertTrue("Invalid promo code", driver.findElement(By.xpath("//span[text()='Invalid promo code']")).isDisplayed());

        WebElement addPrmCodeBtn2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='Apply-Button Show-Promo-Code-Button']")));
        addPrmCodeBtn2.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='Promo-Code-Value']")));
        WebElement invalidPromoCode2 = driver.findElement(By.cssSelector("[class='Promo-Code-Value']"));
        invalidPromoCode2.sendKeys("654254");

        WebElement applyCodeBtn2 = driver.findElement(By.cssSelector("[class='Promo-Apply']"));
        applyCodeBtn2.click();

        Assert.assertTrue("Invalid promo code", driver.findElement(By.xpath("//span[text()='Invalid promo code']")).isDisplayed());

        driver.quit();
    }
}
