package org.coreFramework.pages;



import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.coreFramework.apis.UserApi;
import org.coreFramework.models.TestUser;
import org.coreFramework.utils.ConfigUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

public class RegistrationPage {
    private static RegistrationPage registrationPage;
    //Constructor
    private RegistrationPage(){

    }
    public static RegistrationPage getInstance(){
        if(registrationPage == null){
            registrationPage = new RegistrationPage();
        }
        return registrationPage;
    }

    //Elements
    private final By firstNameInput = By.cssSelector("input[data-testid='first-name']");
    private final By lastNameInput = By.cssSelector("input[data-testid='last-name']");
    private final By emailInput = By.cssSelector("input[data-testid='email']");
    private final By pw = By.cssSelector("input[data-testid='password']");
    private final By confirmPw = By.cssSelector("input[data-testid='confirm-password']");
    private final By signUpButton = By.cssSelector("button[data-testid='submit']");

    //Methods
    @Step("Load the signup page")
    public void load(WebDriver driver){
        driver.get(ConfigUtils.getInstance().getAppUrl()+"/signup");
    }
    public void registerUser(WebDriver driver, String fName, String lName, String email, String password){
        driver.findElement(firstNameInput).sendKeys(fName);
        driver.findElement(lastNameInput).sendKeys(lName);
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(pw).sendKeys(password);
        driver.findElement(confirmPw).sendKeys(password);
        driver.findElement(signUpButton).click();
    }
    @Step("Register user by using API")
    public void registerUserByAPI(WebDriver driver, TestUser user){
        Response res = UserApi.getInstance().registerUserByApi(user);
        String accessToken = res.path("access_token");
        String userID = res.path("userID");
        String fName = res.path("firstName");
        user.setAccessToken(accessToken);

        //Selenium Cookies
        Cookie accessTokenCookie = new Cookie("access_token",accessToken);
        Cookie userIDCookie = new Cookie("userID",userID);
        Cookie firstName = new Cookie("firstName",fName);

        //Insert Cookie to the browser
        //When inserting cookie the instance of webapp must be called
        driver.manage().addCookie(accessTokenCookie);
        driver.manage().addCookie(userIDCookie);
        driver.manage().addCookie(firstName);

        //Reload the page with the injected cookies
        getInstance().load(driver);
    }

}
