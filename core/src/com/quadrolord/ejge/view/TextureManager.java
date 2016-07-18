package com.quadrolord.ejge.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Created by Quadrowin on 04.01.2016.
 */
public class TextureManager {

    private Skin mSkin;

    public TextureManager(Skin skin) {
        mSkin = skin;
    }

    public Texture get(String file) {
        if (mSkin.has(file, Texture.class)) {
            return mSkin.get(file, Texture.class);
        }
        Texture texture = new Texture(Gdx.files.internal(file));
        mSkin.add(file, texture, Texture.class);
        return texture;
    }

    public void loadTextures(Skin skin, String[] textures) {
        for (int i = 0; i < textures.length; i += 2) {
            Texture texture = get(textures[i + 1]);
            skin.add(textures[i], texture);
        }
    }

}
