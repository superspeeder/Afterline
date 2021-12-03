package org.delusion.afterline.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
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
import org.delusion.afterline.AfterlineClient;

public class MainMenu extends ScreenAdapter {

    private Cell<Image> icoImageCell;
    private Image icoimg;
    private Stage stage;
    private Table table;
    private Texture icontex;


    public MainMenu(AfterlineClient afterlineClient) {
        stage = new Stage(new ExtendViewport(1920, 1080));

        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        table.setDebug(false);

        icontex = new Texture(Gdx.files.internal("textures/afterline_icon.png"));

        icoimg = new Image(icontex);
        icoimg.setAlign(Align.center);
        icoimg.setScaling(Scaling.fit);
        icoImageCell = table.add(icoimg).size(Value.percentHeight(0.5f, table), Value.percentHeight(0.5f, table));
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
        icoimg.addAction(Actions.sequence(Actions.delay(10), Actions.fadeIn(1.5f)));
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
