package com.example.appdatamigo.entidades;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.appdatamigo.persistencia.Tabla;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Entity(tableName = Tabla.TIPO_DOCUMENTO)
@NoArgsConstructor
public class TipoDocumento {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "idTipoDocumento")
    private Integer idTipoDocumento;
    @ColumnInfo(name = "nombre")
    private String nombre;
    @ColumnInfo(name = "sigla")
    private String sigla;
    @ColumnInfo(name = "activo")
    private Boolean activo;
}
