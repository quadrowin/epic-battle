package com.quadrolord.epicbattle.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.quadrolord.epicbattle.EpicBattle;

/**
 * Created by Quadrowin on 16.01.2016.
 */
public class MyTownScreen extends AbstractScreen {

    private TiledMap map;
    private TiledMapRenderer renderer;
    private Texture tiles;

    public MyTownScreen(EpicBattle adapter) {
        super(adapter);
        initFitViewport();

        TextButton btnToCampaignSelect = new TextButton("Enter your city", mSkin.get("default-text-button-style", TextButton.TextButtonStyle.class));
        btnToCampaignSelect.setBounds(10, 190, 180, 50);
        mStage.addActor(btnToCampaignSelect);
        btnToCampaignSelect.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                mAdapter.switchToScreen(CampaignSelectScreen.class);
            }

        });

        {
            tiles = new Texture(Gdx.files.internal("town/tiles.png"));
            TextureRegion[][] splitTiles = TextureRegion.split(tiles, 54, 54);
            map = new TiledMap();
            MapLayers layers = map.getLayers();
            for (int l = 0; l < 20; l++) {
                TiledMapTileLayer layer = new TiledMapTileLayer(40, 30, 54, 54);
                for (int x = 0; x < 40; x++) {
                    for (int y = 0; y < 30; y++) {
                        int ty = (int)(Math.random() * splitTiles.length);
                        int tx = (int)(Math.random() * splitTiles[ty].length);
                        TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
                        cell.setTile(new StaticTiledMapTile(splitTiles[ty][tx]));
                        layer.setCell(x, y, cell);
                    }
                }
                layers.add(layer);
            }
        }


        renderer = new OrthogonalTiledMapRenderer(map);
    }

    @Override
    public void draw(float delta) {
        mStage.act(delta);
        mStage.draw();

        mStage.getCamera().update();
        renderer.setView((OrthographicCamera)mStage.getCamera());
        renderer.render();
    }

    @Override
    public void update(float delta) {

    }
}
