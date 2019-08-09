package me.riddhimanadib.formmaster.viewholder;

import android.content.Context;
import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import android.view.View;

import me.riddhimanadib.formmaster.R;
import me.riddhimanadib.formmaster.listener.ReloadListener;
import me.riddhimanadib.formmaster.model.BaseFormElement;
import me.riddhimanadib.formmaster.model.FormElementPickerSingle;

/**
 * Created by Riddhi - Rudra on 30-Jul-17.
 */

public class FormElementPickerSingleViewHolder extends BaseViewHolder {

    private ReloadListener mReloadListener;
    private BaseFormElement mFormElement;
    private FormElementPickerSingle mFormElementPickerSingle;
    private int mPosition;

    public FormElementPickerSingleViewHolder(View v, Context context, ReloadListener reloadListener) {
        super(v);
        mReloadListener = reloadListener;
    }

    @Override
    public void bind(final int position, BaseFormElement formElement, final Context context) {
        super.bind(position, formElement, context);
        mFormElement = formElement;
        mPosition = position;
        mFormElementPickerSingle = (FormElementPickerSingle) mFormElement;
        mEditTextValue.setFocusableInTouchMode(false);

        // reformat the options in format needed
        final CharSequence[] options = new CharSequence[mFormElementPickerSingle.getOptions().size()];
        for (int i = 0; i < mFormElementPickerSingle.getOptions().size(); i++) {
            options[i] = mFormElementPickerSingle.getOptions().get(i);
        }

        final AlertDialog dialog = new AlertDialog.Builder(context)
            .setTitle(mFormElementPickerSingle.getPickerTitle())
            .setItems(options, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    mEditTextValue.setText(options[which]);
                    mFormElementPickerSingle.setValue(options[which].toString());
                    mReloadListener.updateValue(position, options[which].toString());
                }
            }).setNegativeButton("Clear", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                mEditTextValue.getText().clear();
                                mReloadListener.updateValue(position, null);
                            }
                }).create();

        mEditTextValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        mTextViewTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
    }

}
