package com.innova.leaderboard.daos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.innova.leaderboard.models.HourLeader;

import java.util.List;

import static com.innova.leaderboard.models.HourLeader.HOUR_LEADER_TABLE;

@Dao
public interface HourLeaderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertHourLeader(HourLeader leader);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertHourLeader(List<HourLeader> leader);

    @Query("DELETE FROM " + HOUR_LEADER_TABLE)
    void deleteAll();

    @Delete
    void deleteHourLeader(HourLeader leader);

    @Update
    void updateHourLeader(HourLeader leader);

    @Query("SELECT * from " + HOUR_LEADER_TABLE + " ORDER BY id ASC")
    List<HourLeader> getAllHourLeaders();

    @Query("SELECT * FROM " + HOUR_LEADER_TABLE + " WHERE id = :id")
    HourLeader getHourLeader(long id);
}