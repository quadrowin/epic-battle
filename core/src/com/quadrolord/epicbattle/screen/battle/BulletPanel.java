package com.quadrolord.epicbattle.screen.battle;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.quadrolord.ejge.view.AbstractScreen;
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
                screen.getAdapter().getSoundManager().play(Sounds.MENU_CLICK);
                screen.get(BattleGame.class).getPlayerController().callFire(btn.getBulletSkill());
            }

        };

        int i = 0;
        for (Iterator<SkillItem> it = screen.get(BattleGame.class).getPlayerTower().getBulletSkills().values().iterator(); it.hasNext(); ) {
            SkillItem skill = it.next();
            if (!skill.getInfo().isSummon()) {
                // скил - не создание юнита
                continue;
            }
            BulletButton btn = new BulletButton(screen, skill);
            btn.setBounds(i * 50 * SES.F, 0, 40 * SES.F, 40 * SES.F);
            btn.addListener(clickListener);
            addActor(btn);
            ++i;
        }

        setBounds(10 * SES.F, 5 * SES.F, i * 50 * SES.F, 40 * SES.F);

        stage.addActor(this);
    }

}
