package com.example.gitbrowser.ui.starred;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class StarredViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public StarredViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Profile fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}