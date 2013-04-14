package il.ac.huji.todolist;

import java.util.Date;



public class Task implements ITodoItem {
	public Task(String taskTitle, Date taskDate) {
		super();
		this.taskTitle = taskTitle;
		this.taskDate = taskDate;
	}
	private String taskTitle;
	private Date taskDate;
	public String getTaskTitle() {
		return taskTitle;
	}
	@Override
	public String getTitle() {
		return this.taskTitle;
	}
	@Override
	public Date getDueDate() {
		return this.taskDate;
	}
	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}
	public Date getTaskDate() {
		return taskDate;
	}
	public void setTaskDate(Date taskDate) {
		this.taskDate = taskDate;
	}
	@SuppressWarnings("deprecation")
	public String getDateAsString()
	{
		if (this.taskDate==null)
		{
			return "No due date";
		}
		else
		{
			//String mnth=String.format("%02d", (this.taskDate.getMonth()));
			//String datePrint=this.taskDate.getDate()+"/"+mnth+"/"+(this.taskDate.getYear());
			String mnth=String.format("%02d", (this.taskDate.getMonth()+1));
			String datePrint=this.taskDate.getDate()+"/"+mnth+"/"+(this.taskDate.getYear()+1900);
			return datePrint;
		}
	}
}
