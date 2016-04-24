package com.quadrolord.epicbattle.screen.town.item;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.quadrolord.epicbattle.logic.thing.AbstractThingEntity;
import com.quadrolord.epicbattle.logic.town.building.AbstractBuildingItem;
import com.quadrolord.epicbattle.screen.AbstractScreen;

/**
 * Кнопка для добавления задания в план
 * Created by Quadrowin on 20.03.2016.
 */
public class OrderButton extends Group {

    private ImageButton mOrderButton;

    public OrderButton(final AbstractScreen screen, Group parent, final AbstractBuildingItem building, final AbstractThingEntity thing) {
        Texture imageTexture = screen.getTextures().get(thing.getImage());
        Drawable imageDrawable = new TextureRegionDrawable(new TextureRegion(imageTexture));
        mOrderButton = new ImageButton(imageDrawable);
        mOrderButton.setBounds(0, 0, 50, 50);
        addActor(mOrderButton);

        setBounds(0, 0, 50, 50);
        parent.addActor(this);

        mOrderButton.addListener(new ClickListener() {

            @Override
            public void clicked (InputEvent event, float x, float y) {
                Gdx.app.log("OrderButton", "click to plan");
                screen.getGame().getTown().tryOrderThing(building, thing);
            }

        });
    }

}
