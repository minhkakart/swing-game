package com.minhkakart.swinggame.ui;

import com.minhkakart.swinggame.enums.ButtonType;
import com.minhkakart.swinggame.manager.ResourceManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameButton extends JButton {
    private boolean isClicked = false;

    private final ButtonType buttonType;

    private static final ImageIcon border0 = new ImageIcon(ResourceManager.getImagePath("border-0.png"));
    private static final ImageIcon border1 = new ImageIcon(ResourceManager.getImagePath("border-1.png"));
    private static final ImageIcon border2 = new ImageIcon(ResourceManager.getImagePath("border-2.png"));
    private static final ImageIcon border3 = new ImageIcon(ResourceManager.getImagePath("border-3.png"));

    private static final Color loginDefaultColor = new Color(70, 20, 0);
    private static final Color loginLightColor = new Color(97, 32, 0);

    public GameButton(String text, ButtonType buttonType) {
        super(text);

        this.buttonType = buttonType;

        setOpaque(true);
        setBorder(null);
        setMargin(null);
        setBackground(null);
        setForeground(Color.WHITE);
        setFocusable(false);

        setContentAreaFilled(false);

        setSize(getPreferredSizeByType());

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                isClicked = true;
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                isClicked = false;
                repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        // Draw background
        g2d.setColor(loginDefaultColor);
        if (isClicked) {
            g2d.setColor(loginLightColor);
        }
        // Background
        g2d.fillRect(1, 1, getWidth() - 2, getHeight() - 2);

        /* Draw border */
        // Border horizontal lines
        for (int i = 0; i < Math.ceil((double) (getWidth() - border0.getIconWidth() * 2) / border2.getIconWidth()); i++) {
            g2d.drawImage(border2.getImage(), i * border2.getIconWidth() + border0.getIconWidth(), 1, border2.getIconWidth(), border2.getIconHeight(), null);
            g2d.drawImage(border2.getImage(), i * border2.getIconWidth() + border0.getIconWidth(), getHeight() - border2.getIconHeight(), border2.getIconWidth(), border2.getIconHeight(), null);
        }
        // Border vertical lines
        for (int i = 0; i < Math.ceil((double) (getHeight() - border0.getIconHeight() * 2) / border2.getIconWidth()); i++) {
            g2d.drawImage(border3.getImage(), 1, i * border3.getIconHeight() + border0.getIconHeight(), border3.getIconWidth() + 1, (i + 1) * border3.getIconHeight() + border0.getIconHeight(), border3.getIconWidth(), 0, 0, border3.getIconHeight(), null);
            g2d.drawImage(border3.getImage(), getWidth() - border3.getIconWidth() - 1, i * border3.getIconHeight() + border0.getIconHeight(), getWidth() - 1, (i + 1) * border3.getIconHeight() + border0.getIconHeight(), 0, 0, border3.getIconWidth(), border3.getIconHeight(), null);
        }
        g2d.drawImage(border1.getImage(), (getWidth() - border1.getIconWidth()) / 2, 0, border1.getIconWidth(), border1.getIconHeight(), null);

        // Top left
        g2d.drawImage(border0.getImage(), 0, 0, border0.getIconWidth(), border0.getIconHeight(), null);
        // Top right
        g2d.drawImage(border0.getImage(), getWidth(), 0, getWidth() - border0.getIconWidth(), border0.getIconHeight(), 0, 0, border0.getIconWidth(), border0.getIconHeight(), null);
        // Bottom left
        g2d.drawImage(border0.getImage(), 0, getHeight(), border0.getIconWidth(), getHeight() - border0.getIconHeight(), 0, 0, border0.getIconWidth(), border0.getIconHeight(), null);
        // Bottom right
        g2d.drawImage(border0.getImage(), getWidth(), getHeight(), getWidth() - border0.getIconWidth(), getHeight() - border0.getIconHeight(), 0, 0, border0.getIconWidth(), border0.getIconHeight(), null);

        // Draw text
        g2d.setColor(Color.WHITE);
        int textWidth = g2d.getFontMetrics().stringWidth(getText());
        int textHeight = g2d.getFontMetrics().getHeight();
        g2d.drawString(getText(), (getWidth() - textWidth) / 2, textHeight + (getHeight() - textHeight) / 3);
    }

    private Dimension getPreferredSizeByType() {
        Dimension dimension = null;
        switch (buttonType) {
            case LOGIN:
                dimension = new Dimension(200, 40);
                break;
            case MENU:
                dimension = new Dimension(80, 80);
                break;
            default:
                dimension = new Dimension(100, 30);
        }
        return dimension;
    }

}
