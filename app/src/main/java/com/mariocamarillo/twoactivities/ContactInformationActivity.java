package com.mariocamarillo.twoactivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ContactInformationActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvName;
    private TextView tvDescription;
    private TextView tvDate;
    private TextView tvPhone;
    private TextView tvEmail;
    private Button bEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_information);
        setViews();
        setData();
    }



    private void setViews() {
        tvName = findViewById(R.id.name);
        tvDescription = findViewById(R.id.description);
        tvDate = findViewById(R.id.date);
        tvPhone = findViewById(R.id.phone);
        tvEmail = findViewById(R.id.email);
        bEdit = findViewById(R.id.edit);
        bEdit.setOnClickListener(this);
    }

    private void setData() {
        if(getIntent().getStringExtra(getString(R.string.name)) != null){
            tvName.setText(getIntent().getStringExtra(getString(R.string.name)));
            tvPhone.setText(getIntent().getStringExtra(getString(R.string.phone)));
            tvDescription.setText(getIntent().getStringExtra(getString(R.string.description)));
            tvEmail.setText(getIntent().getStringExtra(getString(R.string.email)));
            tvDate.setText(getIntent().getStringExtra(getString(R.string.birthday)));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.edit:
                final Intent intent = new Intent(this, AddContactActivity.class);
                intent.putExtra(getString(R.string.name), tvName.getText().toString());
                intent.putExtra(getString(R.string.phone), tvPhone.getText().toString());
                intent.putExtra(getString(R.string.email), tvEmail.getText().toString());
                intent.putExtra(getString(R.string.description), tvDescription.getText().toString());
                intent.putExtra(getString(R.string.birthday), tvDate.getText().toString());
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }
    }
}
