package br.com.alura.leilao.leiloes;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AuctionsPage {
    private final String URL_REGISTER_AUCTIONS = "http://localhost:8080/leiloes";
    private WebDriver browser;

    public AuctionsPage(WebDriver browser) {
        this.browser = browser;
    }

    public void dispose() {
        this.browser.quit();
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
}
