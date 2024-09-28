package Utility.Utility;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseDriver {

    public static WebDriver driver;
    public static WebDriverWait wait;
    public static JavascriptExecutor js;

    // Bunun sartı extends olmasi ve basta yer almasi
    static //tearStart()
    {
        driver = new ChromeDriver();

        wait = new WebDriverWait(driver,Duration.ofSeconds(20));
        js=(JavascriptExecutor)driver;



        driver.manage().window().maximize(); // Ekranı max yapıyor.
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20)); // 20 sn mühlet: sayfayı yükleme mühleti
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20)); // 20 sn mühlet: elementi bulma mühleti
    }


    public static void BekleKapat() // tearDown()
    {
        MyFunc.Bekle(3);
        driver.quit();
    }
}
