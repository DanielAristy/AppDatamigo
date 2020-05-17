package com.example.appdatamigo.entidades;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.appdatamigo.persistencia.Tabla;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Entity(tableName = Tabla.DOCUMENTO)
@NoArgsConstructor
public class Documento {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "idDocumento")
    private Integer idDocumento;
    @ColumnInfo(name = "nitProveedor")
    private String nitProveedor;
    @ColumnInfo(name = "precio")
    private Double precio;
}
