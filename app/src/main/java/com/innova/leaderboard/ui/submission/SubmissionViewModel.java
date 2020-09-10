package com.innova.leaderboard.ui.submission;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.innova.leaderboard.LeaderBoardRepository;
import com.innova.leaderboard.models.SkillLeader;

import java.util.List;

public class SubmissionViewModel extends AndroidViewModel {
    public final String TAG = this.getClass().getSimpleName();
    private LeaderBoardRepository mRepository;

    public SubmissionViewModel(@NonNull Application application) {
        super(application);
        mRepository = new LeaderBoardRepository(application);
    }

    public LiveData<List<SkillLeader>> getAllSkillLeaders() {
        return mRepository.getAllSkillLeaders();
    }
}
