package me.riddhimanadib.formmaster.viewholder;

import android.content.Context;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import me.riddhimanadib.formmaster.R;
import me.riddhimanadib.formmaster.listener.ReloadListener;
import me.riddhimanadib.formmaster.model.BaseFormElement;
import me.riddhimanadib.formmaster.model.FormElementPickerDate;

/**
 * ViewHolder for DatePicker
 * Created by Riddhi - Rudra on 30-Jul-17.
 */

public class FormElementPickerDateViewHolder extends BaseViewHolder
        implements DatePickerDialog.OnDateSetListener {

    private DatePickerDialog mDatePickerDialog;
    private Calendar mCalendarCurrentDate;
    private ReloadListener mReloadListener;
    private BaseFormElement mFormElement;
    private int mPosition;

    public FormElementPickerDateViewHolder(View v, ReloadListener reloadListener) {
        super(v);
        mReloadListener = reloadListener;
        mCalendarCurrentDate = java.util.Calendar.getInstance();
        mCalendarCurrentDate.setTimeInMillis(System.currentTimeMillis() - 1000);
    }

    @Override
    public void bind(int position, final BaseFormElement formElement, final Context context) {
        super.bind(position, formElement, context);
        mFormElement = formElement;
        mPosition = position;
        mEditTextValue.setFocusableInTouchMode(false);

        mDatePickerDialog = DatePickerDialog.newInstance(this,
                mCalendarCurrentDate.get(Calendar.YEAR),
                mCalendarCurrentDate.get(Calendar.MONTH),
                mCalendarCurrentDate.get(Calendar.DAY_OF_MONTH));
        mDatePickerDialog.setMinDate(mCalendarCurrentDate);
        Calendar[] dates = formElement.getDates();
        boolean isBlockDates = formElement.isBlockDates();
        if (dates != null && dates.length > 0) {
            if(isBlockDates){
                mDatePickerDialog.setDisabledDays(dates);
            } else {
                mDatePickerDialog.setSelectableDays(dates);
            }
        }

        int dateTimePickerColor = ContextCompat.getColor(context,
                R.color.date_time_picker_background_color);

        mDatePickerDialog.setAccentColor(dateTimePickerColor);


        mEditTextValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatePickerDialog.show(((AppCompatActivity) context).getSupportFragmentManager(), formElement.getTitle());
            }
        });

        mTextViewTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatePickerDialog.show(((AppCompatActivity) context).getSupportFragmentManager(), formElement.getTitle());
            }
        });
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        mCalendarCurrentDate.set(Calendar.YEAR, year);
        mCalendarCurrentDate.set(Calendar.MONTH, monthOfYear);
        mCalendarCurrentDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        String myFormatDate = ((FormElementPickerDate) mFormElement).getDateFormat();
        SimpleDateFormat sdfDate = new SimpleDateFormat(myFormatDate, Locale.US);

        String currentValue = mFormElement.getValue();
        String newValue = sdfDate.format(mCalendarCurrentDate.getTime());

        // trigger event only if the value is changed
        if (!currentValue.equals(newValue)) {
            mReloadListener.updateValue(mPosition, newValue);
        }
    }
}
