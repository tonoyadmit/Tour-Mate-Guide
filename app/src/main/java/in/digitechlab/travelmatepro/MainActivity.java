package in.digitechlab.travelmatepro;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


/**
 * Created by DELL on 11/4/2017.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intentR = getIntent();
        Bundle extras = intentR.getExtras();
        final Integer user_id = extras.getInt("Uid");
        String user_name = extras.getString("Uname");
        String user_phone = extras.getString("Uphone");
        String user_email = extras.getString("Uemail");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentS = new Intent(MainActivity.this, NewEventActivity.class);
                intentS.putExtra("Uid", user_id);
                startActivity(intentS);
            }
        });
    }
}
