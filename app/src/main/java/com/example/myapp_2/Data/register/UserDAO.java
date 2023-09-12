package com.example.myapp_2.Data.register;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private SQLiteDatabase database;
    private UserDatabaseHelper dbHelper;

    public UserDAO(Context context) {
        dbHelper = new UserDatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long register(String name, String email, String password) {
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return -1; // email не соответствует формату
        }

        Cursor cursor = database.query(UserDatabaseHelper.TABLE_USERS,
                new String[]{UserDatabaseHelper.COLUMN_EMAIL},
                UserDatabaseHelper.COLUMN_EMAIL + " = ?",
                new String[]{email}, null, null, null, null);

        if (cursor != null && cursor.getCount() > 0) { // если уже есть пользователь с такой почтой
            cursor.close();
            return -2;
        }

        ContentValues values = new ContentValues();
        values.put(UserDatabaseHelper.COLUMN_NAME, name);
        values.put(UserDatabaseHelper.COLUMN_EMAIL, email);
        values.put(UserDatabaseHelper.COLUMN_PASSWORD, password);

        return database.insert(UserDatabaseHelper.TABLE_USERS, null, values);
    }

    public boolean login(String email, String password) {
        Cursor cursor = database.query(UserDatabaseHelper.TABLE_USERS,
                new String[]{UserDatabaseHelper.COLUMN_EMAIL, UserDatabaseHelper.COLUMN_PASSWORD},
                UserDatabaseHelper.COLUMN_EMAIL + " = ? AND " + UserDatabaseHelper.COLUMN_PASSWORD + " = ?",
        new String[]{email, password}, null, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.close();
            return true;
        } else {
            return false;
        }
    }

    @SuppressLint("Range")
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        Cursor cursor = database.query(UserDatabaseHelper.TABLE_USERS,
                new String[]{UserDatabaseHelper.COLUMN_ID, UserDatabaseHelper.COLUMN_NAME,
                        UserDatabaseHelper.COLUMN_EMAIL, UserDatabaseHelper.COLUMN_PASSWORD},
                null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(cursor.getInt(cursor.getColumnIndex(UserDatabaseHelper.COLUMN_ID)));
                user.setName(cursor.getString(cursor.getColumnIndex(UserDatabaseHelper.COLUMN_NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(UserDatabaseHelper.COLUMN_EMAIL)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(UserDatabaseHelper.COLUMN_PASSWORD)));

                users.add(user);
            } while (cursor.moveToNext());

            cursor.close();
        }

        return users;
    }
    public User getUserById(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(UserDatabaseHelper.TABLE_USERS,
                new String[]{UserDatabaseHelper.COLUMN_ID, UserDatabaseHelper.COLUMN_NAME, UserDatabaseHelper.COLUMN_EMAIL, UserDatabaseHelper.COLUMN_PASSWORD},
                UserDatabaseHelper.COLUMN_ID + "= ?", new String[]{String.valueOf(id)},
                null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        @SuppressLint("Range") User user = new User(cursor.getInt(cursor.getColumnIndex(UserDatabaseHelper.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(UserDatabaseHelper.COLUMN_NAME)),
                cursor.getString(cursor.getColumnIndex(UserDatabaseHelper.COLUMN_EMAIL)),
                cursor.getString(cursor.getColumnIndex(UserDatabaseHelper.COLUMN_PASSWORD)));

        cursor.close();
        return user;
    }
    public void updateUser(User user) {
        ContentValues values = new ContentValues();
        values.put(UserDatabaseHelper.COLUMN_NAME, user.getName());
        values.put(UserDatabaseHelper.COLUMN_EMAIL, user.getEmail());
        values.put(UserDatabaseHelper.COLUMN_PASSWORD, user.getPassword());

        database.update(UserDatabaseHelper.TABLE_USERS, values,
                UserDatabaseHelper.COLUMN_ID + " = ?",
        new String[]{String.valueOf(user.getId())});
    }
    @SuppressLint("Range")
    public User getUserByEmail(String email) {
        Cursor cursor = database.query(UserDatabaseHelper.TABLE_USERS,
                new String[]{UserDatabaseHelper.COLUMN_ID, UserDatabaseHelper.COLUMN_NAME,
                        UserDatabaseHelper.COLUMN_EMAIL, UserDatabaseHelper.COLUMN_PASSWORD},
                UserDatabaseHelper.COLUMN_EMAIL + " = ?",
                new String[]{email},
                null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            User user = new User();
            user.setId((int) cursor.getLong(cursor.getColumnIndex(UserDatabaseHelper.COLUMN_ID)));
            user.setName(cursor.getString(cursor.getColumnIndex(UserDatabaseHelper.COLUMN_NAME)));
            user.setEmail(cursor.getString(cursor.getColumnIndex(UserDatabaseHelper.COLUMN_EMAIL)));
            user.setPassword(cursor.getString(cursor.getColumnIndex(UserDatabaseHelper.COLUMN_PASSWORD)));
            cursor.close();
            return user;
        }
        return null;
    }


}
