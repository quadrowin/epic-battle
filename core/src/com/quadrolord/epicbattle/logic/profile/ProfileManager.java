package com.quadrolord.epicbattle.logic.profile;

import com.quadrolord.epicbattle.logic.skill.AbstractSkill;
import com.quadrolord.epicbattle.logic.skill.TowerMaxHp;
import com.quadrolord.epicbattle.logic.skill.TowerRandomBleed;

/**
 * Created by Quadrowin on 14.01.2016.
 */
public class ProfileManager {

    private PlayerProfile mProfile;

    public PlayerProfile getProfile() {
        if (mProfile == null) {
            loadProfile();
        }
        return mProfile;
    }

    private void loadProfile() {
        mProfile = new PlayerProfile();
        mProfile.setName("An elf");

        mProfile.setSkills(new ProfileSkill[] {
                createProfileSkill(TowerMaxHp.class, 0),
                createProfileSkill(TowerRandomBleed.class, 1),
        });
    }

    private ProfileSkill createProfileSkill(Class<? extends AbstractSkill> skillClass, int level) {
        ProfileSkill sk = new ProfileSkill();
        sk.setSkillClass(skillClass);
        sk.setLevel(level);
        return sk;
    }

}
