import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class Base {
	AndroidDriver driver;
	AppiumDriverLocalService service;
	
	@BeforeClass(alwaysRun = true)
	 public void configureAppium() throws MalformedURLException {
		Map<String , String> env = new HashMap<String , String>(System.getenv());
		env.put("ANDROID_HOME", "C:\\Users\\ASUS\\AppData\\Local\\Android\\Sdk");
		env.put("JAVA_HOME", "C:\\Program Files\\Java\\jdk-11.0.16.1");
		
        service = new AppiumServiceBuilder()
                .withAppiumJS(new File("C:\\Users\\ASUS\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
                .withIPAddress("127.0.0.1")
                .usingPort(4723)
                .withTimeout(Duration.ofSeconds(300))
                .build();
        
        service.start();
        
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Automation");
        options.setAppPackage("com.eraspace.app");
        options.setAppActivity("com.eraspace.app.presentation.SplashScreenActivity");
        options.setCapability("autoGrantPermissions", true);
        options.setCapability("appWaitDuration", 30000);
        
        
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        if (driver == null) {
            System.out.println("Driver initialization failed.");
        } else {
            System.out.println("Driver initialized successfully: " + driver);
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
	
	@AfterClass
	public void tearDown() {
		if(driver !=null) {
			driver.quit();
		}
	}
}
