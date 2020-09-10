package com.innova.leaderboard.ui.leader_board;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.innova.leaderboard.R;
import com.innova.leaderboard.adapters.SkillLeadersAdapter;
import com.innova.leaderboard.models.SkillLeader;

import java.util.List;

public class SkillLeadersFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private SkillLeadersAdapter mAdapter;
    private LeaderBoardViewModel mViewModel;

    public SkillLeadersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_skill_leaders, container, false);
        init(view);

        mViewModel.getAllSkillLeaders().observe(getViewLifecycleOwner(), new Observer<List<SkillLeader>>() {
            @Override
            public void onChanged(List<SkillLeader> skillLeaders) {
                mAdapter.setData(skillLeaders);
            }
        });
        return view;
    }

    private void init(View view) {
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mAdapter = new SkillLeadersAdapter(getContext());

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);

        mViewModel = new ViewModelProvider(this).get(LeaderBoardViewModel.class);
    }
}