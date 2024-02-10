package Test;

import io.qameta.allure.*;
import org.coreFramework.base.BaseTest;
import org.coreFramework.models.TestUser;
import org.coreFramework.pages.RegistrationPage;
import org.coreFramework.pages.TodoPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.qameta.allure.SeverityLevel.CRITICAL;

public class MixUIAndAPITest extends BaseTest {

    @Test
    @Epic("Backend")
    @Feature("Essential features")
    @Story("Authentication")
    @Description("Verify to register user from UI and API")
    @Severity(CRITICAL)
    @Owner("John Doe")
    @Link(name="Website",url="https://todo.qacart.com/signup")
    @Issue("AUTH-123")
    @TmsLink("TMS-456")
    public void beAbleToRegisterUser() {
        TestUser user = new TestUser();
        RegistrationPage.getInstance().load(driver.get());
        RegistrationPage.getInstance().registerUserByAPI(driver.get(),user);
    }
    @Test
    @Epic("Backend")
    @Feature("Essential features")
    @Story("Authentication")
    @Description("Add to do and delete to do via API and UI")
    @Severity(CRITICAL)
    @Owner("John Doe")
    @Link(name="Website",url="https://todo.qacart.com/v1/todo")
    @Issue("AUTH-123")
    @TmsLink("TMS-456")
    public void addToDoAndDeleteToDo() {
        TestUser user = new TestUser();
        RegistrationPage.getInstance().load(driver.get());
        RegistrationPage.getInstance().registerUserByAPI(driver.get(),user);
        TodoPage.getInstance().addTodoByAPI(user,"Learn Selenium");
        TodoPage.getInstance().load(driver.get());
        TodoPage.getInstance().deleteTodo(driver.get());
        Assert.assertTrue(TodoPage.getInstance().validateNoToDoMessageVisible(driver.get(), "No Available Todos"));
    }
}
