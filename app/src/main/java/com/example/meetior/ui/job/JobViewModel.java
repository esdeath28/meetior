package com.example.meetior.ui.job;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class JobViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public JobViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is job fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}