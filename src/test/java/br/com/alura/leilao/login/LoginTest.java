package br.com.alura.leilao.login;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {

    String loginUrl = "http://localhost:8080/login";

    @Test
    public void shouldLoginSuccessfully() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        WebDriver browser = new ChromeDriver();
        browser.navigate().to(loginUrl);
        browser.findElement(By.id("username")).sendKeys("fulano");
        browser.findElement(By.id("password")).sendKeys("pass");
        browser.findElement(By.id("login-form")).submit();

        Assertions.assertNotEquals(browser.getCurrentUrl(), loginUrl);
        Assertions.assertEquals("fulano", browser.findElement(By.id("usuario-logado")).getText());
        browser.quit();
    }
}
