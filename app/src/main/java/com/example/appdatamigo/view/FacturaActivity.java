package com.example.appdatamigo.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appdatamigo.R;
import com.example.appdatamigo.adapter.DocumentoAdapter;
import com.example.appdatamigo.entidades.Documento;
import com.example.appdatamigo.persistencia.room.DataBaseHelper;
import com.example.appdatamigo.utilities.ActionBarUtil;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FacturaActivity extends AppCompatActivity implements View.OnClickListener  {

    @BindView(R.id.imgFactura)
    public ImageView imgFactura;
    @BindView(R.id.nitProveedor)
    public EditText nitProveedor;
    @BindView(R.id.precio)
    public EditText precio;
    @BindView(R.id.txtPickerFecha)
    TextView txtPickerFecha;
    Button btnFecha;
    int month, day, year;
    private ActionBarUtil actionBarUtil;
    DataBaseHelper db;
    private static final int COD_SELECCIONA = 1;
    private static final int ABRIR_CAMARA = 2;
    private String absolutePath = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factura);
        ButterKnife.bind(this);
        initComponentes();
        btnFecha.setOnClickListener(this);
    }

    private void initComponentes() {
        db = DataBaseHelper.getSimpleDB(this);
        actionBarUtil = new ActionBarUtil(this);
        actionBarUtil.setToolBar(getString(R.string.factura));
        btnFecha = findViewById(R.id.btnFecha);
    }

    public void guardar(View view) {
//        String imagen = imgFactura.toString();
        String nit = nitProveedor.getText().toString();
        Double total = toDouble(precio.getText().toString());
        String fecha = txtPickerFecha.getText().toString();

        if (validarInformacion(nit, total)){
            Documento documento = getDocumento(nit, total, fecha);
            new InsercionDocumento().execute(documento);
            finish();
        }
        absolutePath = "";
    }

    private Documento getDocumento(String nit,
                                   Double total, String fecha) {
        Documento documento = new Documento();
//        documento.setImagen(imagen);
        documento.setNitProveedor(nit);
        documento.setPrecio(total);
        documento.setFecha(fecha);

        return documento;
    }

    private boolean validarInformacion(String nit, Double total) {

        boolean esValido = true;
        if ("".equals((nit))){
            nitProveedor.setError(getString(R.string.requerido));
            esValido = false;
        }

        if (total == 0){
            precio.setError(getString(R.string.requerido));
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

    /**
     * https://www.youtube.com/watch?v=zw4SMOprdbA&t=417s
     * https://www.youtube.com/watch?v=QC4pI-o4KcU
     * Guardar las imagenes en una direccion del dispositivo*/


    public void loadImage(View view) {
        tomarFoto();
    }

    private void tomarFoto() {
        Intent tomarFotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (tomarFotoIntent.resolveActivity(getPackageManager()) != null){
            File foto = null;

            try {
                foto = crearFoto();

            }catch (Exception e) {
                e.printStackTrace();
            }
            if (foto != null){
                Uri fotoUri = FileProvider.getUriForFile(FacturaActivity.this,
                        "com.example.appdatamigo", foto);
                tomarFotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, fotoUri);
                startActivityForResult(tomarFotoIntent, ABRIR_CAMARA);
            }
        }
    }

    private File crearFoto() throws IOException {

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String nombreArchivo = "factura" + timeStamp;

        File directorio = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File fotoArchivo = File.createTempFile(
                nombreArchivo,
                ".jpg",
                directorio
        );

        absolutePath = fotoArchivo.getAbsolutePath();
        return fotoArchivo;
    }

    public void selectImage(View view) {
        cargarImagen();
    }

    private void cargarImagen() {
        Intent cargarFoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(cargarFoto, COD_SELECCIONA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case COD_SELECCIONA:
                if (resultCode == RESULT_OK){
                    Uri miPath = data.getData();
                    imgFactura.setImageURI(miPath);
                }
                break;

            case ABRIR_CAMARA:
                if (resultCode == RESULT_OK){
                    Uri uri = Uri.parse(absolutePath);
                    imgFactura.setImageURI(uri);
                }
                break;
        }
    }
//    https://www.youtube.com/watch?v=QC4pI-o4KcU Video para tomar la foto
    @Override
    public void onClick(View view) {

        if (view == btnFecha) {
            Calendar calendar = Calendar.getInstance();
            day = calendar.get(Calendar.DAY_OF_MONTH);
            month = calendar.get(Calendar.MONTH);
            year = calendar.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(FacturaActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    txtPickerFecha.setText(dayOfMonth+"/"+(month + 1)+"/"+year);
                }
            }
                    ,day,month,year);
            datePickerDialog.show();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
