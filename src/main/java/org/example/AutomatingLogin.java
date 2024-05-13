package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AutomatingLogin {
    public static void main(String[] args) {

    }
    public static void Login(String URL, String username, String password){
        System.setProperty("webdriver.chrome.driver", "D:\\khaih\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URL);
        WebElement userNameBox = driver.findElement(By.cssSelector("#username"));
        userNameBox.sendKeys(username);
        WebElement passwordBox = driver.findElement(By.cssSelector("#password"));
        passwordBox.sendKeys(password);
        WebElement loginButton = driver.findElement(By.cssSelector("#login_button"));
        loginButton.click();
    }
}
