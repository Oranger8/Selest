package tables;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

public class WebTable {

    ElementsCollection cells;
    private SelenideElement table;
    private String id;

    public WebTable(SelenideElement element) {
        table = element;
        cells = table.$$(By.tagName("td"));
        id = table.attr("id");
    }

    public int getCellsCount() {
        return table.$$(By.tagName("td")).size();
    }

    public String Id() {
        return id;
    }
}
