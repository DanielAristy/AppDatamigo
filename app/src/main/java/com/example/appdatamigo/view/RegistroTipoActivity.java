package com.example.appdatamigo.view;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appdatamigo.R;
import com.example.appdatamigo.entidades.TipoDocumento;
import com.example.appdatamigo.persistencia.room.DataBaseHelper;
import com.example.appdatamigo.utilities.ActionBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegistroTipoActivity extends AppCompatActivity {

//    @BindView(R.id.nombreTipoDocumento)
//    public EditText nombreTipoDocumento;
//    @BindView(R.id.sigla)
//    public EditText sigla;

    private ActionBarUtil actionBarUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_tipo);
//        ButterKnife.bind(this);
        initcomponent();
    }

    private void initcomponent() {
        actionBarUtil = new ActionBarUtil(this);
        actionBarUtil.setToolBar(getString(R.string.tipo_documento));
    }

//    public void registrar(View view) {
//        String nombreTipo = nombreTipoDocumento.getText().toString();
//        String siglaTipo = sigla.getText().toString();
//
//        if(validarInformacion(nombreTipo, siglaTipo)){
//            TipoDocumento tipoDocumento = getTipoDocumento(nombreTipo, siglaTipo);
//            new InsercionTipoDocumento().execute(tipoDocumento);
//            finish();
//        }
//    }
//
//    private TipoDocumento getTipoDocumento(String nombreTipo, String siglaTipo) {
//        TipoDocumento tipoDocumento = new TipoDocumento();
//        tipoDocumento.setNombre(nombreTipo);
//        tipoDocumento.setSigla(siglaTipo);
//        return tipoDocumento;
//    }
//
//    private boolean validarInformacion(String nombreTipo, String siglaTipo) {
//
//        boolean esValido = true;
//        if ("".equals((nombreTipo))){
//            nombreTipoDocumento.setError(getString(R.string.requerido));
//            esValido = false;
//        }
//
//        if ("".equals((siglaTipo))){
//            sigla.setError(getString(R.string.requerido));
//            esValido = false;
//        }
//
//        return esValido;
//    }
//
//
//    private class InsercionTipoDocumento extends AsyncTask<TipoDocumento,Void,Void> {
//
//        @Override
//        protected Void doInBackground(TipoDocumento... tipoDocumentos) {
//            DataBaseHelper.getSimpleDB(getApplicationContext())
//                    .getTipoDocumentoDAO()
//                    .insertTipo(tipoDocumentos[0]);
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void aVoid) {
//            Toast.makeText
//                    (getApplicationContext(),
//                            getString(R.string.succesfully), Toast.LENGTH_SHORT)
//                    .show();
//            super.onPostExecute(aVoid);
//        }
//    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}