package search;


import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SearchTests {

    @BeforeClass
    public void setup() {
        System.out.println("Launching browser for Search tests...");
    }

    @Test
    public void testValidProductSearch() {
        // TODO: implement valid search
    }

    @Test
    public void testInvalidProductSearch() {
        // TODO: implement invalid search
    }

    @Test
    public void testBlankProductSearch() {
        // TODO: implement blank input search
    }

    @Test
    public void testSpecialCharSearch() {
        // TODO: implement search with special characters
    }

    @AfterClass
    public void tearDown() {
        System.out.println("Closing browser after Search tests...");
    }
}
