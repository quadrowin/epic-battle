package com.quadrolord.epicbattle.view.town.resource;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.quadrolord.epicbattle.logic.thing.ThingItem;

/**
 * Created by Quadrowin on 01.02.2016.
 */
public class IronOreLabel extends Group {

    private Label mLabel;

    private ThingItem mResource;

    public IronOreLabel(ThingItem resource, Skin skin, Stage stage) {
        mResource = resource;
        setBounds(0, 0, 120, 30);

        mLabel = new Label(Float.toString(mResource.getCount()), skin.get("default-label-style", Label.LabelStyle.class));
        mLabel.setBounds(0, 0, getWidth(), getHeight());
        mLabel.setAlignment(Align.left);
        addActor(mLabel);

        stage.addActor(this);
    }

    @Override
    public void act(float delta) {
        mLabel.setText(mResource.getInfo().getShort() +" " + Float.toString(mResource.getCount()));
    }

}
