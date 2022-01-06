package org.delusion.afterline.ui.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Skins {
    private static Assets assets;

    public static Skin mainSkin;

    public static void init() {
        assets = new Assets();

        initAssets();
        initSkins();

    }

    private static void initAssets() {
        // Textures

        assets.addTexture("menu.afterlineicon", Gdx.files.internal("textures/menu/afterline_icon_2_128.png"));
        assets.addTexture("menu.cursor.i", Gdx.files.internal("textures/menu/i_cursor.png"));
        assets.addTexture("menu.selection.color", Gdx.files.internal("textures/menu/selection_color.png"));
        assets.addTexture("splash.afterlineicon", Gdx.files.internal("textures/splash/afterline_icon.png"));

        // Font Generators
        assets.addFreetypeFont("font.default.family", Gdx.files.internal("fonts/roboto/Roboto-Regular.ttf"));

        // Fonts
        assets.addFont("font.default.24", Color.BLACK, 24, "font.default.family");
        assets.addFont("font.default.16", Color.BLACK, 16, "font.default.family");
        assets.addFont("font.default.16.white", Color.WHITE, 16, "font.default.family");

        // 9-Patches
        assets.addAsset("menu.winbg.9p", new NinePatch(new Texture(Gdx.files.internal("textures/menu/win_bg.png")), 4, 4, 4, 4));
        assets.addAsset("menu.textfield.9p", new NinePatch(new Texture(Gdx.files.internal("textures/menu/textfield_bg.png")), 4, 4, 4, 4));
        assets.addAsset("menu.button.normal.9p", new NinePatch(new Texture(Gdx.files.internal("textures/menu/button_bg_normal.png")), 4, 4, 4, 4));
        assets.addAsset("menu.button.depressed.9p", new NinePatch(new Texture(Gdx.files.internal("textures/menu/button_bg_depressed.png")), 4, 4, 4, 4));
    }

    private static void initSkins() {

    }




    ////////////////////////////////////////////////////
    //                Helper Functions                //
    ////////////////////////////////////////////////////

    public static <T> T getAsset(String name, Class<T> cls) {
        return assets.getAsset(name, cls);
    }

}
