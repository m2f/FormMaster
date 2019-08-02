package me.riddhimanadib.formmaster.viewholder;

import android.app.DatePickerDialog;
import android.content.Context;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import android.view.View;
import android.widget.DatePicker;

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
        implements DatePickerDialog.OnDateSetListener{

    private DatePickerDialog mDatePickerDialog;
    private Calendar mCalendarCurrentDate;
    private ReloadListener mReloadListener;
    private BaseFormElement mFormElement;
    private int mPosition;

    public FormElementPickerDateViewHolder(View v, Context context, ReloadListener reloadListener) {
        super(v);
        mReloadListener = reloadListener;
        mCalendarCurrentDate = java.util.Calendar.getInstance();
    }

    @Override
    public void bind(int position, BaseFormElement formElement, final Context context) {
        super.bind(position, formElement, context);
        mFormElement = formElement;
        mPosition = position;
        mEditTextValue.setFocusableInTouchMode(false);

        mDatePickerDialog = new DatePickerDialog(context,
                this,
                mCalendarCurrentDate.get(Calendar.YEAR),
                mCalendarCurrentDate.get(Calendar.MONTH),
                mCalendarCurrentDate.get(Calendar.DAY_OF_MONTH));

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

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
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
