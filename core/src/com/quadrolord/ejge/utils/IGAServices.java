package com.quadrolord.ejge.utils;

/**
 * Created by Goorus on 14.09.2016.
 */
public interface IGAServices {

    void signIn();

    void showAchievements();
    void showLeaderboard();

    void addScore(final String scoreName, final Integer value);
    Integer getScore(final String scoreName);

    void unlockAchievement(final String achievementID);
    boolean isAchievementUnlocked(final String achievementID);

}
