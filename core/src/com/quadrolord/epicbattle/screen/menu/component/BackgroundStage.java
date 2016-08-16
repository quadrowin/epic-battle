package com.quadrolord.epicbattle.screen.menu.component;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.quadrolord.ejge.view.AbstractScreen;

/**
 * Created by Goorus on 16.08.2016.
 */
public class BackgroundStage {

    private Texture mBg;

    private Image mBgControl;

    private Stage mBgStage;

    private Viewport mBgViewport;

    public void draw() {
        mBgStage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
        mBgStage.draw();
    }

    public void loadImage(AbstractScreen screen, String texture) {
        mBg = screen.getTextures().get(texture);
        mBgControl = new Image(mBg);
        mBgControl.setBounds(0, 0, 40, 30);

        mBgViewport = new FillViewport(40, 30);
        mBgViewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
        mBgStage = new Stage(mBgViewport);
        mBgStage.addActor(mBgControl);
    }

}
