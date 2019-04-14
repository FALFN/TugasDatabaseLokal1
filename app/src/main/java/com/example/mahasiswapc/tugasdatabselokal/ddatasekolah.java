package com.example.mahasiswapc.tugasdatabselokal;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Angarsa Labs...!
 * Created by Angga on 13/08/2018.
 */
@Database(entities = {DataSekolah.class} , version = 1)
public abstract class ddatasekolah extends RoomDatabase {
    public abstract SekolahDAO dao();
    private static ddatasekolah appDatabase;

    public static ddatasekolah iniDb(Context context){
        if(appDatabase == null)
            appDatabase = Room.databaseBuilder(context, ddatasekolah.class,
                    "dbUser").allowMainThreadQueries().build();

        return appDatabase;
    }

    public static void destroyInstance() {
        appDatabase = null;
    }
}
