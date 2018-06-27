package com.josenaves.roomwordsample;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = { Word.class }, version = 1)
public abstract class WordRoomDatabase extends RoomDatabase {
    public abstract WordDao wordDao();

    private static WordRoomDatabase instance;

    public static WordRoomDatabase getDatabase(final Context context) {
        if (instance == null) {
            synchronized (WordRoomDatabase.class) {
                if (instance == null) {
                    // create database here
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            WordRoomDatabase.class, "word_database")
                            .build();
                }
            }
        }
        return instance;
    }
}
