package org.coreFramework.utils;


import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;

public class TestUtils {


    public static void takeScreenShots(WebDriver driver, File fileDestination) {
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, fileDestination);
            InputStream is = new FileInputStream(fileDestination);
            Allure.addAttachment("screenshot", is);
        } catch (IOException e) {
            throw new RuntimeException(e.getCause());
        }
    }

    @Step
    public static void waitFor(WebDriver driver) {
        int secondsToWait = Integer.parseInt(ConfigUtils.getInstance().getProperty("defaultWaitSeconds"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(secondsToWait));
    }
    @Step
    public static void waitFor(WebDriver driver, Integer sec) {
        if (sec == null || sec == 0) {
            throw new RuntimeException("Error: Waiting Time is undefined");
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(sec));
    }

    @Step
    public static void waitForElementVisibility(WebDriver driver, By element, Integer sec) {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(ConfigUtils.getInstance().getProperty("defaultWaitSeconds"))));
        wait.until(d -> driver.findElement(element).isDisplayed());
    }
}
