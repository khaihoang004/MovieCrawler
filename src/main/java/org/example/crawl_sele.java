package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class crawl_sele {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "D:\\khaih\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless"); // Run Chrome in headless mode, without UI
        WebDriver driver = new ChromeDriver(options);
try {
    driver.get("https://www.themoviedb.org/movie/29228?language=en-US");
}
catch (Exception e){
    e.printStackTrace();
}
//        WebElement title = driver.findElement(By.cssSelector("#original_header > div.header_poster_wrapper.true > section > div.title.ott_true > div > span.release"));
//        System.out.println(driver.getText());
    }
}
