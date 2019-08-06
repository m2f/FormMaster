package me.riddhimanadib.formmaster.model;

import java.util.Arrays;
import java.util.List;

public class GroupedBaseFormElement {

    private String headerText;
    private List<BaseFormElement> items;

    private GroupedBaseFormElement() {}

    public String getHeaderText() {
        return headerText;
    }

    public void setHeaderText(String headerText) {
        this.headerText = headerText;
    }

    public List<BaseFormElement> getItems() {
        return items;
    }

    public void setItems(List<BaseFormElement> items) {
        this.items = items;
    }

    public static GroupedBaseFormElement newInstance(String headerText, BaseFormElement... baseFormElements) {
        GroupedBaseFormElement item = new GroupedBaseFormElement();
        item.headerText = headerText;
        item.items = Arrays.asList(baseFormElements);
        return item;
    }
    public void addItem(BaseFormElement baseFormElement) {
        items.add(baseFormElement);
    }
}
