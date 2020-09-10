package com.innova.leaderboard.utils;

import com.innova.leaderboard.models.HourLeader;
import com.innova.leaderboard.models.SkillLeader;

import java.util.ArrayList;
import java.util.List;

public class DummyData {
    private final String TAG = this.getClass().getSimpleName();

    public static List<HourLeader> getDummyHourLeaders() {
        List<HourLeader> mLeaders;
        mLeaders = new ArrayList<>();
        mLeaders.add(new HourLeader("Helegah Raynard Dodzi", 145, "Ghana", "url"));
        mLeaders.add(new HourLeader("Helegah Raynard Dodzi", 145, "Ghana", "url"));
        mLeaders.add(new HourLeader("Helegah Raynard Dodzi", 145, "Ghana", "url"));
        mLeaders.add(new HourLeader("Helegah Raynard Dodzi", 145, "Ghana", "url"));
        mLeaders.add(new HourLeader("Helegah Raynard Dodzi", 145, "Ghana", "url"));
        mLeaders.add(new HourLeader("Helegah Raynard Dodzi", 145, "Ghana", "url"));
        mLeaders.add(new HourLeader("Helegah Raynard Dodzi", 145, "Ghana", "url"));
        mLeaders.add(new HourLeader("Helegah Raynard Dodzi", 145, "Ghana", "url"));
        return mLeaders;
    }

    public static List<SkillLeader> getDummySkillLeaders() {
        List<SkillLeader> mLeaders;
        mLeaders = new ArrayList<>();
        mLeaders.add(new SkillLeader("Helegah Raynard Dodzi", 145, "Ghana", "url"));
        mLeaders.add(new SkillLeader("Helegah Raynard Dodzi", 145, "Ghana", "url"));
        mLeaders.add(new SkillLeader("Helegah Raynard Dodzi", 145, "Ghana", "url"));
        mLeaders.add(new SkillLeader("Helegah Raynard Dodzi", 145, "Ghana", "url"));
        mLeaders.add(new SkillLeader("Helegah Raynard Dodzi", 145, "Ghana", "url"));
        mLeaders.add(new SkillLeader("Helegah Raynard Dodzi", 145, "Ghana", "url"));
        mLeaders.add(new SkillLeader("Helegah Raynard Dodzi", 145, "Ghana", "url"));
        mLeaders.add(new SkillLeader("Helegah Raynard Dodzi", 145, "Ghana", "url"));
        return mLeaders;
    }
}
