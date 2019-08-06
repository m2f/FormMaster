package me.riddhimanadib.formmaster.model;

/**
 * Created by Riddhi - Rudra on 28-Jul-17.
 */

public class FormElementTextNumber extends BaseFormElement {

    public FormElementTextNumber() {
    }

    public static FormElementTextNumber createInstance() {
        FormElementTextNumber formElementTextNumber = new FormElementTextNumber();
        formElementTextNumber.setType(BaseFormElement.TYPE_EDITTEXT_NUMBER);
        return formElementTextNumber;
    }

    public FormElementTextNumber setTag(int mTag) {
        return (FormElementTextNumber)  super.setTag(mTag);
    }

    public FormElementTextNumber setType(int mType) {
        return (FormElementTextNumber)  super.setType(mType);
    }

    public FormElementTextNumber setTitle(String mTitle) {
        return (FormElementTextNumber)  super.setTitle(mTitle);
    }

    public FormElementTextNumber setValue(String mValue) {
        return (FormElementTextNumber)  super.setValue(mValue);
    }

    public FormElementTextNumber setHint(String mHint) {
        return (FormElementTextNumber)  super.setHint(mHint);
    }

    public FormElementTextNumber setRequired(boolean required) {
        return (FormElementTextNumber)  super.setRequired(required);
    }
    
}
