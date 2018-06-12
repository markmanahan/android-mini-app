// as of 2.6.2018

/*
Various references:

FAB: https://www.programcreek.com/java-api-examples/index.php?class=android.support.design.widget.FloatingActionButton&method=setOnClickListener
Sending data between activities: https://stackoverflow.com/questions/2091465/how-do-i-pass-data-between-activities-in-android-application
Get Intent: https://developer.android.com/reference/android/app/Activity.html#getIntent()
Parceable Bucket List: https://stackoverflow.com/questions/6201311/how-to-read-write-a-boolean-when-implementing-the-parcelable-interface
Back button into toolbar: https://stackoverflow.com/questions/10108774/how-to-implement-the-android-actionbar-back-button
Verifying ability to implement multiple inferfaces: https://stackoverflow.com/questions/4546807/implementing-multiple-interfaces-with-java-is-there-a-way-to-delegate
Saving bundles on rotation: https://stackoverflow.com/questions/12503836/how-to-save-custom-arraylist-on-android-screen-rotate

 */

package cs4720.cs.virginia.edu.updatedList;

import android.app.Activity;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class BucketListActivity extends AppCompatActivity {

    ArrayList<BucketItem> bucket_list;
    RecyclerView rvBucketList;
    FloatingActionButton add_bucket_list_item_button;

    static final int ADD_ITEM_TO_LIST = 1;
    static final int EDIT_ITEM_IN_LIST = 2;

    BucketItem item_to_add;
    String name, description, latitude, longitude, due_date;
    int bucket_list_index;

    TextView some_bucket_item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bucket_list);

        // Lookup the recycler view in activity layout
        rvBucketList = (RecyclerView) findViewById(R.id.rvBucketList);

        if(savedInstanceState == null || !savedInstanceState.containsKey("key")) {
            // Initialize bucket_list
            bucket_list = BucketItem.createInitialBucketList();
        }
        else {
            bucket_list = savedInstanceState.getParcelableArrayList("key");
        }

        // Create adapter passing in the sample user data
        BucketListAdapter adapter = new BucketListAdapter(this, bucket_list);
        // Attach the adapter to the recycler view to populate items
        rvBucketList.setAdapter(adapter);
        // Set layout manager to position the items
        rvBucketList.setLayoutManager(new LinearLayoutManager(this));

        // lookup the floating action button in activity layout
        add_bucket_list_item_button = (FloatingActionButton) findViewById(R.id.add_bucket_list_item_button);
        // attach a plus sign to the floating action button
        add_bucket_list_item_button.setImageResource(R.drawable.ic_add_black_24dp);
        // sets the action on the click of the floating action button
        add_bucket_list_item_button.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // create an intent to send to the add activity, start add activity for a result
                Intent add_item_intent = new Intent(BucketListActivity.this, AddItemActivity.class);
                startActivityForResult(add_item_intent, 1);

            }

        });


    }

    // saves the state of bucket list in the case of a rotate
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("key", bucket_list);
        super.onSaveInstanceState(outState);
    }

    // called when a call to another activity concludes and returns to the bucket list activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // if the result involved adding an item to the list
        if (requestCode == ADD_ITEM_TO_LIST) {

            // and the result was OK
            if (resultCode == Activity.RESULT_OK) {

                // extract the bucket item information from the intent coming from the add activity
                name = data.getStringExtra("name");
                description = data.getStringExtra("description");
                latitude = data.getStringExtra("latitude");
                longitude = data.getStringExtra("longitude");
                due_date = data.getStringExtra("due_date");

                // create a new bucket item given the extracted information
                item_to_add = new BucketItem(name, description, due_date, latitude, longitude, false);

                // append the newly created item to the list
                bucket_list.add(item_to_add);
                // re-sort the list with the new item in it
                Collections.sort(bucket_list);

                // notify the adapter that a change has been made to the list to update the displayed layout
                rvBucketList.getAdapter().notifyDataSetChanged();

            }

        }


        // or if the result involved editing an item that was already on the list
        else if (requestCode == EDIT_ITEM_IN_LIST) {
            //Log.v("fromEdit", data.getStringExtra("name"));
            // Retrieve the bucket item position that was edited
            int originalPosition = Integer.parseInt(data.getStringExtra("position"));
            // extract the bucket item information from the intent coming from the edit activity
            name = data.getStringExtra("name");
            description = data.getStringExtra("description");
            latitude = data.getStringExtra("latitude");
            longitude = data.getStringExtra("longitude");
            due_date = data.getStringExtra("due_date");

            // set changes to the bucket list item
            bucket_list.get(originalPosition).setName_of_bucket_item(name);
            bucket_list.get(originalPosition).setBucket_item_description(description);
            bucket_list.get(originalPosition).setLatitude(latitude);
            bucket_list.get(originalPosition).setLongitude(longitude);
            bucket_list.get(originalPosition).setDue_date(due_date);

            // re-sort the list with the new item in it
            Collections.sort(bucket_list);

            // notify the adapter that a change has been made to the list to update the displayed layout
            rvBucketList.getAdapter().notifyDataSetChanged();
        }


    }

    public void StartAddActivity(View view) {
        Intent intent = new Intent(this, AddItemActivity.class);
        startActivityForResult(intent, 1);
    }


    // called in the list when a previous instance of the activity is going to be restored
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {

        super.onRestoreInstanceState(savedInstanceState, persistentState);

        bucket_list = savedInstanceState.getParcelableArrayList("bucket_list");

    }

}
