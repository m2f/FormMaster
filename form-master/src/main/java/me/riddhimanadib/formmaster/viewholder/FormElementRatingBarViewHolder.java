package me.riddhimanadib.formmaster.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.RatingBar;

import androidx.appcompat.widget.AppCompatTextView;

import me.riddhimanadib.formmaster.R;
import me.riddhimanadib.formmaster.listener.ReloadListener;
import me.riddhimanadib.formmaster.model.BaseFormElement;
import me.riddhimanadib.formmaster.model.FormElementRatingBar;

public class FormElementRatingBarViewHolder extends BaseViewHolder {

    public AppCompatTextView mTextViewTitle;
    public RatingBar mRatingBar;
    private BaseFormElement mFormElement;
    private ReloadListener mReloadListener;
    private FormElementRatingBar mFormElementRatingBar;
    private int mPosition;

    public FormElementRatingBarViewHolder(View v, Context context, ReloadListener reloadListener) {
        super(v);
        mTextViewTitle = (AppCompatTextView) v.findViewById(R.id.formElementTitle);
        mRatingBar = (RatingBar) v.findViewById(R.id.ratingBar);
        mReloadListener = reloadListener;
    }

    @Override
    public void bind(final int position, BaseFormElement formElement, final Context context) {
        mFormElement = formElement;
        mPosition = position;
        mFormElementRatingBar = (FormElementRatingBar) mFormElement;

        mTextViewTitle.setText(mFormElementRatingBar.getTitle());
        mRatingBar.setRating(mFormElementRatingBar.getRatingValue());
        mRatingBar.setIsIndicator(false);
        mRatingBar.setStepSize(0.5F);

        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar mRatingBar, float rating, boolean fromUser) {
                mReloadListener.updateRatingValue(position, rating);
            }
        });
    }
}
