// as of 2.2.2018

package cs4720.cs.virginia.edu.updatedList;

import java.util.ArrayList;

public class Contact { // this will become an activity
    private String mName;
    private boolean mOnline;

    private String month_due, day_due, year_due; // month-day-year date representation of a due date

    private String due_date; // due date as a string, will sort through string order

    public Contact(String name, boolean online, String month, String day, String year) {
        mName = name;
        mOnline = online;

        month_due = month;
        day_due = day;
        year_due = year;

    }

    public String getName() {
        return mName;
    }

    public String getDue_date() {

        due_date = this.month_due + "." + this.day_due + "." + this.year_due;

        return due_date;

    }

    public boolean isOnline() {
        return mOnline;
    }

    private static int lastContactId = 0;

    public static ArrayList<Contact> createContactsList(int numContacts) {
        ArrayList<Contact> contacts = new ArrayList<Contact>();
        String[] prePopulatedNames = {"Bilbo Baggins", "Gollum", "Samwise Gamgee", "Gandalf", "Aragorn"};

        /*
        Removed addition of offline blank people

        for (int i = 1; i <= numContacts; i++) {
            contacts.add(new Contact("Person " + ++lastContactId, i <= numContacts / 2));
        }

        */

        for(int i = 0; i<prePopulatedNames.length; i++){
            contacts.add(new Contact(prePopulatedNames[i], true, "1", "1", "2011"));
        }

        return contacts;
    }
}
