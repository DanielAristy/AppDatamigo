package com.example.appdatamigo.persistencia.room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.appdatamigo.entidades.Documento;
import com.example.appdatamigo.persistencia.dao.DocumentoDAO;

@Database(entities = {
        Documento.class},
        version = DataBaseHelper.VERSION_BASE_DATOS, exportSchema = false
        )
public abstract class DataBaseHelper extends RoomDatabase {

    public static final int VERSION_BASE_DATOS = 1;
    public static final String NOMBRE_BASE_DATOS = "datamigo";
    private static DataBaseHelper instance;

    /**Ejecutar la aplicacion fuera del hilo principal*/
    public static DataBaseHelper getSimpleDB(Context context){
        if (instance == null) {
            instance = Room.databaseBuilder(context, DataBaseHelper.class, NOMBRE_BASE_DATOS).build();
        }
        return instance;
    }

    /**Hilo principal
     * Queries allowMainThreadQueries()*/
    public static DataBaseHelper getDBMainThread(Context context){
        if (instance == null) {
            instance = Room.databaseBuilder(context, DataBaseHelper.class, NOMBRE_BASE_DATOS).allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return instance;
    }

    public abstract DocumentoDAO getDocumentoDAO();

}
