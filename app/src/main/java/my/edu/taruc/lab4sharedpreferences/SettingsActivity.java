package my.edu.taruc.lab4sharedpreferences;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SettingsActivity extends AppCompatActivity {
    private EditText editTextName;
    private RadioGroup radioGroupGender;
    private RadioButton radioButtonMale, radioButtonFemale;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        editTextName = findViewById(R.id.editTextName);
        radioGroupGender = findViewById(R.id.radioGroupGender);
        radioButtonMale = findViewById(R.id.radioButtonMale);
        radioButtonFemale = findViewById(R.id.radioButtonFemale);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Editor is an inner class
        SharedPreferences.Editor editor = sharedPreferences.edit();//to make changes to the preferences file
        String name;
        int gender;
        name = editTextName.getText().toString();
        editor.putString("name", name);

        gender = radioGroupGender.getCheckedRadioButtonId();
        if(gender == R.id.radioButtonMale){
            editor.putInt("gender", 1);
        }else if(gender == R.id.radioButtonFemale){
            editor.putInt("gender", 0);
        }else{
            editor.putInt("gender", -1);
        }
        editor.apply();
    }

    protected void onResume()
    {
        super.onResume();
        sharedPreferences = getSharedPreferences(
                getString(R.string.pref_file), MODE_PRIVATE);
        String name;
        int gender;
        name = sharedPreferences.getString("name", "");
        gender = sharedPreferences.getInt("gender", -1);
        editTextName.setText(name);
        if(gender == 1) {
            radioButtonMale.setChecked(true);
        }else if(gender == 0){
            radioButtonFemale.setChecked(true);
        }


    }
}
