package com.quadrolord.epicbattle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

/**
 * Resource Manager
 * Created by Goorus on 13.08.2016.
 */
public class RM {

    private static Skin mSkin;

    private static final String DEFAULT_LABEL_STYLE = "default-label-style";

    private static final String DEFAULT_CHECK_BOX_STYLE = "default-check-box-style";

    private static final String DEFAULT_TEXT_BUTTON_STYLE = "default-text-button-style";

    public static Label.LabelStyle getLabelStyle() {
        return mSkin.get(DEFAULT_LABEL_STYLE, Label.LabelStyle.class);
    }

    public static CheckBox.CheckBoxStyle getCheckBoxStyle() {
        return mSkin.get(DEFAULT_CHECK_BOX_STYLE, CheckBox.CheckBoxStyle.class);
    }

    public static TextButton.TextButtonStyle getTextButtonStyle() {
        return mSkin.get(DEFAULT_TEXT_BUTTON_STYLE, TextButton.TextButtonStyle.class);
    }

    public static void setSkin(Skin skin) {
        mSkin = skin;

        BitmapFont font = new BitmapFont();
        font.getData().setScale(2);
        skin.add("default", font, BitmapFont.class);

        Label.LabelStyle ls = new Label.LabelStyle(font, Color.WHITE);
        skin.add(DEFAULT_LABEL_STYLE, ls);

        Texture texture = new Texture(Gdx.files.internal("badlogic.jpg"));
        skin.add("test-texture", texture);

        Texture textureBtnUp = new Texture("ui3/btn-normal.png");
        Texture textureBtnDown = new Texture("ui3/btn-pressed.png");

        NinePatch patchUp = new NinePatch(
                textureBtnUp,
                32, 32, 32, 32
        );

        NinePatch patchDown = new NinePatch(
                textureBtnDown,
                32, 32, 32, 32
        );

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle(
                new NinePatchDrawable(patchUp),
                new NinePatchDrawable(patchDown),
                null,
                font
        );
        skin.add(DEFAULT_TEXT_BUTTON_STYLE, textButtonStyle);

        CheckBox.CheckBoxStyle checkBoxStyle = new CheckBox.CheckBoxStyle(
                new NinePatchDrawable(patchUp),
                new NinePatchDrawable(patchDown),
                font,
                Color.GOLD
        );
        skin.add(DEFAULT_CHECK_BOX_STYLE, checkBoxStyle);

        Pixmap transparent = new Pixmap(2, 2, Pixmap.Format.RGBA8888);
        transparent.setColor(1, 1, 1, 0);
        transparent.fill();
        skin.add("transparent", new Texture(transparent));
    }

}
