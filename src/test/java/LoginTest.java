import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.HomePage;
import Pages.LoginPage;
import Pages.ProfilePage;

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
	}
	@Test(description = "TC 1.2 - Login with valid data")
	public void loginTest() throws InterruptedException {
		System.out.println("Navigating to Home Page...");
		driver.findElement(By.id("com.eraspace.app:id/tvLabelButton")).click();
		Thread.sleep(5000);
		homePage.clickLewati();
		homePage.goToLoginPage();
		loginPage.inputEmail();
		loginPage.inputPassword();
		loginPage.clickBtnLogin();
		wait.until(ExpectedConditions.visibilityOf(homePage.getListmenu()));
		Assert.assertTrue(homePage.getListmenu().isDisplayed(),"List menu tidak muncul");
		homePage.goToProfile();
		wait.until(ExpectedConditions.visibilityOf(profilePage.getNameText()));
		Assert.assertTrue(profilePage.getNameText().isDisplayed(),"Nama Tidak muncul");
		System.out.println(profilePage.getNameText().getText());
		Assert.assertTrue(profilePage.getPointText().isDisplayed(),"Point Tidak muncul");
		System.out.println(profilePage.getPointText().getText());
	}
}
