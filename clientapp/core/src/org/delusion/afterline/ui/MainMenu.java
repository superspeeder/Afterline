package org.delusion.afterline.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
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

import java.time.Instant;

public class MainMenu extends ScreenAdapter {

    private Texture icontex2;
    private Cell<Image> icoImageCell;
    private Image icoimg;
    private Stage stage;
    private Table table;
    private Texture icontex;
    private Color bg;
    private long t = 0, t2 = 0;
    private Image icoimg2;


    public MainMenu(AfterlineClient afterlineClient) {
//        stage = new Stage(new ScalingViewport(Scaling.fit, 1920, 1080));
        stage = new Stage(new ExtendViewport(1920, 1080));

        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

//        table.setDebug(true);

        icontex = new Texture(Gdx.files.internal("textures/afterline_icon.png"));
        icontex2 = new Texture(Gdx.files.internal("textures/afterline_icon_2_128.png"));
        icontex.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        icontex2.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        icoimg = new Image(icontex);
        icoimg.setAlign(Align.center);
        icoimg2 = new Image(icontex2);

        icoimg.setScaling(Scaling.fit);
        icoImageCell = table.add(icoimg).size(Value.percentHeight(0.5f, table), Value.percentHeight(0.5f, table)).expand();
        icoImageCell.pad(20);
    }

    @Override
    public void resize(int width, int height) {
//        icoImageCell.width();
        stage.getViewport().update(width, height, true);
    }

    public void flash() {
        t = System.currentTimeMillis();
        t2 = t + 1400;
        icoImageCell.padLeft(0).padTop(0);
        table.padLeft(0).padTop(0);
        icoimg2.setScale(1.0f);
        icoimg2.setAlign(Align.topLeft);
        icoImageCell.setActor(icoimg2).left().top();
        icoImageCell.size(stage.getHeight() / 16, stage.getHeight() / 16);
        table.invalidate();

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        icoimg.setColor(1,1,1,0);
        icoImageCell.getActor().addAction(
                Actions.sequence(Actions.delay(5),
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
                        Actions.scaleTo(1.0f, 1.0f, 0.01f),
                        Actions.run(this::flash)
                )
        );
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        if (t != 0) {
            t = System.currentTimeMillis();
            if (t < t2) {
                float d = ((float) (t2 - t)) / 1400.0f;
                Color c = Color.WHITE.cpy().lerp(AfterlineClient.BACKGROUND, 1.0f - d);
                ScreenUtils.clear(c);
                System.out.println(d + " " + c.toString() + " " + AfterlineClient.BACKGROUND);
            } else {
                ScreenUtils.clear(AfterlineClient.BACKGROUND);
            }
        } else {
            ScreenUtils.clear(AfterlineClient.ICONBG);
        }

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
