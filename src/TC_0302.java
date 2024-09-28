import Utility.BaseDriver;
import Utility.MyFunc;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TC_0302 extends BaseDriver {

    public static WebDriverWait wait;
/*  AC02:
- E-junkie demo sitesine başarılı bir şekilde erişmeliyim.
- "Demo E-Kitabı Sepete Ekle" düğmesini bulun ve tıklayın.
- Demo e-kitabının başarıyla sepete eklenmiş olduğunu doğrulayın.
- Sepet sayfasında "Banka Kartıyla Ödeme Yap" seçeneğini bulun ve tıklayın.
- Ödeme sayfasında e-posta, isim ve banka kartı bilgilerinin (kart numarası, son kullanma tarihi, CVC) girilebileceği alanlar olmalıdır.
- Ödeme sayfasında e-posta adresi ve diğer alanları boş bırakın.
- Ödeme sürecini tamamlamak için "Ödeme Yap" düğmesine tıklayın.
- Ödeme sürecinde, "Geçersiz e-posta" ve "Geçersiz Fatura İsmi" hatalarının aynı anda görüntülendiğini doğrulayın.      */
    @Test
    public void TC_302() {
        driver.get("https://shopdemo.fatfreeshop.com/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement addCart = driver.findElement(By.xpath("//button[contains(@onclick, '1595015')]"));
        addCart.click();
        MyFunc.wait(3);

        WebElement iframeCart = driver.findElement(By.xpath("//iframe[@class='EJIframeV3 EJOverlayV3']"));
        driver.switchTo().frame(iframeCart);

        WebElement productCount = driver.findElement(By.xpath("//div[@class='Fixed-Actions Desktop-Only']//span[@class='Cart-Items-Nos']"));
        wait.until(ExpectedConditions.visibilityOf(productCount));
        String number = productCount.getText();
        Assert.assertTrue("Sepet boş durumda.", !number.equals("0"));

        WebElement bankCredCartButton = driver.findElement(By.xpath("//button[@class='Payment-Button CC']"));
        wait.until(ExpectedConditions.elementToBeClickable(bankCredCartButton));
        bankCredCartButton.click();
        MyFunc.wait(3);

        WebElement payButton = driver.findElement(By.xpath("//button[@class='Pay-Button']"));
        payButton.click();
        MyFunc.wait(3);

        WebElement warning1 = driver.findElement(By.xpath("//span[text()='Invalid Email']"));
        WebElement warning2 = driver.findElement(By.xpath("//span[text()='Invalid Billing Name']"));

        wait.until(ExpectedConditions.visibilityOf(warning1));

        Assert.assertTrue("Mail hatası verilemedi.", warning1.getText().contains("Invalid Email"));
        Assert.assertTrue("Fatura ismi hatası verilemedi.", warning2.getText().contains("Invalid Billing Name"));

        tearDown();
    }
}
