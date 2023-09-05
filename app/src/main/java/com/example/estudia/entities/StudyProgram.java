package com.example.estudia.entities;

public class StudyProgram {
    private String name;
    private String info;
    private String modality;
    private String code;
    private int image;

    public StudyProgram(String name, String info, String modality, String code, int image) {
        this.name = name;
        this.info = info;
        this.modality = modality;
        this.code = code;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getModality() {
        return modality;
    }

    public void setModality(String modality) {
        this.modality = modality;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
