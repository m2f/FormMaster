package me.riddhimanadib.formmaster.model;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class FormElementPickerDateAndTime extends BaseFormElement {
    private String dateFormat;
    private String timeFormat;

    public FormElementPickerDateAndTime(){
    }

    public static FormElementPickerDateAndTime createInstance() {
        FormElementPickerDateAndTime formElementPickerDateAndTime = new FormElementPickerDateAndTime();
        formElementPickerDateAndTime.setType(BaseFormElement.TYPE_DATE_AND_TIME);
        formElementPickerDateAndTime.setDateFormat("dd/MM/yy");
        formElementPickerDateAndTime.setTimeFormat("KK:mm a");
        return formElementPickerDateAndTime;
    }

    public FormElementPickerDateAndTime setTag(int mTag) {
        return (FormElementPickerDateAndTime)  super.setTag(mTag);
    }

    public FormElementPickerDateAndTime setType(int mType) {
        return (FormElementPickerDateAndTime)  super.setType(mType);
    }

    public FormElementPickerDateAndTime setTitle(String mTitle) {
        return (FormElementPickerDateAndTime)  super.setTitle(mTitle);
    }

    public FormElementPickerDateAndTime setValue(String mValue) {
        return (FormElementPickerDateAndTime)  super.setValue(mValue);
    }

    public FormElementPickerDateAndTime setHint(String mHint) {
        return (FormElementPickerDateAndTime)  super.setHint(mHint);
    }

    public FormElementPickerDateAndTime setRequired(boolean required) {
        return (FormElementPickerDateAndTime)  super.setRequired(required);
    }

    public FormElementPickerDateAndTime setDateFormat(String format) {
        checkValidDateFormat(format);
        this.dateFormat = format;
        return this;
    }

    public String getDateFormat() {
        return this.dateFormat;
    }

    private void checkValidDateFormat(String format) {
        try {
            new SimpleDateFormat(format, Locale.US);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Date format is not correct: " + e.getMessage());
        }
    }

    public FormElementPickerDateAndTime setTimeFormat(String format) {
        checkValidTimeFormat(format);
        this.timeFormat = format;
        return this;
    }

    public String getTimeFormat() {
        return this.timeFormat;
    }

    private void checkValidTimeFormat(String format) {
        try {
            new SimpleDateFormat(format, Locale.US);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Time format is not correct: " + e.getMessage());
        }
    }
}
