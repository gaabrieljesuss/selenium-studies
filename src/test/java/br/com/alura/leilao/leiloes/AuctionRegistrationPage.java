package br.com.alura.leilao.leiloes;

import br.com.alura.leilao.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AuctionRegistrationPage extends PageObject {
    private final String URL_REGISTER_AUCTIONS = "http://localhost:8080/leiloes/new";

    public AuctionRegistrationPage(WebDriver browser) {
        super(browser);
    }

    public AuctionsPage registerAuction(String auctionName, String initialValue, String openingDate) {
        browser.findElement(By.id("nome")).sendKeys(auctionName);
        browser.findElement(By.id("valorInicial")).sendKeys(initialValue);
        browser.findElement(By.id("dataAbertura")).sendKeys(openingDate);
        browser.findElement(By.id("button-submit")).submit();

        return new AuctionsPage(browser);
    }

    public boolean isAuctionsRegistrationPage() {
        return browser.getCurrentUrl().equals(URL_REGISTER_AUCTIONS);
    }
}
