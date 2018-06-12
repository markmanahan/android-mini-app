import java.util.ArrayList;

public class BucketItem {
	private String mName;
	private String mDescription;
	private double mLatitude;
	private double mLongitude;
	private boolean mCompleted;
	// Need calendar date

    public BucketItem(String name, String description, double latitude, double longitude) {
		mName = name;
		mDescription = description;
		mLatitude = latitude;
		mLongitude =  longitude;
		mCompleted = false;
		// Need calendar date
    }

	public String getName() {
		return mName;
	}
	
	public String getDescription() {
		return mDescription;
	}
	
	public double getLatitude() {
		return mLatitude;
	}
	
	public double getLongitude() {
		return mLongitude;
	}
	
	public double getCompleted() {
		return mCompleted;
	}

    private static int lastBucketItemId = 0;

    public static ArrayList<BucketItem> createBucketItemsList(int numBucketItems) {
		ArrayList<BucketItem> bucketitems = new ArrayList<BucketItem>();
		
		String[] prePopulatedNames = {"Go to football game", "Steam tunneling", "See rotunda"};

		// Need Calendar Date
		for(int i = 0; i<prePopulatedNames.length; i++){
			bucketitems.add(new BucketItem(prePopulatedNames[i], "No description", 0.0, 0.0));
		}

		return bucketitems;
    }
}