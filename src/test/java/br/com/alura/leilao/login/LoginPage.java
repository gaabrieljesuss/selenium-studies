package br.com.alura.leilao.login;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginPage {
    private final String URL_LOGIN = "http://localhost:8080/login";
    private WebDriver browser;


    public LoginPage() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        this.browser = new ChromeDriver();
        this.browser.navigate().to(URL_LOGIN);
    }

    public void dispose() {
        this.browser.quit();
    }

    public void fillLoginForm(String username, String password) {
        this.browser.findElement(By.id("username")).sendKeys(username);
        this.browser.findElement(By.id("password")).sendKeys(password);
    }

    public void logIn() {
        this.browser.findElement(By.id("login-form")).submit();
    }

    public String getUrlLogin() {
        return URL_LOGIN;
    }

    public String getCurrentUrl() {
        return browser.getCurrentUrl();
    }

    public String getUsernameLoggedIn() {
        try {
            return browser.findElement(By.id("usuario-logado")).getText();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public boolean contains(String message) {
        return browser.getPageSource().contains(message);
    }

    public void navigateToAuctionPage() {
        browser.navigate().to("http://localhost:8080/leiloes/2");
    }

    public String getErrorPageUrl() {
        return URL_LOGIN + "?error";
    }
}
