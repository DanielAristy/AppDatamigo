package com.example.appdatamigo.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.appdatamigo.R;
import com.example.appdatamigo.entidades.Documento;
import com.example.appdatamigo.view.DetallesActivity;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.NonNull;

public class    DocumentoAdapter extends BaseAdapter implements Filterable {

    private final LayoutInflater inflater;
    private List<Documento> listaFacturasOut;
    private List<Documento> listaFacturasIn;
    Context context;

    public DocumentoAdapter(Context context, List<Documento> listaFacturas) {
        this.listaFacturasOut = listaFacturas;
        this.listaFacturasIn = listaFacturas;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listaFacturasOut.size();
    }

    @Override
    public Object getItem(int position) {
        return listaFacturasOut.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = inflater.inflate(R.layout.factura_item_layout, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
            context = convertView.getContext();
        }
//        holder.imagen.setImageURI(listaFacturasOut.get(position).getImagen());
        holder.nitProveedor.setText(listaFacturasOut.get(position).getNitProveedor());
        holder.txtFecha.setText(listaFacturasOut.get(position).getFecha());
        holder.precio.setText( listaFacturasOut.get(position).getPrecio()+"");
        holder.setOnClickListeners(position);

        return convertView;
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    class ViewHolder implements View.OnClickListener {

        @BindView(R.id.imagen)
        ImageView imagen;
        @BindView(R.id.nitProveedor)
        TextView nitProveedor;
        @BindView(R.id.precio)
        TextView precio;
        @BindView(R.id.txtFecha)
        TextView txtFecha;
        @BindView(R.id.btnDetalles)
        Button btnDetalles;
        protected int posicion;

        public ViewHolder(View view) {
            ButterKnife.bind(this,view);
        }

        private void setOnClickListeners(Integer idDocumento){
            btnDetalles.setOnClickListener(this);
            this.posicion = idDocumento;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnDetalles:
                    enviarDatos();
                    break;
            }
        }

        private void enviarDatos() {

            Documento documento = listaFacturasOut.get(getPosicion());
            Intent intent = new Intent(context, DetallesActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("factura",documento);
            intent.putExtras(bundle);
            context.startActivity(intent);
            setPosicion(0);
        }

        public int getPosicion() {
            return posicion;
        }

        public void setPosicion(int posicion) {
            this.posicion = posicion;
        }
    }
}
