package com.example.appdatamigo.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appdatamigo.R;
import com.example.appdatamigo.adapter.DocumentoAdapter;
import com.example.appdatamigo.entidades.Documento;
import com.example.appdatamigo.persistencia.room.DataBaseHelper;
import com.example.appdatamigo.utilities.ActionBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetallesActivity extends AppCompatActivity {

    @BindView(R.id.idDocumento)
    TextView idDocumento;
    @BindView(R.id.nitProveedor)
    TextView nitProveedor;
    @BindView(R.id.valor)
    TextView valor;
    Context context;

    private ActionBarUtil actionBarUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);
        ButterKnife.bind(this);
        initComponents();
        cargarDatos();

    }

    private void cargarDatos() {
        Bundle objetoEnviado = getIntent().getExtras();
        Documento documento = null;

        if (objetoEnviado != null){
            documento = (Documento) objetoEnviado.getSerializable("factura");
            idDocumento.setText(documento.getIdDocumento().toString());
            nitProveedor.setText(documento.getNitProveedor());
            valor.setText(documento.getPrecio()+"");
        }
    }

    private void initComponents() {
        actionBarUtil = new ActionBarUtil(this);
        actionBarUtil.setToolBar(getString(R.string.detalles));

    }

    public void deleteFactura(View view) {

        context = view.getContext();
        new DeleteFactura().execute(Integer.parseInt(idDocumento.getText().toString()));
        finish();
//        DataBaseHelper.getSimpleDB(view.getContext())
//       .getDocumentoDAO()
//        .deleteByIdTarifa(Integer.parseInt(idDocumento.getText().toString()));
    }

    public void editFactura(View view) {

        Toast.makeText(this,R.string.editar_factura,Toast.LENGTH_SHORT).show();
        finish();
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    private class DeleteFactura extends AsyncTask<Integer,Void,Void> {
        @SuppressLint("WrongThread")
        @Override
        protected Void doInBackground(Integer... integers) {
            DataBaseHelper.getSimpleDB(context)
                    .getDocumentoDAO()
                    .deleteByIdTarifa(integers[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Toast.makeText(context,R.string.eliminar_factura,Toast.LENGTH_SHORT).show();
            super.onPostExecute(aVoid);
        }
    }
}