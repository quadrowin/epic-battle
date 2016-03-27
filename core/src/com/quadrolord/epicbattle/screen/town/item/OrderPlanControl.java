package com.quadrolord.epicbattle.screen.town.item;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.quadrolord.epicbattle.logic.town.building.CraftPlanItem;
import com.quadrolord.epicbattle.screen.AbstractScreen;

/**
 * Created by Quadrowin on 27.03.2016.
 */
public class OrderPlanControl extends Group {

    private ImageButton mOrderButton;

    public OrderPlanControl(AbstractScreen screen, Group parent, CraftPlanItem plan) {
        Texture imageTexture = screen.getTextures().get(plan.getThing().getImage());
        Drawable imageDrawable = new TextureRegionDrawable(new TextureRegion(imageTexture));
        mOrderButton = new ImageButton(imageDrawable);

        setBounds(0, 0, 50, 50);
        mOrderButton.setBounds(0, 0, 50, 50);
        addActor(mOrderButton);
        parent.addActor(this);
    }

}
