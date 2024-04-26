package com.letcode.automation;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.basetest.BaseTest;

public class TableAutomation extends BaseTest {

	@Test(priority = 1, enabled = false)
	public void verifyShoppingListTotal() {
		
		// get all rows in the Shopping List table
		WebElement shoppingTable = driver.findElement(By.xpath("//table[@id='shopping']"));
		List<WebElement> shoppingTableRows = shoppingTable.findElements(By.tagName("tr"));

		// to hold the individual item prices from the table
		List<Integer> itemPrices = new ArrayList<Integer>();

		// get each row and get row values
		shoppingTableRows.forEach(row -> {

			List<WebElement> rowData = row.findElements(By.tagName("td"));
			for (WebElement rowValue : rowData) {
				int price;

				try {
					price = Integer.parseInt(rowValue.getText());

				} catch (NumberFormatException e) {
					price = 0;
				}

				itemPrices.add(price);
			}

		});

		// get the displayed price from the table,
		Integer aTotalPrice = 
				Integer.parseInt(driver.findElement(By.xpath("//table[@id='shopping']/tfoot/td[2]")).getText());

		// get the expected price
		int eTotalPrice = 0;
		for( Integer prc:itemPrices) {
			eTotalPrice = eTotalPrice + prc;
		}
		
		Assert.assertEquals(aTotalPrice, eTotalPrice, "Total Price does not match");
	}
	
	
	@Test(priority = 2, enabled = false)
	public void markRajAsPresent() {
		
		// get the rows in the List
		List<WebElement> rowsList = driver.findElements(By.xpath("//table[@id='simpletable']/tbody/tr"));
		
		// loop thru each row and access the TD for each row
		rowsList.stream().forEach(row -> {
			List<WebElement> tdList = row.findElements(By.tagName("td"));
			for(WebElement td:tdList) {
				String tdValue = td.getText();
				if(tdValue.equalsIgnoreCase("RAJ")) {
					WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(12));
					WebElement rajCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.id("second")));
					rajCheckbox.click();
				}
			}
		});
		
	}
	
	@Parameters ("SORTING_COLUMN") 
	@Test(priority = 3)
	public void verifyIfSortingWorks(String columnName) {
		
		System.out.println("column name : " + columnName);
		//get the rows
		WebElement tableHeaders = driver.findElement(By.xpath("//table[contains(@class,'mat-sort table')]/thead"));
		
		
	}

}
