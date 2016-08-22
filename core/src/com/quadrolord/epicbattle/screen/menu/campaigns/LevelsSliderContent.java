package com.quadrolord.epicbattle.screen.menu.campaigns;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.quadrolord.ejge.view.AbstractScreen;
import com.quadrolord.epicbattle.logic.campaign.AbstractCampaign;
import com.quadrolord.epicbattle.logic.campaign.CampaignManager;
import com.quadrolord.epicbattle.logic.campaign.Level;
import com.quadrolord.epicbattle.screen.slider.SliderContent;

/**
 * Created by Goorus on 22.08.2016.
 */
public class LevelsSliderContent extends SliderContent<Level> {

    private AbstractScreen mScreen;

    private AbstractCampaign mCampaign;

    public LevelsSliderContent(AbstractScreen screen, AbstractCampaign camp) {
        mScreen = screen;
        mCampaign = camp;
    }

    @Override
    public int getCount() {
        return mCampaign.getLevels().length;
    }

    @Override
    public Level initButton(TextButton btn, int index) {
        Level level = mCampaign.getLevels()[index];

        Texture tx = mScreen.getTextures().get("campaign/" + mCampaign.getDir() + "/" + mCampaign.getIcon());
        Drawable dr = new TextureRegionDrawable(new TextureRegion(tx));

        TextButton.TextButtonStyle tbs = new TextButton.TextButtonStyle(
                dr,
                dr,
                dr,
                mScreen.getSkin().getFont("default")
        );

        btn.setStyle(tbs);

        return level;
    }
}
