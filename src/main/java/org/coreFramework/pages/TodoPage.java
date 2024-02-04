package org.coreFramework.pages;



import io.qameta.allure.Step;
import org.coreFramework.apis.TodoApi;
import org.coreFramework.models.TestUser;
import org.coreFramework.utils.ConfigUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TodoPage {
    private  static TodoPage todoPage;
    //Constructor
    private TodoPage(){

    }
    public static TodoPage getInstance(){
        if(todoPage == null){
            todoPage = new TodoPage();
        }
        return todoPage;
    }
    //Elements
    private final By plusIcon = By.cssSelector("svg[data-testid='add']");
    private final By toDoInput = By.cssSelector("input[data-testid='new-todo']");
    private final By createToDoButton = By.cssSelector("button[data-testid='submit-newTask']");
    private final By toDoMessage = By.cssSelector("[data-testid='welcome']");
    private final By taskCheckBox = By.cssSelector("input[data-testid='complete-task']");
    private final By deleteTaskIcon = By.cssSelector("button[data-testid='delete']");
    private final By noTodoMessage = By.cssSelector("[data-testid='no-todos']");

    //Methods
    @Step("Load the webpage to do")
    public void load(WebDriver driver){
        driver.get(ConfigUtils.getInstance().getAppUrl()+"/todo");
    }
    public void addTodo(WebDriver driver, String toDo){
        driver.findElement(plusIcon).click();
        driver.findElement(toDoInput).sendKeys(toDo);
        driver.findElement(createToDoButton).click();
        driver.findElement(toDoMessage).getText();
    }

    @Step("Delete to do")
    public void deleteTodo(WebDriver driver){
        driver.findElement(taskCheckBox).click();
        driver.findElement(deleteTaskIcon).click();
    }
    @Step("Validate if To do Message is Visible")
    public boolean validateNoToDoMessageVisible(WebDriver driver,String message){
        return driver.findElement(noTodoMessage).getText().equals(message);
    }
    @Step("Add to do by using API")
    public void addTodoByAPI(TestUser user, String item){
       TodoApi.getInstance().addToDoTask(user,item);
    }

}
