package org.delusion.afterline.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import org.delusion.afterline.AfterlineClient;

public class MainMenu extends ScreenAdapter {

    private Cell<Image> icoImageCell;
    private Image icoimg;
    private Stage stage;
    private Table table;
    private Texture icontex;


    public MainMenu(AfterlineClient afterlineClient) {
//        stage = new Stage(new ScalingViewport(Scaling.fit, 1920, 1080));
        stage = new Stage(new ExtendViewport(1920, 1080));

        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

//        table.setDebug(true);

        icontex = new Texture(Gdx.files.internal("textures/afterline_icon.png"));

        icoimg = new Image(icontex);
        icoimg.setAlign(Align.center);

        icoimg.setScaling(Scaling.fit);
        icoImageCell = table.add(icoimg).size(Value.percentHeight(0.5f, table), Value.percentHeight(0.5f, table)).expand();
        icoImageCell.pad(20);
    }

    @Override
    public void resize(int width, int height) {
//        icoImageCell.width();
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        icoimg.setColor(1,1,1,0);
        icoImageCell.getActor().addAction(
                Actions.sequence(Actions.delay(1),
                        Actions.fadeIn(1.5f),
                        Actions.delay(2), Actions.parallel(
                                Actions.moveToAligned(10, stage.getHeight() - 10,Align.topLeft, 0.33f, Interpolation.pow5Out),
                                Actions.sizeTo(stage.getHeight() / 16, stage.getHeight() / 16, 0.33f, Interpolation.pow4Out)
                        ),
                        Actions.run(() -> {
                            icoimg.setOrigin(Align.center);
                        }),
                        Actions.scaleTo(1.2f, 1.2f, 0.2f, Interpolation.pow2OutInverse),
                        Actions.delay(0.2f),
                        Actions.scaleTo(1.0f / 1.2f, 1.0f / 1.2f, -0.05f),
                        Actions.delay(0.2f),
                        Actions.run(() -> {
                            icoimg.setScale(1.0f);
                            icoimg.setAlign(Align.topLeft);
                            icoImageCell.left().top();
                            icoImageCell.size(stage.getHeight() / 16, stage.getHeight() / 16).padLeft(10).padTop(10);
                            table.invalidate();
                        })
                )
        );
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        ScreenUtils.clear(AfterlineClient.ICONBG);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
