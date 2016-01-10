package com.quadrolord.epicbattle.screen.battle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.quadrolord.epicbattle.logic.Tower;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.screen.AbstractScreen;

/**
 * Created by Quadrowin on 10.01.2016.
 */
public class CreateBulletButton extends Group {

    private Class<? extends AbstractBullet> mBulletClass;

    public CreateBulletButton(final AbstractScreen screen, Stage stage, Class<? extends AbstractBullet> bulletClass) {
        mBulletClass = bulletClass;
        TextButton btnFire = new TextButton("", screen.getSkin().get("default-text-button-style", TextButton.TextButtonStyle.class));
        btnFire.setBounds(
                0,
                0,
                40,
                40
        );

        this.addActor(btnFire);

        btnFire.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("", "create bullet click");
                Tower tower = screen.getGame().getTowers().get(0);
                screen.getGame().createUnit(tower, mBulletClass);
            }

        });

        Label lblPause = new Label("Fire", screen.getSkin(), "default", Color.WHITE);
        lblPause.setBounds(0, 0, btnFire.getWidth(), btnFire.getHeight());
        lblPause.setAlignment(Align.center, Align.center);
        btnFire.addActor(lblPause);

        stage.addActor(this);
    }

}
