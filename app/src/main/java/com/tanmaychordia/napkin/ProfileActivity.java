package com.tanmaychordia.napkin;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.SeekBar;
import android.widget.TabHost;
import android.widget.ViewFlipper;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;


public class ProfileActivity extends ActionBarActivity {

    private ViewFlipper viewFlipper;
    private double lastX;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        TabHost tabs = (TabHost)findViewById(R.id.tabHost);
        tabs.setup();
        TabHost.TabSpec spec=tabs.newTabSpec("Project");

        spec.setContent(R.id.Project);
        spec.setIndicator("Project");
        tabs.addTab(spec);

        spec=tabs.newTabSpec("Me");
        spec.setContent(R.id.Me);
        spec.setIndicator("Me");
        tabs.addTab(spec);

        viewFlipper = (ViewFlipper)findViewById(R.id.Project);
        Button b = (Button)findViewById(R.id.newProjectSubmit);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("button clicked");
                String name = ((EditText)findViewById(R.id.projectNameField)).getText().toString().trim();
                String appType = ((MultiAutoCompleteTextView)findViewById(R.id.projectTypeField)).getText().toString().trim();
                String skills = ((MultiAutoCompleteTextView)findViewById(R.id.profFindSkillsId)).getText().toString().trim();
                String langs = ((MultiAutoCompleteTextView)findViewById(R.id.projectLangField)).getText().toString().trim();
                int difficulty = ((SeekBar)findViewById(R.id.difficultyProgress)).getProgress();
                String description = ((EditText)findViewById(R.id.projectDescriptionField)).getText().toString().trim();

                Project project = new Project(name,difficulty,langs, skills, appType, description );
                final ProfProjectAdapter ad = (ProfProjectAdapter)listView.getAdapter();
                ad.add(project);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ad.notifyDataSetChanged();
                        leftToRight();
                    }
                });



            }
        });

        listView = (ListView) findViewById(R.id.profProjectListId);
        ParseQuery<Project> query = new ParseQuery(Project.class);
        query.whereEqualTo("Owner", ParseUser.getCurrentUser().getUsername());



        query.findInBackground(new FindCallback<Project>() {
            @Override
            public void done(List<Project> scoreList, ParseException e) {
                if (e == null) {

                    ProfProjectAdapter adapter = new ProfProjectAdapter(ProfileActivity.this,scoreList);
                    listView.setAdapter(adapter);
                    Log.d("score", "Retrieved " + scoreList.size() + " scores");

                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });



    }
    @Override
    public boolean onTouchEvent(MotionEvent touchevent)
    {

        if(viewFlipper.getVisibility() == View.VISIBLE) {
            flip(touchevent);
        }


        return false;
    }

    private void flip(MotionEvent touchevent)
    {
        switch (touchevent.getAction())
        {
            // when user first touches the screen to swap
            case MotionEvent.ACTION_DOWN:
            {
                lastX = touchevent.getX();
                break;
            }
            case MotionEvent.ACTION_UP:
            {
                float currentX = touchevent.getX();

                // if left to right swipe on screen
                if (lastX < currentX)
                {
                    // If no more View/Child to flip
                    if (viewFlipper.getDisplayedChild() == 0)
                        break;
                    leftToRight();


                }

                // if right to left swipe on screen
                if (lastX > currentX)
                {
                    if (viewFlipper.getDisplayedChild() == 1)
                        break;
                    // set the required Animation type to ViewFlipper
                    // The Next screen will come in form Right and current Screen will go OUT from Left
                    rightToLeft();
                }
                break;
            }
        }

    }

    private void leftToRight() {
        // set the required Animation type to ViewFlipper
        // The Next screen will come in form Left and current Screen will go OUT from Right
        viewFlipper.setInAnimation(this, R.anim.in_from_left);
        viewFlipper.setOutAnimation(this, R.anim.out_to_right);
        // Show the next Screen
        viewFlipper.showNext();
    }

    private void rightToLeft() {
        viewFlipper.setInAnimation(this, R.anim.in_from_right);
        viewFlipper.setOutAnimation(this, R.anim.out_to_left);
        // Show The Previous Screen
        viewFlipper.showPrevious();
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
