package com.innova.leaderboard.ui.submission;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;
import com.innova.leaderboard.R;
import com.innova.leaderboard.network.GetDataService;
import com.innova.leaderboard.network.RetrofitClientInstance;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmissionActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = "SubmissionActivity";

    private Button mSubmitBtn;
    private TextInputEditText mFirstName;
    private TextInputEditText mLastName;
    private TextInputEditText mEmail;
    private TextInputEditText mGithubLink;
    private AlertDialog mSuccessDialog;
    private AlertDialog mConfirmationDialog;
    private AlertDialog mFailureDialog;
    private ProgressBar mProgressBar;
    private SubmissionViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submission);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        init();

        // Set back arrow
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mSubmitBtn.setOnClickListener(this);

        mViewModel.isBusy().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean busy) {
                if (busy) {
                    mProgressBar.setVisibility(View.VISIBLE);
                    mSubmitBtn.setVisibility(View.GONE);
                } else {
                    mProgressBar.setVisibility(View.GONE);
                    mSubmitBtn.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void init() {
        mSubmitBtn = findViewById(R.id.submit_btn);
        mFirstName = findViewById(R.id.first_name);
        mLastName = findViewById(R.id.last_name);
        mEmail = findViewById(R.id.email);
        mGithubLink = findViewById(R.id.github_link);
        mProgressBar = findViewById(R.id.progress_bar);

        mViewModel = new ViewModelProvider(this).get(SubmissionViewModel.class);
    }

    public void showConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final View customLayout = getLayoutInflater().inflate(R.layout.confimation_dialog, null);
        Button yesBtn = customLayout.findViewById(R.id.yes_button);
        ImageView cancelBtn = customLayout.findViewById(R.id.cancel_btn);
        builder.setView(customLayout);

        mConfirmationDialog = builder.create();
        Objects.requireNonNull(mConfirmationDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mConfirmationDialog.show();

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mConfirmationDialog.dismiss();
            }
        });

        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = mFirstName.getText().toString();
                String lastName = mLastName.getText().toString();
                String email = mEmail.getText().toString();
                String githubLink = mGithubLink.getText().toString();

                if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || githubLink.isEmpty()) {
                    mConfirmationDialog.dismiss();
                    showFailureDialog(getString(R.string.fields_required));
                    return;
                }
                submitProject(email, firstName, lastName, githubLink);
                mConfirmationDialog.dismiss();
            }
        });
    }

    public void showSuccessDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final View customLayout = getLayoutInflater().inflate(R.layout.success_dialog, null);
        builder.setView(customLayout);

        mSuccessDialog = builder.create();
        Objects.requireNonNull(mSuccessDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mSuccessDialog.show();
    }

    public void showFailureDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final View customLayout = getLayoutInflater().inflate(R.layout.failure_dialog, null);
        builder.setView(customLayout);

        TextView messageView = customLayout.findViewById(R.id.message);
        messageView.setText(message);

        mFailureDialog = builder.create();
        Objects.requireNonNull(mFailureDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mFailureDialog.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.submit_btn) {
            showConfirmationDialog();
        }
    }

    public void submitProject(String email, String firstName, String lastName, String link) {
        mViewModel.setBusy(true);

        GetDataService mFormDataService = RetrofitClientInstance.getRetrofitFormInstance().create(GetDataService.class);
        Call<Void> mCall = mFormDataService.submitProject(email, firstName, lastName, link);
        mCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                showSuccessDialog();
                mViewModel.setBusy(false);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showFailureDialog(getString(R.string.failed) + ": "+t.getMessage());
                mViewModel.setBusy(false);
            }
        });
    }
}