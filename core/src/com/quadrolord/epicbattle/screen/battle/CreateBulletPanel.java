package com.quadrolord.epicbattle.screen.battle;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.quadrolord.epicbattle.logic.Tower;
import com.quadrolord.epicbattle.logic.bullet.worker.Big;
import com.quadrolord.epicbattle.logic.bullet.worker.Simple;
import com.quadrolord.epicbattle.screen.AbstractScreen;

/**
 * Created by Quadrowin on 10.01.2016.
 */
public class CreateBulletPanel extends Group {

    public CreateBulletPanel(final AbstractScreen screen, Stage stage) {

        ClickListener clickListener = new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                CreateBulletButton btn = (CreateBulletButton)event.getListenerActor();
                Tower tower = screen.getGame().getTowers().get(0);
                screen.getGame().createUnit(tower, btn.getBulletClass());
            }

        };

        CreateBulletButton btn1 = new CreateBulletButton(screen, Simple.class);
        btn1.setBounds(0, 0, 40, 40);
        btn1.addListener(clickListener);
        addActor(btn1);

        CreateBulletButton btn2 = new CreateBulletButton(screen, Big.class);
        btn2.setBounds(50, 0, 40, 40);
        btn2.addListener(clickListener);
        addActor(btn2);

        setBounds(100, 250, 250, 40);

        stage.addActor(this);
    }

}
