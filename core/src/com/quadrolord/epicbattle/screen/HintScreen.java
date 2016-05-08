package com.quadrolord.epicbattle.screen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;

/**
 * Created by Quadrowin on 19.01.2016.
 */
public class HintScreen extends AbstractScreen {

    public HintScreen(final AbstractScreen parentScreen, float x, float y, String text) {
        super(parentScreen);
        initFitViewport();

        Pixmap white = new Pixmap(2, 2, Pixmap.Format.RGBA8888);
        white.setColor(Color.WHITE);
        white.fill();
        mSkin.add("white", new Texture(white));

        Drawable drBg1 = mSkin.newDrawable("white", Color.BLACK);
        TextButton bg1 = new TextButton("", new TextButton.TextButtonStyle(drBg1, drBg1, null, getSkin().getFont("default")));
        bg1.setBounds(30, 30, 340, 240);
        mStage.addActor(bg1);

        bg1.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                mAdapter.switchToScreen(parentScreen, true);
            }

        });

        Drawable drBg2 = mSkin.newDrawable("white", Color.WHITE);
        TextButton bg2 = new TextButton("", new TextButton.TextButtonStyle(drBg2, drBg2, null, getSkin().getFont("default")));
        bg2.setBounds(3, 3, bg1.getWidth() - 6, bg1.getHeight() - 6);
        bg1.addActor(bg2);

        Drawable drBg3 = mSkin.newDrawable("white", Color.BLACK);
        TextButton bg3 = new TextButton("", new TextButton.TextButtonStyle(drBg3, drBg3, null, getSkin().getFont("default")));
        bg3.setBounds(3, 3, bg2.getWidth() - 6, bg2.getHeight() - 6);
        bg2.addActor(bg3);

        Label lbl = new Label(text, mSkin.get("default-label-style", Label.LabelStyle.class));
        lbl.setAlignment(Align.center, Align.center);
        lbl.setBounds(0, 0, bg3.getWidth(), bg3.getHeight());
        bg3.addActor(lbl);
    }

    @Override
    public void draw(float delta) {
        mStage.act(delta);
        mStage.draw();
    }

    @Override
    public void update(float delta) {

    }
}
