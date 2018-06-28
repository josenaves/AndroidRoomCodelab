package com.josenaves.roomwordsample;


import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

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
                            .addCallback(callback)
                            .build();
                }
            }
        }
        return instance;
    }

    private static RoomDatabase.Callback callback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(instance).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private final WordDao dao;

        PopulateDbAsync(WordRoomDatabase db) {
            dao = db.wordDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao.insert(new Word("Hello"));
            dao.insert(new Word("World"));
            dao.insert(new Word("this"));
            dao.insert(new Word("is"));
            dao.insert(new Word("Room"));
            dao.insert(new Word("LiveData"));
            dao.insert(new Word("RecyclerView"));

            return null;
        }
    }
}
