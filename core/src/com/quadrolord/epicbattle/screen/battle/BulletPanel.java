package com.quadrolord.epicbattle.screen.battle;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.quadrolord.epicbattle.logic.bullet.BulletSkill;
import com.quadrolord.epicbattle.screen.AbstractScreen;
import com.quadrolord.epicbattle.view.sounds.SoundManager;

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
                screen.getGame().getSoundManager().play(SoundManager.MENU_CLICK);
                screen.getGame().getPlayerController().callFire(btn.getBulletClass());
            }

        };

        int i = 0;
        for (Iterator<BulletSkill> it = screen.getGame().getPlayerTower().getBulletSkills().values().iterator(); it.hasNext(); ) {
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
