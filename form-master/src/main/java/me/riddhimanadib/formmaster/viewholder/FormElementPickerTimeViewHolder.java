package me.riddhimanadib.formmaster.viewholder;

import android.content.Context;
import android.view.View;

import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import me.riddhimanadib.formmaster.listener.ReloadListener;
import me.riddhimanadib.formmaster.model.BaseFormElement;
import me.riddhimanadib.formmaster.model.FormElementPickerTime;

/**
 * Created by Riddhi - Rudra on 30-Jul-17.
 */

public class FormElementPickerTimeViewHolder extends BaseViewHolder {
    private TimePickerDialog mTimePickerDialog;
    private Calendar mCalendarCurrentTime;
    private ReloadListener mReloadListener;
    private BaseFormElement mFormElement;
    private int mPosition;

    public FormElementPickerTimeViewHolder(View v, ReloadListener reloadListener) {
        super(v);
        mReloadListener = reloadListener;
        mCalendarCurrentTime = java.util.Calendar.getInstance();
        mTimePickerDialog = TimePickerDialog.newInstance(
                timeCallback,
                mCalendarCurrentTime.get(Calendar.HOUR),
                mCalendarCurrentTime.get(Calendar.MINUTE),
                false);
    }

    @Override
    public void bind(int position, final BaseFormElement formElement, final Context context) {
        super.bind(position, formElement, context);
        mFormElement = formElement;
        mPosition = position;
        mEditTextValue.setFocusableInTouchMode(false);

        mEditTextValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTimePickerDialog.show(((AppCompatActivity)context).getSupportFragmentManager(), formElement.getTitle());
            }
        });

        mTextViewTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTimePickerDialog.show(((AppCompatActivity)context).getSupportFragmentManager(), formElement.getTitle());
            }
        });
    }

    /**
     * setting up time picker dialog listener
     */
    private TimePickerDialog.OnTimeSetListener timeCallback = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
            mCalendarCurrentTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            mCalendarCurrentTime.set(Calendar.MINUTE, minute);

            String myFormatTime = ((FormElementPickerTime) mFormElement).getTimeFormat(); // custom format
            SimpleDateFormat sdfTime = new SimpleDateFormat(myFormatTime, Locale.US);

            String currentValue = mFormElement.getValue();
            String newValue = sdfTime.format(mCalendarCurrentTime.getTime());

            // trigger event only if the value is changed
            if (!currentValue.equals(newValue)) {
                mReloadListener.updateValue(mPosition, newValue);
            }
        }
    };
}
