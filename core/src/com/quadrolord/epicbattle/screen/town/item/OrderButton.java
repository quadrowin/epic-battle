package com.quadrolord.epicbattle.screen.town.item;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.quadrolord.epicbattle.logic.thing.AbstractThingEntity;
import com.quadrolord.epicbattle.screen.AbstractScreen;

/**
 * Created by Quadrowin on 20.03.2016.
 */
public class OrderButton extends Group {

    private ImageButton mOrderButton;

    public OrderButton(AbstractScreen screen, Group parent, AbstractThingEntity thing) {
        Texture imageTexture = screen.getTextures().get(thing.getImage());
        Drawable imageDrawable = new TextureRegionDrawable(new TextureRegion(imageTexture));
        mOrderButton = new ImageButton(imageDrawable);

        setBounds(50, 200, 300, 50);
        mOrderButton.setBounds(0, 0, 50, 50);
        parent.addActor(mOrderButton);
        screen.getStage().addActor(this);
    }

}
