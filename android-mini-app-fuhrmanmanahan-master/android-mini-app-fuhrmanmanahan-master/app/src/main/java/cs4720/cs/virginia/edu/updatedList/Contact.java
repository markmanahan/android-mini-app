package cs4720.cs.virginia.edu.updatedList;

import java.util.ArrayList;

public class Contact {
    private String mName;
    private boolean mOnline;

    public Contact(String name, boolean online) {
        mName = name;
        mOnline = online;
    }

    public String getName() {
        return mName;
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
            contacts.add(new Contact(prePopulatedNames[i], true));
        }

        return contacts;
    }
}
