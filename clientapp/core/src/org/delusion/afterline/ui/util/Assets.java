package org.delusion.afterline.ui.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import org.delusion.afterline.ui.util.FontBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Assets {

    private Map<Class<?>, Map<String, Object>> map = new HashMap<>();

    public Assets() {}

    public <T> void addAsset(String name, T o) {
        Class<T> cls = (Class<T>) o.getClass();
        if (!map.containsKey(cls)) { map.put(cls, new HashMap<>()); }
        map.get(cls).put(name, o);
    }

    public void addFont(String name, Color color, int size, String freetypeFont) {
        FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();
        param.color = color;
        param.size = size;

        FreeTypeFontGenerator gen = getAsset(freetypeFont, FreeTypeFontGenerator.class);

        BitmapFont fnt = gen.generateFont(param);
        fnt.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        addAsset(name, fnt);
    }

    public void addFont(String name, String freetypeFont, FontBuilder fb) {
        FreeTypeFontGenerator gen = getAsset(freetypeFont, FreeTypeFontGenerator.class);
        BitmapFont fnt = gen.generateFont(fb.build());
        fnt.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        addAsset(name, fnt);
    }

    public void addFreetypeFont(String name, FileHandle file) {
        addAsset(name, new FreeTypeFontGenerator(file));

    }

    public void addTexture(String name, FileHandle file) {
        addAsset(name, new Texture(file));
    }

    public <T> T getAsset(String name, Class<T> cls) {
        return (T) (map.get(cls).getOrDefault(name, null));
    }
}
