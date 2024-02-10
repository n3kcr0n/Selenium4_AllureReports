package Test;


import io.qameta.allure.*;
import org.coreFramework.base.BaseTest;
import org.coreFramework.models.TestUser;
import org.coreFramework.pages.RegistrationPage;
import org.coreFramework.pages.TodoPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.qameta.allure.SeverityLevel.NORMAL;


public class UserRegistrationTest extends BaseTest {

    @Test
    @Epic("Web Interface")
    @Feature("Essential features")
    @Story("Authentication")
    @Description("Verify to register user from UI")
    @Severity(NORMAL)
    @Owner("John Doe")
    @Link(name="Website",url="https://todo.qacart.com/signup")
    @Issue("AUTH-123")
    @TmsLink("TMS-456")
    public void beAbleToRegisterUser(){
        RegistrationPage.getInstance().load(driver.get());
        TestUser user = new TestUser();
        RegistrationPage.getInstance().registerUser(driver.get(),user.getFirstName(),user.getLastName(),user.getEmail(),user.getPassword());

    }

    @Test
    @Epic("Web Interface")
    @Feature("Essential features")
    @Story("Authentication")
    @Description("Verify to add delete and delete it.")
    @Severity(NORMAL)
    @Owner("John Doe")
    @Link(name="Website",url="https://todo.qacart.com/signup")
    @Issue("AUTH-123")
    @TmsLink("TMS-456")
    public void addToDoAndDeleteToDo(){
        TestUser user = new TestUser();
        RegistrationPage.getInstance().load(driver.get());
        RegistrationPage.getInstance().registerUser(driver.get(),user.getFirstName(),user.getLastName(),user.getEmail(),user.getPassword());
        TodoPage.getInstance().addTodo(driver.get(),"Testing To Do");
        TodoPage.getInstance().deleteTodo(driver.get());
        Assert.assertTrue(TodoPage.getInstance().validateNoToDoMessageVisible(driver.get(),"No Available Todos"));
    }
}
