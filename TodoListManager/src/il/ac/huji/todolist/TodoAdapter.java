package il.ac.huji.todolist;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class TodoAdapter extends ArrayAdapter<Task> {
	
	//ArrayList tasks, dates;
	private ArrayList<Task> tasks;
	
	public TodoAdapter(
			TodoListManagerActivity activity, List<Task> tasks) {
		super(activity, android.R.layout.simple_list_item_1, tasks);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Task task = getItem(position);
		LayoutInflater inflater = LayoutInflater.from(getContext());
		View view = inflater.inflate(R.layout.row, null);
		
		Calendar validDate = new GregorianCalendar();
		validDate.setTime(task.getTaskDate());
		
		
		TextView tasktxt = (TextView)view.findViewById(R.id.txtTodoTitle);
		tasktxt.setText(task.getTaskTitle());
		TextView taskdate=(TextView)view.findViewById(R.id.txtTodoDueDate);
		taskdate.setText(task.getDateAsString());
		
if (Calendar.getInstance().after(validDate)) {
		    tasktxt.setTextColor(Color.RED);
		    taskdate.setTextColor(Color.RED);
		}
		
		//Text date=task.getDateAsString();
		//taskdate.setText();
		
		/*
		if (position % 2 == 1) {
		    tasktxt.setTextColor(Color.BLUE);
			//view.setBackgroundColor(Color.BLUE);  
		} else {
			tasktxt.setTextColor(Color.RED);
		    //view.setBackgroundColor(Color.RED);  
		}*/

		return view;
	}
}
