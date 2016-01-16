package com.quadrolord.epicbattle.screen.battle;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.quadrolord.epicbattle.logic.tower.Tower;
import com.quadrolord.epicbattle.screen.AbstractScreen;
import com.quadrolord.epicbattle.view.TowerView;

/**
 * Created by Quadrowin on 14.01.2016.
 */
public class BangAnimation extends Group {

    private Animation mAnim;

    private float mStateTime = 0;

    public BangAnimation(AbstractScreen screen, Tower tower) {
        mAnim = getAnimation1(screen.getSkin());
        TowerView tv = (TowerView)tower.getViewObject();

        float x1 = MathUtils.random(0, tv.getWidth() / 2);
        float y1 = MathUtils.random(0, tv.getHeight() / 2);

        setBounds(tv.getX() + x1, 50 + y1, 30, 30);
        screen.getStage().addActor(this);
    }

    @Override
    public void act(float delta) {
        mStateTime += delta;
        if (mAnim.isAnimationFinished(mStateTime)) {
            remove();
        }
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        TextureRegion frame = mAnim.getKeyFrame(mStateTime);
        Matrix4 transform = computeTransform();
        applyTransform(batch, transform);
        batch.draw(frame, 0, 0, 30, 30);
        resetTransform(batch);
    }

    private Animation getAnimation1(Skin skin) {
        if (skin.has("bang-animation-1", Animation.class)) {
            return skin.get("bang-animation-1", Animation.class);
        }
        Array<TextureRegion> frames = new Array<TextureRegion>();
        frames.add(new TextureRegion(new Texture("animation/smoke_plume/smoke_plume_0001.png")));
        frames.add(new TextureRegion(new Texture("animation/smoke_plume/smoke_plume_0002.png")));
        frames.add(new TextureRegion(new Texture("animation/smoke_plume/smoke_plume_0003.png")));
        frames.add(new TextureRegion(new Texture("animation/smoke_plume/smoke_plume_0004.png")));
        frames.add(new TextureRegion(new Texture("animation/smoke_plume/smoke_plume_0005.png")));
        frames.add(new TextureRegion(new Texture("animation/smoke_plume/smoke_plume_0006.png")));
        frames.add(new TextureRegion(new Texture("animation/smoke_plume/smoke_plume_0007.png")));
        frames.add(new TextureRegion(new Texture("animation/smoke_plume/smoke_plume_0008.png")));
        frames.add(new TextureRegion(new Texture("animation/smoke_plume/smoke_plume_0009.png")));
        frames.add(new TextureRegion(new Texture("animation/smoke_plume/smoke_plume_0010.png")));
        Animation anim = new Animation(0.1f, frames);
        skin.add("bang-animation-1", anim);
        return anim;
    }

}
