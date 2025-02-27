package Pages;

import java.time.Duration;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class HomePage {
AndroidDriver driver;

@AndroidFindBy(xpath="(//androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.eraspace.app:id/rvIconsLabelMenu\"])[2]/android.view.ViewGroup[4]")
private WebElement buttonLewati;

@AndroidFindBy(id="com.eraspace.app.home:id/btnLogin")
private WebElement ButtonMasuk;
//@AndroidFindBy(xpath="//androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.eraspace.app:id/rvIconsLabelMenu\"])[2]/android.view.ViewGroup[5]")
//private WebElement buttonLanjut;
@AndroidFindBy(xpath="(//androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.eraspace.app:id/rvIconsLabelMenu\"])[1]")
 WebElement listMenu;

@AndroidFindBy(id="com.eraspace.app.home:id/navigationProfile")
WebElement btnNavAkun;
public HomePage(AndroidDriver driver) {
	this.driver = driver;
	PageFactory.initElements(new AppiumFieldDecorator(driver,Duration.ofSeconds(3)),this);
}

public void clickLewati() {
	buttonLewati.click();
}
public void goToLoginPage() {
	ButtonMasuk.click();
}
public void goToProfile() {
	btnNavAkun.click();
}

public WebElement getListmenu() {
	return listMenu;
}

}
