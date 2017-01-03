package com.example.pr_idi.mydatabaseexample;

/**
 * BookData
 * Created by pr_idi on 10/11/16.
 */
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class BookData {

    // Database fields
    private SQLiteDatabase database;

    // Helper to manipulate table
    private MySQLiteHelper dbHelper;

    // Here we only select Title and Author, must select the appropriate columns
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_TITLE, MySQLiteHelper.COLUMN_YEAR,
            MySQLiteHelper.COLUMN_AUTHOR, MySQLiteHelper.COLUMN_CATEGORY,
            MySQLiteHelper.COLUMN_PERSONAL_EVALUATION, MySQLiteHelper.COLUMN_PUBLISHER};

    public BookData(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Book createBook(String title, String author, String publisher, int year,
                           String category, String eval) {
        ContentValues values = new ContentValues();
        Log.d("Creating", "Creating " + title + " " + author);

        values.put(MySQLiteHelper.COLUMN_TITLE, title);
        values.put(MySQLiteHelper.COLUMN_AUTHOR, author);
        values.put(MySQLiteHelper.COLUMN_PUBLISHER, publisher);
        values.put(MySQLiteHelper.COLUMN_YEAR, year);
        values.put(MySQLiteHelper.COLUMN_CATEGORY, category);
        values.put(MySQLiteHelper.COLUMN_PERSONAL_EVALUATION, eval);

        // Actual insertion of the data using the values variable
        long insertId = database.insert(MySQLiteHelper.TABLE_BOOKS, null,
                values);

        // Main activity calls this procedure to create a new book
        // and uses the result to update the listview.
        // Therefore, we need to get the data from the database
        // (you can use this as a query example)
        // to feed the view.

        Cursor cursor = database.query(MySQLiteHelper.TABLE_BOOKS,
                allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Book newBook = cursorToBook(cursor);

        // Do not forget to close the cursor
        cursor.close();

        // Return the book
        return newBook;
    }

    public Book findBookById(long id){

        Book b;
        Cursor cursor = database.query(MySQLiteHelper.TABLE_BOOKS,
                allColumns, MySQLiteHelper.COLUMN_ID
                        + " =?", new String[] {String.valueOf(id)}, null, null, null);

        cursor.moveToFirst();
        b = cursorToBook(cursor);
        cursor.close();

        return b;
    }

    public void deleteBook(Book book) {
        long id = book.getId();
        System.out.println("Book deleted with id: " + id);
        int afected = database.delete(MySQLiteHelper.TABLE_BOOKS, MySQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    public ArrayList<Book> findBookByTitle(String title){
        ArrayList<Book> books = new ArrayList<>();
        Cursor cursor = database.query(MySQLiteHelper.TABLE_BOOKS,
                allColumns, MySQLiteHelper.COLUMN_TITLE
                        + " =?", new String[] {title}, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Book book = cursorToBook(cursor);
            books.add(book);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return books;
    }


    public void insertBook(Book book){
        long id = database.insert(MySQLiteHelper.TABLE_BOOKS,null,book.toContentValues());
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_BOOKS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Book book = cursorToBook(cursor);
            books.add(book);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return books;
    }

    public void updateValoracion(long id, String valoracion) {
        ContentValues cv = new ContentValues();
        cv.put(dbHelper.COLUMN_PERSONAL_EVALUATION,valoracion);
        database.update(dbHelper.TABLE_BOOKS,cv,dbHelper.COLUMN_ID+"="+id,null);
    }

    private Book cursorToBook(Cursor cursor) {
        Book book = new Book();

        book.setId(cursor.getLong(0));
        book.setTitle(cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_TITLE)));
        book.setAuthor(cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_AUTHOR)));
        book.setPublisher(cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_PUBLISHER)));
        book.setYear(cursor.getInt(cursor.getColumnIndex(dbHelper.COLUMN_YEAR)));
        book.setCategory(cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_CATEGORY)));
        book.setPersonal_evaluation(cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_PERSONAL_EVALUATION)));
        return book;
    }
}