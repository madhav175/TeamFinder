package com.mindorks.test;

import java.util.List;
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
    private TreeSet<String> teamMemberId;
    private String idea;
    private String phoneNumber;
    private String mySkill1;
    private String mySkill2;
    private String skill1;
    private String skill2;
    private String skill3;


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



    public  TreeSet<String> getTeamMemberId() {
        if(teamMemberId!=null){
            return teamMemberId;
        }else {
            return new  TreeSet<String>();
        }

    }
    public void setTeamMemberId(TreeSet<String>teamMemberId) {
        this.teamMemberId = teamMemberId;
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

    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}
    public void setSkill1(String skill1) {this.skill1 = skill1;}
    public void setSkill2(String skill2) {this.skill2 = skill2;}
    public void setSkill3(String skill3) {this.skill3 = skill3;}
    public void setMySkill1(String myskill1) {this.mySkill1 = myskill1;}
    public void setMySkill2(String myskill2) {this.mySkill2 = myskill2;}

    public String getPhoneNumber(String phoneNumber) {return phoneNumber;}
    public String getSkill1(String skill1) {return skill1;}
    public String getSkill2(String skill2) {return skill2;}
    public String getSkill3(String skill3) {return skill3;}
    public String getMySkill1(String myskill1) {return mySkill1;}
    public String getMySkill2(String myskill2) {return mySkill2;}





}
