package me.riddhimanadib.formmaster;

import android.content.Context;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;

import java.util.List;

import me.riddhimanadib.formmaster.adapter.FormAdapter;
import me.riddhimanadib.formmaster.adapter.GroupedFormAdapter;
import me.riddhimanadib.formmaster.listener.OnFormElementValueChangedListener;
import me.riddhimanadib.formmaster.model.BaseFormElement;
import me.riddhimanadib.formmaster.model.GroupedBaseFormElement;

/** Wrapper class around the adapter to assist in building form
 * Created by Adib on 16-Apr-17.
 */

public class FormBuilder {

    private FormAdapter mFormAdapter;
    private GroupedFormAdapter groupedFormAdapter;
    private boolean isGrouped;

    public FormBuilder(Context context, RecyclerView recyclerView) {
        initializeFormBuildHelper(context, recyclerView, null, false);
    }

    public FormBuilder(Context context, RecyclerView recyclerView, boolean isGrouped) {
        initializeFormBuildHelper(context, recyclerView, null, isGrouped);
    }

    public FormBuilder(Context context, RecyclerView recyclerView,
                       OnFormElementValueChangedListener listener) {
        initializeFormBuildHelper(context, recyclerView, listener, isGrouped);
    }

    public FormBuilder(Context context, RecyclerView recyclerView,
                       OnFormElementValueChangedListener listener, boolean isGrouped) {
        initializeFormBuildHelper(context, recyclerView, listener, isGrouped);
    }

    /**
     * private method for initializing form build helper
     */
    private void initializeFormBuildHelper(Context context, RecyclerView recyclerView,
                                           OnFormElementValueChangedListener listener, boolean isGrouped) {
        this.isGrouped = isGrouped;
        // set up the recycler view with adapter
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        linearLayoutManager.setStackFromEnd(false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        if(isGrouped) {
            this.groupedFormAdapter = new GroupedFormAdapter(listener);
            recyclerView.setAdapter(groupedFormAdapter);
        } else {
            this.mFormAdapter = new FormAdapter(context, listener);
            recyclerView.setAdapter(mFormAdapter);
        }
    }

    /**
     * add list of form elements to be shown
     */
    public void addFormElements(List<BaseFormElement> baseFormElements) {
        if(!isGrouped) {
            this.mFormAdapter.addElements(baseFormElements);
        }
    }

    /**
     * add group form elements
     */
    public void addGroupFormElements(List<GroupedBaseFormElement> groupedBaseFormElements) {
        if(isGrouped) {
            this.groupedFormAdapter.setGroupedItems(groupedBaseFormElements);
        }
    }

    /**
     * get value of specific form element
     */
    public BaseFormElement getFormElement(int tag) {
        if(!isGrouped) {
            return this.mFormAdapter.getValueAtTag(tag);
        }
        return null;
    }

    /**
     * return true if the form is valid
     */
    public boolean isValidForm() {
        boolean isValid = true;
        if(isGrouped) {

        } else {
            for (int i = 0; i < this.mFormAdapter.getItemCount(); i++) {
                BaseFormElement baseFormElement = this.mFormAdapter.getValueAtIndex(i);
                if (baseFormElement.isRequired() && TextUtils.isEmpty(baseFormElement.getValue())) {
                    baseFormElement.setError("The Field is required");
                    isValid = false;
                } else {
                    baseFormElement.setError("");
                }
            }
            this.mFormAdapter.notifyDataSetChanged();
        }
        return isValid;
    }

}