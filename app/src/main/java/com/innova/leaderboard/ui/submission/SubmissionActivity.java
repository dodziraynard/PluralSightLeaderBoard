package com.innova.leaderboard.ui.submission;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputEditText;
import com.innova.leaderboard.R;
import com.innova.leaderboard.network.GetDataService;
import com.innova.leaderboard.network.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmissionActivity extends AppCompatActivity implements View.OnClickListener{
    private final String TAG = "SubmissionActivity";

    private Button mSubmitBtn;
    private TextInputEditText mFirstName;
    private TextInputEditText mLastName;
    private TextInputEditText mEmail;
    private TextInputEditText mGithubLink;

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
    }

    private void init() {
        mSubmitBtn =  findViewById(R.id.submit_btn);
        mFirstName = findViewById(R.id.first_name);
        mLastName = findViewById(R.id.last_name);
        mEmail = findViewById(R.id.email);
        mGithubLink = findViewById(R.id.github_link);
    }

    public void showConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final View customLayout = getLayoutInflater().inflate(R.layout.confimation_dialog, null);
        builder.setView(customLayout);

        final AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void showSuccessDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final View customLayout = getLayoutInflater().inflate(R.layout.success_dialog, null);
        builder.setView(customLayout);

        final AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void showFailureDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final View customLayout = getLayoutInflater().inflate(R.layout.failure_dialog, null);
        builder.setView(customLayout);

        final AlertDialog dialog = builder.create();
        dialog.show();
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
        showConfirmationDialog();
        if(view.getId() == R.id.submit_btn){
            String firstName = mFirstName.getText().toString();
            String lastName = mLastName.getText().toString();
            String email = mEmail.getText().toString();
            String githubLink = mGithubLink.getText().toString();

            submitProject(email, firstName, lastName, githubLink);
        }
    }

    public void submitProject(String email, String firstName, String lastName, String link){
        GetDataService mFormDataService = RetrofitClientInstance.getRetrofitFormInstance().create(GetDataService.class);
        Call<Void> mCall = mFormDataService.submitProject(email, firstName, lastName, link);
        mCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                showSuccessDialog();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showFailureDialog();
            }
        });
    }
}