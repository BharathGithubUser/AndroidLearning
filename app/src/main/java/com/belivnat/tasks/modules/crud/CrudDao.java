package com.belivnat.tasks.modules.crud;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CrudDao {
    @Query("SELECT * FROM crudentity")
    List<CrudEntity> getAll();

    @Query("SELECT * FROM crudentity WHERE uid IN (:dataIds)")
    List<CrudEntity> loadAllByIds(int[] dataIds);

    @Query("SELECT * FROM crudentity WHERE someDataList LIKE :someData LIMIT 1")
    CrudEntity findByName(String someData);

    @Insert
    void insertAll(CrudEntity... data);

    @Delete
    void delete(CrudEntity id);

    @Update
    void update(CrudEntity withId);
}
