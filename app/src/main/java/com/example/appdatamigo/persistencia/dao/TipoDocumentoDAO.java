package com.example.appdatamigo.persistencia.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.appdatamigo.entidades.TipoDocumento;

import java.util.List;


@Dao
public interface TipoDocumentoDAO {


    @Insert
    void insertTipo(TipoDocumento tipoDocumento);

    @Query("SELECT * FROM tipoDocumento")
    List<TipoDocumento> listarTipos();

    @Query("DELETE FROM tipoDocumento WHERE idTipoDocumento=:idTipoDocumento")
    void deleteByIdTipoDocumento(Integer idTipoDocumento);
}
