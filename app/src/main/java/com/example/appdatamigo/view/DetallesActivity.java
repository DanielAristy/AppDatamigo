package com.example.appdatamigo.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
    @BindView(R.id.btnDelete)
    ImageView btnDelete;
    @BindView(R.id.btnEdit)
    ImageView btnEdit;
    @BindView(R.id.idDocumento)
    TextView idDocumento;
    @BindView(R.id.nitProveedor)
    TextView nitProveedor;
    @BindView(R.id.valor)
    TextView valor;
    @BindView(R.id.txtIdFactura)
    TextView txtIdFactura;
    @BindView(R.id.txtNitProveedor)
    TextView txtNitProveedor;
    @BindView(R.id.txtValorFactura)
    TextView txtValorFactura;
    @BindView(R.id.txtValores)
    TextView txtValores;
    @BindView(R.id.editNit)
    EditText editNit;
    @BindView(R.id.editValor)
    EditText editValor;
    @BindView(R.id.btnRegistrar)
    Button btnRegistrar;
    Context context;

    private ActionBarUtil actionBarUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);
        ButterKnife.bind(this);
        initComponents();
        cargarDatos();
        hideComponentsEdit();

    }

    private void hideComponentsEdit() {
        txtValores.setVisibility(View.GONE);
        editNit.setVisibility(View.GONE);
        editValor.setVisibility(View.GONE);
        btnRegistrar.setVisibility(View.GONE);
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

        hideComponents();
        showComponentsEdit();

        Bundle objetoEnviado = getIntent().getExtras();
        Documento documento = null;

        if (objetoEnviado != null){
            documento = (Documento) objetoEnviado.getSerializable("factura");
            editNit.setText(documento.getNitProveedor());
            editValor.setText(documento.getPrecio()+"");
        }

//        finish();
    }

    private void showComponentsEdit() {
        txtValores.setVisibility(View.VISIBLE);
        editNit.setVisibility(View.VISIBLE);
        editValor.setVisibility(View.VISIBLE);
        btnRegistrar.setVisibility(View.VISIBLE);
    }

    private void hideComponents() {
        btnDelete.setVisibility(View.GONE);
        btnEdit.setVisibility(View.GONE);
        idDocumento.setVisibility(View.GONE);
        nitProveedor.setVisibility(View.GONE);
        valor.setVisibility(View.GONE);
        txtIdFactura.setVisibility(View.GONE);
        txtNitProveedor.setVisibility(View.GONE);
        txtValorFactura.setVisibility(View.GONE);

    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    /**Editar los datos*/
    public void editarFactura(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        Toast.makeText(this,R.string.editar_factura,Toast.LENGTH_SHORT).show();
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