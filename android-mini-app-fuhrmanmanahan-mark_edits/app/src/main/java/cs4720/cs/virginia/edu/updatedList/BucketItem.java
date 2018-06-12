// as of 2.6.2018

package cs4720.cs.virginia.edu.updatedList;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BucketItem implements Comparable<BucketItem>, Parcelable {

    private String name_of_bucket_item;
    private String bucket_item_description;
    private String due_date;
    private String latitude, longitude;
    private boolean done_status;

    // BucketItem constructor
    public BucketItem(String name, String description, String the_due_date, String latitude_coordinates, String longitude_coordinates, boolean isDue) {

        name_of_bucket_item = name;
        bucket_item_description = description;

        due_date = the_due_date;

        latitude = latitude_coordinates;
        longitude = longitude_coordinates;

        done_status = isDue;

    }

    // For screen rotation
    private BucketItem(Parcel in) {
        name_of_bucket_item = in.readString();
        bucket_item_description = in.readString();

        due_date = in.readString();

        latitude = in.readString();
        longitude = in.readString();

        done_status = (in.readInt() == 0) ? false : true;
    }

    // BucketItem getter methods
    public String getName_of_bucket_item() { return name_of_bucket_item; }
    public String getBucket_item_description() { return bucket_item_description; }
    public String getLatitude() { return latitude; }
    public String getLongitude() { return longitude; }
    public String getDue_date() { return due_date; }

    // BucketItem setter methods
    public void setName_of_bucket_item(String s) { name_of_bucket_item = s;}
    public void setBucket_item_description(String s) { bucket_item_description = s; }
    public void setLatitude(String s) { latitude = s; }
    public void setLongitude(String s) { longitude = s; }
    public void setDue_date(String s) { due_date = s; }

    public boolean getDone_status() { return done_status; }

    // done status setter
    public void toggleDone_status(){
        if(done_status){
            done_status = false;
        }
        else {
            done_status = true;
        }
    }

    // BucketItem compareTo method, sorts by completion status and date
    @Override
    public int compareTo(BucketItem otherItem) {
        boolean completedOne = this.done_status;
        boolean completedTwo = otherItem.getDone_status();

        String dateOne = this.due_date;
        String dateTwo = otherItem.getDue_date();

        Log.v("sort", "("+dateOne + ", " + completedOne + ") (" + dateTwo + ", " + completedTwo + ")");

        if(!completedOne && completedTwo){
            //Log.v("sort", "-1");
            return -1;
        }
        if(completedOne && !completedTwo){
            //Log.v("sort", "1");
            return 1;
        }
        if((completedOne && completedTwo) || (!completedOne && !completedTwo)) {
            //Log.v("sort", " " + dateOne.compareTo(dateTwo));
            return dateOne.compareTo(dateTwo);
        }
        return 0;
    }

    // For screen rotation
    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(name_of_bucket_item);
        out.writeString(bucket_item_description);
        out.writeString(due_date);
        out.writeString(latitude);
        out.writeString(longitude);
        out.writeInt(done_status ? 1 : 0);
    }

    public static final Parcelable.Creator<BucketItem> CREATOR = new Parcelable.Creator<BucketItem>() {
        public BucketItem createFromParcel(Parcel in) {
            return new BucketItem(in);
        }

        public BucketItem[] newArray(int size) {
            return new BucketItem[size];
        }
    };

    // method that is called to prepopulate the bucket list
    public static ArrayList<BucketItem> createInitialBucketList() {

        ArrayList<BucketItem> bucket_list = new ArrayList<BucketItem>();

        String[] some_bucket_list_items = { "Tunnels", "Rotunda", "Ohill" };
        String[] some_bucket_item_description = {"Go steam tunneling", "Visit lighting of the lawn", "Visit the telescope"};
        String[] some_due_dates = {"2017-3-23", "2015-5-1", "2016-12-31"};
        String[] some_latitude_coordinates = { "10.1", "35.1", "43.1" };
        String[] some_longitude_coordinates = { "77.2", "56.3", "12.1" };

        // Add the bucket items to the bucketList array
        for (int i = 0; i < 3; ++i) {

            bucket_list.add(new BucketItem(some_bucket_list_items[i], some_bucket_item_description[i], some_due_dates[i], some_latitude_coordinates[i],
                    some_longitude_coordinates[i], false));

        }

        // sort the array
        Collections.sort(bucket_list);

        return bucket_list;

    }

}
