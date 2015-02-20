package com.tanmaychordia.napkin;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.SeekBar;
import android.widget.TabHost;


public class ProfileActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        TabHost tabs = (TabHost)findViewById(R.id.tabHost);
        tabs.setup();
        TabHost.TabSpec spec=tabs.newTabSpec("Project");

        spec.setContent(R.id.Project);
        spec.setIndicator("Clock");
        tabs.addTab(spec);

        spec=tabs.newTabSpec("Me");
        spec.setContent(R.id.Me);
        spec.setIndicator("Button");
        tabs.addTab(spec);

        Button b = (Button)findViewById(R.id.newProjectSubmit);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ((EditText)findViewById(R.id.projectNameField)).getText().toString().trim();
                String appType = ((MultiAutoCompleteTextView)findViewById(R.id.projectTypeField)).getText().toString().trim();
                String skills = ((MultiAutoCompleteTextView)findViewById(R.id.profFindSkillsId)).getText().toString().trim();
                String langs = ((MultiAutoCompleteTextView)findViewById(R.id.projectLangField)).getText().toString().trim();
                int difficulty = ((SeekBar)findViewById(R.id.difficultyProgress)).getProgress();
                String description = ((EditText)findViewById(R.id.projectDescriptionField)).getText().toString().trim();

                Project project = new Project(name,difficulty,langs, skills, appType,description );



            }
        });

        ListView listview = (ListView) findViewById(R.id.profProjectListId);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
