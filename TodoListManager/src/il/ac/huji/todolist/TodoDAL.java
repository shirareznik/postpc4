package il.ac.huji.todolist;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;



public class TodoDAL {
	Context context;
	private SQLiteDatabase db;
	public TodoDAL(Context context) { 
		 this.context=context;
		  TasksDatabaseHelper helper = new TasksDatabaseHelper(context); 
	         db = helper.getWritableDatabase();
	         Parse.initialize(context,  context.getString(R.string.parseApplication), context.getString(R.string.clientKey)); 
	         ParseUser.enableAutomaticUser();
	         //ParseACL defaultACL = new ParseACL();
	         //ParseACL.setDefaultACL(defaultACL, true);
	 }
	
	 public boolean insert(ITodoItem todoItem) { 
			try {
				ContentValues taskData = new ContentValues();  
				long dbTime=todoItem.getDueDate().getTime(); //ask regarding the -1 issues
				taskData.put("title", todoItem.getTitle());
				taskData.put("due", dbTime);
				db.insert("todo", null, taskData);
				
				ParseObject parseObject = new ParseObject("todo");
				parseObject.put("title", todoItem.getTitle());
				parseObject.put("due", dbTime);
					parseObject.save();
			} catch (ParseException e) {
				return false;
			}
			return true; 
	 }
	 public boolean update(ITodoItem todoItem) {  
		 try {
			 long updateDue=todoItem.getDueDate().getTime();
			 ContentValues args = new ContentValues();
			 args.put("due", updateDue);
			 db.update("todo", args, "title" + "='" + todoItem.getTitle()+"'", null);	
		ParseQuery query = new ParseQuery("todo");
			query.whereEqualTo("title", todoItem.getTitle());
					List<ParseObject> objects=query.find();
					for (ParseObject object : objects) {
					
							object.put("due", updateDue);
							object.save();
					}
			} catch (Exception e) {
				return false;
			}
			 return true;
	 }
	 public boolean delete(ITodoItem todoItem) { 
		 
		 try {
			//db.delete("todo", "name like '?%'", 
				//	 new String[] { todoItem.getTitle() });
			 db.delete("todo", "title" + "='"+todoItem.getTitle()+"'",null);
			 ParseQuery query = new ParseQuery("todo");
				query.whereEqualTo("title", todoItem.getTitle());
				List<ParseObject> objects=query.find();//TODO check if deleted more than needed!
				for (ParseObject object : objects) {
					try {
						object.delete();
					
					} catch (ParseException e) {
						e.printStackTrace();
						throw new RuntimeException();
					}
				}
		} catch (Exception e) {
			return false;
		}
		 return true;
		 }
	
	 public List<ITodoItem> all() { 
		 
		  List<ITodoItem> items=new ArrayList<ITodoItem>();
		
		  try {
			items = new ArrayList<ITodoItem>();
			String selectQuery = "SELECT  * FROM " + "todo";
		    Cursor cursor = db.rawQuery(selectQuery, null);
		 
		    // looping through all rows and adding to list
		    if (cursor.moveToFirst()) {
		        do {
		        	Task t = new Task(cursor.getString(1),  new Date(cursor.getLong(2))); 
					items.add(t);
		        } while (cursor.moveToNext());
		    }
			/* ParseQuery query = new ParseQuery("todo");//TODO: maybe change to not background
			 List<ParseObject> objects=query.find();
			 for (ParseObject object : objects) {
					Task t = new Task(object.getString("title"), new Date(object.getLong("due")));
					items.add(t);
			}
			*/
		} catch (Exception e) {
			//return null; //TODO: check what to return in this case
		}
 		return items; //check if there is a problem with the "final"	  
	 }
	}