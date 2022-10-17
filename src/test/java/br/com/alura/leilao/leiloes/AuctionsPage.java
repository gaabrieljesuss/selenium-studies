package br.com.alura.leilao.leiloes;

import br.com.alura.leilao.PageObject;
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
}
