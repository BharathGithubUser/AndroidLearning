package com.belivnat.tasks.modules.crud;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {CrudEntity.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CrudDao crudDao();
}
