package com.innova.leaderboard.ui.leader_board;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.innova.leaderboard.R;
import com.innova.leaderboard.adapters.SkillLeadersAdapter;
import com.innova.leaderboard.models.HourLeader;
import com.innova.leaderboard.models.SkillLeader;

import java.util.List;

public class SkillLeadersFragment extends Fragment {

    private SkillLeadersAdapter mAdapter;
    private LeaderBoardViewModel mViewModel;
    private ProgressBar mProgressBar;

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
                mViewModel.setBusy(false);
            }
        });

        mViewModel.isBusy().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean busy) {
                if (busy) {
                    mProgressBar.setVisibility(View.VISIBLE);
                } else {
                    mProgressBar.setVisibility(View.GONE);
                }
            }
        });
        return view;
    }

    private void init(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        mProgressBar = view.findViewById(R.id.progress_bar);

        mAdapter = new SkillLeadersAdapter(getContext());

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        mViewModel = new ViewModelProvider(this).get(LeaderBoardViewModel.class);
    }
}