package com.example.estudia.enums;

import static com.example.estudia.enums.CustomConstants.EstudiaConstants.PERSONALITIES;

import com.example.estudia.R;

public enum PersonalityEnum {
    REALISTA(PERSONALITIES[0], R.drawable.realista, R.drawable.realista_icon, R.string.realista_description),
    INVESTIGADOR(PERSONALITIES[1], R.drawable.investigador, R.drawable.investigador_icon, R.string.investigador_description),
    ARTISTA(PERSONALITIES[2], R.drawable.artista, R.drawable.artista_icon, R.string.artista_description),
    SOCIAL(PERSONALITIES[3], R.drawable.social, R.drawable.social_icon, R.string.social_description),
    EMPRENDEDOR(PERSONALITIES[4], R.drawable.emprendedor, R.drawable.emprendedor_icon, R.string.emprendedor_description),
    CONVENCIONAL(PERSONALITIES[5], R.drawable.convencional, R.drawable.convencional_icon, R.string.convencional_description);

    private String personality;
    private int imageId;
    private int icon;
    private int descriptionId;

    PersonalityEnum(String personality, int imageId, int icon, int descriptionId) {
        this.personality = personality;
        this.imageId = imageId;
        this.icon = icon;
        this.descriptionId = descriptionId;
    }

    public String getPersonality() {
        return personality;
    }

    public int getImageId() {
        return imageId;
    }

    public int getIcon() {
        return icon;
    }

    public int getDescriptionId() {
        return descriptionId;
    }
}
