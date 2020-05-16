package com.example.appdatamigo.entidades;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.appdatamigo.persistencia.Tabla;

import java.util.Date;

import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity(tableName = Tabla.DOCUMENTO)
@NoArgsConstructor
public class Documento {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "idDocumento")
    private Integer idDocumento;
    @ColumnInfo(name = "nombreProveedor")
    private String  nombreProveedor;
    @ColumnInfo(name = "fecha")
    private Date fecha;
    @ColumnInfo(name = "nitProveedor")
    private String nitProveedor;
    @ColumnInfo(name = "total")
    private Double total;
    @ColumnInfo(name = "observacion")
    private String observacion;
    @ColumnInfo(name = "")
    private TipoDocumento tipoDocumento;
}
