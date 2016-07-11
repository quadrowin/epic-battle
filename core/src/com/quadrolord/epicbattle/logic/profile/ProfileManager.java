package com.quadrolord.epicbattle.logic.profile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.quadrolord.ejge.utils.PlatformServices;
import com.quadrolord.epicbattle.logic.skill.active.PowerWave;
import com.quadrolord.epicbattle.logic.skill.passive.TowerMaxHp;
import com.quadrolord.epicbattle.logic.skill.passive.TowerRandomBleed;

/**
 * Created by Quadrowin on 14.01.2016.
 */
public class ProfileManager {

    private static final String PROFILE_FILE = "profile.json";

    private PlayerProfile mProfile;

    private PlatformServices mPlatformServices;

    public ProfileManager(PlatformServices platformServices) {
        mPlatformServices = platformServices;
    }

    public PlayerProfile getProfile() {
        if (mProfile == null) {
            loadProfile();
        }
        return mProfile;
    }

    private FileHandle getProfileFileHandle() {
        return mPlatformServices.getFileService().getUserStorageFile(PROFILE_FILE);
    }

    private Json createJson() {
        Json json = new Json();
        json.addClassTag("profile", PlayerProfile.class);
        json.addClassTag("skill", ProfileSkill.class);
        json.addClassTag("building", ProfileBuilding.class);
        return json;
    }

    private void loadProfile() {
        FileHandle file = getProfileFileHandle();

        if (file.exists()) {
            Json json = createJson();
            try {
                mProfile = json.fromJson(PlayerProfile.class, file);
            } catch (Exception e) {
                Gdx.app.error(getClass().getName(), "loadProfile fromJson exception", e);
            }
        }

        if (mProfile == null) {
            mProfile = new PlayerProfile();
            mProfile.setName("An elf");
        }

        mProfile.addSkillSafe(TowerMaxHp.class, 0);
        mProfile.addSkillSafe(TowerRandomBleed.class, 1);
        mProfile.addSkillSafe(PowerWave.class, 0);

        // скилы юнитов
        mProfile.addSkillSafe(com.quadrolord.epicbattle.logic.skill.bullet.Wheel001.class, 0);
        mProfile.addSkillSafe(com.quadrolord.epicbattle.logic.skill.bullet.Simple.class, 0);
        mProfile.addSkillSafe(com.quadrolord.epicbattle.logic.skill.bullet.Big.class, 3);
        mProfile.addSkillSafe(com.quadrolord.epicbattle.logic.skill.bullet.Forks.class, 0);
        mProfile.addSkillSafe(com.quadrolord.epicbattle.logic.skill.bullet.Girl.class, 0);

//        if (mProfile.getBullets().size < 1) {
//            mProfile.addBullet(SimpleLogic.class);
//        }
        mProfile.getBuildings().clear();
//        mProfile.addBuildingSafe(BigBullet.class).setLevel(3);
//        mProfile.addBuildingSafe(ForksLogic.class);
//        mProfile.addBuildingSafe(GirlLogic.class);
    }

    public void saveProfile() {
        FileHandle file = getProfileFileHandle();
        Gdx.app.log("saveProfile", file.path());
        Json json = createJson();
        file.writeString(
                json.prettyPrint(getProfile()),
                false
        );
    }

}
