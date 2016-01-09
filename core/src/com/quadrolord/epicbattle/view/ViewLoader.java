package com.quadrolord.epicbattle.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Created by Quadrowin on 04.01.2016.
 */
public class ViewLoader {

    public void loadTextures(Skin skin, String[] textures) {
        for (int i = 0; i < textures.length; i += 2) {
            Texture texture = new Texture(Gdx.files.internal(textures[i + 1]));
            skin.add(textures[i], texture);
        }
    }

}
