package com.mindorks.test;

/**
 * Created by Maddy on 21/01/17.
 */

public class EventDetails {

    private String id;
    private String name;
    private Integer teamSize;
    private String eventLocation;
    private String universityName;



    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getTeamSize() {
        return teamSize;
    }
    public void setTeamSize(Integer teamSize) {
        this.teamSize = teamSize;
    }
    public String getEventLocation() {
        return eventLocation;
    }
    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public String getUniversityName() {
        return universityName;
    }
    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }
    @Override
    public String toString() {
        return "Event [id=" + id + ", name=" + name + ", teamSize=" + teamSize + ", eventLocation=" + eventLocation
                + "]";
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
        EventDetails other = (EventDetails) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }


}
