package com.quadrolord.epicbattle.screen.unitstest;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.quadrolord.ejge.view.AbstractScreen;
import com.quadrolord.epicbattle.EpicBattle;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractLogic;
import com.quadrolord.epicbattle.logic.bullet.worker.big.BigLogic;
import com.quadrolord.epicbattle.logic.bullet.worker.epic.EpicLogic;
import com.quadrolord.epicbattle.logic.bullet.worker.forks.ForksLogic;
import com.quadrolord.epicbattle.logic.bullet.worker.girl.GirlLogic;
import com.quadrolord.epicbattle.logic.bullet.worker.simple.SimpleLogic;
import com.quadrolord.epicbattle.logic.bullet.worker.bike.BikeLogic;
import com.quadrolord.epicbattle.logic.tower.BattleGame;
import com.quadrolord.epicbattle.screen.slider.SliderContent;


/**
 * Created by Goorus on 20.07.2016.
 */
public class UnitTestSliderContent extends SliderContent<AbstractLogic> {

    private EpicBattle mAdapter;

    private AbstractScreen mScreen;

    private Class<? extends AbstractLogic>[] mAllClasses;

    public UnitTestSliderContent(AbstractScreen screen) {
        mScreen = screen;
        mAdapter = (EpicBattle)screen.getAdapter();

        mAllClasses = new Class[] {
                BikeLogic.class,
                SimpleLogic.class,
                BigLogic.class,
                ForksLogic.class,
                GirlLogic.class,
                EpicLogic.class,
        };
    }

    @Override
    public int getCount() {
        return mAllClasses.length;
    }

    @Override
    public AbstractLogic initButton(TextButton btn, int index) {
        Class<? extends AbstractLogic> lc = mAllClasses[index];
        AbstractLogic logic = mAdapter.get(BattleGame.class).getBulletInfoManager().getBulletLogic(lc);

        Texture tx = mScreen.getTextures().get(logic.getIcon());
        Drawable dr = new TextureRegionDrawable(new TextureRegion(tx));

        TextButton.TextButtonStyle tbs = new TextButton.TextButtonStyle(
                dr,
                dr,
                dr,
                mScreen.getSkin().getFont("default")
        );

        btn.setStyle(tbs);

        return logic;
    }

}
