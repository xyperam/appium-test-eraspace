import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import Pages.HomePage;
import Pages.LoginPage;
import Pages.ProfilePage;
import io.appium.java_client.AppiumBy;

public class LoginTest extends Base{
	HomePage homePage;
	LoginPage loginPage;
	ProfilePage profilePage;
	WebDriverWait wait;
	@BeforeMethod
	public void setUpPage(){
		this.homePage = new HomePage(driver);
		this.loginPage = new LoginPage(driver);
		this.profilePage = new ProfilePage(driver);
		this.wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		
		driver.activateApp("com.eraspace.app");
		
	}
	@DataProvider(name = "validDataLogin")
	public Object[][] getData(){
		return new Object[][]{
			{"ilhamimaninuralam@gmail.com","nullPoint5!"}
		};
	}
	
	@DataProvider(name = "invalidDataLogin")
	public Object[][] getDataInvalid(){
		return new Object[][]{
			{"ilhamimaninuralam@gmail.com","invalidPassword"}
		};
	}
	
	@Test(description = "TC 1.1 - Login with invalid data", dataProvider = "invalidDataLogin")
	public void loginFailedTest(String email, String password) throws InterruptedException {
		
		try {
			wait.until(ExpectedConditions.elementToBeClickable(homePage.getNextBtn()));
			homePage.getNextBtn().click();
		}catch(Exception e){
			System.out.println("tidak ditemukan lanjut ke step selanjutnya");
		}
		try {
			wait.until(ExpectedConditions.elementToBeClickable(homePage.getLewatiBtn()));
			homePage.getLewatiBtn().click();
		}catch(Exception e){
			System.out.println("tidak ditemukan lanjut ke step selanjutnya");
		}
		wait.until(ExpectedConditions.elementToBeClickable(homePage.btnMasuk()));
		homePage.btnMasuk().click();
		loginPage.inputEmail(email);
		loginPage.inputPassword(password);
		loginPage.clickBtnLogin();
		wait.until(ExpectedConditions.visibilityOf(loginPage.errorLoginMsg()));
		Assert.assertTrue(loginPage.errorLoginMsg().isDisplayed(),"Error Message tidak muncul");
		String errorMsg = loginPage.errorLoginMsg().getText();
		System.out.println("Test Passed: tampil error -  "+ errorMsg);
	}
	
	@Test(description = "TC 1.2 - Login with valid data", dataProvider = "validDataLogin")
	public void loginTest(String email, String password) throws InterruptedException {
		System.out.println("Navigating to Home Page...");
		wait.until(ExpectedConditions.elementToBeClickable(homePage.btnMasuk()));
		homePage.btnMasuk().click();
		loginPage.inputEmail(email);
		loginPage.inputPassword(password);
		loginPage.clickBtnLogin();
		wait.until(ExpectedConditions.visibilityOf(homePage.getListmenu()));
		Assert.assertTrue(homePage.getListmenu().isDisplayed(),"List menu tidak muncul");
		System.out.println("List menu ditemukan");
		homePage.goToProfile();
		wait.until(ExpectedConditions.visibilityOf(profilePage.getNameText()));
		//driver.findElement(By.xpath("//button[contains(text(), 'Lewati')]")).click();
		Assert.assertTrue(profilePage.getNameText().isDisplayed(),"Nama Tidak muncul");
		System.out.println(profilePage.getNameText().getText());
		Assert.assertTrue(profilePage.getPointText().isDisplayed(),"Point Tidak muncul");
		System.out.println(profilePage.getPointText().getText());
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		if(driver !=null) {
			driver.terminateApp("com.eraspace.app");
		}
	}
}
