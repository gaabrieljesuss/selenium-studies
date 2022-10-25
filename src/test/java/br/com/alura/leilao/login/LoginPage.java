package br.com.alura.leilao.login;

import br.com.alura.leilao.PageObject;
import br.com.alura.leilao.auctions.AuctionsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class LoginPage extends PageObject {
    private final String URL_LOGIN = "http://localhost:8080/login";

    public LoginPage() {
        super(null);
        this.browser.navigate().to(URL_LOGIN);
    }

    public LoginPage(WebDriver browser) {
        super(browser);
    }

    public void fillLoginForm(String username, String password) {
        this.browser.findElement(By.id("username")).sendKeys(username);
        this.browser.findElement(By.id("password")).sendKeys(password);
    }

    public AuctionsPage logIn() {
        this.browser.findElement(By.id("login-form")).submit();
        return new AuctionsPage(browser);
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
