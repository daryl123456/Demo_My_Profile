package sg.edu.rp.c346.id19042545.demomyprofile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.preference.PreferenceManager;

public class MainActivity extends AppCompatActivity {
    EditText etName,etGpa;
    RadioGroup rgGender;
    Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName = findViewById(R.id.editTextName);
        etGpa = findViewById(R.id.editTextGPA);
        rgGender = findViewById(R.id.radioGroupGender);
        btnSave = findViewById(R.id.buttonSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
    }

    protected void save() {
        //Step 1a:Get the user input from the EditeText and store it in a variable
        String strName = etName.getText().toString();
        float gpa = Float.parseFloat(etGpa.getText().toString());
        int intGenderID = rgGender.getCheckedRadioButtonId();

        //step 1b: Obtain an instance of the SharedPreferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        //step 1c:Obtain an instances of the SharedPreference Editor for update later
        SharedPreferences.Editor prefEdit = prefs.edit();

        //Step 1d:Add the key-value pair
        prefEdit.putString("name",strName);
        prefEdit.putFloat("gpa",gpa);
        prefEdit.putInt("genderId",intGenderID);

        //Step 1e: Call commit() to save the changes into SharedPreferences
        prefEdit.commit();
    }
    @Override
    protected void onResume() {
        super.onResume();

        //Step 2a:Obtain instance of the SharedPreferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        //Step 2b: Retrieve the saved data from the key "greeting" from the SharedPreferences object
        String names = prefs.getString("name","");
        float getGpa = prefs.getFloat("gpa",1.2f);
        int gender = prefs.getInt("genderId",R.id.radioButtonGenderMale);

        //Step 2c:Update the UI element with the value
        etName.setText(names);
        etGpa.setText(String.valueOf(getGpa));
        rgGender.check(gender);
    }
}
