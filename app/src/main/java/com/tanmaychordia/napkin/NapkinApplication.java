package com.tanmaychordia.napkin;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * Created by Tanmay on 2/18/15.
 */
public class NapkinApplication extends Application {
@Override
public void onCreate() {
    Parse.enableLocalDatastore(this);
    ParseObject.registerSubclass(Project.class);
    ParseObject.registerSubclass(ParseUser.class);
    ParseUser.registerSubclass(NUser.class);
    Parse.initialize(this, "bpYNOCYCCaXlqWTv1gebMzd1HHP2gkiB2C3qG4yX", "jClGi10JOEsHzt5ImAi7sapXRUgNtGOi0UclCgyf");

}
}
