package com.belivnat.tasks.modules.multisearch;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.belivnat.tasks.R;

import java.util.ArrayList;

public class MultiSelectAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements MultiSelectSectionDecorator.StickyHeaderInterface {
    int SECTION_VIEW = 0;
    int DATA_VIEW = 1;
    ArrayList<MultiSelectModel> multiSelectList;
    Context context;

    public MultiSelectAdapter(Context context, ArrayList<MultiSelectModel> multiSelectModel) {
        this.multiSelectList = multiSelectModel;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == DATA_VIEW) {
            View dataView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.adapter_multiselect_data, parent, false);
            return new DataView(dataView);
        } else if (viewType == SECTION_VIEW) {
            View sectionView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.adapter_multiselect_section, parent, false);
            return new SectionView(sectionView);
        }
        //Default View Should be the DataView
        View dataView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_multiselect_data, parent, false);
        return new DataView(dataView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof DataView) {
            ((DataView) holder).dataTextView.setText(multiSelectList.get(position).getDataString());
        } else if (holder instanceof SectionView) {
            ((SectionView) holder).sectionTextView.setText(multiSelectList.get(position).getDataString());
            ((SectionView) holder).sectionTextView.setClickable(false);
        }
    }

    @Override
    public int getItemViewType(int position) {
        super.getItemViewType(position);
        if (multiSelectList != null && multiSelectList.size() > 0) {
            if (multiSelectList.get(position).isSection) {
                return SECTION_VIEW;
            }
        }
        return DATA_VIEW;
    }

    @Override
    public int getItemCount() {
        return multiSelectList.size();
    }

    @Override
    public int getHeaderPositionForItem(int itemPosition) {
        int headerPosition = 0;
        do {
            if (this.isHeader(itemPosition)) {
                headerPosition = itemPosition;
                break;
            }
            itemPosition -= 1;
        } while (itemPosition >= 0);
        return headerPosition;
    }

    @Override
    public int getHeaderLayout(int headerPosition) {
        if (multiSelectList.get(headerPosition).isSection) {
            return R.layout.adapter_multiselect_section;
        } else {
            return 0;
        }
    }

    @Override
    public void bindHeaderData(View header, int headerPosition) {
        new SectionView(header).sectionTextView.setText(multiSelectList.get(headerPosition).dataString);
        Log.d("bind","Bind Header");
    }

    @Override
    public boolean isHeader(int itemPosition) {
        return multiSelectList.get(itemPosition).isSection;
    }

    public class DataView extends RecyclerView.ViewHolder {
        TextView dataTextView;

        public DataView(@NonNull View itemView) {
            super(itemView);
            dataTextView = itemView.findViewById(R.id.txt_multiselect_data);
        }
    }

    public class SectionView extends RecyclerView.ViewHolder {
        TextView sectionTextView;

        public SectionView(@NonNull View itemView) {
            super(itemView);
            sectionTextView = itemView.findViewById(R.id.txt_multiselect_section);
        }
    }
}
