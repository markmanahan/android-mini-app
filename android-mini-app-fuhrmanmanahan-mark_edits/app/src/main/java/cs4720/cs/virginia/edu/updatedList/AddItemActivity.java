// as  of 2.6.2018

package cs4720.cs.virginia.edu.updatedList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

public class AddItemActivity extends AppCompatActivity {

    EditText name_field, description_field, latitude_field, longitude_field;
    Button save_new_item_button;
    DatePicker calendar;

    String name, description, month, day, year, latitude, longitude, due_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // layout lookup
        name_field = (EditText) findViewById(R.id.name_of_bucket_item);
        description_field = (EditText) findViewById(R.id.bucket_item_description);
        latitude_field = (EditText) findViewById(R.id.latitude_of_bucket_item);
        longitude_field = (EditText) findViewById(R.id.longitude_of_bucket_item);

        calendar = (DatePicker) findViewById(R.id.datePicker2);

        save_new_item_button = (Button) findViewById(R.id.save_new_item_button);
        // at the click of the save new item button
        save_new_item_button.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // retrieve the information entered into the field
                name = name_field.getText().toString();
                description = description_field.getText().toString();
                latitude = latitude_field.getText().toString();
                longitude = longitude_field.getText().toString();
                month = Integer.toString(calendar.getMonth() + 1);
                day = Integer.toString(calendar.getDayOfMonth());
                year = Integer.toString(calendar.getYear());

                // attach the retrieved information
                Intent result_intent = new Intent();
                result_intent.putExtra("name", name);
                result_intent.putExtra("description", description);
                result_intent.putExtra("latitude", longitude);
                result_intent.putExtra("longitude", longitude);

                due_date = year + "-" + month + "-" + day;
                result_intent.putExtra("due_date", due_date);

                // set the status of the intent to OK
                setResult(Activity.RESULT_OK, result_intent);

                // terminate, go back to the caller
                finish();

            }

        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
