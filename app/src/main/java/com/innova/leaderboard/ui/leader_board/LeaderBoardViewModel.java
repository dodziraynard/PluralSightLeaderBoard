package com.innova.leaderboard.ui.leader_board;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.innova.leaderboard.LeaderBoardRepository;
import com.innova.leaderboard.models.HourLeader;
import com.innova.leaderboard.models.SkillLeader;

import java.util.List;

public class LeaderBoardViewModel extends AndroidViewModel {
    public final String TAG = this.getClass().getSimpleName();
    private LeaderBoardRepository mRepository;
    private MutableLiveData<Boolean> isBusy = new MutableLiveData<>();


    public LeaderBoardViewModel(@NonNull Application application) {
        super(application);
        mRepository = new LeaderBoardRepository(application);
    }

    public LiveData<List<HourLeader>> getAllHourLeaders() {
        isBusy.setValue(true);
        return mRepository.getAllHourLeaders();
    }

    public LiveData<List<SkillLeader>> getAllSkillLeaders() {
        isBusy.setValue(true);
        return mRepository.getAllSkillLeaders();
    }

    public MutableLiveData<Boolean> isBusy() {
        return isBusy;
    }

    public void setBusy(Boolean busy) {
        isBusy.setValue(busy);
    }
}
