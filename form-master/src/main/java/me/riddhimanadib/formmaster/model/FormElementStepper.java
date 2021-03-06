package me.riddhimanadib.formmaster.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FormElementStepper extends BaseFormElement {

    public static final String OPTION_SEP = " / ";
    public static final String COUNT_SEP = " ";
    private List<String> stepperOptions;
    private Map<String, Integer> stepperCounts;

    private FormElementStepper() {
        stepperCounts = new HashMap<>();
    }

    public static FormElementStepper createInstance() {
        FormElementStepper formElementStepper = new FormElementStepper();
        formElementStepper.setType(BaseFormElement.TYPE_STEPPER);
        return formElementStepper;
    }

    public FormElementStepper setTag(int mTag) {
        return (FormElementStepper)  super.setTag(mTag);
    }

    public FormElementStepper setType(int mType) {
        super.setType(mType);
        return this;
    }

    public FormElementStepper setTitle(String mTitle) {
        super.setTitle(mTitle);
        return this;
    }

    public FormElementStepper setValue(String mValue) {
        super.setValue(mValue);
        if(null != mValue && mValue.length() > 0) {
            String[] optionValues = mValue.split(OPTION_SEP);
            for(String optionValue : optionValues) {
                int sepIndex = optionValue.indexOf(COUNT_SEP);
                if(sepIndex > 0) {
                    String stepperKey = optionValue.substring(sepIndex + 1, optionValue.length());
                    String stepperValue = optionValue.substring(0, sepIndex);
                    try {
                        stepperCounts.put(stepperKey, Integer.parseInt(stepperValue));
                    } catch (Exception e) {
                        stepperCounts.put(stepperKey, 0);
                    }
                }
            }
        }
        return this;
    }

    public FormElementStepper setHint(String mHint) {
        super.setHint(mHint);
        return this;
    }

    public FormElementStepper setRequired(boolean required) {
        super.setRequired(required);
        return this;
    }

    public List<String> getStepperOptions() {
        return stepperOptions;
    }

    public FormElementStepper setStepperOptions(List<String> stepperOptions) {
        this.stepperOptions = stepperOptions;
        return this;
    }

    public Map<String, Integer> getStepperCounts() {
        return stepperCounts;
    }

    public String getStepperCount(String option) {
        Integer value = stepperCounts.get(option);
        return (null == value) ? "0" : value.toString();
    }

    public List<String> getStepperValues() {
        List<String> values = new ArrayList<>(stepperCounts.size());
        for(String stepperOption : stepperCounts.keySet()) {
            values.add(stepperOption + " - " + stepperCounts.get(stepperOption));
        }
        return values;
    }

    public String getStepperValue() {
        StringBuilder value = new StringBuilder();
        boolean isFirst = true;
        for(String stepperOption : stepperCounts.keySet()) {
            String count = getStepperCount(stepperOption);
            if("0".equalsIgnoreCase(count)) {
                continue;
            }
            if(isFirst) {
                isFirst = false;
            } else {
                value.append(OPTION_SEP);
            }
            value.append(count).append(COUNT_SEP).append(stepperOption);
        }
        String val = value.toString();
        return val.isEmpty() ? getHint() : val;
    }

    public void addCount(String option) {
        Integer currentValue = stepperCounts.get(option);
        currentValue = (null == currentValue) ? 1 : currentValue + 1;
        stepperCounts.put(option, currentValue);
    }

    public void subtractCount(String option) {
        Integer currentValue = stepperCounts.get(option);
        currentValue = (null == currentValue) ? 0 : currentValue - 1;
        currentValue = currentValue < 0 ? 0 : currentValue;
        stepperCounts.put(option, currentValue);
    }

}
