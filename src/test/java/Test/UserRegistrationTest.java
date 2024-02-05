package Test;


import org.coreFramework.base.BaseTest;
import org.coreFramework.models.TestUser;
import org.coreFramework.pages.RegistrationPage;
import org.coreFramework.pages.TodoPage;
import org.testng.Assert;
import org.testng.annotations.Test;


public class UserRegistrationTest extends BaseTest {

    @Test(description = "Verify if a user can be register")
    public void beAbleToRegisterUser(){
        RegistrationPage.getInstance().load(driver.get());
        TestUser user = new TestUser();
        RegistrationPage.getInstance().registerUser(driver.get(),user.getFirstName(),user.getLastName(),user.getEmail(),user.getPassword());

    }

    @Test(description = "Add task to do and Delete task to do")
    public void addToDoAndDeleteToDo(){
        TestUser user = new TestUser();
        RegistrationPage.getInstance().load(driver.get());
        RegistrationPage.getInstance().registerUser(driver.get(),user.getFirstName(),user.getLastName(),user.getEmail(),user.getPassword());
        TodoPage.getInstance().addTodo(driver.get(),"Testing To Do");
        TodoPage.getInstance().deleteTodo(driver.get());
        Assert.assertTrue(TodoPage.getInstance().validateNoToDoMessageVisible(driver.get(),"No Available Todos"));
    }
}
