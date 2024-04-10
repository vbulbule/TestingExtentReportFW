package utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverInstannce {
	
	public static String sUrl, sHubUrl, sHubPort, sPlatform="windows";
	public static String runOn = "Server";
	
	private static final ThreadLocal<RemoteWebDriver> remoteWebDriver = new ThreadLocal<RemoteWebDriver>();
	private static final ThreadLocal<WebDriverWait> wait = new ThreadLocal<WebDriverWait>();
	
	public void setWait() {
		wait.set(new WebDriverWait(getDriver(), Duration.ofSeconds(50)));
	}

	public WebDriverWait getWait() {
		return wait.get();
	}
	public RemoteWebDriver getDriver() {
		return remoteWebDriver.get();
	}
	
	public void setDriver(String browser) {
		  DesiredCapabilities dc = new DesiredCapabilities();
		switch (browser) {
		case "chrome":
			HashMap<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("download.default_directory", "./");
			prefs.put("safebrowsing.enabled", "false");
			
			ChromeOptions co = new ChromeOptions();
			co.setExperimentalOption("prefs", prefs);
			co.addArguments("--disable-notifications");
			
			dc.setCapability(ChromeOptions.CAPABILITY, co);
			remoteWebDriver.set(new ChromeDriver(co));
			break;
		case "firefox":
			remoteWebDriver.set(new FirefoxDriver());
			break;
		case "internet explorer":
			remoteWebDriver.set(new InternetExplorerDriver());
		default:
			break;
		}
	}
	
	public void setDriver(String browser, boolean grid) {
           DesiredCapabilities dc = new DesiredCapabilities();
		
		if(grid) {
			switch (browser) {
			case "chrome":
				dc.setBrowserName("chrome");
				HashMap<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("download.default_directory", "./");
				prefs.put("safebrowsing.enabled", "false");
				
				ChromeOptions co = new ChromeOptions();
				co.setExperimentalOption("prefs", prefs);
				co.addArguments("--disable-notifications");
				
				dc.setCapability(ChromeOptions.CAPABILITY, co);
				
				if(sPlatform.equalsIgnoreCase("windows"))
					dc.setPlatform(Platform.WINDOWS);
				else if(sPlatform.equalsIgnoreCase("linux"))
					dc.setPlatform(Platform.LINUX);
				
				try {
					remoteWebDriver.set(new RemoteWebDriver(new URL("http://"+sHubUrl+":"+sHubPort+"/wd/hub"),dc));
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				break;
		 		
			case "ie":
				
				
				
				break;
			case "firefox":
				
				
				
				break;
			default:
				break;
			}
			
			
		}
		
	}
	
	
	
	
	
	

}
