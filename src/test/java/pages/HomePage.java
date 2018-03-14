package pages;

import org.openqa.selenium.By;
import tables.HomeTable;

import static com.codeborne.selenide.Selenide.$;

public class HomePage {

    private HomeTable table;

    public HomePage() {
        table = new HomeTable($(By.tagName("table")));
    }

    public boolean validateTable() {
        return table.getCellsCount() == 95;
    }

    public int checkLinks() {
        return table.checkLinks();
    }
}
