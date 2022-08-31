package com.snail.adapterlist.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.snail.adapterlist.objects.Animal;

import java.util.ArrayList;

/** Class for work with Database SQLITE
 *
 */
public class DBHelper extends SQLiteOpenHelper {

    /** Database name */
    private static final String DATABASE_NAME  = "animalsDB";
    /** Database version */
    private static final int    DATABASE_VERSION = 1;

    /** Table name for save info about animals */
    private static final String TABLE_ANIMALS  = "animals";

    /** Column name _id */
    private static final String KEY_ID     = "_id";
    /** Column name name */
    private static final String KEY_NAME   = "name";
    /** Column name age */
    private static final String KEY_AGE    = "age";
    /** Column name length */
    private static final String KEY_LENGTH = "length";
    /** Column name weight */
    private static final String KEY_WEIGHT = "weight";
    /** Column name color */
    private static final String KEY_COLOR  = "color";

    /** Constructor for DBHelper
     *
     * @param context Context
     */
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /** Call if database not exist
     *
     * @param sqLiteDatabase SQLITE
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_ANIMALS + "(" +
                KEY_ID       + " integer primary key," +
                KEY_NAME     + " text,"                +
                KEY_AGE      + " integer,"             +
                KEY_LENGTH   + " real,"                +
                KEY_WEIGHT   + " integer, "            +
                KEY_COLOR    + " text"                 +
                ")");
    }

    /**Call if need to update version of database
     *
     * @param SQLiteDatabase SQLITE
     * @param oldVersion number of old version db
     * @param newVersion number of new version db
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("drop table if exists " + TABLE_ANIMALS);
        onCreate(sqLiteDatabase);
    }

    /** Insert new animal in person table
     *
     * @param animal Object animal, who need to add
     *
     * @return -1: error
     * @return -2: animal exist in table
     * @return  another: id row in table
     */
    public long insertAnimal(Animal animal) {
        if (IsAnimalExist(animal)) {
            return -2;
        }

        ContentValues contentValues = new ContentValues();

        contentValues.put(DBHelper.KEY_NAME,    animal.getName());
        contentValues.put(DBHelper.KEY_AGE,     animal.getAge());
        contentValues.put(DBHelper.KEY_LENGTH,  animal.getLength());
        contentValues.put(DBHelper.KEY_WEIGHT,  animal.getWeight());
        contentValues.put(DBHelper.KEY_COLOR,   animal.getColor());

        SQLiteDatabase database = getWritableDatabase();
        long res_insert         = database.insert(TABLE_ANIMALS, null, contentValues);

        database.close();

        return res_insert;
    }

    /** Check if animal exist in animal table
     *
     * @param animal Object animal, who need to find
     * @return true: exist
     * @return false: not exist
     */
    public boolean IsAnimalExist(Animal animal) {
        SQLiteDatabase database = getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = database.query(TABLE_ANIMALS, null,
                KEY_NAME   + " = ? AND " +
                        KEY_AGE    + " = ? AND " +
                        KEY_LENGTH + " = ? AND " +
                        KEY_WEIGHT + " = ? AND " +
                        KEY_COLOR  + " = ? "     ,
                new String[] {animal.getName()                   ,
                        String.valueOf(animal.getAge()),
                        String.valueOf(animal.getLength()),
                        String.valueOf(animal.getWeight())       ,
                        animal.getColor() },
                null, null, null);

        boolean isExist = cursor.moveToFirst();
        cursor.close();
        database.close();

        return isExist;
    }

    /**Method to get all animals from DB and set them in arraylist
     *
     * @param animals ArrayList<Animal>
     */
    public void FillListAnimal(ArrayList<Animal> animals) {
        animals.clear();

        SQLiteDatabase database = getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = database.query(TABLE_ANIMALS, null,
                null ,
                null,
                null, null, null);

        if (cursor.moveToFirst()) {
            int column_id     = cursor.getColumnIndex(KEY_ID);
            int column_name   = cursor.getColumnIndex(KEY_NAME);
            int column_age    = cursor.getColumnIndex(KEY_AGE);
            int column_length = cursor.getColumnIndex(KEY_LENGTH);
            int column_weight = cursor.getColumnIndex(KEY_WEIGHT);
            int column_color  = cursor.getColumnIndex(KEY_COLOR);

            do {
                animals.add(new Animal(cursor.getLong(column_id),
                                       cursor.getString(column_name),
                                       cursor.getInt(column_age),
                                       cursor.getDouble(column_length),
                                       cursor.getInt(column_weight),
                                       cursor.getString(column_color)));
            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();
    }

    /**Method to delete animal by id row
     *
     * @param id_animal id row in SQLITE
     */
    public void deleteAnimal(long id_animal) {
        SQLiteDatabase database = getWritableDatabase();
        database.delete(TABLE_ANIMALS, KEY_ID + " = " + id_animal, null);
        database.close();
    }
}
