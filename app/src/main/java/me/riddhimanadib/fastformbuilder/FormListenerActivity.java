package me.riddhimanadib.fastformbuilder;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

import me.riddhimanadib.formmaster.FormBuilder;
import me.riddhimanadib.formmaster.listener.OnFormElementValueChangedListener;
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
import me.riddhimanadib.formmaster.model.FormHeader;

public class FormListenerActivity extends AppCompatActivity implements OnFormElementValueChangedListener {

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
        mFormBuilder = new FormBuilder(this, mRecyclerView, this);

        FormHeader header1 = FormHeader.createInstance("Personal Info");
        FormElementTextEmail element11 = FormElementTextEmail.createInstance().setTitle("Email").setHint("Enter Email");
        FormElementTextPhone element12 = FormElementTextPhone.createInstance().setTitle("Phone").setValue("+8801712345678");

        FormHeader header2 = FormHeader.createInstance("Family Info");
        FormElementTextSingleLine element21 = FormElementTextSingleLine.createInstance().setTitle("Location").setValue("Dhaka");
        FormElementTextMultiLine element22 = FormElementTextMultiLine.createInstance().setTitle("Address");
        FormElementTextNumber element23 = FormElementTextNumber.createInstance().setTitle("Zip Code").setValue("1000");

        Set<Calendar> enableDates = new TreeSet<>(Calendar::compareTo);
        Calendar minDate = Calendar.getInstance();
        String[] dates = new String[]{"18-10-2019",
                "19-02-2020",
                "25-02-2020",
                "26-02-2020",
                "01-03-2020",
                "02-03-2020",
                "09-03-2020",
                "08-03-2020",
                "15-03-2020",
                "16-03-2020",
                "23-03-2020",
                "30-03-2020"};

        for (String date : dates) {
            Date allowedDate = parseToDate(new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH), date);
            if (null != allowedDate) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(allowedDate);
                int compare = cal.compareTo(minDate);
                if (compare >= 0) {
                    enableDates.add(cal);
                }
            }
        }

        FormHeader header3 = FormHeader.createInstance("Schedule");
        FormElementPickerDate element31 = FormElementPickerDate.createInstance().setTitle("Date").setDateFormat("MMM dd, yyyy");

        if (enableDates.size() > 0) {
            Calendar[] datesToEnable = enableDates.toArray(new Calendar[0]);
            element31.setDates(datesToEnable);
            element31.setIsBlockDates(false);
        }
        FormElementPickerTime element32 = FormElementPickerTime.createInstance().setTitle("Time").setTimeFormat("hh mm a");
        FormElementTextPassword element33 = FormElementTextPassword.createInstance().setTitle("Password").setValue("abcd1234");

        FormHeader header4 = FormHeader.createInstance("Preferred Items");
        List<String> fruits = new ArrayList<>();
        fruits.add("Banana");
        fruits.add("Orange");
        fruits.add("Mango");
        fruits.add("Guava");
        FormElementPickerSingle element41 = FormElementPickerSingle.createInstance().setTitle("Single Item").setOptions(fruits).setPickerTitle("Pick any item").setHint("Tap here to select");
        FormElementPickerMulti element42 = FormElementPickerMulti.createInstance().setTitle("Multi Items").setOptions(fruits).setPickerTitle("Pick one or more").setNegativeText("reset").setHint("Tap here to choose");
        FormElementSwitch element43 = FormElementSwitch.createInstance().setTitle("Frozen?").setSwitchTexts("Yes", "No");

        List<String> stepperOptions = new ArrayList<>();
        stepperOptions.add("Adult");
        stepperOptions.add("Kids (Age 4 - 12 Yrs)");
        stepperOptions.add("IInfant (under 2 years old)");
        stepperOptions.add("Infant (under 2 years old abcd abcd)");
        FormElementStepper element44 = FormElementStepper.createInstance().setTitle("Traveller's Detail").setHint("No of People travelling")
                .setStepperOptions(stepperOptions)
                .setValue("1 Adult / 6 Kids (Age 4 - 12 Yrs)");

        FormElementRatingBar element45 = FormElementRatingBar.createInstance().setTitle("Rating").setRequired(true);

        List<BaseFormElement> formItems = new ArrayList<>();
        formItems.add(header1);
        formItems.add(element11);
        formItems.add(element12);
        formItems.add(header2);
        formItems.add(element21);
        formItems.add(element22);
        formItems.add(element23);
        formItems.add(header3);
        formItems.add(element31);
        formItems.add(element32);
        formItems.add(element33);
        formItems.add(header4);
        formItems.add(element41);
        formItems.add(element42);
        formItems.add(element43);
        formItems.add(element44);
        formItems.add(element45);
        mFormBuilder.addFormElements(formItems);

    }

    public static Date parseToDate(SimpleDateFormat format, String dateStr) {
        if (!(dateStr == null || dateStr.trim().isEmpty())) {
            try {
                return format.parse(dateStr);
            } catch (ParseException ex) {
                Log.e("Error", "parseToDate: ");
            }
        }
        return null;
    }

    @Override
    public void onValueChanged(BaseFormElement formElement) {
        Toast.makeText(this, formElement.getValue(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRatingValueChanged(BaseFormElement formElement) {
        Toast.makeText(this, Float.toString(formElement.getRatingValue()), Toast.LENGTH_SHORT).show();
    }
}
