package com.quadrolord.epicbattle.screen.town;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.quadrolord.epicbattle.screen.AbstractScreen;

/**
 * Created by Quadrowin on 07.03.2016.
 */
public class SubScreenWindow extends Group {

    private Group mInnerGroup;

    public SubScreenWindow(AbstractScreen screen) {
        Skin skin = screen.getSkin();
        BitmapFont font = skin.getFont("default");

        if (!skin.has("white", Texture.class)) {
            Pixmap white = new Pixmap(2, 2, Pixmap.Format.RGBA8888);
            white.setColor(Color.WHITE);
            white.fill();
            skin.add("white", new Texture(white));
        }

        Drawable drBg1 = skin.newDrawable("white", Color.BLACK);
        TextButton bg1 = new TextButton("", new TextButton.TextButtonStyle(drBg1, drBg1, null, font));
        bg1.setBounds(30, 30, 340, 240);
        addActor(bg1);

        Drawable drBg2 = skin.newDrawable("white", Color.WHITE);
        TextButton bg2 = new TextButton("", new TextButton.TextButtonStyle(drBg2, drBg2, null, font));
        bg2.setBounds(3, 3, bg1.getWidth() - 6, bg1.getHeight() - 6);
        bg1.addActor(bg2);

        Drawable drBg3 = skin.newDrawable("white", Color.BLACK);
        TextButton bg3 = new TextButton("", new TextButton.TextButtonStyle(drBg3, drBg3, null, font));
        bg3.setBounds(3, 3, bg2.getWidth() - 6, bg2.getHeight() - 6);
        bg2.addActor(bg3);

        mInnerGroup = bg3;
        setBounds(0, 0, 400, 300);
        screen.getStage().addActor(this);
    }

    public Group getInnerGroup() {
        return mInnerGroup;
    }

}
