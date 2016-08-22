package com.quadrolord.epicbattle.screen.menu.campaigns;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.quadrolord.ejge.view.AbstractScreen;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractLogic;
import com.quadrolord.epicbattle.logic.campaign.AbstractCampaign;
import com.quadrolord.epicbattle.logic.campaign.CampaignManager;
import com.quadrolord.epicbattle.screen.slider.SliderContent;

/**
 * Created by Goorus on 21.08.2016.
 */
public class CampaignSliderContent extends SliderContent<AbstractCampaign> {

    private AbstractScreen mScreen;

    private CampaignManager mCampaignManager;

    public CampaignSliderContent(AbstractScreen screen, CampaignManager cm) {
        mScreen = screen;
        mCampaignManager = cm;
    }

    @Override
    public int getCount() {
        return mCampaignManager.getCampaigns().length;
    }

    @Override
    public AbstractCampaign initButton(TextButton btn, int index) {
        AbstractCampaign camp = mCampaignManager.getCampaigns()[index];

        Texture tx = mScreen.getTextures().get("campaign/" + camp.getDir() + "/" + camp.getIcon());
        Drawable dr = new TextureRegionDrawable(new TextureRegion(tx));

        TextButton.TextButtonStyle tbs = new TextButton.TextButtonStyle(
                dr,
                dr,
                dr,
                mScreen.getSkin().getFont("default")
        );

        btn.setStyle(tbs);

        return camp;
    }
}
