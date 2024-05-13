package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.InetSocketAddress;
import java.net.URL;
import java.time.Duration;
import java.util.*;


public class CustomisedAction {
    public static void main(String[] args) throws InterruptedException {

        ArrayList<String> proxies = getProxy();
//        ArrayList<String> proxies = new ArrayList<>();
//       proxies.add("14.231.200.48:80");

       for (String proxy : proxies){
           System.out.print(proxy);
           if (isProxyAvailable(proxy)){
               System.out.println("  Available");
           }
           else{
               System.out.println("  Unavailable");
           }
       }
    }
    public static void ScrollDown(WebDriver driver, int time) throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for (int i = 0; i < time; i++) {
            if (i % 10 == 0){
                js.executeScript("window.scrollTo(0,0)", "");
                System.out.println(i);
            }
            js.executeScript("window.scrollTo(0, document.body.scrollHeight - 1000)", "");
            Thread.sleep(3000);
        }
    }

    public static String monthParser(String input) {
        // Create a HashMap to map month names to their corresponding numbers
        Map<String, String> monthMap = new HashMap<>();
        monthMap.put("january", "01");
        monthMap.put("february", "02");
        monthMap.put("march", "03");
        monthMap.put("april", "04");
        monthMap.put("may", "05");
        monthMap.put("june", "06");
        monthMap.put("july", "07");
        monthMap.put("august", "08");
        monthMap.put("september", "09");
        monthMap.put("october", "10");
        monthMap.put("november", "11");
        monthMap.put("december", "12");

        String inputLowercase = input.toLowerCase();

        if (monthMap.containsKey(inputLowercase)) {
            return monthMap.get(inputLowercase);
        } else {
            System.out.println("This is not a month");
            return input;
        }
    }

    public static String dateParser(int input) {
        String date;
        if (0 < input && input < 10){
            date = "0" + input;
        }
        else {
            date = Integer.toString(input);
        }
        return date;
    }
    public static String dateParser(String input) {
        if (input.length() < 2){
            return "0" + input;
        }
        else return input;
    }

    public static ArrayList<String> getProxy() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://free-proxy-list.net/");
        WebElement proxyListElement = driver.findElement(By.cssSelector("#list > div > div.text-center > ul > li:nth-child(5) > a"));
        proxyListElement.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String proxies = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("form-control"))).getText();

        ArrayList<String> proxyList = new ArrayList<>();
        String[] pL = proxies.split("\n");

        for (int i = pL.length - 1; i > 2; i--){
            proxyList.add(pL[i]);
        }   

//        for (String proxy : proxyList){
//            System.out.println(proxy);
//        }
        driver.quit();

        return proxyList;
    }

    public static boolean isProxyAvailable(String proxyAddress) {
        String[] p = proxyAddress.split(":");
        String proxy_address = p[0];
        int port = Integer.parseInt(p[1]);
        try {
            // Create a proxy object with the provided proxy address
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxy_address, port));

            // Open a connection to a sample URL using the proxy
            URL url = new URL("http://www.example.com");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection(proxy);

            // Set connection timeout to avoid long waits
            connection.setConnectTimeout(5000); // 5 seconds

            // Check if the connection is successful (HTTP status 200)
            int responseCode = connection.getResponseCode();
            return responseCode == HttpURLConnection.HTTP_OK;
        } catch (Exception e) {
            // Proxy is not available or connection failed
            return false;
        }
    }
    public static void setProxy(String proxyAddress){
        String[] p = proxyAddress.split(":");
        String proxy_address = p[0];
        int port = Integer.parseInt(p[1]);

    }

    public static int parseStringToInt(String input) {
        String outputString = input;
        if (outputString.contains(",")) {
            outputString = outputString.replaceAll(",", "");
        }
        if (outputString.contains(".")){
            outputString = outputString.substring(0,outputString.indexOf("."));
        }

        int outputInt = Integer.parseInt(outputString);

        return outputInt;
    }
}
