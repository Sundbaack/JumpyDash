package main.java.org.chalmers.jumpydash.controller.screen;


import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import main.java.org.chalmers.jumpydash.controller.Options;
import main.java.org.chalmers.jumpydash.view.screen.OptionView;


/**
 * Created by alexsundback on 2016-05-25.
 */
public class OptionScreen extends JDScreen {

    private Stage stage;
    private Stage uiStage;
    private OptionView optionView;

    public OptionScreen(Stage stage, Stage uiStage) {
        this.stage = stage;
        this.uiStage = uiStage;
        optionView = new OptionView(uiStage);

        setListeners();

        // Check if sound and/or music is muted
        if (!Options.getInstance().getSound()) {
            optionView.getMuteSoundButton().setText("Unmute sound");
        }
        if (!Options.getInstance().getMusic()) {
            optionView.getMuteMusicButton().setText("Unmute music");
        }
    }

    private void setListeners() {
        optionView.getMenuButton().addListener(new ClickListener() {
           public void clicked(InputEvent event, float x, float y) {
               // Switch to game
               ScreenManager.getInstance().initMenu(stage, uiStage);
           }
        });

        optionView.getMuteSoundButton().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                // Mute sound
                if (Options.getInstance().getSound()) {
                    optionView.getMuteSoundButton().setText("Unmute sound");
                    Options.getInstance().setSound(false);
                } else {
                    optionView.getMuteSoundButton().setText("Mute sound");
                    Options.getInstance().setSound(true);
                }
            }
        });

        optionView.getMuteMusicButton().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                // Mute music
                if (Options.getInstance().getMusic()) {
                    optionView.getMuteMusicButton().setText("Unmute music");
                    Options.getInstance().setMusic(false);
                } else {
                    optionView.getMuteMusicButton().setText("Mute music");
                    Options.getInstance().setMusic(true);
                }
            }
        });
    }

    @Override
    public void render(float delta) {
        optionView.update();
    }

    @Override
    public void dispose() {
        stage.dispose();
        uiStage.dispose();
    }
}
