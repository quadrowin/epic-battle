package com.quadrolord.epicbattle.screen.battle;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.quadrolord.ejge.view.AbstractScreen;
import com.quadrolord.epicbattle.logic.skill.SkillItem;
import com.quadrolord.epicbattle.logic.tower.BattleGame;
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
            BulletButton btn = new BulletButton(screen, it.next());
            btn.setBounds(i * 50, 0, 40, 40);
            btn.addListener(clickListener);
            addActor(btn);
            ++i;
        }

        setBounds(10, 5, i * 50, 40);

        stage.addActor(this);
    }

}
