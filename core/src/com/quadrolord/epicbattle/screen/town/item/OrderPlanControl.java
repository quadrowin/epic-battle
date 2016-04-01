package com.quadrolord.epicbattle.screen.town.item;

import com.badlogic.gdx.graphics.Texture;
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
 * Created by Quadrowin on 27.03.2016.
 */
public class OrderPlanControl extends Group {

    private ImageButton mOrderButton;

    private CraftPlanItem mPlanItem;

    private AbstractScreen mScreen;

    private Label mTimeLabel;

    public OrderPlanControl(AbstractScreen screen, Group parent, CraftPlanItem plan) {
        mScreen = screen;
        mPlanItem = plan;

        Texture imageTexture = screen.getTextures().get(plan.getThing().getImage());
        Drawable imageDrawable = new TextureRegionDrawable(new TextureRegion(imageTexture));
        mOrderButton = new ImageButton(imageDrawable);
        mOrderButton.setBounds(0, 0, 50, 50);

        mTimeLabel = new Label(
                getTimeLeftText(),
                screen.getSkin().get("default-label-style", Label.LabelStyle.class)
        );
        mTimeLabel.setBounds(0, 0, 50, 20);
        mTimeLabel.setAlignment(Align.center);

        setBounds(0, 0, 50, 50);
        addActor(mOrderButton);
        addActor(mTimeLabel);
        parent.addActor(this);
    }

    private String getTimeLeftText() {
        long delta = mScreen.getGame().getGameMillis() - mPlanItem.getCreated();
        long seconds = mPlanItem.getThing().getCraftTime() - delta / 1000;

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
        mTimeLabel.setText(getTimeLeftText());
    }

}
