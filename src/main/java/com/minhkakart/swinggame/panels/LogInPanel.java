package com.minhkakart.swinggame.panels;

import com.minhkakart.swinggame.MainApplication;
import com.minhkakart.swinggame.enums.ButtonType;
import com.minhkakart.swinggame.supports.CenterBox;
import com.minhkakart.swinggame.ui.GameButton;

import javax.swing.*;
import java.awt.*;

public class LogInPanel extends JPanel {
    private final GameButton newGameButton;
    private final GameButton continueButton;
    private final GameButton exitButton;
    private final GameButton changeBackground;

    public LogInPanel() {
        setOpaque(true);
        setBackground(new Color(0, 0, 0, 0f));
        setPreferredSize(new Dimension(MainApplication.WIDTH, MainApplication.HEIGHT));

        newGameButton = new GameButton("New Game", ButtonType.LOGIN);
        continueButton = new GameButton("Continue", ButtonType.LOGIN);
        exitButton = new GameButton("Exit", ButtonType.LOGIN);
        changeBackground = new GameButton("Change background", ButtonType.LOGIN);

        setLayout(new CenterBox(10));

        newGameButton.setBounds(0, 0, newGameButton.getWidth(), newGameButton.getHeight());
//        add(newGameButton);
//        add(continueButton);
        add(exitButton);
        add(changeBackground);

    }

    public GameButton getNewGameButton() {
        return newGameButton;
    }

    public GameButton getContinueButton() {
        return continueButton;
    }

    public GameButton getExitButton() {
        return exitButton;
    }

    public GameButton getChangeBackground() {
        return changeBackground;
    }
}
