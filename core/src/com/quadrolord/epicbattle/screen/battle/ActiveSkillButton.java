package com.quadrolord.epicbattle.screen.battle;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.quadrolord.ejge.view.AbstractScreen;
import com.quadrolord.epicbattle.logic.tower.BattleGame;

/**
 * Created by Quadrowin on 21.01.2016.
 */
public class ActiveSkillButton extends Group {

    private ImageButton mFireButton;

    public ActiveSkillButton(final AbstractScreen screen, Stage stage) {
        String icon = screen.get(BattleGame.class).getPlayerTower().getActiveSkill().getInfo().getIcon();
        Skin skin = screen.getSkin();
        if (!skin.has(icon, Texture.class)) {
            skin.add(icon, new Texture(icon));
        }
        mFireButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(skin.get(icon, Texture.class))));

        this.setBounds(
                0,
                80,
                40,
                40
        );
        mFireButton.setBounds(0, 0, getWidth(), getHeight());
        this.addActor(mFireButton);
        stage.addActor(this);

        this.addListener(new ClickListener() {

            @Override
            public void clicked (InputEvent event, float x, float y) {
                screen.get(BattleGame.class).getPlayerTower().useActiveSkill();
            }

        });
    }

}
