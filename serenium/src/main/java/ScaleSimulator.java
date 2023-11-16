import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ScaleSimulator {
    private WebDriver driver;

    public ScaleSimulator() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/ZZJ/Downloads/chromedriver-win64/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://sdetchallenge.fetch.com/");
    }

    public void weigh(List<GoldBar> leftBars, List<GoldBar> rightBars) {
        WebElement[] leftBowl = new WebElement[3];
        WebElement[] rightBowl = new WebElement[3];
        int n = leftBars.size();
        for (int i = 0; i < 3; i++){
            leftBowl[i] = driver.findElement(By.id("left_" + i));
            leftBowl[i].sendKeys(Keys.BACK_SPACE);
        }

        for (int i = 0; i < 3; i++){
            rightBowl[i] = driver.findElement(By.id("right_" + i));
            rightBowl[i].sendKeys(Keys.BACK_SPACE);
        }


        for (int i = 0; i < n; i++) {
            leftBowl[i] = driver.findElement(By.id("left_" + i));
            leftBowl[i].sendKeys(String.valueOf(leftBars.get(i).getId()));
        }
        for (int i = 0; i < n; i++) {
            rightBowl[i] = driver.findElement(By.id("right_" + i));
            rightBowl[i].sendKeys(String.valueOf(rightBars.get(i).getId()));
        }

        List<WebElement> currentListItems = driver.findElements(By.cssSelector("div.game-info li"));
        int currentSize = currentListItems.size();
        driver.findElement(By.id("weigh")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until((WebDriver d) -> d.findElements(By.cssSelector("div.game-info li")).size() > currentSize);
    }

    public String getWeighingResult() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement weighingList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.game-info")));

        List<WebElement> listItems = weighingList.findElements(By.tagName("li"));
        if (!listItems.isEmpty()) {
            return listItems.get(listItems.size() - 1).getText();
        }

        return "No result found";
    }

    public void resetScale() {
//        try {
//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//            WebElement resetButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.game div button#reset")));
//            if (resetButton.isEnabled()) {
//                System.out.println("Reset button is enabled. Clicking it now.");
//                resetButton.click();
//            } else {
//                System.out.println("Reset button is not enabled.");
//            }
//        } catch (Exception e) {
//            System.out.println("An exception occurred in resetScale method.");
//            e.printStackTrace();
//        }
    }

    public void selectGoldBar(int barId) {
        driver.findElement(By.id("coin_" + barId)).click();
    }

    public String getAlertMessage() {
        String alertText;
        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            alertText = alert.getText();
            alert.accept();
            return alertText;
        } catch(Exception e) {
            return "No alert";
        }
    }

    public void close() {
        driver.quit();
    }
}
