package com.belivnat.tasks.modules.crud;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.belivnat.tasks.R;

import java.util.List;

public class CrudAdapter extends RecyclerView.Adapter<CrudAdapter.MyCrudViewHolder> {
    Context context;
    List<String> someDataList;
    CrudOnClickListener crudOnClickListener;
    interface CrudOnClickListener {
        void onClickedCrudList(int position);
        void onEdit(int position);
        void onDelete(int position);
        void onSave(int position, String updatedData);
    }
    public CrudAdapter(Context context, CrudOnClickListener crudOnClickListener, List<String> someData ) {
        this.context = context;
        this.someDataList = someData;
        this.crudOnClickListener = crudOnClickListener;
    }

    @NonNull
    @Override
    public CrudAdapter.MyCrudViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_crud, parent, false);
        return new MyCrudViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CrudAdapter.MyCrudViewHolder holder, final int position) {
        holder.etSomeData.setText(someDataList.get(position));
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.imgEdit.setVisibility(View.GONE);
                holder.etSomeData.setEnabled(true);
                holder.etSomeData.setSelection(holder.etSomeData.getText().length());
                crudOnClickListener.onEdit(position);
            }
        });
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crudOnClickListener.onDelete(position);
            }
        });
        holder.imgSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.imgEdit.setVisibility(View.VISIBLE);
                holder.etSomeData.setEnabled(false);
                crudOnClickListener.onSave(position, holder.etSomeData.getText().toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return someDataList.size();
    }

    public class MyCrudViewHolder extends RecyclerView.ViewHolder {
        EditText etSomeData;
        ImageView imgEdit;
        ImageView imgDelete;
        ImageView imgSave;
        public MyCrudViewHolder(@NonNull View itemView) {
            super(itemView);
            etSomeData = itemView.findViewById(R.id.et_some_data);
            imgEdit = itemView.findViewById(R.id.img_edit);
            imgDelete = itemView.findViewById(R.id.img_delete);
            imgSave = itemView.findViewById(R.id.img_save);
        }
    }
}
