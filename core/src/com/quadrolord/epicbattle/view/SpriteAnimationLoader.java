package com.quadrolord.epicbattle.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Quadrowin on 10.01.2016.
 */
public class SpriteAnimationLoader {

    public SpriteAnimationDrawable createDrawable(
            Skin skin,
            String resource,
            int width,
            int height,
            int framesCount,
            float frameDuration,
            int paddingX,
            int paddingY,
            boolean isLooped
    ) {
        Animation anim = getAnimation(skin, resource, width, height, framesCount, frameDuration, paddingX, paddingY);
        return new SpriteAnimationDrawable(anim, width, height, isLooped);
    }

    public Animation getAnimation(
            Skin skin,
            String resource,
            int width,
            int height,
            int framesCount,
            float frameDuration,
            int paddingX,
            int paddingY
    ) {
        if (skin.has(resource, Animation.class)) {
            return skin.get(resource, Animation.class);
        }

        Array<TextureRegion> frames = new Array<TextureRegion>();

        Texture texture = new Texture(resource);
        TextureRegion region = new TextureRegion(texture);

        int frameWidth = width + 2 * paddingX - 1;

        for (int i = 0; i < framesCount; i++) {
            frames.add(new TextureRegion(region, i * frameWidth, paddingY, frameWidth, height));
        }

        Animation anim = new Animation(frameDuration, frames);
        skin.add(resource, anim);

        return anim;
    }

}
