package automatizacionAspor.AUTOMATIZACIONASPOR;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BaseClass {
	private WebDriver driver = null;
	private WebDriverWait wait = null;
	
	protected WebDriver getDriver() { 
		return this.driver;
	}
	
	protected WebDriverWait getWait() {
		return this.wait;
	}
	
	
	@BeforeEach
	public void start() throws MalformedURLException{
		//Crear Driver y Espera explícita
		
		DesiredCapabilities cap = new DesiredCapabilities();
        cap.setBrowserName(BrowserType.CHROME);
        driver = new RemoteWebDriver(new URL("http://rapel.aspor.cl:4444/wd/hub"),cap);
		
		wait = new WebDriverWait(driver, 60); //max 60 segundos
		
		driver.get(Configuration.URL);
		driver.manage().window().maximize();
	
	}
	
	@AfterEach
	public void finish() throws Exception{
		driver.quit(); 
	}
	
		
	/*
	 * takeScreenShotTest es una clase que permite guardar evidencias de imágenes en un repositorio local.
	 */
	public static void takeScreenShotTest(WebDriver driver, String imageName) {
		TakesScreenshot captura = (TakesScreenshot) driver; //castear de un WebDriver a TakesScreenshot
		File archivoCaptura = captura.getScreenshotAs(OutputType.FILE);
		String path = String.format("%s%s-%s-%s.png", Configuration.SCREENSHOT_DIR, "Captura",imageName,System.currentTimeMillis());
		archivoCaptura.renameTo(new File(path));
	}
}
