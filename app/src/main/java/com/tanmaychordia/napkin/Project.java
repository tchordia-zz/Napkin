package com.tanmaychordia.napkin;

import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Tanmay on 2/20/15.
 */
@ParseClassName("Project")
public class Project extends ParseObject{
    public Project(){

    }
    public Project(String name, int difficulty, String langs, String skills, String apptype, String d)
    {

        setName(name);
        setDifficulty(difficulty);
        addLang(langs);
        addSkills(skills);
        setAppType(apptype);
        put("Description", d);
        System.out.println("in Constructor");
        put("Owner", ParseUser.getCurrentUser().getUsername());

        ParseUser.getCurrentUser().addUnique("projects", this);
        saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    //ObjectSave
                    System.out.println("Saved");
                } else {
                    System.out.println(e.getMessage());
                }
            }
        });


    }

    public void setName(String name)
    {
        put("ProjectName", name);
        saveInBackground();
    }
    public String getName()
    {
        return (String)get("ProjectName");
    }
    public void setDifficulty(int difficulty)
    {
        if(difficulty > 5 || difficulty < 0)
        {
            throw new IndexOutOfBoundsException("Number " + difficulty + " is out of bounds");
        }
        else
        {
            put("Difficulty", difficulty);
            saveInBackground();
        }
    }
    public int getDifficulty()
    {
        return (int)get("Difficulty");
    }
    public void addLang(String s)
    {
        List<String> sList = Arrays.asList(s.split(" "));
        addAllUnique("Langs", sList);
        saveInBackground();
    }
    public List<String> getLangs()
    {
        return (List<String>)get("Langs");
    }
    public void addSkills(String s)
    {
        List<String> sList = Arrays.asList(s.split(" "));
        addAllUnique("Skills", sList);
        saveInBackground();
    }
    public List<String> getSkills()
    {
        return (List<String>)get("Skills");
    }
    public String getAppType()
    {
        return (String)get("AppType");
    }
    public void setAppType(String s)
    {
        put("AppType",s );
    }

    public void setDescription(String d)
    {
        put("Description",d);
        saveInBackground();
    }
    public String getDescription()
    {
        return (String)get("Description");
    }


}
