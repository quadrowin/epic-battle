package com.quadrolord.epicbattle.screen.upgrading.slider;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ArrayMap;
import com.quadrolord.ejge.view.AbstractScreen;
import com.quadrolord.epicbattle.logic.bullet.BulletSkill;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.logic.tower.BattleGame;

/**
 * Created by Quadrowin on 20.02.2016.
 */
public class UpgradingSlider extends Group {

    private TextButton mBackground;

    private Group mWrapper;

    private float mPosition;

    private AbstractScreen mScreen;

    public UpgradingSlider(final AbstractScreen screen) {
        mScreen = screen;
        Skin skin = screen.getSkin();

        Texture texture = new Texture("ui/panel-64.png");
        skin.add("ui-panel-64", texture);

        NinePatch npPanel = new NinePatch(
                skin.get("ui-panel-64", Texture.class),
                16, 16, 16, 16
        );

        Drawable npdPanel = new NinePatchDrawable(npPanel);

        mBackground = new TextButton(
                "",
                new TextButton.TextButtonStyle(
                        npdPanel,
                        npdPanel,
                        null,
                        skin.getFont("default")
                )
        );
        mBackground.setBounds(30, 30, 340, 120);
        addActor(mBackground);

        // TODO Тут нужно загрузить скилы
        ArrayMap<Class<? extends AbstractBullet>, BulletSkill> skills = screen.get(BattleGame.class).getPlayerTower().getBulletSkills();
        Gdx.app.log("UpgradingSlider", "loaded skills: " + skills.size);

        mWrapper = new UpgradingSliderWrapper(mScreen);
        mWrapper.setBounds(0, 0, skills.size * 120 + 20, mBackground.getHeight());
        mBackground.addActor(mWrapper);

        ClickListener cl = new ClickListener() {

            public void clicked (InputEvent event, float x, float y) {
                // upgrade

                screen.getAdapter().switchToScreen(screen.getParentScreen(), true);
            }

        };

        for (int i = 0; i < skills.size; i++) {
            BulletSkill skill = skills.getValueAt(i);
            String txFile = skill.getIcon();
            if (txFile == null) {
                Gdx.app.error("UpgradingSlider", "no slider texture for building " + skill.getTitle());
            }
            Texture tx = screen.getTextures().get(txFile);
            Drawable dr = new TextureRegionDrawable(new TextureRegion(tx));

            TextButton.TextButtonStyle tbs = new TextButton.TextButtonStyle(
                    dr,
                    dr,
                    dr,
                    screen.getSkin().getFont("default")
            );
            TextButton tb = new TextButton("", tbs);
            tb.setUserObject(skill);

            tb.setBounds(i * 120 + 10, 10, 100, 100);
            tb.addListener(cl);

            Label btnLabel = new Label(
                    skill.getTitle(),
                    skin.get("default-label-style", Label.LabelStyle.class)
            );
            btnLabel.setAlignment(Align.left);
            btnLabel.setBounds(0, 0, tb.getWidth(), 15);
            tb.addActor(btnLabel);

            mWrapper.addActor(tb);
        }

        // Кнопка закрытия
        TextButton btnClose = new TextButton("Close", skin.get("default-text-button-style", TextButton.TextButtonStyle.class));
        btnClose.setBounds(260, 80, 65, 30);
        mBackground.addActor(btnClose);
        btnClose.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                screen.getAdapter().switchToScreen(screen.getParentScreen(), true);
            }

        });

        setBounds(0, 0, 400, 180);
        screen.getStage().addActor(this);
    }

    @Override
    public void act(float delta) {
        if (Gdx.input.isTouched()) {
            mPosition = Math.max(
                    mBackground.getWidth() - mWrapper.getWidth(),
                    Math.min(
                            0,
                            mPosition + Gdx.input.getDeltaX()
                    )
            );
            mWrapper.setX(30 + mPosition);
        }
    }

}
