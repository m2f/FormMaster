package me.riddhimanadib.formmaster.viewholder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.SwitchCompat;
import me.riddhimanadib.formmaster.R;
import me.riddhimanadib.formmaster.listener.ReloadListener;
import me.riddhimanadib.formmaster.model.BaseFormElement;
import me.riddhimanadib.formmaster.model.FormElementStepper;
import me.riddhimanadib.formmaster.model.FormElementSwitch;

public class FormElementStepperViewHolder extends BaseViewHolder {

    LinearLayout stepperContainer;
    private LayoutInflater inflater;
    private ReloadListener reloadListener;

    public FormElementStepperViewHolder(View v, LayoutInflater inflater, ReloadListener reloadListener) {
        super(v);
        this.stepperContainer = v.findViewById(R.id.stepper_options_container);
        this.inflater = inflater;
        this.reloadListener = reloadListener;
    }

    @Override
    public void bind(final int position, final BaseFormElement formElement, final Context context) {
        final FormElementStepper formElementStepper = (FormElementStepper) formElement;

        mTextViewTitle.setText(formElementStepper.getTitle());
        if(formElement.getError().isEmpty()){
            mTextViewError.setVisibility(View.GONE);
        } else {
            mTextViewError.setVisibility(View.VISIBLE);
        }
        mTextViewError.setText(formElementStepper.getError());
        mEditTextValue.setFocusableInTouchMode(false);
        String stepperValue = formElementStepper.getStepperValue();
        formElement.setValue(stepperValue);
        mEditTextValue.setText(stepperValue);

        stepperContainer.removeAllViews();
        for(final String option: formElementStepper.getStepperOptions()) {
            View view = inflater.inflate(R.layout.form_element_stepper_item, this.stepperContainer, false);

            TextView stepperLabel = view.findViewById(R.id.stepper_label);
            stepperLabel.setText(option);

            TextView stepperCountTv = view.findViewById(R.id.stepper_value);
            stepperCountTv.setText(formElementStepper.getStepperCount(option));

            ImageView stepperPlus = view.findViewById(R.id.stepper_plus);
            ImageView stepperMinus = view.findViewById(R.id.stepper_minus);

            stepperPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    formElementStepper.addCount(option);
                    reloadListener.updateValue(position, formElementStepper.getStepperValue());
                }
            });

            stepperMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    formElementStepper.subtractCount(option);
                    reloadListener.updateValue(position, formElementStepper.getStepperValue());
                }
            });
            stepperContainer.addView(view);
        }
    }

}
