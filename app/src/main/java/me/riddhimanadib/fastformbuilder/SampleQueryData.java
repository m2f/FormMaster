package me.riddhimanadib.fastformbuilder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import me.riddhimanadib.formmaster.FormBuilder;
import me.riddhimanadib.formmaster.model.BaseFormElement;
import me.riddhimanadib.formmaster.model.FormElementPickerMulti;
import me.riddhimanadib.formmaster.model.FormElementPickerSingle;
import me.riddhimanadib.formmaster.model.FormElementTextMultiLine;
import me.riddhimanadib.formmaster.model.FormElementTextNumber;
import me.riddhimanadib.formmaster.model.FormElementTextSingleLine;

public class SampleQueryData extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private FormBuilder mFormBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_query_data);
        setupForm();
    }

    private void setupForm() {
        List<String> fruits = new ArrayList<>();
        fruits.add("Banana");
        fruits.add("Orange");
        fruits.add("Mango");
        fruits.add("Guava");

        mRecyclerView = findViewById(R.id.sampleRV);
        mFormBuilder = new FormBuilder(this, mRecyclerView);
        List<BaseFormElement> forms = new ArrayList<>();
        forms.add(FormElementTextSingleLine.createInstance().setTitle("Name and Age"));
        forms.add(FormElementTextMultiLine.createInstance().setTitle("123456 this is text are"));
        forms.add(FormElementTextNumber.createInstance().setTitle("This are Number"));
        forms.add(FormElementPickerMulti.createInstance().setTitle("This is Dropdown").setOptions(fruits));
        forms.add(FormElementPickerSingle.createInstance().setTitle("This is Dropdown").setOptions(fruits));
        mFormBuilder.addFormElements(forms);
    }
}
