package com.example.appdatamigo.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appdatamigo.R;
import com.example.appdatamigo.entidades.Documento;
import com.example.appdatamigo.persistencia.room.DataBaseHelper;
import com.example.appdatamigo.utilities.ActionBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FacturaActivity extends AppCompatActivity {

    @BindView(R.id.nitProveedor)
    public EditText nitProveedor;
    @BindView(R.id.valorFactura)
    public EditText valorFactura;
    private ActionBarUtil actionBarUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factura);
        ButterKnife.bind(this);
        initComponentes();
    }

    private void initComponentes() {
        actionBarUtil = new ActionBarUtil(this);
        actionBarUtil.setToolBar(getString(R.string.factura));
    }

    public void guardar(View view) {
        String nit = nitProveedor.getText().toString();
        Double total = toDouble(valorFactura.getText().toString());

        if (validarInformacion(nit, total)){
            Documento documento = getDocumento(nit, total);
            new InsercionDocumento().execute(documento);
            finish();
        }

    }

    private Documento getDocumento(String nit, Double total) {
        Documento documento = new Documento();
        documento.setNitProveedor(nit);
        documento.setPrecio(total);

        return documento;
    }

    private boolean validarInformacion(String nit, Double total) {

        boolean esValido = true;
        if ("".equals((nit))){
            nitProveedor.setError(getString(R.string.requerido));
            esValido = false;
        }

        if (total == 0){
            valorFactura.setError(getString(R.string.requerido));
            esValido = false;
        }

        return esValido;
    }

    private Double toDouble(String valor) {
        return "".equals(valor)? 0: Double.parseDouble(valor);
    }

    private class InsercionDocumento extends AsyncTask<Documento,Void,Void>{

        @Override
        protected Void doInBackground(Documento... documentos) {
            DataBaseHelper.getSimpleDB(getApplicationContext())
                    .getDocumentoDAO()
                    .insert(documentos[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Toast.makeText(
                    getApplicationContext(),
                        getString(R.string.succesfully), Toast.LENGTH_SHORT
            ).show();
            super.onPostExecute(aVoid);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
