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
import com.innova.leaderboard.adapters.HourLeadersAdapter;
import com.innova.leaderboard.models.HourLeader;

import java.util.List;

public class LeadingLeadersFragment extends Fragment {
    private final String TAG = this.getClass().getSimpleName();
    private RecyclerView mRecyclerView;
    private HourLeadersAdapter mAdapter;
    private List<HourLeader> mLeaders;
    private LeaderBoardViewModel mViewModel;
    private ProgressBar mProgressBar;

    public LeadingLeadersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_leading_leaders, container, false);
        init(view);

        mViewModel.getAllHourLeaders().observe(getViewLifecycleOwner(), new Observer<List<HourLeader>>() {
            @Override
            public void onChanged(List<HourLeader> hourLeaders) {
                mAdapter.setData(hourLeaders);
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
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mProgressBar = view.findViewById(R.id.progress_bar);
        mAdapter = new HourLeadersAdapter(getContext());

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);

        mViewModel = new ViewModelProvider(this).get(LeaderBoardViewModel.class);
    }
}