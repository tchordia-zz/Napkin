package com.tanmaychordia.napkin;

import com.parse.ParseClassName;
import com.parse.ParseUser;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Tanmay on 2/20/15.
 */

@ParseClassName("_User")
public class NUser extends ParseUser {


    public NUser()
    {

    }
    public NUser(String email, String pass)
    {
        setUsername(email);
        setPassword(pass);
        setEmail(email);
    }
    public NUser(String email, String pass, String name, String phone, int skill)

    {
        this(email,pass);
        put("name", name);
        put("phone", phone);

    }
    public String getName() {
        return (String)get("name");
    }

    public void setName(String name) {
        put("name", name);
        saveInBackground();
    }
    public String getPhone() {
        return (String)get("phone");
    }

    public void setPhone(String name) {
        put("phone", name);
        saveInBackground();
    }
    public String getSkillLevel() {
        return (String)get("skillLevel");
    }

    public void setSkillLevel(String name) {
        put("skillLevel", name);
        saveInBackground();
    }
    public void setSkills(String skills)
    {
        List<String> sList = Arrays.asList(skills.split(" "));
        addAllUnique("skills", sList);
        saveInBackground();
    }
    public List<String> getSkills()
    {
        return (List<String>)get("skills");
    }
    public void setLangs(String skills)
    {
        List<String> sList = Arrays.asList(skills.split(" "));
        addAllUnique("langs", sList);
        saveInBackground();
    }
    public List<String> getLangs()
    {
        return (List<String>)get("langs");
    }
    public List<Project> getProjects() {


// create a relation based on the authors key
        return (List<Project>)get("projects");
    }
    public void addProject(Project p)
    {
        p.addUnique("contributors",this);
        p.saveInBackground();
        addUnique("projects",p);
        saveInBackground();
    }

    public void addMyProject(Project p)
    {
        addUnique("projects",p);
        p.put("Owner", getUsername());
        p.saveInBackground();
        saveInBackground();
    }




}
