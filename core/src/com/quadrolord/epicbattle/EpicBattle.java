package com.quadrolord.epicbattle;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.quadrolord.epicbattle.logic.Game;
import com.quadrolord.epicbattle.logic.utils.PlatformServices;
import com.quadrolord.epicbattle.screen.AbstractScreen;
import com.quadrolord.epicbattle.screen.MyTownScreen;

public class EpicBattle extends ApplicationAdapter {

	private FPSLogger mFps;

	private Game mGame;

	private Skin mSkin;

	private AbstractScreen mScreen;

	private PlatformServices mPlatformServices;

	public EpicBattle(PlatformServices platformServices) {
		mPlatformServices = platformServices;
	}
	
	@Override
	public void create () {
		mGame = new Game(mPlatformServices);
		mFps = new FPSLogger();
//		mScreen = new BattleScreen(this, null);
		mScreen = new MyTownScreen(this);
	}

	public Game getGame() {
		return mGame;
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

	public Skin getSkin() {
		if (mSkin != null) {
			return mSkin;
		}
		mSkin = new Skin();

		BitmapFont font = new BitmapFont();
		mSkin.add("default", font, BitmapFont.class);

		Label.LabelStyle ls = new Label.LabelStyle(font, Color.WHITE);
		mSkin.add("default-label-style", ls);

		Texture texture = new Texture(Gdx.files.internal("badlogic.jpg"));
		mSkin.add("test-texture", texture);

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
		mSkin.add("default-text-button-style", textButtonStyle);

		return mSkin;
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		mScreen.render(Gdx.graphics.getDeltaTime());
		mFps.log();
	}

	@Override
	public void resize (int width, int height) {
		if (mScreen != null) {
			mScreen.resize(width, height);
		}
	}

	@Override
	public void resume() {
		if (mScreen != null) {
			mScreen.resume();
		}
	}

	public void switchToScreen(Class<? extends AbstractScreen> screenClass) {
		try {
			AbstractScreen screen = screenClass.getConstructor(EpicBattle.class).newInstance(this);
			switchToScreen(screen, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void switchToScreen(AbstractScreen newScreen, boolean dispose) {
		if (dispose && mScreen != null) {
			mScreen.dispose();
		}
		mScreen = newScreen;
		if (mScreen != null) {
			mScreen.switchIn();
		}
	}

}
