package br.com.alura.leilao.leiloes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AuctionRegistrationPage {
    private WebDriver browser;

    public AuctionRegistrationPage(WebDriver browser) {
        this.browser = browser;
    }

    public void dispose() {
        this.browser.quit();
    }

    public AuctionsPage registerAuction(String auctionName, String initialValue, String openingDate) {
        browser.findElement(By.id("nome")).sendKeys(auctionName);
        browser.findElement(By.id("valorInicial")).sendKeys(initialValue);
        browser.findElement(By.id("dataAbertura")).sendKeys(openingDate);
        browser.findElement(By.id("button-submit")).submit();

        return new AuctionsPage(browser);
    }
}
