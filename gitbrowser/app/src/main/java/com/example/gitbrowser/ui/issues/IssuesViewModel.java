package com.example.gitbrowser.ui.issues;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class IssuesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public IssuesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Profile fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}