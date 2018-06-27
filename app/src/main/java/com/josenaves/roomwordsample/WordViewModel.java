package com.josenaves.roomwordsample;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;


// Warning: Never pass context into ViewModel instances.
// Do not store Activity, Fragment, or View instances or their Context in the ViewModel.

// Important: ViewModel is not a replacement for the onSaveInstanceState() method,
// because the ViewModel does not survive a process shutdown. Learn more here.

public class WordViewModel extends AndroidViewModel {
    private WordRepository repository;

    private LiveData<List<Word>> words;

    public WordViewModel(Application application) {
        super(application);
        repository = new WordRepository(application);
        words = repository.getAllWords();
    }

    public LiveData<List<Word>> getWords() {
        return words;
    }

    public void insert(Word word) {
        repository.insert(word);
    }
}
