package com.mindorks.test;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/**
 * Created by Maddy on 21/01/17.
 */

public class EventRegister {

    private String id;
    private String user_id;
    private String eventId;
    private List<String> skillset;
    private Integer teamsize;
    private List<String> lookingForSkills;
    private Map<String,Boolean> teamMemberId;
    private String idea;
    private user usd;


    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("url")
    @Expose
    private String imageUrl;

    @SerializedName("skills")
    @Expose
    private String skills;

    @SerializedName("size")
    @Expose
    private String size;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAge() {
        return size;
    }

    public void setAge(String size) {
        this.size = size;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String size) {
        this.skills = size;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUser_id() {
        return user_id;
    }
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getEventId() {
        return eventId;
    }
    public void setEventId(String eventId) {
        this.eventId = eventId;
    }
    public List<String> getSkillset() {
        return skillset;
    }
    public void setSkillset(List<String> skillset) {
        this.skillset = skillset;
    }
    public Integer getTeamsize() {
        return teamsize;
    }
    public void setTeamsize(Integer teamsize) {
        this.teamsize = teamsize;
    }
    public List<String> getLookingForSkills() {
        return lookingForSkills;
    }
    public void setLookingForSkills(List<String> lookingForSkills) {
        this.lookingForSkills = lookingForSkills;
    }



    public void setTeamMemberId(Map<String, Boolean> teamMemberId) {
        this.teamMemberId = teamMemberId;
    }

    public Map<String, Boolean> getTeamMemberId() {
        if(null != teamMemberId)
            return teamMemberId;
        else
            return new HashMap<String,Boolean>();
    }
    public String getIdea() {
        return idea;
    }
    public void setIdea(String idea) {
        this.idea = idea;
    }

    @Override
    public String toString() {
        return "EventRegister [id=" + id + ", eventId=" + eventId + ", skillset=" + skillset + ", teamsize=" + teamsize
                + ", lookingForSkills=" + lookingForSkills + ", idea=" + idea + "]";
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        EventRegister other = (EventRegister) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    public user getUsd() {
        return usd;
    }
    public void setUsd(user usd) {
        this.usd = usd;
    }



}
