package il.ac.huji.todolist;

import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;

public class AddNewTodoItemActivity extends Activity {
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_item);
        findViewById(R.id.btnOK).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				EditText edtTaskName = (EditText)findViewById(R.id.edtNewItem);
				DatePicker dp=(DatePicker)findViewById(R.id.datePicker);
				Calendar calendar = Calendar.getInstance();
			    calendar.set(dp.getYear(), dp.getMonth(), dp.getDayOfMonth());
				
				
				String taskName = edtTaskName.getText().toString();
				if (taskName == null || "".equals(taskName)) {
					setResult(RESULT_CANCELED);
					finish();
				} else {
					Intent resultIntent = new Intent();
					resultIntent.putExtra("title", taskName);
					
					resultIntent.putExtra("dueDate", calendar.getTime());
					setResult(RESULT_OK, resultIntent);
					finish();
				}
			}
		});
        findViewById(R.id.btnCancel).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				 
					setResult(RESULT_CANCELED);
					finish();
			}
		});
    }
}
