package com.proyecto.bookingrestaurantapi;

import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumTest {
	
	@Test
	public void pruebaReservation() {
		MockitoAnnotations.openMocks(this);
		System.setProperty("webdriver.chrome.driver","Resources/chromedriver_win32/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://localhost:4200/");
		
		
		
	}
	
	private WebDriver driver;

}
