package com.innova.leaderboard.ui.submission;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.innova.leaderboard.LeaderBoardRepository;
import com.innova.leaderboard.models.SkillLeader;

import java.util.List;

public class SubmissionViewModel extends AndroidViewModel {
    public final String TAG = this.getClass().getSimpleName();

    private MutableLiveData<Boolean> isBusy = new MutableLiveData<>();

    public SubmissionViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Boolean> isBusy() {
        return isBusy;
    }

    public void setBusy(Boolean busy) {
        isBusy.setValue(busy);
    }
}
