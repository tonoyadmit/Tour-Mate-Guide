package in.digitechlab.travelmatepro;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by DELL on 11/4/2017.
 */

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final Uri INSERT_USER_URI = Uri.parse(TravelProvider.CONTENT_URI+"/"+TravelDatabase.TABLE_USER);
    private static final Uri QUERY_USER_URI = Uri.parse(TravelProvider.CONTENT_URI+"/"+TravelDatabase.TABLE_USER);

    private EditText etName, etPhone, etEmail, etPassword;
    private String nm, phn, email, password;
    private Button saveBtn;
    private TextView tv201;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regi);

        saveBtn = (Button) findViewById(R.id.sSave);

        saveBtn.setOnClickListener(this);

        etName = (EditText)findViewById(R.id.sName);
        etPhone = (EditText) findViewById(R.id.sPhone);
        etEmail = (EditText) findViewById(R.id.sEmail);
        etPassword = (EditText) findViewById(R.id.sPassword);

        //tv201 = (TextView) findViewById(R.id.tv201);

        getUserData();

    }

    @Override
    public void onClick(View view) {
        nm = etName.getText().toString();
        phn = etPhone.getText().toString();
        email = etEmail.getText().toString();
        password = etPassword.getText().toString();

        saveUser(view);
    }

    public void saveUser(View view) {
        ContentValues values = new ContentValues();
        values.put(TravelDatabase.NAME,nm);
        values.put(TravelDatabase.PHONE,phn);
        values.put(TravelDatabase.EMAIL,email);
        values.put(TravelDatabase.PASSWORD,password);
        Uri insertedUri = getContentResolver().insert(INSERT_USER_URI,values);
        Toast.makeText(this, insertedUri.toString(), Toast.LENGTH_SHORT).show();
        Log.e(TAG, "inserted uri: "+insertedUri.toString());
        getUserData();
    }

    private void getUserData() {
        Cursor cursor = getContentResolver().query(QUERY_USER_URI,null,null,null,null);

        if(cursor != null && cursor.getCount() > 0){
            String txt = "";
            cursor.moveToFirst();
            do{
                String qNm = cursor.getString(cursor.getColumnIndex(TravelDatabase.NAME));
                String qPn = cursor.getString(cursor.getColumnIndex(TravelDatabase.PHONE));
                String qEm = cursor.getString(cursor.getColumnIndex(TravelDatabase.EMAIL));
                String qPw = cursor.getString(cursor.getColumnIndex(TravelDatabase.PASSWORD));
                txt += qNm+"\n"+qPn+"\n"+qEm+"\n"+qPw+"\n\n";
            }while (cursor.moveToNext());
            //tv201.setText(txt);
        }
    }
}
