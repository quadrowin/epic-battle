package com.quadrolord.epicbattle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.quadrolord.ejge.AbstractGameAdapter;
import com.quadrolord.ejge.utils.PlatformServices;
import com.quadrolord.ejge.view.AbstractScreen;
import com.quadrolord.epicbattle.logic.profile.ProfileManager;
import com.quadrolord.epicbattle.logic.tower.BattleGame;
import com.quadrolord.epicbattle.logic.town.MyTown;
import com.quadrolord.epicbattle.screen.MyTownScreen;

public class EpicBattle extends AbstractGameAdapter {

	private BattleGame mBattleGame;

	private MyTown mTownGame;

	private ProfileManager mProfileManager;

	public EpicBattle(PlatformServices platformServices) {
		super(platformServices);
	}
	
	@Override
	public void create () {
		super.create();
		mProfileManager = new ProfileManager(getPlatformServices());

//		AbstractScreen screen = new BattleScreen(this, null);
		AbstractScreen screen = new MyTownScreen(this);
		switchToScreen(screen, true);
	}

	public BattleGame getBattleGame() {
		if (mBattleGame == null) {
			mBattleGame = new BattleGame(this);
		}
		return mBattleGame;
	}

	public ProfileManager getProfileManager() {
		return mProfileManager;

	}

	public MyTown getTown() {
		if (mTownGame == null) {
			mTownGame = new MyTown(this);
		}
		return mTownGame;
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
		BitmapFont font = new BitmapFont();
		skin.add("default", font, BitmapFont.class);

		Label.LabelStyle ls = new Label.LabelStyle(font, Color.WHITE);
		skin.add("default-label-style", ls);

		Texture texture = new Texture(Gdx.files.internal("badlogic.jpg"));
		skin.add("test-texture", texture);

		Texture textureBtnUp = new Texture("ui/button64-up.png");
		Texture textureBtnDown = new Texture("ui/button64-down.png");

		NinePatch patchUp = new NinePatch(
				textureBtnUp,
				16, 16, 16, 16
		);

		NinePatch patchDown = new NinePatch(
				textureBtnDown,
				16, 16, 16, 16
		);

		TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle(
				new NinePatchDrawable(patchUp),
				new NinePatchDrawable(patchDown),
				null,
				font
		);
		skin.add("default-text-button-style", textButtonStyle);
	}

}
