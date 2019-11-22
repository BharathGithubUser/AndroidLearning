package com.belivnat.tasks.modules.crud;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CrudEntity {
        @PrimaryKey(autoGenerate = true)
        public int uid;

        @ColumnInfo(name = "someDataList")
        public String someDataList;
}
