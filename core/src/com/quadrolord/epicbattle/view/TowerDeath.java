package com.quadrolord.epicbattle.view;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.quadrolord.ejge.view.AbstractScreen;

/**
 * Created by Quadrowin on 09.01.2016.
 */
public class TowerDeath extends Group {

    private TowerView mSourceView;

    private float mTime = 0;

    private float mOriginalX = 0;

    /**
     * in seconds
     */
    private float mAnimationTime = 3;

    public TowerDeath(TowerView tv, AbstractScreen screen) {
        mSourceView = tv;
        mOriginalX = tv.getX();
        setBounds(tv.getX(), tv.getY(), tv.getWidth(), tv.getHeight());
        addActor(tv);
        tv.setPosition(0, 0);

        screen.getStage().addActor(this);

        mSourceView.getHpLabel().remove();
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        mTime += delta / mAnimationTime;

        if (mTime > 1) {
            remove();
            return;
        }

        setScaleY(1 - mTime);
    }

}
