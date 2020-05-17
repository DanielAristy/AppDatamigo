package com.example.appdatamigo.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.appdatamigo.R;
import com.example.appdatamigo.utilities.ActionBarUtil;

public class FacturaActivity extends AppCompatActivity {

    private ActionBarUtil actionBarUtil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factura);
        initComponentes();
    }

    private void initComponentes() {
        actionBarUtil = new ActionBarUtil(this);
        actionBarUtil.setToolBar(getString(R.string.factura));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
