package com.quadrolord.epicbattle.screen.slider;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.TimeUtils;
import com.quadrolord.ejge.view.AbstractScreen;

import java.util.Iterator;

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

    private int mItemIndex = 0;

    private Group mWrapper;

    private TextButton[] mButtons;

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
        mButtons = new TextButton[items_count];

        mWrapper = new SliderWrapper(mScreen);
        mWrapper.setBounds(0, 0, items_count * (mItemWidth + mItemPaddingX) - mItemPaddingX + 2 * mListPaddingX, mBackground.getHeight());
        mBackground.addActor(mWrapper);

        TextButton.TextButtonStyle defaultTextButtonStyle = skin.get("default-text-button-style", TextButton.TextButtonStyle.class);

        for (int i = 0; i < items_count; i++) {
            TextButton tb = new TextButton("", defaultTextButtonStyle);
            tb.setBounds(i * (mItemWidth + mItemPaddingX) + mListPaddingX, mListPaddingBot, mItemWidth, mItemHeight);
            mButtons[i] = tb;

            mContent.initButton(tb, i);

            mWrapper.addActor(tb);
        }

        // какой-то баг, последний лабел во вропере выходит за границы внешнего элемента,
        // поэтому добавляем еще одну копку, которая будет его перекрывать
        TextButton tbViewFix = new TextButton("", defaultTextButtonStyle);
        tbViewFix.setBounds(items_count * (mItemWidth + mItemPaddingX) + mListPaddingX, mListPaddingBot, mItemWidth, mItemHeight);
        mWrapper.addActor(tbViewFix);

        setBounds(0, 0, 400, 180);
        screen.getStage().addActor(this);

        mShapeRenderer = new ShapeRenderer();
    }

    @Override
    public void act(float delta) {
        float bgWidth = mBackground.getWidth();
        float freeSpace = (bgWidth - mItemWidth);
        if (Gdx.input.isTouched()) {
            mPosition = Math.max(
                    bgWidth - mWrapper.getWidth() - freeSpace + mItemWidth + mItemPaddingX * 3,
                    Math.min(
                            freeSpace / 2 - mListPaddingX,
                            mPosition + Gdx.input.getDeltaX()
                    )
            );
            mWrapper.setX(mPosition);
        } else {
            float zero_x = freeSpace / 2 - mListPaddingX;

            float x = mWrapper.getX();
            // определяем текущий элемент
            int itemIndex = -Math.round((x - zero_x) / (mItemWidth + mItemPaddingX));
            if (itemIndex >= mButtons.length) {
                itemIndex = mButtons.length - 1;
            }
            if (itemIndex < 0) {
                itemIndex = 0;
            }
            if (itemIndex != mItemIndex) {
                mItemIndex = itemIndex;
                triggerCurrentButtonClick();
            }
            // как должно быть с точным позиционированием
            float exact_x = -itemIndex * (mItemWidth + mItemPaddingX) + zero_x;
            float delta_x = exact_x - x;
            float delta_val = Math.abs(delta_x);
            if (delta_val < 0.001) {
                mWrapper.setX(exact_x);
                return;
            }

            delta_val = Math.min(Math.max(1, delta_val), delta * 30);

            mWrapper.setX(x + delta_val * Math.signum(delta_x));
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

//        Gdx.app.log("slider select size", "x " + x + " y " + y);

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

    public TextButton getCurrentItem() {
        return 0 <= mItemIndex && mItemIndex < mButtons.length
                ? mButtons[mItemIndex]
                : null;
    }

    /**
     * Возвращает UserObject текущего элемента
     * @return
     */
    public Object getCurrentObject() {
        return 0 <= mItemIndex && mItemIndex < mButtons.length
            ? mButtons[mItemIndex].getUserObject()
            : null;
    }

    public void setItemIndex(int index) {
        if (mItemIndex != index) {
            mItemIndex = index;
            triggerCurrentButtonClick();
        }
    }

    public void triggerCurrentButtonClick() {
        TextButton btn = mButtons[mItemIndex];
        InputEvent ie = new InputEvent();
        ie.setRelatedActor(btn);
        for (Iterator<EventListener> it = btn.getListeners().iterator(); it.hasNext(); ) {
            EventListener el = it.next();
            if (el instanceof ClickListener) {
                ((ClickListener) el).clicked(ie, 0, 0);
            }
        }
    }

}
