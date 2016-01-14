package com.quadrolord.epicbattle.logic.profile;

/**
 * Created by Quadrowin on 14.01.2016.
 */
public class PlayerProfile {

    /**
     * Кол-во запусков битв
     */
    private int battlesStarted = 0;

    /**
     * Кол-во побед
     */
    private int battlesVictory = 0;

    /**
     * Кол-во поражений
     */
    private int battlesDefeat = 0;

    private String name;

    private ProfileSkill[] skills;

    public String getName() {
        return name;
    }

    public ProfileSkill[] getSkills() {
        return skills;
    }

    public void setName(String value) {
        name = value;
    }

    public void setSkills(ProfileSkill[] value) {
        skills = value;
    }

}
