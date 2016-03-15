package ro.pub.cs.systems.pdsd.lab04.contactsmanager;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class ContactsManagerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_manager);
    
        Intent intent = getIntent();
        if (intent != null) {
          String phone = intent.getStringExtra("ro.pub.cs.systems.pdsd.lab04.contactsmanager.PHONE_NUMBER_KEY");
          if (phone != null) {
        	  EditText phoneEdit = (EditText)findViewById(R.id.Phone);
        	  phoneEdit.setText(phone);
          } else {
        	  Toast.makeText(this,getResources().getString(R.string.phone_error), Toast.LENGTH_LONG).show();
          }
        } 
        
        final Button hideAdditional = (Button)findViewById(R.id.button1);
        if(hideAdditional != null){
        	hideAdditional.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					View hidden = findViewById(R.id.additional);
					if(hidden.getVisibility() == View.VISIBLE){
						hidden.setVisibility(View.INVISIBLE);
						hideAdditional.setText("Show Additional Fields");
						
					}else{
						hidden.setVisibility(View.VISIBLE);
						hideAdditional.setText("Hide Additional Fields");
					}
				}
			});
        	
        }
        
        final Button saveButton = (Button)findViewById(R.id.button2);
        
        if(saveButton!=null){
        	saveButton.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
					intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
					
					EditText nameEdit = (EditText)findViewById(R.id.Name);
					if (nameEdit != null){
						intent.putExtra(ContactsContract.Intents.Insert.NAME, nameEdit.getText().toString());
					}
					EditText phoneEdit = (EditText)findViewById(R.id.Phone);
					if (phoneEdit != null){
						intent.putExtra(ContactsContract.Intents.Insert.PHONE, phoneEdit.getText().toString());
					}
					EditText emailEdit = (EditText)findViewById(R.id.Email);
					if (emailEdit != null){
						intent.putExtra(ContactsContract.Intents.Insert.EMAIL, emailEdit.getText().toString());
					}
					EditText address = (EditText)findViewById(R.id.Address);
					if (address != null){
						intent.putExtra(ContactsContract.Intents.Insert.POSTAL, address.getText().toString());
					}
					EditText jobTitle = (EditText)findViewById(R.id.jobTitle);
					if (jobTitle != null){
						intent.putExtra(ContactsContract.Intents.Insert.JOB_TITLE, jobTitle.getText().toString());
					}
					EditText company = (EditText)findViewById(R.id.company);
					if (company != null){
						intent.putExtra(ContactsContract.Intents.Insert.COMPANY, company.getText().toString());
					}
					ArrayList<ContentValues> contactData = new ArrayList<ContentValues>();
					
					EditText website = (EditText)findViewById(R.id.website);
					if (website != null) {
					  ContentValues websiteRow = new ContentValues();
					  websiteRow.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Website.CONTENT_ITEM_TYPE);
					  websiteRow.put(ContactsContract.CommonDataKinds.Website.URL, website.getText().toString());
					  contactData.add(websiteRow);
					}
					EditText im = (EditText)findViewById(R.id.im);
					if (im != null) {
					  ContentValues imRow = new ContentValues();
					  imRow.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Im.CONTENT_ITEM_TYPE);
					  imRow.put(ContactsContract.CommonDataKinds.Im.DATA, im.getText().toString());
					  contactData.add(imRow);
					}
					intent.putParcelableArrayListExtra(ContactsContract.Intents.Insert.DATA, contactData);
					startActivityForResult(intent, 13);
					
				}
			});
        }
        
        final Button cancelButton = (Button)findViewById(R.id.button3);
        if(cancelButton != null){
        	cancelButton.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					setResult(Activity.RESULT_CANCELED,new Intent());
				}
			});
        }
       
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.contacts_manager, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
    	switch(requestCode) {
    	  case 13:
    	    setResult(resultCode, new Intent());
    	    finish();
    	    break;
    	  }
    	}
}
