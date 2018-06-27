package com.josenaves.roomwordsample;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class WordRepository {
    private WordDao wordDao;
    private LiveData<List<Word>> allWords;

    WordRepository(Application application) {
        final WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        this.wordDao = db.wordDao();
        this.allWords = wordDao.getAllWords();
    }

    public LiveData<List<Word>> getAllWords() {
        return allWords;
    }

    public void insert(Word word) {
        new InsertAsyncTask(wordDao).execute(word);
    }

    private static class InsertAsyncTask extends AsyncTask<Word, Void, Void> {
        private WordDao asyncTaskDao;

        InsertAsyncTask(WordDao wordDao) {
            this.asyncTaskDao = wordDao;
        }

        @Override
        protected Void doInBackground(final Word... words) {
            asyncTaskDao.insert(words[0]);
            return null;
        }
    }
}
