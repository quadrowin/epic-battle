package com.quadrolord.epicbattle.screen.town.item;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.quadrolord.epicbattle.logic.town.building.CraftPlanItem;
import com.quadrolord.epicbattle.screen.AbstractScreen;

/**
 * Задание в плане
 * Created by Quadrowin on 27.03.2016.
 */
public class OrderPlanControl extends Group {

    private ImageButton mOrderButton;

    private CraftPlanItem mPlanItem;

    private AbstractScreen mScreen;

    private ProgressSquare mProgress;
    private Label mTimeLabel;

    private Texture mWhiteTexture;

    public OrderPlanControl(AbstractScreen screen, Group parent, CraftPlanItem plan) {
        mScreen = screen;
        mPlanItem = plan;
        setBounds(0, 0, 50, 50);

        Texture imageTexture = screen.getTextures().get(plan.getThing().getImage());
        Drawable imageDrawable = new TextureRegionDrawable(new TextureRegion(imageTexture));
        mOrderButton = new ImageButton(imageDrawable);
        mOrderButton.setBounds(0, 0, getWidth(), getHeight());

        mTimeLabel = new Label(
                getTimeLeftText(plan.getThing().getCraftTime()),
                screen.getSkin().get("default-label-style", Label.LabelStyle.class)
        );
        mTimeLabel.setBounds(0, 0, getWidth(), 20);
        mTimeLabel.setAlignment(Align.center);

        Pixmap white = new Pixmap(1, 5, Pixmap.Format.RGBA8888);
        white.setColor(Color.WHITE);
        white.fill();
        Texture whiteTexture = new Texture(white);
        screen.getSkin().add("white", whiteTexture);

        mProgress = new ProgressSquare(whiteTexture);
        mProgress.setBounds(0, 0, getWidth(), getHeight());

        addActor(mOrderButton);
        addActor(mProgress);
        addActor(mTimeLabel);
        parent.addActor(this);
    }

    private String getTimeLeftText(long seconds) {
        if (seconds < 0) {
            return "done";
        }

        if (seconds < 60) {
            return seconds + " s";
        }

        long minutes = seconds / 60;
        seconds -= minutes * 60;

        if (minutes < 60) {
            return String.format("%d:%02d", minutes, seconds);
        }

        long hours = minutes / 60;
        minutes -= hours * 60;

        return String.format("%d:%02d:%02d", hours, minutes, seconds);
    }

    public void act (float delta) {
        long craftDelta = mScreen.getGame().getGameMillis() - mPlanItem.getCreated();
        long fullSeconds = mPlanItem.getThing().getCraftTime();
        long leftSeconds = fullSeconds - craftDelta / 1000;
        mProgress.setProgress((float) (craftDelta / fullSeconds) / 1000 );
        mTimeLabel.setText(getTimeLeftText(leftSeconds));
    }

    public void draw (Batch batch, float parentAlpha) {
        applyTransform(batch, computeTransform());
        drawChildren(batch, parentAlpha);
        resetTransform(batch);
    }

}
