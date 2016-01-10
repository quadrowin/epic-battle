package com.quadrolord.epicbattle.screen.battle;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.quadrolord.epicbattle.logic.bullet.BulletInfo;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.screen.AbstractScreen;

/**
 * Created by Quadrowin on 10.01.2016.
 */
public class CreateBulletButton extends Group {

    private Class<? extends AbstractBullet> mBulletClass;

    public CreateBulletButton(AbstractScreen screen, Class<? extends AbstractBullet> bulletClass) {
        mBulletClass = bulletClass;
        TextButton btnFire = new TextButton("", screen.getSkin().get("default-text-button-style", TextButton.TextButtonStyle.class));
        btnFire.setBounds(
                0,
                0,
                40,
                40
        );

        this.addActor(btnFire);

        BulletInfo bi = screen.getGame().getBulletInfo(bulletClass);

        Label lbl = new Label(bi.getTitle(), screen.getSkin(), "default", Color.WHITE);
        lbl.setBounds(0, 0, btnFire.getWidth(), btnFire.getHeight());
        lbl.setAlignment(Align.center, Align.center);
        btnFire.addActor(lbl);
    }

    public Class<? extends AbstractBullet> getBulletClass() {
        return mBulletClass;
    }

}
