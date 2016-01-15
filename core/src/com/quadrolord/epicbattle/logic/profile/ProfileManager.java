package com.quadrolord.epicbattle.logic.profile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.quadrolord.epicbattle.logic.skill.AbstractSkill;
import com.quadrolord.epicbattle.logic.skill.TowerMaxHp;
import com.quadrolord.epicbattle.logic.skill.TowerRandomBleed;

/**
 * Created by Quadrowin on 14.01.2016.
 */
public class ProfileManager {

    private static final String PROFILE_FILE = "profile.json";

    private PlayerProfile mProfile;

    public PlayerProfile getProfile() {
        if (mProfile == null) {
            loadProfile();
        }
        return mProfile;
    }

    private Json createJson() {
        Json json = new Json();
        json.addClassTag("profile", PlayerProfile.class);
        json.addClassTag("skill", ProfileSkill.class);
        return json;
    }

    private void loadProfile() {
        FileHandle file = Gdx.files.local(PROFILE_FILE);

        if (file.exists()) {
            Json json = createJson();
            mProfile = json.fromJson(PlayerProfile.class, file);
        }

        if (mProfile == null) {
            mProfile = new PlayerProfile();
            mProfile.setName("An elf");

            mProfile.setSkills(new ProfileSkill[] {
                    createProfileSkill(TowerMaxHp.class, 0),
                    createProfileSkill(TowerRandomBleed.class, 1),
            });
        }
    }

    private ProfileSkill createProfileSkill(Class<? extends AbstractSkill> skillClass, int level) {
        ProfileSkill sk = new ProfileSkill();
        sk.setSkillClass(skillClass);
        sk.setLevel(level);
        return sk;
    }

    public void saveProfile() {
        FileHandle file = Gdx.files.local(PROFILE_FILE);
        Json json = createJson();
        file.writeString(
                json.prettyPrint(getProfile()),
                false
        );
    }

}
