package org.delusion.afterline.ui.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
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
        assets.addTexture("splash.afterlineicon", Gdx.files.internal("textures/splash/afterline_icon.png"));

        // Font Generators
        assets.addFreetypeFont("font.default.family", Gdx.files.internal("fonts/roboto/Roboto-Regular.ttf"));

        // Fonts
        assets.addFont("font.default.24", Color.BLACK, 24, "font.default.family");
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
