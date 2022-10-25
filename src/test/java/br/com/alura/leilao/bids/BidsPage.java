package br.com.alura.leilao.bids;

import br.com.alura.leilao.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BidsPage extends PageObject {

    public BidsPage(WebDriver browser) {
        super(browser);
    }

    public void registerBid(String value) {
        browser.findElement(By.id("valor")).sendKeys(value);
        browser.findElement(By.id("btnDarLance")).submit();
    }

    public boolean BidIsRegistered(String date, String user, String value) {
        WebElement tableRow = this.browser.findElement(By.cssSelector("#lancesDados tbody tr:last-child"));
        WebElement columnDate = tableRow.findElement(By.cssSelector("td:nth-child(1)"));
        WebElement columnUser = tableRow.findElement(By.cssSelector("td:nth-child(2)"));
        WebElement columnValue = tableRow.findElement(By.cssSelector("td:nth-child(3)"));

        return columnDate.getText().equals(date)
                && columnUser.getText().equals(user)
                && columnValue.getText().equals(value);
    }

    public boolean contains(String message) {return browser.getPageSource().contains(message);}
}
