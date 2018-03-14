package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import tables.WebTable;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;

public class CalcPage {

    private List<WebTable> tables = new ArrayList<WebTable>(4);

    public CalcPage() {
        for (SelenideElement element :
                $(By.tagName("table")).$$(By.tagName("table"))) {
            tables.add(new WebTable(element));
        }
    }

    public boolean tablesAreValid(String row, String col) {
        if (!tables.get(1).Id().equals(row) && !tables.get(1).Id().equals(col))
            return false;
        if (tables.size() > 3)
            if (!tables.get(2).Id().equals(row) && !tables.get(2).Id().equals(col))
                return false;
        return true;
    }
}
