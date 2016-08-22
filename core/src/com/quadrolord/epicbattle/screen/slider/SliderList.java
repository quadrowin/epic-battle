package com.quadrolord.epicbattle.screen.slider;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.TimeUtils;
import com.quadrolord.ejge.view.AbstractScreen;
import com.quadrolord.epicbattle.RM;

import java.util.Iterator;

/**
 * Created by Quadrowin on 20.02.2016.
 */
public class SliderList extends Group {

    private boolean mDragging = false;
    private float mDragStartWrapperX;
    private float mDragStartCursorX;

    private int mListPaddingX = 20;
    private int mListPaddingBot = 20;
    private int mItemPaddingX = 20;
    private int mItemWidth = 200;
    private int mItemHeight = 200;

    private TextButton mBackground;

    private int mItemIndex = 0;

    private SliderWrapper mWrapper;

    private TextButton[] mButtons;

    private AbstractScreen mScreen;

    private SliderContent mContent;

    private ShapeRenderer mShapeRenderer;

    public SliderList(final AbstractScreen screen, final SliderContent content) {
        final float CONTROL_WIDTH = 800;
        final float CONTROL_HEIGHT = 240;

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
        mBackground.setBounds(0, 0, CONTROL_WIDTH, CONTROL_HEIGHT);
        addActor(mBackground);

        int items_count = mContent.getCount();
        mButtons = new TextButton[items_count];

        mWrapper = new SliderWrapper(mScreen);
        mWrapper.setPaddingX(10);
        mWrapper.setBounds(0, 0, items_count * (mItemWidth + mItemPaddingX) - mItemPaddingX + 2 * mListPaddingX, mBackground.getHeight());
        mBackground.addActor(mWrapper);

        for (int i = 0; i < items_count; i++) {
            TextButton tb = new TextButton("", RM.getTextButtonStyle());
            tb.setBounds(i * (mItemWidth + mItemPaddingX) + mListPaddingX, mListPaddingBot, mItemWidth, mItemHeight);
            mButtons[i] = tb;
            tb.addListener(mContent.getClickListener());
            Object data = mContent.initButton(tb, i);
            tb.setUserObject(data);
            mWrapper.addActor(tb);
        }

        // какой-то баг, последний лабел во вропере выходит за границы внешнего элемента,
        // поэтому добавляем еще одну копку, которая будет его перекрывать
        Texture transparentTexture = skin.get("transparent", Texture.class);
        TextureRegionDrawable transparentRd = new TextureRegionDrawable(new TextureRegion(transparentTexture));
        TextButton.TextButtonStyle transparentTextButtonStyle = new TextButton.TextButtonStyle(
                transparentRd,
                transparentRd,
                transparentRd,
                skin.getFont("default")
        );
//        TextButton tbViewFix = new TextButton("", defaultTextButtonStyle);
        TextButton tbViewFix = new TextButton("", transparentTextButtonStyle);
        tbViewFix.setBounds(items_count * (mItemWidth + mItemPaddingX) + mListPaddingX, mListPaddingBot, mItemWidth, mItemHeight);
        mWrapper.addActor(tbViewFix);

        setBounds(0, 100, CONTROL_WIDTH, CONTROL_HEIGHT);
        screen.getStage().addActor(this);

        mShapeRenderer = new ShapeRenderer();
    }

    @Override
    public void act(float delta) {
        float bgWidth = mBackground.getWidth();
        float freeSpace = (bgWidth - mItemWidth);
        if (Gdx.input.isTouched()) {
            if (!mDragging) {
                mDragging = true;
                mDragStartWrapperX = mWrapper.getX();
                mDragStartCursorX = Gdx.input.getX();
            }
            float cursorDeltaX = Gdx.input.getX() - mDragStartCursorX;
            // На сколько можно протянуть список дальше крайнего положения
            float tolerance = mItemWidth * 0.25f;
            float newX = Math.max(
                    bgWidth - mWrapper.getWidth() - freeSpace + mItemWidth + mItemPaddingX * 3 - tolerance,
                    Math.min(
                            freeSpace / 2 - mListPaddingX + tolerance,
                            mDragStartWrapperX + cursorDeltaX
                    )
            );
            mWrapper.setX(newX);
        } else {
            mDragging = false;
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
            if (delta_val < 1) {
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
