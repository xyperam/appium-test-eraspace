package Pages;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LoginPage {
AndroidDriver driver;

public  LoginPage(AndroidDriver driver) {
	this.driver = driver;
	PageFactory.initElements(new AppiumFieldDecorator(driver,Duration.ofSeconds(3)),this);
}

@AndroidFindBy(id="com.eraspace.app.membership:id/edtPhoneOrEmail")
WebElement inputEmailField;

@AndroidFindBy(id="com.eraspace.app.membership:id/edtPassword")
WebElement inputPasswordField;

@AndroidFindBy(id="com.eraspace.app.membership:id/btnLogin")
WebElement btnLogin;

@AndroidFindBy(id="com.eraspace.app.membership:id/tvError")
WebElement errorMsg;
public void inputEmail(String email){
	inputEmailField.sendKeys(email);
}
public void inputPassword(String password) {
	inputPasswordField.sendKeys(password);
}
public void clickBtnLogin() {
	btnLogin.click();
}
public WebElement errorLoginMsg() {
	return errorMsg;
}


}

