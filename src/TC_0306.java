import Utility.BaseDriver;
import Utility.MyFunc;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.time.Duration;

public class TC_0306 extends BaseDriver {
/*  AC06:
- Ana sayfada ""Bize Ulaşın"" gibi bir iletişim formu düğmesi bulun ve tıklayın.
- İletişim formunda şu bilgileri doldurun: İsim, e-posta, konu, mesaj.
- "Mesaj Gönder" düğmesine tıklayarak iletişim formunu göndermeyi deneyin.
- "Recaptcha Eşleşmedi" uyarısını doğrulayın.   */
    @Test
    public void TC_01() throws AWTException {
        driver.get("https://shopdemo.fatfreeshop.com/?");
        WebElement contactUs=driver.findElement(By.xpath("((//div[@class=\"container\"])[3]//following::div[2])[1]//following-sibling::a"));
        contactUs.click();
        MyFunc.wait(1);

        WebElement name=driver.findElement(By.id("sender_name"));
        name.sendKeys("Saint Sun");
        MyFunc.wait(2);

        WebElement ePosta=driver.findElement(By.id("sender_email"));
        ePosta.sendKeys("Team10@test.com");
        MyFunc.wait(2);

        WebElement subject=driver.findElement(By.id("sender_subject"));
        subject.sendKeys("TestNG Sevenler Derneğine Hoşgeldiniz.");
        MyFunc.wait(2);

        WebElement message=driver.findElement(By.id("sender_message"));
        message.sendKeys("Download butonunu bulup tıklatamadık. Manuel testte çıkıyor, otomasyonda çıkmıyor.");
        MyFunc.wait(2);

        WebElement sendButton=driver.findElement(By.id("send_message_button"));
        sendButton.click();
        MyFunc.wait(2);

        WebDriverWait bekle=new WebDriverWait(driver, Duration.ofSeconds(10));

        bekle.until(ExpectedConditions.alertIsPresent());
        String alertMessage=driver.switchTo().alert().getText();
        Assert.assertTrue("Mesaj Görüntülenemedi!",alertMessage.contains("Recaptcha"));
        MyFunc.wait(2);

        driver.switchTo().alert().accept();
        MyFunc.wait(2);

        tearDown();
    }
}
