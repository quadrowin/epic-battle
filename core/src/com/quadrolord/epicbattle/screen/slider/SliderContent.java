package com.quadrolord.epicbattle.screen.slider;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.quadrolord.epicbattle.screen.upgrading.UpgradingItemData;

/**
 * Created by Quadrowin on 28.06.2016.
 */
abstract public class SliderContent<T> {

    protected ClickListener mClickListener;

    protected SliderListener<T> mSelectListener;

    public SliderContent() {
        mClickListener = new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (event.getRelatedActor() instanceof TextButton) {
                    T data = (T)event.getRelatedActor().getUserObject();
                    mSelectListener.onSelect((TextButton) event.getRelatedActor(), data);
                }
            }

        };
    }

    abstract public int getCount();

    public ClickListener getClickListener() {
        return mClickListener;
    }

    /**
     * Первая инициализация элемента списка
     * @param btn
     * @param index
     */
    abstract public T initButton(TextButton btn, int index);

    public void setSliderListener(SliderListener<T> listener) {
        mSelectListener = listener;
    }

}
