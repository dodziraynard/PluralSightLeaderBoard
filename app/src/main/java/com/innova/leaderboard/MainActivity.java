package com.innova.leaderboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.innova.leaderboard.adapters.TabAdapter;
import com.innova.leaderboard.ui.leader_board.LeadingLeadersFragment;
import com.innova.leaderboard.ui.leader_board.SkillLeadersFragment;
import com.innova.leaderboard.ui.submission.SubmissionActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = this.getClass().getSimpleName();
    private ViewPager2 mViewPager;
    private TabLayout mTabLayout;
    private TabAdapter mAdapter;
    private Button mSubmitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        init();

        mViewPager.setAdapter(mAdapter);
        new TabLayoutMediator(mTabLayout, mViewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(mAdapter.getPageTitle(position));
            }
        }).attach();

        mSubmitBtn.setOnClickListener(this);
    }

    private void init() {
        mViewPager = findViewById(R.id.view_pager);
        mTabLayout = findViewById(R.id.tab_layout);
        mAdapter = new TabAdapter(this);
        mSubmitBtn = findViewById(R.id.submit_btn);

        mAdapter.addFragment(new LeadingLeadersFragment(), "Leading Leaders");
        mAdapter.addFragment(new SkillLeadersFragment(), "Skill IQ Leaders");
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.submit_btn) {
            startActivity(new Intent(this, SubmissionActivity.class));
        }
    }
}