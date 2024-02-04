package Test;

import org.coreFramework.base.BaseTest;
import org.coreFramework.models.TestUser;
import org.coreFramework.pages.RegistrationPage;
import org.coreFramework.pages.TodoPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MixUIAndAPITest extends BaseTest {

    @Test(description = "Verify to register user from UI and API")
    public void beAbleToRegisterUser() {
        TestUser user = new TestUser();
        RegistrationPage.getInstance().load(driver.get());
        RegistrationPage.getInstance().registerUserByAPI(driver.get(),user);

    }

    @Test(description = "Add to do and delete to do via API and UI")
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
