import Utility.BaseDriver;
import Utility.MyFunc;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
/*  AC03:
- Sepet sayfasında "Banka Kartıyla Ödeme Yap" seçeneğini bulun ve tıklayın.
- Ödeme sayfasında kart üzerindeki e-posta, isim ve kart bilgileri (kart numarası, son kullanma tarihi, CVC) girilebilecek alanlar olmalıdır.
- Ödeme sayfasındaki zorunlu alanları (e-posta, e-posta onayı, kart üzerindeki isim vb.) doldurun.
- Ödeme süreci sırasında geçersiz bir kredi kartı numarası olan "1111 1111 1111 1111" girin.
- Ödeme sürecini tamamlamak için "Ödeme Yap" düğmesine tıklayın.
- Ödeme sürecinde "Kart numaranız geçersiz" mesajının görüntülendiğini doğrulayın.      */
public class TC_0303 extends BaseDriver {
    @Test
    public void TC_01(){
        driver.get("https://shopdemo.e-junkie.com/");
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement eBook =
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@onclick=\"return EJProductClick('1595015')\"]")));
        eBook.click();      // Ebook'a tıklat.
        MyFunc.wait(3);

        wait.withTimeout(Duration.ofSeconds(5));

        WebElement iframe =
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe[@class='EJIframeV3 EJOverlayV3']")));
        driver.switchTo().frame(iframe);        // driver iFrame'e geçiş yapsın

        WebElement payDebit =
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(), 'Visa')]")));
        payDebit.click();       // payDebit'e tıkla
        MyFunc.wait(3);

        WebElement email =
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Email']")));
        email.sendKeys("team10forever@gmail.com");     // email kutusuna "team10forever@gmail.com 'u gönder
        MyFunc.wait(3);

        WebElement rEmail =
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Confirm Email']")));
        rEmail.sendKeys("team10forever@gmail.com");
        MyFunc.wait(3);

        WebElement nameCard =
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Name On Card']")));
        nameCard.sendKeys("TeamTen Forever");
        MyFunc.wait(3);

        driver.switchTo().frame(1);
        WebElement cardNum =
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='cardnumber']")));
        cardNum.sendKeys("1111111111111111");
        MyFunc.wait(3);

        driver.switchTo().parentFrame();
        WebElement payBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='Pay-Button']")));
        payBtn.click();

        WebElement msgFail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='SnackBar']")));

        System.out.println(msgFail.getText());

        Assert.assertTrue("Message not Displayed", msgFail.isDisplayed());

        driver.quit();
    }
}
