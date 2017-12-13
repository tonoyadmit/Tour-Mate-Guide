package in.digitechlab.travelmatepro;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import in.digitechlab.travelmatepro.LoginActivity;
import in.digitechlab.travelmatepro.MainActivity;
import in.digitechlab.travelmatepro.R;

/**
 * Created by DELL on 11/4/2017.
 */

public class NewEventActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = NewEventActivity.class.getSimpleName();

    private static final Uri INSERT_EVENT_URI = Uri.parse(TravelProvider.CONTENT_URI+"/"+TravelDatabase.TABLE_EVENT);
    //private static final Uri QUERY_USER_URI = Uri.parse(TravelProvider.CONTENT_URI+"/"+TravelDatabase.TABLE_USER);

    private EditText eName, eSLoc, eDLoc, eDpDate, eBudget;
    private String eNameTx, eSLocTx, eDLocTx, eBudgetTx, eDpDateTx;
    private Button createBtn;
    private Integer user_id;
    private Double iBudget;
    //private DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    //private TextView tv201;

    final Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);

        Intent intentR = getIntent();
        user_id = intentR.getIntExtra("Uid",0);

        createBtn = (Button) findViewById(R.id.sEvent);

        createBtn.setOnClickListener(this);

        eName = (EditText)findViewById(R.id.eName);
        eSLoc = (EditText) findViewById(R.id.eStartLoc);
        eDLoc = (EditText) findViewById(R.id.eDest);
        eDpDate = (EditText) findViewById(R.id.sDeptDate);
        eBudget = (EditText) findViewById(R.id.eBudget);

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        eDpDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(NewEventActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        eDpDate.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    public void onClick(View view) {

        eNameTx = eName.getText().toString();
        eSLocTx = eSLoc.getText().toString();
        eDLocTx = eDLoc.getText().toString();
        eDpDateTx = eDpDate.getText().toString();
        iBudget = Double.parseDouble(eBudget.getText().toString());

        createEvent(view);
    }

    public void createEvent(View view) {
        ContentValues values = new ContentValues();
        values.put(TravelDatabase.EVENT_NAME,eNameTx);
        values.put(TravelDatabase.STARTING_LOCATION,eSLocTx);
        values.put(TravelDatabase.DESTINATION,eDLocTx);
        values.put(TravelDatabase.DEPARTURE_DATE,eDpDateTx);
        values.put(TravelDatabase.ESTIMATED_BUDGET,iBudget);
        values.put(TravelDatabase.USER_ID,user_id);
        values.put(TravelDatabase.EVENT_STATUS,"1");
        Uri insertedEventUri = getContentResolver().insert(INSERT_EVENT_URI,values);
        Toast.makeText(this, "New Event: "+insertedEventUri.toString(), Toast.LENGTH_SHORT).show();
        Log.e(TAG, "inserted uri: "+insertedEventUri.toString());
        //getUserData();
    }
}
