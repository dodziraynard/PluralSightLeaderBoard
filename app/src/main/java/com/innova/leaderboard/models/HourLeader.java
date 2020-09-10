package com.innova.leaderboard.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = HourLeader.HOUR_LEADER_TABLE)
public class HourLeader {
    @Expose(serialize = false, deserialize = false)
    @Ignore
    private final String TAG = this.getClass().getSimpleName();

    @Expose(serialize = false, deserialize = false)
    @Ignore
    public static final String HOUR_LEADER_TABLE = "hour_leaders";

    @Expose(serialize = false, deserialize = false)
    @PrimaryKey(autoGenerate = true)
    private long id;

    @SerializedName("name")
    @ColumnInfo(name = "name")
    private String mName;

    @SerializedName("hours")
    @ColumnInfo(name = "hours")
    private int mHours;

    @SerializedName("country")
    @ColumnInfo(name = "country")
    private String mCountry;

    @SerializedName("badgeUrl")
    @ColumnInfo(name = "badge_url")
    private String mBadgeUrl;

    @Ignore
    private String mDescription;

    public HourLeader(String name, int hours, String country, String badgeUrl) {
        mName = name;
        mHours = hours;
        mCountry = country;
        mBadgeUrl = badgeUrl;
    }

    public String getName() {
        return mName;
    }

    public long getId() {
        return id;
    }

    public int getHours() {
        return mHours;
    }

    public String getCountry() {
        return mCountry;
    }

    public String getBadgeUrl() {
        return mBadgeUrl;
    }

    public String getDescription() {
        mDescription = mHours + " learning hours, " + mCountry;
        return mDescription;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        mName = name;
    }

    public void setHours(int hours) {
        mHours = hours;
    }

    public void setCountry(String country) {
        mCountry = country;
    }

    public void setBadgeUrl(String badgeUrl) {
        mBadgeUrl = badgeUrl;
    }
}
