package me.riddhimanadib.formmaster.model;

public class FormElementRatingBar extends BaseFormElement {

    private float rating;

    public FormElementRatingBar() {
    }

    public static FormElementRatingBar createInstance() {
        FormElementRatingBar FormElementRatingBar = new FormElementRatingBar();
        FormElementRatingBar.setType(BaseFormElement.TYPE_RATING_BAR);
        return FormElementRatingBar;
    }

    public FormElementRatingBar setTag(int mTag) {
        return (FormElementRatingBar)  super.setTag(mTag);
    }

    public FormElementRatingBar setType(int mType) {
        return (FormElementRatingBar)  super.setType(mType);
    }

    public FormElementRatingBar setTitle(String mTitle) {
        return (FormElementRatingBar)  super.setTitle(mTitle);
    }

    public FormElementRatingBar setRequired(boolean required) {
        return (FormElementRatingBar)  super.setRequired(required);
    }

    // custom setter
    public FormElementRatingBar setRatingValue(float rating) {
        this.rating = rating;
        return this;
    }

    // custom getter
    public float getRatingValue() {
        return this.rating;
    }

}
