package com.quadrolord.epicbattle.screen.slider;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.TimeUtils;
import com.quadrolord.ejge.view.AbstractScreen;

/**
 * Created by Quadrowin on 20.02.2016.
 */
public class SliderList extends Group {

    private int mListPaddingX = 10;
    private int mListPaddingBot = 10;
    private int mItemPaddingX = 10;
    private int mItemWidth = 100;
    private int mItemHeight = 100;

    private TextButton mBackground;

    private Group mWrapper;

    private float mPosition;

    private AbstractScreen mScreen;

    private SliderContent mContent;

    private ShapeRenderer mShapeRenderer;

    public SliderList(final AbstractScreen screen, final SliderContent content) {
        mContent = content;
        mScreen = screen;
        Skin skin = screen.getSkin();

        Texture texture = new Texture("ui/panel-64.png");
        skin.add("ui-panel-64", texture);

        NinePatch npPanel = new NinePatch(
                skin.get("ui-panel-64", Texture.class),
                16, 16, 16, 16
        );

        Drawable npdPanel = new NinePatchDrawable(npPanel);

        mBackground = new TextButton(
                "",
                new TextButton.TextButtonStyle(
                        npdPanel,
                        npdPanel,
                        null,
                        skin.getFont("default")
                )
        );
        mBackground.setBounds(30, 30, 340, 120);
        addActor(mBackground);

        int items_count = mContent.getCount();

        mWrapper = new SliderWrapper(mScreen);
        mWrapper.setBounds(0, 0, items_count * (mItemWidth + mItemPaddingX) - mItemPaddingX + 2 * mListPaddingX, mBackground.getHeight());
        mBackground.addActor(mWrapper);

        TextButton.TextButtonStyle defaultTextButtonStyle = skin.get("default-text-button-style", TextButton.TextButtonStyle.class);

        for (int i = 0; i < items_count; i++) {
            TextButton tb = new TextButton("", defaultTextButtonStyle);
            tb.setBounds(i * (mItemWidth + mItemPaddingX) + mListPaddingX, mListPaddingBot, mItemWidth, mItemHeight);

            mContent.initButton(tb, i);

            mWrapper.addActor(tb);
        }

        // какой-то баг, последний лабел во вропере выходит за границы внешнего элемента,
        // поэтому добавляем еще одну копку, которая будет его перекрывать
        TextButton tbViewFix = new TextButton("", defaultTextButtonStyle);
        tbViewFix.setBounds(items_count * (mItemWidth + mItemPaddingX) + mListPaddingX, mListPaddingBot, mItemWidth, mItemHeight);
        mWrapper.addActor(tbViewFix);

        // Кнопка закрытия
        TextButton btnClose = new TextButton("Close", defaultTextButtonStyle);
        btnClose.setBounds(260, 80, 65, 30);
        mBackground.addActor(btnClose);
        btnClose.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                screen.getAdapter().switchToScreen(screen.getParentScreen(), true);
            }

        });

        setBounds(0, 0, 400, 180);
        screen.getStage().addActor(this);

        mShapeRenderer = new ShapeRenderer();
    }

    @Override
    public void act(float delta) {
        if (Gdx.input.isTouched()) {
            float bgWidth = mBackground.getWidth();
            float freeSpace = (bgWidth - mItemWidth);
            mPosition = Math.max(
                    bgWidth - mWrapper.getWidth() - freeSpace + mItemWidth + mItemPaddingX * 3,
                    Math.min(
                            freeSpace / 2 - mItemPaddingX,
                            mPosition + Gdx.input.getDeltaX()
                    )
            );
            mWrapper.setX(mPosition);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        applyTransform(batch, computeTransform());
        drawChildren(batch, parentAlpha);

        drawSelectBox(batch);

        resetTransform(batch);
    }

    private void drawSelectBox(Batch batch) {
        batch.end();
        float bgWidth = mBackground.getWidth();
        mShapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        mShapeRenderer.setTransformMatrix(batch.getTransformMatrix());
        mShapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        Gdx.gl.glEnable(Gdx.gl20.GL_BLEND);

        float ms = (float)(TimeUtils.millis() % 5000) / 5000;
        float ms2 = 2 * Math.abs(0.5f - ms);

        mShapeRenderer.setColor(ms2, 1 - ms2, ms2, 0.5f);

        float x = mBackground.getX() + (bgWidth - mItemWidth) / 2;
        float y = mBackground.getY() + mListPaddingBot;

        Gdx.app.log("slider select size", "x " + x + " y " + y);

        float border_width = 3;

        mShapeRenderer.rect(
                x + border_width, y,
                mItemWidth - 2 * border_width, border_width
        );
        mShapeRenderer.rect(
                x + border_width, y + mItemHeight - border_width,
                mItemWidth - 2 * border_width, border_width
        );
        mShapeRenderer.rect(x, y, border_width, mItemHeight);
        mShapeRenderer.rect(x + mItemWidth - border_width, y, border_width, mItemHeight);

        mShapeRenderer.end();
        batch.begin();
    }

}