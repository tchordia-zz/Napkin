package com.tanmaychordia.napkin;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by Tanmay on 2/20/15.
 */
public class Tag {

    private static boolean init = false;

    public static final int LANGUAGE = 0;
    public static final int SKILLS = 1;
    public static final int APPTYPE = 2;
    private static ParseObject lang = null;
    private static ParseObject skills= null;
    private static ParseObject apptype = null;


    public static final String[] ids = {"NNyarfJgDp", "4E8HJFTopv","PbYtIvcobV" };
    private static ParseObject[] objs = {lang, skills, apptype};


    public static void init()
    {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Tag");
        for(int i = 0; i < 3 ;i++)
        {
            final int a = i;
            query.getInBackground(ids[i], new GetCallback<ParseObject>() {
                @Override
                public void done(ParseObject object, ParseException e) {
                    if (e == null) {
                        objs[a] = object;

                    } else {
                        // something went wrong
                    }
                }
            });
        }



        init = true;
    }
    public static void addTag(String type, final int tag)
    {
        if (!init)
        {
            init();
        }
        if (objs[tag] == null)
        {
            ParseQuery<ParseObject> query = ParseQuery.getQuery("Tag");

            query.getInBackground(ids[tag], new GetCallback<ParseObject>() {
                @Override
                public void done(ParseObject object, ParseException e) {
                    if (e == null) {
                        objs[tag] = object;



                    } else {
                        // something went wrong
                    }
                }
            });
        }

    }
}
