package com.quadrolord.epicbattle;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.quadrolord.epicbattle.logic.Game;
import com.quadrolord.epicbattle.screen.AbstractScreen;
import com.quadrolord.epicbattle.screen.BattleScreen;

public class EpicBattle extends ApplicationAdapter {

	private FPSLogger mFps;

	private Game mGame;

	private Skin mSkin;

	private AbstractScreen mScreen;
	
	@Override
	public void create () {
		mGame = new Game();
		mFps = new FPSLogger();
		mScreen = new BattleScreen(this, mGame);
	}

	/**
	 * Создание нового скина с базовыми ресурсами
	 * @return
	 */
	public Skin getNewSkin() {
		Skin src = getSkin();
		Skin dst = new Skin();

		dst.add("default", src.getFont("default"), BitmapFont.class);

		String[] copyTextures = new String[] {
				"test-texture"
		};

		for (int i = 0; i < copyTextures.length; i++) {
			dst.add(
					copyTextures[i],
					src.get(copyTextures[i], Texture.class),
					Texture.class
			);
		}

		String[] copyDrawables = new String[] {

		};

		for (int i = 0; i < copyDrawables.length; i++) {
			dst.add(
					copyDrawables[i],
					src.getDrawable(copyDrawables[i]),
					Drawable.class
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

		Texture texture = new Texture(Gdx.files.internal("badlogic.jpg"));
		mSkin.add("test-texture", texture);

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
			AbstractScreen screen = screenClass.getConstructor(EpicBattle.class, Game.class).newInstance(this, mGame);
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
			Gdx.input.setInputProcessor(mScreen.getStage());
		}
	}

}
