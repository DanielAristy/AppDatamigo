package com.example.appdatamigo.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.appdatamigo.R;
import com.example.appdatamigo.utilities.ActionBarUtil;

public class MainActivity extends AppCompatActivity {

    private ActionBarUtil actionBarUtil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponentes();
    }

    private void initComponentes() {
        actionBarUtil = new ActionBarUtil(this);
        actionBarUtil.setToolBar(getString(R.string.facturas));
    }

    public void goToRegistroFactura(View view) {
        Intent factura = new Intent(this,FacturaActivity.class);
        startActivity(factura);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
