package org.delusion.afterline.ui.util;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PixmapPacker;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class FontBuilder {
    private FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();

    public FontBuilder() {
    }

    public FontBuilder size(int size) {
        param.size = size;
        return this;
    }

    public FontBuilder fontSmoothing(boolean enable) {
        param.mono = !enable;
        return this;
    }

    public FontBuilder hinting(FreeTypeFontGenerator.Hinting hinting) {
        param.hinting = hinting;
        return this;
    }

    public FontBuilder color(Color color) {
        param.color = color;
        return this;
    }

    public FontBuilder gamma(float gamma) {
        param.gamma = gamma;
        return this;
    }

    public FontBuilder renderCount(int cnt) {
        param.renderCount = cnt;
        return this;
    }

    public FontBuilder borderWidth(float bw) {
        param.borderWidth = bw;
        return this;
    }

    public FontBuilder borderStraight(boolean bs) {
        param.borderStraight = bs;
        return this;
    }

    public FontBuilder borderGamma(float bg) {
        param.borderGamma = bg;
        return this;
    }

    public FontBuilder shadowOffsetX(int sox) {
        param.shadowOffsetX = sox;
        return this;
    }

    public FontBuilder shadowOffsetY(int soy) {
        param.shadowOffsetY = soy;
        return this;
    }

    public FontBuilder shadowColor(Color sc) {
        param.shadowColor = sc;
        return this;
    }

    public FontBuilder spaceX(int spaceX) {
        param.spaceX = spaceX;
        return this;
    }

    public FontBuilder spaceY(int spaceY) {
        param.spaceY = spaceY;
        return this;
    }

    public FontBuilder padTop(int padTop) {
        param.padTop = padTop;
        return this;
    }

    public FontBuilder padLeft(int padLeft) {
        param.padLeft = padLeft;
        return this;
    }

    public FontBuilder padBottom(int padBottom) {
        param.padBottom = padBottom;
        return this;
    }

    public FontBuilder padRight(int padRight) {
        param.padRight = padRight;
        return this;
    }

    public FontBuilder characters(String chars) {
        param.characters = chars;
        return this;
    }

    public FontBuilder kerning(boolean kerning) {
        param.kerning = kerning;
        return this;
    }

    public FontBuilder packer(PixmapPacker packer) {
        param.packer = packer;
        return this;
    }

    public FontBuilder flip(boolean flip) {
        param.flip = flip;
        return this;
    }

    public FontBuilder genMipMaps(boolean genMipMaps) {
        param.genMipMaps = genMipMaps;
        return this;
    }

    public FontBuilder minFilter(Texture.TextureFilter minFilter) {
        param.minFilter = minFilter;
        return this;
    }

    public FontBuilder magFilter(Texture.TextureFilter magFilter) {
        param.magFilter = magFilter;
        return this;
    }

    public FreeTypeFontGenerator.FreeTypeFontParameter build() {
        return param;
    }
}
