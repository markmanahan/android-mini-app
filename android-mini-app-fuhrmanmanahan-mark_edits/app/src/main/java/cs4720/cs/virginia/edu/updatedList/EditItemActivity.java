// as of 2.6.18

package cs4720.cs.virginia.edu.updatedList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Collections;
import java.util.Date;

public class EditItemActivity extends AppCompatActivity {

    EditText name_field, description_field, latitude_field, longitude_field;
    Button save_changes_button;
    DatePicker calendar;

    String name, description, month, day, year, latitude, longitude, original_due_date, new_due_date, position;

    static final int EDIT_ITEM_IN_LIST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        // Back button implementation
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //possibly final
        Intent data = getIntent(); // extracts the intent that is sent to the edit activity

        // Find the various objects in the layout
        name_field = (EditText) findViewById(R.id.name_of_bucket_item);
        description_field = (EditText) findViewById(R.id.bucket_item_description);
        latitude_field = (EditText) findViewById(R.id.latitude_of_bucket_item);
        longitude_field = (EditText) findViewById(R.id.longitude_of_bucket_item);
        calendar = (DatePicker) findViewById(R.id.datePicker2);

        // extracts the data stored as extras in the intent that was sent
        name = data.getStringExtra("name");
        description = data.getStringExtra("description");
        latitude = data.getStringExtra("latitude");
        longitude = data.getStringExtra("longitude");
        position = data.getStringExtra("position");
        original_due_date = data.getStringExtra("due_date");

        // pre-populates the fields with the data that was extracted
        name_field.setText(name);
        description_field.setText(description);
        latitude_field.setText(latitude);
        longitude_field.setText(longitude);

        // Obtain integer values of the month, year, and day from the passed in original due date string
        String[] splitIntoParts = original_due_date.split("-");
        int original_year = Integer.parseInt(splitIntoParts[0]);
        int original_month = Integer.parseInt(splitIntoParts[1]) -1;
        int original_day = Integer.parseInt(splitIntoParts[2]);
        calendar.updateDate(original_year, original_month, original_day);

        save_changes_button = (Button) findViewById(R.id.save_changes_button);

        // When the save button is clicked
        save_changes_button.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // obtain the information entered into the fields
                name = name_field.getText().toString();
                description = description_field.getText().toString();
                latitude = latitude_field.getText().toString();
                longitude = longitude_field.getText().toString();
                month = Integer.toString(calendar.getMonth() + 1);
                day = Integer.toString(calendar.getDayOfMonth());
                year = Integer.toString(calendar.getYear());

                // attach the information to an intent that will be sent back to the caller
                Intent result_intent = new Intent();
                result_intent.putExtra("name", name);
                result_intent.putExtra("description", description);
                result_intent.putExtra("latitude", longitude);
                result_intent.putExtra("longitude", longitude);

                new_due_date = year + "-" + month + "-" + day;

                result_intent.putExtra("due_date", new_due_date);


                result_intent.putExtra("position", position);

                setResult(EDIT_ITEM_IN_LIST, result_intent);

                // set the status of the intent to be OK
                setResult(Activity.RESULT_OK, result_intent);


                // terminate, go back to the caller
                finish();

            }

        });

    }

}
