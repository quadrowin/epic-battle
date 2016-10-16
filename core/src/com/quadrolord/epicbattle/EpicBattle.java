package com.quadrolord.epicbattle;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.quadrolord.ejge.AbstractGameAdapter;
import com.quadrolord.ejge.utils.PlatformServices;
import com.quadrolord.ejge.utils.ServiceFactory;
import com.quadrolord.ejge.view.AbstractScreen;
import com.quadrolord.epicbattle.logic.profile.ProfileManager;
import com.quadrolord.epicbattle.logic.tower.BattleGame;
import com.quadrolord.epicbattle.logic.town.MyTown;
import com.quadrolord.epicbattle.screen.BattleScreen;
import com.quadrolord.epicbattle.screen.menu.MainMenuScreen;
import com.quadrolord.epicbattle.screen.menu.UnitsTestScreen;

public class EpicBattle extends AbstractGameAdapter {

	private ProfileManager mProfileManager;

	public EpicBattle(PlatformServices platformServices) {
		super(platformServices);
	}
	
	@Override
	public void create () {
		super.create();

		mProfileManager = new ProfileManager(getPlatformServices());
		registerService(mProfileManager);

		registerFactory(BattleGame.class, new ServiceFactory() {
			@Override
			public Object create(AbstractGameAdapter ad) {
				return new BattleGame((EpicBattle)ad);
			}
		});

		registerFactory(MyTown.class, new ServiceFactory() {
			@Override
			public Object create(AbstractGameAdapter ad) {
				return new MyTown((EpicBattle)ad);
			}
		});

		switchToScreen(getDefaultScreen(), true);
	}

	@Override
	public AbstractScreen getDefaultScreen() {
//		return new MainMenuScreen(this);

//		return new BattleScreen(this, null);
//		return new CampaignSelectScreen(this);
//		return new UnitsUpgradingScreen(this);
		return new UnitsTestScreen(this);
//		return new MyTownScreen(this);
	}

	public ProfileManager getProfileManager() {
		return mProfileManager;
	}

	/**
	 * Создание нового скина с базовыми ресурсами
	 * @return
	 */
	public Skin getNewSkin() {
		Skin src = getSkin();
		Skin dst = new Skin();

		Object[] copyResources = new Object[] {
				"default", BitmapFont.class,
				"test-texture", Texture.class,
				"default-label-style", Label.LabelStyle.class,
				"default-text-button-style", TextButton.TextButtonStyle.class,
				"transparent", Texture.class,
		};

		for (int i = 0; i < copyResources.length; i += 2) {
			dst.add(
					(String)copyResources[i],
					src.get((String)copyResources[i], (Class)copyResources[i + 1]),
					(Class)copyResources[i + 1]
			);
		}

		return dst;
	}



	public void initCommonSkin(Skin skin) {
		RM.setSkin(skin);
	}

}
