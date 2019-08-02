package me.riddhimanadib.formmaster.viewholder;

import android.content.Context;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import me.riddhimanadib.formmaster.R;
import me.riddhimanadib.formmaster.listener.FormItemEditTextListener;
import me.riddhimanadib.formmaster.model.BaseFormElement;

/**
 * Base ViewHolder for all other viewholders
 * Created by Riddhi - Rudra on 30-Jul-17.
 */

public class BaseViewHolder extends RecyclerView.ViewHolder implements BaseViewHolderInterface {

    protected AppCompatTextView mTextViewTitle;
    protected AppCompatEditText mEditTextValue;
    protected AppCompatTextView mTextViewError;

    public BaseViewHolder(View itemView) {
        super(itemView);
        mTextViewTitle = itemView.findViewById(R.id.formElementTitle);
        mEditTextValue = itemView.findViewById(R.id.formElementValue);
        mTextViewError = itemView.findViewById(R.id.formElementError);
    }

    @Override
    public FormItemEditTextListener getListener() {
        return null;
    }

    @Override
    public void bind(int position, BaseFormElement formElement, Context context){
        mTextViewTitle.setText(formElement.getTitle());
        mEditTextValue.setText(formElement.getValue());
        mEditTextValue.setHint(formElement.getHint());

        if(formElement.getError().isEmpty()){
            mTextViewError.setVisibility(View.INVISIBLE);
            mTextViewError.setText("");
        } else {
            mTextViewError.setVisibility(View.VISIBLE);
            mTextViewError.setText(formElement.getError());
        }
    }
}
