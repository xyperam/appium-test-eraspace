package Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProfilePage {
	AndroidDriver driver;
	public ProfilePage(AndroidDriver driver){
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver,Duration.ofSeconds(3)),this);
	}
	
	@AndroidFindBy(id="com.eraspace.app:id/tvName")
	WebElement nameText;

	@AndroidFindBy(id="com.eraspace.app:id/tvName")
	WebElement pointText;
	
	public void closeTooltip() {
		driver.findElement(By.xpath("//android.widget.TextView[contains(@text, 'Lewati')]")).click();
	}
	
	public WebElement getNameText() {
		return  nameText;
	}
	
	public WebElement getPointText() {
		return pointText;
	}
}
