package com.quadrolord.epicbattle.screen.slider;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.quadrolord.ejge.view.AbstractScreen;

/**
 * Created by Quadrowin on 08.05.2016.
 */
public class SliderWrapper extends Group {

    private AbstractScreen mScreen;

    private Rectangle mScissorsRect = new Rectangle();
    private Rectangle mScissorsClipBounds = new Rectangle();

    private int mPaddingX = 40;

    public SliderWrapper(final AbstractScreen screen) {
        mScreen = screen;
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        //Draw the actor as usual
        applyTransform(batch, computeTransform());

        //Create a scissor rectangle that covers my Actor.
        mScissorsClipBounds.set(
                mPaddingX - getX(),
                0,
                getParent().getWidth() - mPaddingX / 2,
                getHeight()
        );
        ScissorStack.calculateScissors(mScreen.getStage().getCamera(), batch.getTransformMatrix(), mScissorsClipBounds, mScissorsRect);
        batch.flush(); // Make sure nothing is clipped before we want it to.
        boolean pushed = ScissorStack.pushScissors(mScissorsRect);

        drawChildren(batch, parentAlpha);

        //Perform the actual clipping
        if (pushed) {
            ScissorStack.popScissors();
        } else {
            Gdx.app.log("SliderList", "No scissors");
        }

        resetTransform(batch);

    }

}
