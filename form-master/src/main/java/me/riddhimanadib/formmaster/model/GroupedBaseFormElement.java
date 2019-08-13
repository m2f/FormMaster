package me.riddhimanadib.formmaster.model;

import java.util.Arrays;
import java.util.List;

import me.riddhimanadib.formmaster.adapter.FormAdapter;

public class GroupedBaseFormElement {

    private String headerText;
    private List<BaseFormElement> items;
    private FormAdapter formAdapter;

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

    public GroupedBaseFormElement setItems(List<BaseFormElement> items) {
        this.items = items;
        return this;
    }

    public FormAdapter getFormAdapter() {
        return formAdapter;
    }

    public void setFormAdapter(FormAdapter formAdapter) {
        this.formAdapter = formAdapter;
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
