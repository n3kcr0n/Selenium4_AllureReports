package Test;

import org.coreFramework.base.BaseTest;
import org.coreFramework.pages.DigitalBiblePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DigitalBibleTest extends BaseTest {

    @Test
    public void searchWordFunctionality()  {
        DigitalBiblePage.getInstance().load(driver.get());
        DigitalBiblePage.getInstance().searchText(driver.get(),"In the beginning");
        Assert.assertTrue(DigitalBiblePage.getInstance().validateSearchResultText(driver.get()));
        Assert.assertTrue(DigitalBiblePage.getInstance().validateSearchResultVisibility(driver.get()));
    }

    @Test
    public void goToVerse(){
        DigitalBiblePage.getInstance().load(driver.get());
        DigitalBiblePage.getInstance().selectBook(driver.get(),"Genesis");
        DigitalBiblePage.getInstance().selectChapter(driver.get(),"1");
        DigitalBiblePage.getInstance().selectVerse(driver.get(),"1");
        Assert.assertTrue(DigitalBiblePage.getInstance().validateBookAndChapter(driver.get(), "GENESIS","1"));
    }

    @Test
    public void checkJournal() {
        DigitalBiblePage.getInstance().load(driver.get());
        DigitalBiblePage.getInstance().takeNotes(driver.get(), "This is just a test");
    }
}
