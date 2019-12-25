package com.example.gitbrowser.ui.topics;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TopicsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public TopicsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Profile fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
