package com.theironyard.androidcontactmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemLongClickListener{

    ListView list;
    EditText textName;
    EditText textPhone;
    Button addButton;

    ArrayAdapter<String> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (ListView) findViewById(R.id.listView);
        textName = (EditText) findViewById(R.id.addName);
        textPhone = (EditText) findViewById(R.id.addPhoneNumber);
        addButton = (Button) findViewById(R.id.buttonAdd);

        contacts = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        list.setAdapter(contacts);

        addButton.setOnClickListener(this);
        list.setOnItemLongClickListener(this);
        //this arranges the phone digits according to US phone number formatting standards
        textPhone.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
    }

    @Override
    public void onClick(View v){
        String contactName = textName.getText().toString();
        String contactPhone = textPhone.getText().toString();
        contacts.add(contactName + " (" + contactPhone + ")");
        textName.setText("");
        textPhone.setText("");
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id){
        String contact = contacts.getItem(position);
        contacts.remove(contact);
        return true;
    }
}
