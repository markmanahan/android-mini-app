// as of 2.2.2018

package cs4720.cs.virginia.edu.updatedList;

import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


// Development notes
/*

a contact is an activity to be done on the to-do list for the final
contact = activity

a contact "object" should be the task itself with a due-date property - change Contact class

 */

public class MainActivity extends AppCompatActivity {

    ArrayList<Contact> contacts;
    EditText nameField;
    RecyclerView rvContacts;
    FloatingActionButton FAB;

    String text_in_editText_field, task, month, day, year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Lookup the recyclerview in activity layout
        rvContacts = (RecyclerView) findViewById(R.id.rvContacts);
        nameField = (EditText)findViewById(R.id.personName);

        // Initialize contacts
        contacts = Contact.createContactsList(1);

        // Create adapter passing in the sample user data
        ContactsAdapter adapter = new ContactsAdapter(this, contacts);
        // Attach the adapter to the recyclerview to populate items
        rvContacts.setAdapter(adapter);
        // Set layout manager to position the items
        rvContacts.setLayoutManager(new LinearLayoutManager(this));
        // That's all!


        // Clears the list of contacts
        // FAB = (FloatingActionButton) findViewById(R.id.myFAB);
        // FAB.setOnClickListener(new View.OnClickListener() {
        //     public void onClick(View v) {
        //         contacts.clear();
        //         nameField.setText("");
        //     }
        // });

        FAB = (FloatingActionButton) findViewById(R.id.myFAB);
        FAB.setOnClickListener( new View.OnClickListener() {

            public void onClick(View v) { // simulate addContact (add an activity to be done to the to-do list)

                text_in_editText_field = nameField.getText().toString();

                // Split the task to be done from its due date, which is assumed to be one space apart
                task = text_in_editText_field.substring(0, text_in_editText_field.indexOf(" "));
                month = text_in_editText_field.substring(text_in_editText_field.indexOf(" ") + 1, text_in_editText_field.indexOf(" ") + 2);
                day = text_in_editText_field.substring(text_in_editText_field.indexOf(" ") + 3, text_in_editText_field.indexOf(" ") + 4);
                year = text_in_editText_field.substring(text_in_editText_field.indexOf(" ") + 5);

                // contacts.add(new Contact(nameField.getText().toString(), true));

                contacts.add(new Contact(task, true, month, day, year)); // add an activity to the to-do list

                rvContacts.getAdapter().notifyDataSetChanged();
                nameField.setText("");

            }

        });

    }

    // Called when you tap the Add Contact button
    public void addContact(View view) {
        // Make sure it is a name
        if(nameField.getText().toString() != null && !nameField.getText().toString().equals("")) {
            // Log the action
            Log.d("ListExample", "addContact " + nameField.getText().toString());
            // Make a new contact

            // contacts.add(new Contact(nameField.getText().toString(), true));
            contacts.add(new Contact(task, true, month, day, year)); // broken but just keep for safekeeping

            // Get the adapter that manages the data set and let it know something new was added
            rvContacts.getAdapter().notifyDataSetChanged();
            // Blank the name field
            nameField.setText("");
        }
    }

    // Called tapping on an online contact
    public void sendMessage(View view) {
        TextView currentItem = (TextView)view;
        Log.d("ListExample", "sendMessage to " + currentItem.getText().toString());
        // Make Toast
        Toast.makeText(this, "Sending message to " + currentItem.getText().toString(), Toast.LENGTH_LONG).show();
    }
}
