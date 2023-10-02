package com.example.estudia.entities;

public class StudyProgram {
    private String name;
    private String id;
    private String description;
    private String[] topics;
    private String requirements;
    private String schedule;
    private String study_center;
    private String personality;
    private String programType;
    private String city;
    private int image;

    public StudyProgram(String name, String id, String description, String[] topics, String requirements, String schedule, String study_center, String personality, String programType, String city, int image) {
        this.name = name;
        this.id = id;
        this.description = description;
        this.topics = topics;
        this.requirements = requirements;
        this.schedule = schedule;
        this.study_center = study_center;
        this.personality = personality;
        this.programType = programType;
        this.city = city;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getTopics() {
        return topics;
    }

    public void setTopics(String[] topics) {
        this.topics = topics;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getStudy_center() {
        return study_center;
    }

    public void setStudy_center(String study_center) {
        this.study_center = study_center;
    }

    public String getPersonality() {
        return personality;
    }

    public void setPersonality(String personality) {
        this.personality = personality;
    }

    public String getProgramType() {
        return programType;
    }

    public void setProgramType(String programType) {
        this.programType = programType;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
