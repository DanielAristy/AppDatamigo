package com.example.appdatamigo.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.appdatamigo.R;
import com.example.appdatamigo.adapter.DocumentoAdapter;
import com.example.appdatamigo.entidades.Documento;
import com.example.appdatamigo.persistencia.room.DataBaseHelper;
import com.example.appdatamigo.utilities.ActionBarUtil;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity{

    private ActionBarUtil actionBarUtil;
    @BindView(R.id.listViewFacturas)
    public ListView listViewFacturas;
    private DocumentoAdapter facturaAdapter;
    public List<Documento> listaDocumentos;
    DataBaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initComponentes();
        loadDocumentos();
    }
    private void initComponentes() {
        db = DataBaseHelper.getDBMainThread(this);
        actionBarUtil = new ActionBarUtil(this);
        actionBarUtil.setToolBar(getString(R.string.facturas));
    }

    private void loadDocumentos() {
        listaDocumentos = db.getDocumentoDAO().listar();
        if (listaDocumentos.isEmpty()){
            Toast.makeText(getApplicationContext(),R.string.sin_facturas, Toast.LENGTH_SHORT).show();
        }else{
            facturaAdapter = new DocumentoAdapter(this,listaDocumentos);
            listViewFacturas.setAdapter(facturaAdapter);
        }
    }

    public void goToRegistroFactura(View view) {
        Intent factura = new Intent(this,FacturaActivity.class);
        startActivity(factura);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        loadDocumentos();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}