package com.innova.leaderboard.ui.leader_board;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.innova.leaderboard.LeaderBoardRepository;
import com.innova.leaderboard.models.HourLeader;
import com.innova.leaderboard.models.SkillLeader;

import java.util.List;

public class LeaderBoardViewModel extends AndroidViewModel {
    public final String TAG = this.getClass().getSimpleName();
    private LeaderBoardRepository mRepository;

    public LeaderBoardViewModel(@NonNull Application application) {
        super(application);
        mRepository = new LeaderBoardRepository(application);
    }

    public LiveData<List<HourLeader>> getAllHourLeaders() {
        return mRepository.getAllHourLeaders();
    }

    public LiveData<List<SkillLeader>> getAllSkillLeaders() {
        return mRepository.getAllSkillLeaders();
    }
}
