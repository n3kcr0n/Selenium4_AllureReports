package org.coreFramework.base;


import org.coreFramework.driverFactory.DriverFactory;
import org.coreFramework.utils.TestUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;

public class BaseTest implements ITestListener {
    //ThreadLocal use to share the intance of driver to all thread
    protected ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @BeforeMethod
    public void setup() {
        WebDriver driver = new DriverFactory().initDriver();
        this.driver.set(driver);

    }

    @AfterMethod()
    public void tearDown(ITestResult result) {
        if(!result.isSuccess()){
            String caseName = result.getMethod().getMethodName();
            File destination = new File("target"+File.separator+"screenshots"+File.separator+caseName+".png");
            TestUtils.takeScreenShots(driver.get(),destination);
        }
        driver.get().quit();
    }

}