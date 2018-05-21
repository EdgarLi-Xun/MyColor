package cn.EdgarLi.tools;

import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.util.Log;

public class ColorDatabase {
	public class SQLHelper extends SQLiteOpenHelper {
		public final static int VERSION = 1;
		public final static String TABLE_NAME = "collection";
		public final static String ID = "id";
		public final static String VALUE = "value";
		public static final String DATABASE_NAME = "collection.db";

		public SQLHelper(Context context) {
			super(context, DATABASE_NAME, null, VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			String str_sql = "CREATE TABLE " + TABLE_NAME + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + VALUE
					+ " int );";
			db.execSQL(str_sql);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		}
	}

	private SQLiteDatabase database;
	private SQLHelper sqlHelper;
	private ArrayList<ColorInfo> dat = new ArrayList<ColorInfo>();
	ContentValues cValues;

	public ColorDatabase(Context context) { // 初始化
		sqlHelper = new SQLHelper(context);
		dat = new ArrayList<ColorInfo>();
		cValues = new ContentValues();
		database = sqlHelper.getWritableDatabase();
		Cursor cur = database.rawQuery("SELECT * FROM " + SQLHelper.TABLE_NAME, null);
		if (cur != null) {
			int count = 0;
			while (cur.moveToNext()) {
				count++;
				dat.add(new ColorInfo(ColorHelper.toHex(cur.getInt(1)),
						Color.red(cur.getInt(1)) + "," + Color.green(cur.getInt(1)) + "," + Color.blue(cur.getInt(1)),
						cur.getInt(1)));
			}
			if (count == 0) {

				add(Color.DKGRAY);
				add(Color.GRAY);
				add(Color.LTGRAY);
				add(Color.RED);
				add(Color.GREEN);
				add(Color.BLUE);
				add(Color.YELLOW);
				add(Color.CYAN);
				add(Color.MAGENTA);
				add(Color.BLUE);
			}
		}
	}

	private String getID(int id) { // 映射到数据库ID
		Cursor cur = database.rawQuery("SELECT * FROM " + SQLHelper.TABLE_NAME, null);
		String dat_id = "0";
		if (cur != null) {
			while (cur.moveToNext() && id-- > 0) {
				dat_id = cur.getString(0);
			}
		}
		return dat_id;
	}

	public ArrayList<ColorInfo> getArrayList() { // 获取同步ArrayList
		return dat;
	}

	public int add(int color) { // 同步添加数据
		int flag = 1;
		try {
			database = sqlHelper.getWritableDatabase();

			Cursor cur = database.rawQuery("SELECT * FROM " + SQLHelper.TABLE_NAME, null);
			if (cur != null) {
				while (cur.moveToNext()) {
					if (cur.getInt(1) == color) {
						flag = 0;
					}
				}
			}
			if (flag == 1) {
				cValues.clear();
				cValues.put(sqlHelper.VALUE, color);
				database.insert(sqlHelper.TABLE_NAME, null, cValues);
			}
		} catch (Exception e) {
			flag = -1;
		} finally {
			database.close();
		}

		if (flag == 1) {
			Log.i("sql", "OK " + color);
			dat.add(new ColorInfo(ColorHelper.toHex(color),
					Color.red(color) + "," + Color.green(color) + "," + Color.blue(color), color));
		}
		return flag;
	}

	public Boolean del(int id) { // 同步删除数据
		Boolean ok = true;
		try {
			database = sqlHelper.getWritableDatabase();
			database.delete(SQLHelper.TABLE_NAME, SQLHelper.ID + "=" + getID(id+1), null);
		} catch (Exception e) {
			ok = false;
		} finally {
			database.close();
		}
		if (ok) {
			dat.remove(id);
		}
		return ok;
	}

}
