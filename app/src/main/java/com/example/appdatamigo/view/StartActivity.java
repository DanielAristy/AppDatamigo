package com.example.appdatamigo.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.appdatamigo.R;
import com.example.appdatamigo.utilities.ActionBarUtil;

public class StartActivity extends AppCompatActivity {

    private ActionBarUtil actionBarUtil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        initcomponent();
    }
    private void initcomponent() {
        actionBarUtil = new ActionBarUtil(this);
        actionBarUtil.setToolBar(getString(R.string.menu_principal));
    }

    public void goToMainActivity(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void goToListadoTipos(View view) {
        Intent intent = new Intent(this,TipoDocumentoActivity.class);
        startActivity(intent);
    }
}