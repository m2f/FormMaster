package me.riddhimanadib.formmaster.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import me.riddhimanadib.formmaster.R;
import me.riddhimanadib.formmaster.listener.OnFormElementValueChangedListener;
import me.riddhimanadib.formmaster.model.GroupedBaseFormElement;

public class GroupedFormAdapter extends RecyclerView.Adapter<GroupedFormAdapter.GroupedFormViewHolder> {

    List<GroupedBaseFormElement> groupedItems;
    private OnFormElementValueChangedListener listener;

    public GroupedFormAdapter(OnFormElementValueChangedListener listener) {
        this.listener = listener;
        this.groupedItems = Collections.emptyList();
    }

    public void setGroupedItems(List<GroupedBaseFormElement> groupedItems) {
        this.groupedItems = groupedItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GroupedFormViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.grouped_form_item, parent, false);
        return new GroupedFormViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupedFormViewHolder holder, int position) {
        GroupedBaseFormElement groupedBaseFormElement = groupedItems.get(position);
        holder.headerTextView.setText(groupedBaseFormElement.getHeaderText());
        FormAdapter formAdapter = new FormAdapter(holder.context, listener, true);
        holder.formItemList.setAdapter(formAdapter);
        formAdapter.addElements(groupedBaseFormElement.getItems());
    }

    @Override
    public int getItemCount() {
        return groupedItems.size();
    }

    static class GroupedFormViewHolder extends RecyclerView.ViewHolder {

        TextView headerTextView;
        RecyclerView formItemList;
        Context context;

        public GroupedFormViewHolder(@NonNull View itemView) {
            super(itemView);
            this.context = itemView.getContext();
            headerTextView = itemView.findViewById(R.id.group_header);
            formItemList =itemView.findViewById(R.id.group_item_list);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(itemView.getContext());
            linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
            formItemList.setLayoutManager(linearLayoutManager);
        }
    }
}
