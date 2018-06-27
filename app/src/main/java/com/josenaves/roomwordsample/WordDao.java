package com.josenaves.roomwordsample;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface WordDao {
    @Insert
    void insert(Word word);

    @Query("DELETE FROM word_table")
    void deletaAll();

    @Query("SELECT * FROM word_table ORDER BY word asc")
    LiveData<List<Word>> getAllWords();
}
