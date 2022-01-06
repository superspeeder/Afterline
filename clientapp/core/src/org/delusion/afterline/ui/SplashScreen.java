package org.delusion.afterline.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import org.delusion.afterline.AfterlineClient;
import org.delusion.afterline.net.message.login.FederatedLoginRequest;
import org.delusion.afterline.ui.util.Skins;

import java.util.Objects;

public class SplashScreen extends ScreenAdapter {

    private Texture icontex2;
    private Cell<Image> icoImageCell;
    private Image icoimg;
    private Stage stage;
    private Table table;
    private Texture icontex;
    private Color bg;
    private long t = 0, t2 = 0;
    private Image icoimg2;
    private boolean finishedLoading = false;
    private Label label;
    private Dialog loginDialog;


    public SplashScreen(AfterlineClient afterlineClient) {
        stage = new Stage(new ExtendViewport(1920, 1080));

        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

//        table.setDebug(true);

        icontex = Skins.getAsset("splash.afterlineicon", Texture.class);
        icontex2 = Skins.getAsset("menu.afterlineicon", Texture.class);
        icontex.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        icontex2.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        icoimg = new Image(icontex);
        icoimg.setAlign(Align.center);
        icoimg2 = new Image(icontex2);

        icoimg.setScaling(Scaling.fit);
        icoImageCell = table.add(icoimg).size(Value.percentHeight(0.5f, table), Value.percentHeight(0.5f, table)).expand();
        icoImageCell.pad(20);


        Label.LabelStyle lstyle = new Label.LabelStyle();
        lstyle.font = Skins.getAsset("font.default.24",BitmapFont.class);

        label = new Label("", lstyle);

        stage.addActor(label);
        label.setFillParent(true);
        label.setAlignment(Align.center);
        label.setVisible(false);
//
        Window.WindowStyle ws = new Window.WindowStyle();
        ws.titleFont = Skins.getAsset("font.default.24",BitmapFont.class);
//        Window win = new Window("Create Account", ws);
//        win.setDebug(true,true);
//        win.set
        ws.background = new NinePatchDrawable(Skins.getAsset("menu.winbg.9p", NinePatch.class));

        TextField.TextFieldStyle tfs = new TextField.TextFieldStyle();
        tfs.font = Skins.getAsset("font.default.16",BitmapFont.class);
        tfs.fontColor = Color.BLACK;
        tfs.background = new NinePatchDrawable(Skins.getAsset("menu.textfield.9p", NinePatch.class));
        tfs.cursor = new TextureRegionDrawable(Skins.getAsset("menu.cursor.i", Texture.class));
        tfs.selection = new TextureRegionDrawable(Skins.getAsset("menu.selection.color", Texture.class));
        tfs.focusedFontColor = Color.WHITE;
        tfs.disabledFontColor = Color.GRAY;
        TextField tf = new TextField("text", tfs);
        tf.setBlinkTime(0.5f);
        tf.setTextFieldFilter((textField, c) -> Character.isLowerCase(c) || Character.isDigit(c) || c == '-' || c == '_' || c == '.');
        tf.setMaxLength(24);

        lstyle.font = Skins.getAsset("font.default.16",BitmapFont.class);

        Label l2 = new Label("Enter a username: ", lstyle);

        TextButton.TextButtonStyle tbs = new TextButton.TextButtonStyle();
        tbs.font = Skins.getAsset("font.default.16",BitmapFont.class);
        tbs.up = new NinePatchDrawable(Skins.getAsset("menu.button.normal.9p", NinePatch.class));
        tbs.down = new NinePatchDrawable(Skins.getAsset("menu.button.depressed.9p", NinePatch.class));
        TextButton okBtn = new TextButton("Sign In", tbs);
        okBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                afterlineClient.getNetClient().postMessage(new FederatedLoginRequest(tf.getText()));
                afterlineClient.getContext().beginUserAuth(tf.getText());
                loginDialog.clear();
                loginDialog.remove();
            }
        });

        loginDialog = new Dialog("Login", ws);
        loginDialog.getContentTable().left().padLeft(12).padRight(8).padTop(36).padBottom(12).add(l2);
        loginDialog.getContentTable().right().add(tf).maxHeight(24).fillX().expandX();
        loginDialog.getButtonTable().center().add(okBtn).maxWidth(80).maxHeight(24);
        loginDialog.setMovable(true);
        loginDialog.setResizable(true);
        loginDialog.setModal(true);
        loginDialog.pack();

        loginDialog.setPosition(stage.getWidth() / 2 - loginDialog.getWidth() / 2, stage.getHeight() / 2 - loginDialog.getHeight() / 2);
        loginDialog.setVisible(false);

        loginDialog.getTitleLabel().setAlignment(Align.top);
        loginDialog.getTitleTable().getCell(loginDialog.getTitleLabel()).minHeight(28).padTop(30);


        stage.addActor(loginDialog);
        stage.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if (keycode == Input.Keys.SPACE) {
//                    AfterlineClient.LOGGER.info("Sending loginreq");
//                    afterlineClient.getNetClient().postMessage(new FederatedLoginRequest());
                    return true;
                }

                return super.keyDown(event, keycode);
            }
        });
    }

    @Override
    public void resize(int width, int height) {
//        icoImageCell.width();
        stage.getViewport().update(width, height, true);
    }

    public void flash() {
        t = System.currentTimeMillis();
        t2 = t + 400;
        icoImageCell.padLeft(0).padTop(0);
        table.padLeft(0).padTop(0);
        icoimg2.setScale(1.0f);
        icoimg2.setAlign(Align.topLeft);
        icoImageCell.setActor(icoimg2).left().top();
        icoImageCell.size(stage.getHeight() / 16, stage.getHeight() / 16);
        table.invalidate();
        finishedLoading = true;
        label.setVisible(true);
        loginDialog.setVisible(true);
        loginDialog.show(Objects.requireNonNullElse(loginDialog.getStage(), stage));





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

    public void setText(String data) {
        label.setText(data);
    }
}
