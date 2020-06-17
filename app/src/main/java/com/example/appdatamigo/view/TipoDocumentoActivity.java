package com.example.appdatamigo.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.appdatamigo.R;
import com.example.appdatamigo.persistencia.room.DataBaseHelper;
import com.example.appdatamigo.utilities.ActionBarUtil;

public class TipoDocumentoActivity extends AppCompatActivity {

    private ActionBarUtil actionBarUtil;
    /**Donde se van a listar los tipos de documentos*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_documento);
        initComponentes();

    }

    private void initComponentes() {
        actionBarUtil = new ActionBarUtil(this);
        actionBarUtil.setToolBar(getString(R.string.listado_tipos));
    }

    public void goToRegistroTipo(View view) {
        Intent intent = new Intent(this,RegistroTipoActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}