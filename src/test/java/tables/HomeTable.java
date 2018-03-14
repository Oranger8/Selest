package tables;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import pages.CalcPage;

import static com.codeborne.selenide.Selenide.back;

public class HomeTable extends WebTable {

    private String[] cols = new String[9];
    private String[] rows = new String[9];

    public HomeTable(SelenideElement element) {
        super(element);

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
    }

    // Check if result tables ids are correct
    public int checkLinks() {
        int result = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (i * 10 + j + 11 > 94) break;
                SelenideElement cell = cells.get(i * 10 + j + 11);
                if (!cell.getText().equals(" ") && !cell.getText().equals("Columns marked in blue are in the Titan Quest: Immortal Throne only.")) {
                    cell.$(By.tagName("a")).click();
                    CalcPage page = new CalcPage();
                    if (!page.tablesAreValid(rows[i], cols[i])) result++;
                    back();
                }
                if (cell.attr("colspan") != null) j = 8;
            }
        }
        return result;
    }
}
