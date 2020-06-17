package com.example.appdatamigo.persistencia.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.appdatamigo.entidades.Documento;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface DocumentoDAO {

    @Insert
    void insert(Documento documento);

    @Delete
    void delete(Documento documento);

    @Update
    void update(Documento documento);

    @Query("DELETE FROM documento WHERE idDocumento=:idDocumento")
    void deleteByIdTarifa(Integer idDocumento);

    @Query("SELECT * FROM documento")
    List<Documento> listar();
}
