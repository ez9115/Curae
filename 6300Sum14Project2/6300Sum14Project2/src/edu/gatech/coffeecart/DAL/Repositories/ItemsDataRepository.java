package edu.gatech.coffeecart.DAL.Repositories;
import java.util.ArrayList;
import java.util.List;

import edu.gatech.coffeecart.DAL.SqlLiteMarshal;
import edu.gatech.coffeecart.DAL.Models.Item;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ItemsDataRepository {

	  // Database fields
	  private SQLiteDatabase database;
	  private SqlLiteMarshal dbHelper;
	  private String[] allColumns = { SqlLiteMarshal.COLUMN_ID,
			  SqlLiteMarshal.COLUMN_ITEM };

	  public ItemsDataRepository(Context context) {
	    dbHelper = new SqlLiteMarshal(context);
	  }

	  public void open() throws SQLException {
	    database = dbHelper.getWritableDatabase();
	  }

	  public void close() {
	    dbHelper.close();
	  }

	  public Item createComment(String comment) {
	    ContentValues values = new ContentValues();
	    values.put(SqlLiteMarshal.COLUMN_ITEM, comment);
	    long insertId = database.insert(SqlLiteMarshal.TABLE_ITEMS, null,
	        values);
	    Cursor cursor = database.query(SqlLiteMarshal.TABLE_ITEMS,
	        allColumns, SqlLiteMarshal.COLUMN_ID + " = " + insertId, null,
	        null, null, null);
	    cursor.moveToFirst();
	    Item newComment = cursorToComment(cursor);
	    cursor.close();
	    return newComment;
	  }

	  public void deleteComment(Item comment) {
	    long id = comment.getId();
	    System.out.println("Comment deleted with id: " + id);
	    database.delete(SqlLiteMarshal.TABLE_ITEMS, SqlLiteMarshal.COLUMN_ID
	        + " = " + id, null);
	  }

	  public List<Item> getAllComments() {
	    List<Item> comments = new ArrayList<Item>();

	    Cursor cursor = database.query(SqlLiteMarshal.TABLE_ITEMS,
	        allColumns, null, null, null, null, null);

	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	      Item comment = cursorToComment(cursor);
	      comments.add(comment);
	      cursor.moveToNext();
	    }
	    // make sure to close the cursor
	    cursor.close();
	    return comments;
	  }

	  private Item cursorToComment(Cursor cursor) {
	    Item comment = new Item();
	    comment.setId(cursor.getLong(0));
	    comment.setComment(cursor.getString(1));
	    return comment;
	  }

}
