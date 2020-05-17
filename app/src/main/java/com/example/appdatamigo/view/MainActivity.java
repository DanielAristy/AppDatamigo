package com.example.appdatamigo.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.amitshekhar.utils.DatabaseHelper;
import com.example.appdatamigo.R;
import com.example.appdatamigo.entidades.Documento;
import com.example.appdatamigo.persistencia.room.DataBaseHelper;
import com.example.appdatamigo.utilities.ActionBarUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private ActionBarUtil actionBarUtil;
    @BindView(R.id.listViewFacturas)
    public ListView listViewFacturas;
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

    private void loadDocumentos() {
        listaDocumentos = db.getDocumentoDAO().listar();
        if (listaDocumentos.isEmpty()){
            Toast.makeText(getApplicationContext(),R.string.sin_facturas, Toast.LENGTH_SHORT).show();
        }else{
            String[] facturasArray = new String[listaDocumentos.size()];
            for (int i = 0; i < listaDocumentos.size() ; i++) {
                facturasArray[i] = listaDocumentos.get(i).getNitProveedor();
            }

            ArrayAdapter<String> arrayAdapter = new
                    ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, facturasArray);
            listViewFacturas.setAdapter(arrayAdapter);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        loadDocumentos();
    }

    private void initComponentes() {
        db = DataBaseHelper.getDBMainThread(this);
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
