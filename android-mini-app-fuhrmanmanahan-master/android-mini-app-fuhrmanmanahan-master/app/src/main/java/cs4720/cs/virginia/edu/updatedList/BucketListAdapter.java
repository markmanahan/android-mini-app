package cs4720.cs.virginia.edu.updatedList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        public TextView nameTextView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

			// NEED TO CHANGE R.ID.CONTACT_NAME
            nameTextView = (TextView) itemView.findViewById(R.id.contact_name);

        }
    }

    // Store a member variable for the bucketitem
    private List<BucketItem> mBucketItems;
    // Store the context for easy access
    private Context mContext;

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
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
		
		// NEED TO CHANGE R.LAYOUT.ITEM_CONTACT
        View bucketitemView = inflater.inflate(R.layout.item_contact, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(bucketitemView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(BucketListAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        BucketItem bucketitem = mBucketItems.get(position);

        // Set item views based on your views and data model
        TextView textView = viewHolder.nameTextView;
        textView.setText(bucketitem.getName_of_bucket_item());
        if(!bucketitem.getDone_status()) {
            textView.setClickable(true);
            textView.setActivated(true);
            textView.setEnabled(true);

        }

    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mBucketItems.size();
    }
}