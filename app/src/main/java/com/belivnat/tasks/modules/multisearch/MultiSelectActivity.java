package com.belivnat.tasks.modules.multisearch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.belivnat.tasks.R;

import java.util.ArrayList;

public class MultiSelectActivity extends AppCompatActivity {
    ArrayList<MultiSelectModel> multiSelectList;
    ArrayList<MultiSelectModel> multiSelectFinalList;
    RecyclerView multiSelectRecyclerView;
    SearchView multiSelectSearchView;
    MultiSelectModel multiSelectModel;
    MultiSelectAdapter multiSelectAdapter;
    TextView txtNoData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_select);
        multiSelectList = new ArrayList<>();
        multiSelectFinalList = new ArrayList<>();
        multiSelectRecyclerView = findViewById(R.id.rv_multiselect);
        multiSelectSearchView = findViewById(R.id.sv_multiselect_search);
        txtNoData = findViewById(R.id.txt_no_data_found);
        for (int i = 0; i < 10; i++) {
            if (i % 5 == 0) {
                multiSelectModel = new MultiSelectModel("SomeData" + i, false, true);
            } else {
                multiSelectModel = new MultiSelectModel("SomeData" + i);
            }
            multiSelectList.add(multiSelectModel);
        }
        multiSelectList.add(new MultiSelectModel("Numbers", false, true));
        multiSelectList.add(new MultiSelectModel("one"));
        multiSelectList.add(new MultiSelectModel("two"));
        multiSelectList.add(new MultiSelectModel("three"));
        multiSelectList.add(new MultiSelectModel("four"));
        multiSelectList.add(new MultiSelectModel("five"));
        multiSelectList.add(new MultiSelectModel("six"));
        multiSelectList.add(new MultiSelectModel("Alphabets", false, true));
        multiSelectList.add(new MultiSelectModel("A"));
        multiSelectList.add(new MultiSelectModel("BC"));
        multiSelectList.add(new MultiSelectModel("DEF"));
        multiSelectList.add(new MultiSelectModel("GHIJ"));
        multiSelectList.add(new MultiSelectModel("KLMNO"));
        multiSelectList.add(new MultiSelectModel("PQRSTU"));
        multiSelectList.add(new MultiSelectModel("VWXYZAB"));
        multiSelectList.add(multiSelectModel);
        multiSelectFinalList.addAll(multiSelectList);
        multiSelectRecyclerView.setLayoutManager(
                new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        multiSelectAdapter =
                new MultiSelectAdapter(this.getApplicationContext(), multiSelectList);
        multiSelectRecyclerView.setAdapter(multiSelectAdapter);
        multiSelectRecyclerView.addItemDecoration(new MultiSelectSectionDecorator(multiSelectRecyclerView, multiSelectAdapter));
        multiSelectSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText != null) {
                    multiSelectList.clear();
                    multiSelectList.addAll(filteredResult(newText));
                    multiSelectAdapter.notifyDataSetChanged();
                    if (multiSelectList.size() == 0) {
                        txtNoData.setVisibility(View.VISIBLE);
                        multiSelectRecyclerView.setVisibility(View.GONE);
                    } else {
                        txtNoData.setVisibility(View.GONE);
                        multiSelectRecyclerView.setVisibility(View.VISIBLE);
                    }
                }
                return false;
            }
        });
        multiSelectSearchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                multiSelectList.clear();
                multiSelectList.addAll(multiSelectFinalList);
                multiSelectAdapter.notifyDataSetChanged();
                return false;
            }
        });
    }

    public ArrayList<MultiSelectModel> filteredResult(String searchQuery) {
        if (searchQuery.equals("")) {
            return multiSelectFinalList;
        } else {
            String currentSectionName = "";
            boolean isNewSection = false;
            ArrayList<MultiSelectModel> filteredList = new ArrayList<>();
            for (MultiSelectModel data : multiSelectFinalList) {
                if (data.isSection) {
                    isNewSection = true;
                    currentSectionName = data.dataString;
                } else {
                    if (data.dataString.toLowerCase().contains(searchQuery.toLowerCase())) {
                        if (isNewSection) {
                            filteredList.add(new MultiSelectModel(currentSectionName, false, true));
                            filteredList.add(new MultiSelectModel(data.dataString, false, false));
                            isNewSection = false;
                        } else {
                            filteredList.add(new MultiSelectModel(data.dataString, false, false));
                        }
                    }
                }
            }
            return filteredList;
        }
    }
}
