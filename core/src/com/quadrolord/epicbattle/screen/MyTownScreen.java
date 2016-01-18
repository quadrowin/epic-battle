package com.quadrolord.epicbattle.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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

    OrthographicCamera mMapCamera = new OrthographicCamera(400, 300);

    private int mSizeX = 7;
    private int mSizeY = 7;
    private int mCellWidth = 32;
    private int mCellHeight = 32;

    private TiledMap mMap;
    private TiledMapRenderer mRenderer;

    private float mDeltaX, mDeltaY;

    public MyTownScreen(EpicBattle adapter) {
        super(adapter);
        initFitViewport();
        mMapCamera.position.set(mSizeX * mCellWidth / 2, mSizeY * mCellHeight / 2, 0);
        mMapCamera.update();

        TextButton btnToCampaignSelect = new TextButton("Select campaign", mSkin.get("default-text-button-style", TextButton.TextButtonStyle.class));
        btnToCampaignSelect.setBounds(10, 240, 180, 50);
        mStage.addActor(btnToCampaignSelect);
        btnToCampaignSelect.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                mAdapter.switchToScreen(CampaignSelectScreen.class);
            }

        });

        {
            Texture tiles = new Texture(Gdx.files.internal("town/wood_tileset.png"));
            TextureRegion[][] splitTiles = TextureRegion.split(tiles, mCellWidth, mCellHeight);
            mMap = new TiledMap();
            MapLayers layers = mMap.getLayers();
            for (int l = 0; l < 3; l++) {
                TiledMapTileLayer layer = new TiledMapTileLayer(mSizeX, mSizeY, mCellWidth, mCellHeight);
                for (int x = 0; x < 7; x++) {
                    for (int y = 0; y < 7; y++) {
                        int ty = 0;
                        int tx = 0;
                        if (l == 0) {
                            tx = (int)(Math.random() * 8);
                        } else if (Math.random() > 0.8) {
                            ty = (int)(Math.random() * 8);
                            tx = (int)(Math.random() * 8);
                        }
                        TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
                        cell.setTile(new StaticTiledMapTile(splitTiles[ty][tx]));
                        layer.setCell(x, y, cell);
                    }
                }
                layers.add(layer);
            }
        }

//        mRenderer = new IsometricTiledMapRenderer(mMap);
        mRenderer = new OrthogonalTiledMapRenderer(mMap);
    }

    @Override
    public void draw(float delta) {

        float width = mSizeX * mCellWidth + mDeltaX;
        float height = mSizeY * mCellHeight + mDeltaY;
        mRenderer.setView(
                mMapCamera.combined,
                0, 0,
                width,
                height
        );
//            Gdx.app.log("map size", "" + width + "x" + height);

        mRenderer.render();

        mStage.act(delta);
        mStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        mStage.getViewport().update(width, height, true);
    }

    @Override
    public void update(float delta) {
        float dx = delta * 10;
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            mDeltaX -= dx;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            mDeltaY -= dx;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            mDeltaX += dx;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            mDeltaY += dx;
        }
    }
}
