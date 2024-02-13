package org.coreFramework.pages;

import io.qameta.allure.Step;
import org.coreFramework.utils.ConfigUtils;
import org.coreFramework.utils.GenericValidations;
import org.coreFramework.utils.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class DigitalBiblePage {
    //Singleton Pattern
    private static DigitalBiblePage digitalBiblePage;

    private DigitalBiblePage(){}

    public static DigitalBiblePage getInstance(){
        if(digitalBiblePage == null){
            digitalBiblePage = new DigitalBiblePage();
        }
        return digitalBiblePage;
    }
    //Elements

    private final By logo = By.cssSelector("div#screen1[class='view view-main'] div.title");
    private final By searchIcon = By.cssSelector("div#screen1[class='view view-main'] a[href='/search/bible/']");
    private final By journalIcon = By.cssSelector("div#screen1[class='view view-main'] a[href='/journals/']");
    private final By searchTextBox = By.cssSelector("div#screen1[class='view view-main'] input.inputSearch");
    private final By searchResultText = By.cssSelector("span#searchResultsCountMessage");
    private final By translationButton = By.cssSelector("div#screen1[class='view view-main'] div.mb-menubar-buttons>button:nth-child(1)");
    private final By selectBookButton = By.cssSelector("div#screen1[class='view view-main'] div.mb-menubar-buttons>button:nth-child(2)");
    private final By bookChapterButton = By.cssSelector("div#screen1[class='view view-main'] div.mb-menubar-buttons>button:nth-child(2)");
    private final By getSearchResultList = By.cssSelector("div#searchResults div.list>div.list-group");
    private final By bookTitleChapterText = By.cssSelector("div.page-current div.swiper-slide-active div.block-title");
    private final By addJournalPlusIcon = By.cssSelector("div.page-current div.fab-right-bottom");
    private final By journalTextField = By.cssSelector("div.db-note__body[data-placeholder]");

    private By getBook(String book){
        return By.cssSelector("div#picker-content div.picker-books>div.row button[data-book-title='"+book+"']");
    }
    private By getChapter(String chapter){
        return By.cssSelector("div#picker-content div.picker-chapters>div.row button[data-chapter='"+chapter+"']");
    }
    private By getVerse(String verse){
        return By.cssSelector("div#picker-content div.picker-verses>div.row button[data-verse='"+verse+"']");
    }



    //Methods

    @Step
    public void load(WebDriver driver){
        driver.get(ConfigUtils.getInstance().getProperty("digitalURl"));
        TestUtils.waitForElementVisibility(driver,searchIcon,60);
    }

    @Step
    public boolean validateLogoVisibility(WebDriver driver){
        return GenericValidations.validateElementVisibility(driver,logo);
    }

    @Step
    public void searchText(WebDriver driver,String text){
        driver.findElement(searchIcon).click();
        driver.findElement(searchTextBox).sendKeys(text);
        driver.findElement(searchTextBox).sendKeys(Keys.ENTER);
    }

    @Step
    public void selectBook(WebDriver driver,String book){
        driver.findElement(selectBookButton).click();
        driver.findElement(getBook(book)).click();
    }

    @Step
    public void selectChapter(WebDriver driver,String chapter){
        driver.findElement(getChapter(chapter)).click();
    }

    @Step
    public void selectVerse(WebDriver driver,String verse){
        driver.findElement(getVerse(verse)).click();
    }

    @Step
    public boolean validateSearchResultText(WebDriver driver){
        return GenericValidations.validateElementVisibility(driver,searchResultText);
    }

    @Step
    public boolean validateSearchResultVisibility(WebDriver driver){
        return GenericValidations.validateElementsVisibility(driver,getSearchResultList);
    }

    @Step
    public boolean validateBookAndChapter(WebDriver driver, String book, String chapter){
        String actualText = driver.findElement(bookTitleChapterText).getText();
        System.out.println(actualText);
        return actualText.contains(book) && actualText.contains(chapter);
    }

    public void takeNotes(WebDriver driver,String text) {
        driver.findElement(journalIcon).click();
        driver.findElement(addJournalPlusIcon).click();
        driver.findElement(journalTextField).sendKeys(text);
        driver.findElement(addJournalPlusIcon).click();
    }
}
