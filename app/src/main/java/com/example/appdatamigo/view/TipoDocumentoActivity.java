package com.example.appdatamigo.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

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

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}