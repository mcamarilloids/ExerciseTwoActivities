package com.mariocamarillo.twoactivities;

import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddContactActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputEditText tilName;
    private TextInputEditText tilPhone;
    private TextInputEditText tilEmail;
    private TextInputEditText tilDescription;
    private DatePicker dpDate;
    private Button bNext;
    private CoordinatorLayout cLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        setViews();
        setData();
    }

    private void setViews() {
        tilName = findViewById(R.id.name);
        tilPhone = findViewById(R.id.phone);
        tilEmail = findViewById(R.id.email);
        tilDescription = findViewById(R.id.contact_description);
        dpDate = findViewById(R.id.datepicker);
        bNext = findViewById(R.id.next);
        cLayout = findViewById(R.id.parent);
        bNext.setOnClickListener(this);
    }

    private void setData() {
        if(getIntent().getStringExtra(getString(R.string.name)) != null){
            tilName.setText(getIntent().getStringExtra(getString(R.string.name)));
            tilPhone.setText(getIntent().getStringExtra(getString(R.string.phone)));
            tilDescription.setText(getIntent().getStringExtra(getString(R.string.description)));
            tilEmail.setText(getIntent().getStringExtra(getString(R.string.email)));
            final int day, month, year;
            day = Integer.parseInt(getIntent().getStringExtra(getString(R.string.birthday)).substring(0,2));
            month = Integer.parseInt(getIntent().getStringExtra(getString(R.string.birthday)).substring(3,5))-1;
            year = Integer.parseInt(getIntent().getStringExtra(getString(R.string.birthday)).substring(6,10));
            dpDate.updateDate(year, month, day);

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.next:
                if(tilName.getText().toString().isEmpty() || tilPhone.getText().toString().isEmpty() ||
                        tilEmail.getText().toString().isEmpty() || tilDescription.getText().toString().isEmpty()){
                    Snackbar.make(cLayout, "Ingresar todos los campos", Snackbar.LENGTH_LONG).show();
                }else{
                    final Intent intent = new Intent(this, ContactInformationActivity.class);
                    intent.putExtra(getString(R.string.name), tilName.getText().toString());
                    intent.putExtra(getString(R.string.phone), tilPhone.getText().toString());
                    intent.putExtra(getString(R.string.email), tilEmail.getText().toString());
                    intent.putExtra(getString(R.string.description), tilDescription.getText().toString());
                    final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                    final Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.YEAR, dpDate.getYear());
                    calendar.set(Calendar.MONTH, dpDate.getMonth());
                    calendar.set(Calendar.DAY_OF_MONTH, dpDate.getDayOfMonth());
                    Date valueDatePicket = calendar.getTime();
                    final String sDate = dateFormatter.format(valueDatePicket);
                    intent.putExtra(getString(R.string.birthday), sDate);
                    startActivity(intent);
                    finish();
                }
                break;
            default:
                break;
        }
    }
}
