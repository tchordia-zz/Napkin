package com.tanmaychordia.napkin;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Tanmay on 2/20/15.
 */
@ParseClassName("Project")
public class Project extends ParseObject{
    public Project(){

        setName("");
        setDifficulty(0);
        put("Langs", Arrays.asList(new String[]{}));
        put("Skills", Arrays.asList(new String[]{}));
        setAppType("");
        saveInBackground();

    }
    public Project(String name, int difficulty, String langs, String skills, String apptype, String d)
    {
        setName(name);
        setDifficulty(difficulty);
        addLang(langs);
        addSkills(skills);
        setAppType(apptype);
        put("Description", d);
        saveInBackground();


    }

    public void setName(String name)
    {
        put("Project Name", name);
        saveInBackground();
    }
    public String getName()
    {
        return (String)get("Project Name");
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
        addUnique("Skills", sList);
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

}
