package com.innova.leaderboard.daos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.innova.leaderboard.models.SkillLeader;

import java.util.List;

import static com.innova.leaderboard.models.SkillLeader.SKILL_LEADERS_TABLE;

@Dao
public interface SkillLeaderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertSkillLeader(SkillLeader leader);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSkillLeader(List<SkillLeader> leader);

    @Query("DELETE FROM " + SKILL_LEADERS_TABLE)
    void deleteAll();

    @Delete
    void deleteSkillLeader(SkillLeader leader);

    @Update
    void updateSkillLeader(SkillLeader leader);

    @Query("SELECT * from " + SKILL_LEADERS_TABLE + " ORDER BY id ASC")
    List<SkillLeader> getAllSkillLeaders();

    @Query("SELECT * FROM " + SKILL_LEADERS_TABLE + " WHERE id = :id")
    SkillLeader getSkillLeader(long id);
}