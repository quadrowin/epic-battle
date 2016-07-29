package com.quadrolord.epicbattle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.quadrolord.ejge.AbstractGameAdapter;
import com.quadrolord.ejge.utils.Dates;
import com.quadrolord.ejge.utils.PlatformServices;
import com.quadrolord.ejge.utils.ServiceFactory;
import com.quadrolord.ejge.view.AbstractScreen;
import com.quadrolord.epicbattle.logic.profile.ProfileManager;
import com.quadrolord.epicbattle.logic.tower.BattleGame;
import com.quadrolord.epicbattle.logic.town.MyTown;
import com.quadrolord.epicbattle.screen.BattleScreen;
import com.quadrolord.epicbattle.screen.UnitsTestScreen;

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


		AbstractScreen screen = new BattleScreen(this, null);
//		AbstractScreen screen = new CampaignSelectScreen(this);
//		AbstractScreen screen = new UnitsUpgradingScreen(this);
//		AbstractScreen screen = new UnitsTestScreen(this);
//		AbstractScreen screen = new MyTownScreen(this);
		switchToScreen(screen, true);

		Firebase ref1 = new Firebase("http://epic-battle.firebaseio.com/web/data/users/last_auth/time");
		ref1.setValue(Dates.now(), new Firebase.CompletionListener() {
            @Override
            public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                if (firebaseError != null) {
                    Gdx.app.log("Firebase", "Data could not be saved. " + firebaseError.getMessage());
                } else {
                    Gdx.app.log("Firebase", "Data saved successfully.");
                }
            }
        });
        Firebase ref2 = new Firebase("http://epic-battle.firebaseio.com/web/data/test");
        ref2.setValue("5");

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

		Pixmap transparent = new Pixmap(2, 2, Pixmap.Format.RGBA8888);
		transparent.setColor(1, 1, 1, 0);
		transparent.fill();
		skin.add("transparent", new Texture(transparent));
	}

}
