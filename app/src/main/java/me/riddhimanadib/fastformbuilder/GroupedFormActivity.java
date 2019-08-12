package me.riddhimanadib.fastformbuilder;

import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import me.riddhimanadib.formmaster.FormBuilder;
import me.riddhimanadib.formmaster.model.BaseFormElement;
import me.riddhimanadib.formmaster.model.FormElementPickerDate;
import me.riddhimanadib.formmaster.model.FormElementPickerMulti;
import me.riddhimanadib.formmaster.model.FormElementPickerSingle;
import me.riddhimanadib.formmaster.model.FormElementPickerTime;
import me.riddhimanadib.formmaster.model.FormElementRatingBar;
import me.riddhimanadib.formmaster.model.FormElementStepper;
import me.riddhimanadib.formmaster.model.FormElementSwitch;
import me.riddhimanadib.formmaster.model.FormElementTextEmail;
import me.riddhimanadib.formmaster.model.FormElementTextMultiLine;
import me.riddhimanadib.formmaster.model.FormElementTextNumber;
import me.riddhimanadib.formmaster.model.FormElementTextPassword;
import me.riddhimanadib.formmaster.model.FormElementTextPhone;
import me.riddhimanadib.formmaster.model.FormElementTextSingleLine;
import me.riddhimanadib.formmaster.model.GroupedBaseFormElement;

public class GroupedFormActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private FormBuilder mFormBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen_form);

        setupToolBar();

        setupForm();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupToolBar() {

        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }

    }

    private void setupForm() {

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mFormBuilder = new FormBuilder(this, mRecyclerView, true);

        List<GroupedBaseFormElement> formElements = new ArrayList<>();
        FormElementTextEmail element11 = FormElementTextEmail.createInstance().setTitle("Email").setHint("Enter Email");
        FormElementTextPhone element12 = FormElementTextPhone.createInstance().setTitle("Phone").setValue("+8801712345678");
        //formElements.add(GroupedBaseFormElement.newInstance("Personal Info", element11, element12));


        FormElementTextSingleLine element21 = FormElementTextSingleLine.createInstance().setTitle("Location").setValue("Dhaka");
        FormElementTextMultiLine element22 = FormElementTextMultiLine.createInstance().setTitle("Address");
        FormElementTextNumber element23 = FormElementTextNumber.createInstance().setTitle("Zip Code").setValue("1000");
        //formElements.add(GroupedBaseFormElement.newInstance("Family Info", element21, element22, element23));

        FormElementPickerDate element31 = FormElementPickerDate.createInstance().setTitle("Date").setDateFormat("MMM dd, yyyy");
        FormElementPickerTime element32 = FormElementPickerTime.createInstance().setTitle("Time").setTimeFormat("hh mm a");
        FormElementTextPassword element33 = FormElementTextPassword.createInstance().setTitle("Password").setValue("abcd1234");
        //formElements.add(GroupedBaseFormElement.newInstance("Schedule", element31, element32, element33));

        List<String> fruits = new ArrayList<>();
        fruits.add("Banana");
        fruits.add("Orange");
        fruits.add("Mango");
        fruits.add("Guava");

        List<String> stepperOptions = new ArrayList<>();
        stepperOptions.add("Adult");
        stepperOptions.add("Kids (Age 4 - 12 Yrs)");
        stepperOptions.add("Infants (Below 3 Yrs)");


        FormElementPickerSingle element41 = FormElementPickerSingle.createInstance().setTitle("Single Item").setOptions(fruits).setPickerTitle("Pick any item").setHint("Tap here to select");
        FormElementPickerMulti element42 = FormElementPickerMulti.createInstance().setTitle("Multi Items").setOptions(fruits).setPickerTitle("Pick one or more").setNegativeText("reset").setHint("Tap here to choose");
        FormElementRatingBar element45 = FormElementRatingBar.createInstance().setTitle("Rating").setRequired(true);

        List<BaseFormElement > formElementList = new ArrayList<>();
        formElementList.add(element11);
        formElementList.add(element12);
        formElementList.add(element31);
        formElementList.add(element32);
        formElementList.add(element41);
        formElementList.add(element45);
        formElementList.add(element42);

        //FormElementSwitch element43 = FormElementSwitch.createInstance().setTitle("Frozen?").setSwitchTexts("Yes", "No");
        FormElementStepper element44 = FormElementStepper.createInstance().setTitle("Traveller's Detail").setStepperOptions(stepperOptions);

        formElements.add(GroupedBaseFormElement.newInstance("Adult-1").setItems(formElementList));
        formElements.add(GroupedBaseFormElement.newInstance("Adult-2", element11, element12, element31, element32, element41, element45, element42));

        mFormBuilder.addGroupFormElements(formElements);

    }
}
