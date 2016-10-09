package com.quadrolord.epicbattle.screen.battle;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ArrayMap;
import com.quadrolord.ejge.view.AbstractScreen;
import com.quadrolord.epicbattle.logic.skill.AbstractSkillEntity;
import com.quadrolord.epicbattle.logic.skill.SkillItem;
import com.quadrolord.epicbattle.logic.tower.BattleGame;
import com.quadrolord.epicbattle.screen.SES;
import com.quadrolord.epicbattle.view.Sounds;

import java.util.Iterator;

/**
 * Created by Quadrowin on 10.01.2016.
 */
public class BulletPanel extends Group {

    public BulletPanel(final AbstractScreen screen, Stage stage) {

        ClickListener clickListener = new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                BulletButton btn = (BulletButton)event.getListenerActor();
                SkillItem skill = btn.getBulletSkill();
                if (skill == null) {
                    return;
                }
                screen.getAdapter().getSoundManager().play(Sounds.MENU_CLICK);
                screen.get(BattleGame.class).getPlayerController().callFire(skill);
            }

        };

        ArrayMap<AbstractSkillEntity, SkillItem> skills = screen.get(BattleGame.class).getPlayerTower().getBulletSkills();
        int skillsCount = 0;
        for (Iterator<SkillItem> it = skills.values().iterator(); it.hasNext(); ) {
            SkillItem skill = it.next();
            if (!skill.getInfo().isSummon()) {
                // скил - не создание юнита
                continue;
            }
            skillsCount++;
            BulletButton btn = new BulletButton(screen, skill);
            btn.setPosition(skillsCount * 50 * SES.F, 0);
            btn.addListener(clickListener);
            addActor(btn);
        }

        while (skillsCount < 5) {
            skillsCount++;
            BulletButton btn = new BulletButton(screen, null);
            btn.setPosition(skillsCount * 50 * SES.F, 0);
            btn.addListener(clickListener);
            addActor(btn);
        }

        setBounds(10 * SES.F, 5 * SES.F, skillsCount * 50 * SES.F, 40 * SES.F);

        stage.addActor(this);
    }

}
