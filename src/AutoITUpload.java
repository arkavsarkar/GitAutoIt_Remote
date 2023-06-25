import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import graphql.Assert;

public class AutoITUpload {

	public static void main(String[] args) throws InterruptedException, IOException {
		//customized downloadpath 
		String downloadPath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\drivers\\chromedriver.exe");
		//customizing chrome preference 
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadPath);
		ChromeOptions option = new ChromeOptions();
		//passing chromeprefrence to chromeoption
		option.setExperimentalOption("prefs", chromePrefs);
		WebDriver driver = new ChromeDriver(option);
		driver.get("https://www.ilovepdf.com/pdf_to_jpg");
		driver.findElement(By.cssSelector("[class*='uploader__btn']")).click();
		Thread.sleep(3000);
		Runtime.getRuntime().exec("C:\\Selenium\\AutoIT\\fileUpload.exe");
		// [class*="btn--process"]
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class*='btn--process']")));
		driver.findElement(By.cssSelector("[class*='btn--process']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pickfiles")));
		driver.findElement(By.id("pickfiles")).click();
		
		File f = new File(downloadPath+"/ilovepdf_pages-to-jpg.zip");
		Thread.sleep(3000);
		if (f.exists()) {
			System.out.println("file is found");
			Assert.assertTrue(f.exists());
			if (f.delete()) {
			System.out.println("file deleted");
			}
		}

	}

}
