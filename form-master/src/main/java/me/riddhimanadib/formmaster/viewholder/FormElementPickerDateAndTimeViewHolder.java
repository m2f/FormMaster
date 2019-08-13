package me.riddhimanadib.formmaster.viewholder;

import android.content.Context;
import android.view.View;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import me.riddhimanadib.formmaster.R;
import me.riddhimanadib.formmaster.listener.ReloadListener;
import me.riddhimanadib.formmaster.model.BaseFormElement;
import me.riddhimanadib.formmaster.model.FormElementPickerDateAndTime;

public class FormElementPickerDateAndTimeViewHolder extends BaseViewHolder
        implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {


    private DatePickerDialog mDatePickerDialog;
    private Calendar mCalendarCurrentDate;
    private AppCompatActivity context;
    private ReloadListener mReloadListener;
    private BaseFormElement mFormElement;
    private int mPosition;

    private TimePickerDialog mTimePickerDialog;
    private Calendar mCalendarCurrentTime;

    public FormElementPickerDateAndTimeViewHolder(View v, Context context, ReloadListener reloadListener) {
        super(v);
        this.context = (AppCompatActivity) context;
        mReloadListener = reloadListener;

        mCalendarCurrentDate = java.util.Calendar.getInstance();
        mDatePickerDialog = DatePickerDialog.newInstance(this,
                mCalendarCurrentDate.get(Calendar.YEAR),
                mCalendarCurrentDate.get(Calendar.MONTH),
                mCalendarCurrentDate.get(Calendar.DAY_OF_MONTH));

        int dateTimePickerColor = ContextCompat.getColor(context,
                R.color.date_time_picker_background_color);

        mDatePickerDialog.setAccentColor(dateTimePickerColor);


        mCalendarCurrentTime = java.util.Calendar.getInstance();
        mTimePickerDialog = TimePickerDialog.newInstance(this,
                mCalendarCurrentTime.get(Calendar.HOUR),
                mCalendarCurrentTime.get(Calendar.MINUTE), false);
        mTimePickerDialog.setAccentColor(dateTimePickerColor);
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
                mDatePickerDialog.show(((AppCompatActivity)context).getSupportFragmentManager(), formElement.getTitle());
            }
        });

        mTextViewTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatePickerDialog.show(((AppCompatActivity)context).getSupportFragmentManager(), formElement.getTitle());
            }
        });
    }

    private String currentValue = "";
    private String newValue = "";

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
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
        mTimePickerDialog.show(context.getSupportFragmentManager(), "");
    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
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
}
