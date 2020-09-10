package com.innova.leaderboard.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = SkillLeader.SKILL_LEADERS_TABLE)
public class SkillLeader {
    @Expose(serialize = false, deserialize = false)
    @Ignore
    private final String TAG = this.getClass().getSimpleName();

    @Expose(serialize = false, deserialize = false)
    @Ignore
    public static final String SKILL_LEADERS_TABLE = "skill_leaders";

    @Expose(serialize = false, deserialize = false)
    @PrimaryKey(autoGenerate = true)
    private long id;

    @SerializedName("name")
    @ColumnInfo(name = "name")
    private String mName;

    @SerializedName("score")
    @ColumnInfo(name = "score")
    private int mScore;

    @SerializedName("country")
    @ColumnInfo(name = "country")
    private String mCountry;

    @SerializedName("badgeUrl")
    @ColumnInfo(name = "badge_url")
    private String mBadgeUrl;

    @Expose(serialize = false, deserialize = false)
    @Ignore
    private String mDescription;

    public SkillLeader(String name, int score, String country, String badgeUrl) {
        mName = name;
        mScore = score;
        mCountry = country;
        mBadgeUrl = badgeUrl;
    }

    public String getName() {
        return mName;
    }

    public long getId() {
        return id;
    }

    public int getScore() {
        return mScore;
    }

    public String getCountry() {
        return mCountry;
    }

    public String getBadgeUrl() {
        return mBadgeUrl;
    }

    public String getDescription() {
        mDescription = mScore + " skill IQ Score, " + mCountry;
        return mDescription;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        mName = name;
    }

    public void setScore(int score) {
        mScore = score;
    }

    public void setCountry(String country) {
        mCountry = country;
    }

    public void setBadgeUrl(String badgeUrl) {
        mBadgeUrl = badgeUrl;
    }
}
