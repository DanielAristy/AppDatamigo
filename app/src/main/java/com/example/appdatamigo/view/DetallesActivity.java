package com.example.appdatamigo.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.appdatamigo.R;
import com.example.appdatamigo.utilities.ActionBarUtil;

public class DetallesActivity extends AppCompatActivity {

    private ActionBarUtil actionBarUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);
        initComponents();
    }

    private void initComponents() {
        actionBarUtil = new ActionBarUtil(this);
        actionBarUtil.setToolBar(getString(R.string.detalles));

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}