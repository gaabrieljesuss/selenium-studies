package br.com.alura.leilao.auctions;

import br.com.alura.leilao.PageObject;
import br.com.alura.leilao.bids.BidsPage;
import br.com.alura.leilao.login.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AuctionsPage extends PageObject {
    private final String URL_AUCTIONS = "http://localhost:8080/leiloes";

    public AuctionsPage(WebDriver browser) {
        super(browser);
    }

    public AuctionRegistrationPage loadForm() {
        this.browser.findElement(By.id("novo_leilao_link")).click();
        return new AuctionRegistrationPage(browser);
    }

    public AuctionRegistrationPage loadEditForm() {
        WebElement tableRow = this.browser.findElement(By.cssSelector("#tabela-leiloes tbody tr:last-child"));
        tableRow.findElement(By.cssSelector("td:nth-child(5)")).click();
        return new AuctionRegistrationPage(browser);
    }

    public boolean auctionIsRegistered(String auctionName, String initialValue, String openingDate) {
        WebElement tableRow = this.browser.findElement(By.cssSelector("#tabela-leiloes tbody tr:last-child"));
        WebElement columnName = tableRow.findElement(By.cssSelector("td:nth-child(1)"));
        WebElement columnOpeningDate = tableRow.findElement(By.cssSelector("td:nth-child(2)"));
        WebElement columnValue = tableRow.findElement(By.cssSelector("td:nth-child(3)"));

        return columnName.getText().equals(auctionName)
                && columnOpeningDate.getText().equals(openingDate)
                && columnValue.getText().equals(initialValue);
    }

    public boolean isAuctionsPage() {
        return browser.getCurrentUrl().equals(URL_AUCTIONS);
    }

    public boolean containsMessage(String message) {
        return browser.getPageSource().contains(message);
    }

    public void logout() {
        this.browser.findElement(By.id("logout")).click();
    }

    public LoginPage logIn() {
        this.browser.findElement(By.id("log-in")).click();
        return new LoginPage(this.browser);
    }

    public BidsPage loadBids() {
        WebElement tableRow = this.browser.findElement(By.cssSelector("#tabela-leiloes tbody tr:last-child"));
        tableRow.findElement(By.cssSelector("td:nth-child(5)")).click();

        return new BidsPage(browser);
    }
}
