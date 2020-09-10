package com.innova.leaderboard;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.innova.leaderboard.daos.HourLeaderDao;
import com.innova.leaderboard.daos.SkillLeaderDao;
import com.innova.leaderboard.models.HourLeader;
import com.innova.leaderboard.models.SkillLeader;
import com.innova.leaderboard.network.GetDataService;
import com.innova.leaderboard.network.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaderBoardRepository {
    private final String TAG = this.getClass().getSimpleName();

    private final MutableLiveData<List<HourLeader>> mHourLeaders;
    private final MutableLiveData<List<SkillLeader>> mSkillLeaders;

    Application mApplication;
    private final HourLeaderDao mHourLeaderDao;
    private final SkillLeaderDao mSkillLeaderDao;
    private GetDataService mDataService;

    public LeaderBoardRepository(Application application) {
        LeaderBoardRoomDatabase db = LeaderBoardRoomDatabase.getDatabase(application);
        mDataService = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);

        mApplication = application;
        mHourLeaderDao = db.mHourLeaderDao();
        mSkillLeaderDao = db.mSkillLeaderDao();

        mHourLeaders = new MutableLiveData<>();
        mSkillLeaders = new MutableLiveData<>();
    }

    public LiveData<List<HourLeader>> getAllHourLeaders() {
        apiGetAllHourLeaders();
        return mHourLeaders;
    }

    public LiveData<List<SkillLeader>> getAllSkillLeaders() {
        apiGetAllSkillLeaders();
        return mSkillLeaders;
    }

    public void apiGetAllHourLeaders() {
        Call<List<HourLeader>> mCall = mDataService.getAllHourLeaders();

        mCall.enqueue(new Callback<List<HourLeader>>() {
            @Override
            public void onResponse(Call<List<HourLeader>> call, Response<List<HourLeader>> response) {
                List<HourLeader> leaders = response.body();
                if (leaders.size() > 0) {
                    mHourLeaders.setValue(leaders);
                    mHourLeaderDao.insertHourLeader(leaders);
                }
            }

            @Override
            public void onFailure(Call<List<HourLeader>> call, Throwable t) {
                mHourLeaders.setValue(mHourLeaderDao.getAllHourLeaders());
            }
        });
    }

    public void apiGetAllSkillLeaders() {
        Call<List<SkillLeader>> mCall = mDataService.getAllSKillLeaders();

        mCall.enqueue(new Callback<List<SkillLeader>>() {
            @Override
            public void onResponse(Call<List<SkillLeader>> call, Response<List<SkillLeader>> response) {
                List<SkillLeader> leaders = response.body();
                if (leaders.size() > 0) {
                    mSkillLeaders.setValue(leaders);
                    mSkillLeaderDao.insertSkillLeader(leaders);
                }
            }

            @Override
            public void onFailure(Call<List<SkillLeader>> call, Throwable t) {
                mSkillLeaders.setValue(mSkillLeaderDao.getAllSkillLeaders());
            }
        });
    }

}
