package com.example.pr_idi.mydatabaseexample;

/**
 * MySQLiteHelper
 * Created by pr_idi on 10/11/16.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_BOOKS = "books";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_AUTHOR = "author";
    public static final String COLUMN_YEAR = "year";
    public static final String COLUMN_PUBLISHER= "publisher";
    public static final String COLUMN_CATEGORY= "category";
    public static final String COLUMN_PERSONAL_EVALUATION = "personal_evaluation";


    private static final String DATABASE_NAME = "books.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table " + TABLE_BOOKS + "( "
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_TITLE + " text not null, "
            + COLUMN_AUTHOR + " text not null, "
            + COLUMN_YEAR + " integer, "
            + COLUMN_PUBLISHER + " text not null, "
            + COLUMN_CATEGORY + " text not null, "
            + COLUMN_PERSONAL_EVALUATION + " text"
            + ");";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);

        //Insert 4 mock books

        Book a = new Book("Albert Sánchez Piñol","Vae Victus",2015,
                "La Campana","Historical Fiction","bo");
        Book b = new Book("John Boyne","El noi del pijama de ratlles",2007,
                "Editorial Empúries","Historical Fiction","molt bo");
        Book c = new Book("Dmitry Glukhovsky","Metro 2033",2008,"Heyne Verlag","Science Fiction","molt bo");
        Book d = new Book("Carlos Ruiz Zafón","L'ombra del vent",2006,"Editorial Planeta","Fiction","molt bo");

        database.insert(TABLE_BOOKS,null,a.toContentValues());
        database.insert(TABLE_BOOKS,null,b.toContentValues());
        database.insert(TABLE_BOOKS,null,c.toContentValues());
        database.insert(TABLE_BOOKS,null,d.toContentValues());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKS);
        onCreate(db);
    }

}