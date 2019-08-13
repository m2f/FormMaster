package me.riddhimanadib.formmaster.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import me.riddhimanadib.formmaster.R;
import me.riddhimanadib.formmaster.listener.OnFormElementValueChangedListener;
import me.riddhimanadib.formmaster.model.GroupedBaseFormElement;

public class GroupedFormAdapter extends RecyclerView.Adapter<GroupedFormAdapter.GroupedFormViewHolder> {

    private List<GroupedBaseFormElement> groupedItems;
    private AppCompatActivity context;
    private OnFormElementValueChangedListener listener;

    public GroupedFormAdapter(AppCompatActivity context, OnFormElementValueChangedListener listener) {
        this.context = context;
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
        FormAdapter formAdapter = new FormAdapter(context, listener, true);
        groupedBaseFormElement.setFormAdapter(formAdapter);
        holder.formItemList.setAdapter(formAdapter);
        formAdapter.addElements(groupedBaseFormElement.getItems());
    }

    @Override
    public int getItemCount() {
        return groupedItems.size();
    }

    public List<GroupedBaseFormElement> getGroupedItems() {
        return groupedItems;
    }

    static class GroupedFormViewHolder extends RecyclerView.ViewHolder {

        TextView headerTextView;
        RecyclerView formItemList;

        public GroupedFormViewHolder(@NonNull View itemView) {
            super(itemView);
            headerTextView = itemView.findViewById(R.id.group_header);
            formItemList =itemView.findViewById(R.id.group_item_list);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(itemView.getContext());
            linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
            formItemList.setLayoutManager(linearLayoutManager);
        }
    }
}
