// as of 2.6.18

package cs4720.cs.virginia.edu.updatedList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class BucketListAdapter extends
        RecyclerView.Adapter<BucketListAdapter.ViewHolder> {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row

        //public CheckBox nameCheckBox;
        public TextView nameTextView;
        public TextView dateTextView;
        public CheckBox theCheckBox;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

			// NEED TO CHANGE R.ID.CONTACT_NAME
            nameTextView = (TextView) itemView.findViewById(R.id.bucket_name);
            dateTextView = (TextView) itemView.findViewById(R.id.bucket_date);
            theCheckBox = (CheckBox) itemView.findViewById(R.id.checkBox);
        }
    }

    // Store a member variable for the bucket item
    private List<BucketItem> mBucketItems;
    // Store the context for easy access
    private Context mContext;

    private Activity main_context;

    // Pass in the bucket item array into the constructor
    public BucketListAdapter(Context context, List<BucketItem> bucketitems) {
        mBucketItems = bucketitems;
        mContext = context;
    }

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mContext;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public BucketListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        main_context = (Activity) context;

        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
		
		// NEED TO CHANGE R.LAYOUT.ITEM_CONTACT
        View bucketItemView = inflater.inflate(R.layout.item_bucket, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(bucketItemView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(final BucketListAdapter.ViewHolder viewHolder, final int position) {
        // Get the data model based on position
        final BucketItem bucketitem = mBucketItems.get(position);

        // Set item views based on your views and data model
        //CheckBox checkBox = viewHolder.nameCheckBox;
        TextView textViewName = viewHolder.nameTextView;
        TextView textViewDate= viewHolder.dateTextView;

        CheckBox aCheckbox = viewHolder.theCheckBox;
        aCheckbox.setClickable(true);
        aCheckbox.setOnClickListener( new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  bucketitem.toggleDone_status();
                  Collections.sort(mBucketItems);
                  // Debugging until realized it should be onClickListener
                  //Log.v("hi", "sd:" + mBucketItems.get(0).getDone_status() + " | " + mBucketItems.get(1).getDone_status() + " | " + mBucketItems.get(2).getDone_status());
                  //Log.v("hi", "sd:" + mBucketItems.get(0).getName_of_bucket_item() + " | " + mBucketItems.get(1).getName_of_bucket_item() + " | " + mBucketItems.get(2).getName_of_bucket_item());
                  notifyDataSetChanged();
                  //rvBucketList.getAdapter().notifyDataSetChanged();
              }
          }
        );

        // Set the text view of the bucket item
        textViewName.setText(bucketitem.getName_of_bucket_item());
        textViewDate.setText(bucketitem.getDue_date());

        // Allow the name text view to be clickable
        textViewName.setClickable(true);
        //textViewName.setActivated(true);
        textViewName.setEnabled(true);

        // When the name text view is clicked, send bucket item to the edit item activity and expect result back
        textViewName.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View view){

                Intent edit_intent = new Intent(view.getContext(), EditItemActivity.class);

                edit_intent.putExtra("name", bucketitem.getName_of_bucket_item());
                edit_intent.putExtra("description", bucketitem.getBucket_item_description());
                edit_intent.putExtra("latitude", bucketitem.getLatitude());
                edit_intent.putExtra("longitude", bucketitem.getLongitude());
                edit_intent.putExtra("position", Integer.toString(position));
                edit_intent.putExtra("due_date", bucketitem.getDue_date());

                main_context.startActivityForResult(edit_intent, 2);

            }
        });
        // Set the checkbox to boolean status of the bucket item
        aCheckbox.setChecked(bucketitem.getDone_status());


    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mBucketItems.size();
    }
}