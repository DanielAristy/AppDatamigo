package com.example.appdatamigo.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.appdatamigo.R;
import com.example.appdatamigo.entidades.Documento;
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

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}