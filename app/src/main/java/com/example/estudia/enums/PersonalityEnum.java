package com.example.estudia.enums;

import static com.example.estudia.enums.CustomConstants.EstudiaConstants.PERSONALITIES;

import com.example.estudia.R;

public enum PersonalityEnum {
    REALISTA(PERSONALITIES[0], R.drawable.realista, R.string.realista_description),
    INVESTIGADOR(PERSONALITIES[1], R.drawable.investigador, R.string.investigador_description),
    ARTISTA(PERSONALITIES[2], R.drawable.artista, R.string.artista_description),
    SOCIAL(PERSONALITIES[3], R.drawable.social, R.string.social_description),
    EMPRENDEDOR(PERSONALITIES[4], R.drawable.emprendedor, R.string.emprendedor_description),
    CONVENCIONAL(PERSONALITIES[5], R.drawable.convencional, R.string.convencional_description);

    private String personality;
    private int imageId;
    private int descriptionId;

    PersonalityEnum(String personality, int imageId, int descriptionId) {
        this.personality = personality;
        this.imageId = imageId;
        this.descriptionId = descriptionId;
    }

    public String getPersonality() {
        return personality;
    }

    public int getImageId() {
        return imageId;
    }

    public int getDescriptionId() {
        return descriptionId;
    }
}
