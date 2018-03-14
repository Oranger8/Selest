import org.junit.Ignore;
import org.junit.Test;
import pages.HomePage;

import static org.junit.Assert.assertTrue;
import static com.codeborne.selenide.Selenide.*;

public class TitanCalcTest {

    private static final String URL = "http://titancalc.com/";

    @Test
    public void homePageTest() {
        open(URL);
        HomePage homePage = new HomePage();
        assertTrue(homePage.validateTable());
    }

    // Check links for correct table selection
    //@Ignore("~1.5min execution time")
    @Test
    public void testLinks() {
        open(URL);
        HomePage homePage = new HomePage();
        int result = homePage.checkLinks();
        assertTrue(result + " invalid tables", result != 0);
    }
}
