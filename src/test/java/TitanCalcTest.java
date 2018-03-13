import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class TitanCalcTest {

    private static final String URL = "http://titancalc.com/";

    @Test
    public void openSite() {
        open(URL);
        SelenideElement table = $(By.tagName("table"));
        table.$$(By.tagName("a")).shouldHaveSize(45);
        table.$$(By.className("header")).shouldHaveSize(19);
        table.$$(By.className("light")).shouldHaveSize(20);
        table.$$(By.className("dark")).shouldHaveSize(16);
        table.$$(By.className("new")).shouldHaveSize(9);
    }

    // Check links for correct table selection
    //@Ignore("1m 18s execution time")
    @Test
    public void testLinks() {
        String[] cols = new String[9];
        String[] rows = new String[9];
        open(URL);
        ElementsCollection cells = $(By.tagName("table")).$$(By.tagName("td"));

        // Getting rows and columns titles
        int c = 0; int r = 0;
        for (int i = 1; i < cells.size(); i++) {
            if (i < 10) {
                cols[c] = cells.get(i).getText().toLowerCase();
                c++;
            } else if (i % 10 == 0) {
                rows[r] = cells.get(i).getText().toLowerCase();
                r++;
            }
        }

        // Check if result tables ids are correct
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (i * 10 + j + 11 > 94) break;
                SelenideElement cell = cells.get(i * 10 + j + 11);
                if (!cell.getText().equals(" ") && !cell.getText().equals("Columns marked in blue are in the Titan Quest: Immortal Throne only.")) {
                    cell.$(By.tagName("a")).click();
                    ElementsCollection tables = $(By.tagName("table")).$$(By.tagName("table"));
                    tables.get(0).shouldHave(or("table id's", id(rows[i]), id(cols[j])));
                    if (!cols[j].equals(" "))
                        tables.get(1).shouldHave(or("table id's", id(rows[i]), id(cols[j])));
                    back();
                }
                if (cell.has(attribute("colspan"))) j = 8;
            }
        }
    }
}
