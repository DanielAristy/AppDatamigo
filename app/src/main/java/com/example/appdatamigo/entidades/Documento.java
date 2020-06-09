package com.example.appdatamigo.entidades;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.appdatamigo.persistencia.Tabla;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Entity(tableName = Tabla.DOCUMENTO)
@NoArgsConstructor
public class Documento implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "idDocumento")
    private Integer idDocumento;
    @ColumnInfo(name = "imagen")
    private String imagen;
    @ColumnInfo(name = "nitProveedor")
    private String nitProveedor;
    @ColumnInfo(name = "fecha")
    private String fecha;
    @ColumnInfo(name = "precio")
    private double precio;

}
