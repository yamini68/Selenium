package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class p1 {

    public static void main(String args[]) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_win32\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);

        driver.get("https://rahulshettyacademy.com/client/");
        driver.manage().window().maximize();
        driver.findElement(By.id("userEmail")).sendKeys("anala@example.com");
        driver.findElement(By.id("userPassword")).sendKeys("Anala@123");
        driver.findElement(By.id("login")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By productSelector = By.cssSelector(".mb-3");

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productSelector));
        List<WebElement> products = driver.findElements(productSelector);
       // System.out.println(products.size());

//        for (WebElement item : products) {
//
//            String text = item.findElement(By.cssSelector("b")).getText();
//            System.out.println(text);
//        }

     WebElement item=   products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).
                findFirst().orElse(null);

  item.findElement(By.cssSelector(".card-body button:last-of-type")).click();

        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));

        driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
        Thread.sleep(3000l);
      Boolean value=  driver.findElement(By.xpath("//div[@class='cartSection']/h3")).getText().equalsIgnoreCase("ZARA COAT 3");
        Assert.assertTrue(value);
       wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class,'subtotal')]//button")));
        driver.findElement(By.xpath("//*[contains(@class,'subtotal')]//button")).click();
        driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("ind");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='ng-star-inserted']")));
       List<WebElement> names=driver.findElements(By.xpath("//span[@class='ng-star-inserted']"));
      WebElement select= names.stream().filter(s->s.getText().equals("India")).findFirst().orElse(null);
      select.click();
      driver.findElement(By.xpath("//div[@class='actions']/a")).click();

      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class='hero-primary']")));
       Boolean conform= driver.findElement(By.xpath("//h1[@class='hero-primary']")).getText().equalsIgnoreCase("THANKYOU FOR THE ORDER.");
       Assert.assertTrue(conform);

      driver.quit();







    }

    }


