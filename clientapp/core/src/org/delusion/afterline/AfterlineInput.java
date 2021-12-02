package org.delusion.afterline;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;

public class AfterlineInput extends InputAdapter {
    private AfterlineClient app;

    public AfterlineInput(AfterlineClient app) {
        this.app = app;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.SPACE) {
            app.requestColor();
        }
        return super.keyDown(keycode);
    }
}
