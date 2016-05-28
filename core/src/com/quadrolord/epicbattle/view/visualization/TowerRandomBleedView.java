package com.quadrolord.epicbattle.view.visualization;

import com.quadrolord.ejge.view.AbstractScreen;
import com.quadrolord.epicbattle.screen.battle.BleedAnimation;

/**
 * Created by Quadrowin on 14.01.2016.
 */
public class TowerRandomBleedView extends AbstractVisualization {

    public TowerRandomBleedView(AbstractScreen screen, float x, float y) {
        super(screen, x, y);
        new BleedAnimation(screen, x, y);
    }

}
