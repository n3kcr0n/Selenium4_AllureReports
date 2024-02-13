package org.coreFramework.utils;

import com.google.common.primitives.Booleans;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GenericValidations  {
    public static boolean validateElementsVisibility(WebDriver driver,By webElementsList){
        List<WebElement> elements = driver.findElements(webElementsList);
        boolean[] flag = new boolean[elements.size()];
        int c =0;
        for(WebElement element:elements){
            flag[c] = element.isDisplayed();
            c++;
        }

        return !Booleans.contains(flag, false);
    }

    public static boolean validateElementVisibility(WebDriver driver,By webElement) {
        return driver.findElement(webElement).isDisplayed();
    }
}
