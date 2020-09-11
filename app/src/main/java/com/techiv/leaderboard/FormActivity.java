package com.techiv.leaderboard;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.techiv.leaderboard.utils.Constants;

import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class FormActivity extends AppCompatActivity {
    private RequestQueue queue;
    private EditText mFirstName;
    private EditText mLastName;
    private EditText mEmail;
    private EditText mProjectLink;
    private Button mSubmit;
    private Dialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        //form_toolbar
        Toolbar toolbar = findViewById(R.id.form_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        // Initializing Queue for Volley
        queue = Volley.newRequestQueue(getApplicationContext());
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
        mDialog = new Dialog(this);
        mFirstName = findViewById(R.id.editText_firstname);
        mLastName = findViewById(R.id.editText_lastname);
        mEmail = findViewById(R.id.editText_email);
        mProjectLink = findViewById(R.id.editText_project_link);
        mSubmit = findViewById(R.id.form_submit_button);
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateForm()) {
                    showPositivePopup();
                }
            }
        });


    }

    private boolean validateForm() {
        boolean valid = true;

        String firstName = mFirstName.getText().toString();
        if (TextUtils.isEmpty(firstName)) {
            mFirstName.setError("Required.");
            valid = false;
        } else {
            mFirstName.setError(null);
        }

        String lastName = mLastName.getText().toString();
        if (TextUtils.isEmpty(lastName)) {
            mLastName.setError("Required.");
            valid = false;
        } else {
            mLastName.setError(null);
        }

        String email = mEmail.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mEmail.setError("Required.");
            valid = false;
        } else {
            mEmail.setError(null);
        }

        String projectLink = mProjectLink.getText().toString();
        if (TextUtils.isEmpty(projectLink)) {
            mProjectLink.setError("Required.");
            valid = false;
        } else {
            mProjectLink.setError(null);
        }

        return valid;
    }

    public void showPositivePopup() {
        mDialog.setContentView(R.layout.question_dialog);
        ImageView closePopup = mDialog.findViewById(R.id.dialog_cancel);
        final Button confirm = mDialog.findViewById(R.id.dialog_confirm_button);

        closePopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest request = new StringRequest(
                        Request.Method.POST,
                        Constants.googleFormUrl,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.d("TAG", "Response: " + response);
                                if (response.length() > 0) {
                                    mDialog.setContentView(R.layout.success_dialog);
                                    mDialog.show();
                                    mFirstName.setText(null);
                                    mLastName.setText(null);
                                    mEmail.setText(null);
                                    mProjectLink.setText(null);
                                } else {
                                    mDialog.setContentView(R.layout.failure_dialog);
                                    mDialog.show();
                                }
                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mDialog.setContentView(R.layout.failure_dialog);
                        mDialog.show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();
                        params.put(Constants.formFirstNameField, mFirstName.getText().toString());
                        params.put(Constants.formLastNameField, mLastName.getText().toString());
                        params.put(Constants.formEmailField, mEmail.getText().toString());
                        params.put(Constants.formProjectLinkField, mProjectLink.getText().toString());
                        return params;
                    }
                };
                request.setRetryPolicy(new DefaultRetryPolicy(
                        10000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                queue.add(request);
            }
        });
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDialog.show();
    }
}