package me.riddhimanadib.formmaster.viewholder;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import me.riddhimanadib.formmaster.R;
import me.riddhimanadib.formmaster.listener.ReloadListener;
import me.riddhimanadib.formmaster.model.BaseFormElement;
import me.riddhimanadib.formmaster.model.FormElementPickerDate;
import me.riddhimanadib.formmaster.model.FormElementPickerDateAndTime;
import me.riddhimanadib.formmaster.model.FormElementPickerTime;

public class FormElementPickerDateAndTimeViewHolder extends BaseViewHolder {

    private AppCompatTextView mTextViewTitle;
    private AppCompatEditText mEditTextValue;
    private DatePickerDialog mDatePickerDialog;
    private Calendar mCalendarCurrentDate;
    private ReloadListener mReloadListener;
    private BaseFormElement mFormElement;
    private int mPosition;

    private TimePickerDialog mTimePickerDialog;
    private Calendar mCalendarCurrentTime;

    public FormElementPickerDateAndTimeViewHolder(View v, Context context, ReloadListener reloadListener) {
        super(v);

        mTextViewTitle = (AppCompatTextView) v.findViewById(R.id.formElementTitle);
        mEditTextValue = (AppCompatEditText) v.findViewById(R.id.formElementValue);
        mReloadListener = reloadListener;

        mCalendarCurrentDate = java.util.Calendar.getInstance();
        mDatePickerDialog = new DatePickerDialog(context,
                date,
                mCalendarCurrentDate.get(Calendar.YEAR),
                mCalendarCurrentDate.get(Calendar.MONTH),
                mCalendarCurrentDate.get(Calendar.DAY_OF_MONTH));

        mCalendarCurrentTime = java.util.Calendar.getInstance();
        mTimePickerDialog = new TimePickerDialog(context,
                time,
                mCalendarCurrentTime.get(Calendar.HOUR),
                mCalendarCurrentTime.get(Calendar.MINUTE),
                false);
    }

    @Override
    public void bind(int position, BaseFormElement formElement, final Context context) {
        mFormElement = formElement;
        mPosition = position;
        mTextViewTitle.setText(formElement.getTitle());
        mEditTextValue.setText(formElement.getValue());
        mEditTextValue.setHint(formElement.getHint());
        mEditTextValue.setFocusableInTouchMode(false);

        mEditTextValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatePickerDialog.show();
            }
        });

        mTextViewTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatePickerDialog.show();
            }
        });
    }

    String currentValue = "";
    String newValue = "";

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mCalendarCurrentDate.set(Calendar.YEAR, year);
            mCalendarCurrentDate.set(Calendar.MONTH, monthOfYear);
            mCalendarCurrentDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            String myFormatDate = ((FormElementPickerDateAndTime) mFormElement).getDateFormat();
            SimpleDateFormat sdfDate = new SimpleDateFormat(myFormatDate, Locale.US);

            currentValue = mFormElement.getValue();
            newValue = sdfDate.format(mCalendarCurrentDate.getTime());

            // trigger event only if the value is changed
            if (!currentValue.equals(newValue)) {
                mReloadListener.updateValue(mPosition, newValue);
            }
            mTimePickerDialog.show();
        }
    };

    TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            mCalendarCurrentTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            mCalendarCurrentTime.set(Calendar.MINUTE, minute);

            String myFormatTime = ((FormElementPickerDateAndTime) mFormElement).getTimeFormat(); // custom format
            SimpleDateFormat sdfTime = new SimpleDateFormat(myFormatTime, Locale.US);

            currentValue += mFormElement.getValue();
            newValue += ", " + sdfTime.format(mCalendarCurrentTime.getTime());

            // trigger event only if the value is changed
            if (!currentValue.equals(newValue)) {
                mReloadListener.updateValue(mPosition, newValue);
            }
        }
    };
}
