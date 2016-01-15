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

    /**
     * Текущий опыт
     */
    private long experience = 0;

    /**
     * Всего получено опыта
     */
    private long experienceTotal = 0;

    private String name;

    private ProfileSkill[] skills;

    public long decExperience(long value) {
        return experience -= value;
    }

    public long getExperience() {
        return experience;
    }

    public long getExperienceTotal() {
        return experienceTotal;
    }

    public long incExperience(long value) {
        experienceTotal += value;
        return experience += value;
    }

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
