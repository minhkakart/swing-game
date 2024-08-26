package com.minhkakart.swinggame.supports;

import java.awt.*;

public class CenterBox implements LayoutManager {
    private int gap;

    public CenterBox() {
        gap = 0;
    }

    public CenterBox(int gap) {
        this.gap = gap;
    }

    public int getGap() {
        return gap;
    }

    public void setGap(int gap) {
        this.gap = gap;
    }

    @Override
    public void addLayoutComponent(String name, Component comp) {
        System.out.println("CenterBox addLayoutComponent");
    }

    @Override
    public void removeLayoutComponent(Component comp) {
        System.out.println("CenterBox removeLayoutComponent");

    }

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        System.out.println("CenterBox preferredLayoutSize");
        return null;
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        System.out.println("CenterBox minimumLayoutSize");
        return null;
    }

    @Override
    public void layoutContainer(Container parent) {
        synchronized (parent.getTreeLock()) {
            int parentHeight = parent.getHeight();
            int mComponents = parent.getComponentCount();

            int sumComponentHeight = 0;
            for (int i = 0; i < mComponents; i++) {
                sumComponentHeight += parent.getComponent(i).getHeight();
            }

            int componentHeight = -1;
            if (sumComponentHeight + gap * (mComponents + 1) > parentHeight) {
                componentHeight = (parentHeight - gap * (mComponents + 1)) / mComponents;
            }

            if (componentHeight != -1) {
                for (int i = 0; i < mComponents; i++) {
                    Component component = parent.getComponent(i);
                    component.setSize(component.getWidth(), componentHeight);
                }
            }

            centerComponent(parent);

        }
    }

    private void centerComponent(Container parent) {
        int pw = parent.getWidth();
        int ph = parent.getHeight();
        int mComponents = parent.getComponentCount();

        int sumComponentHeight = 0;
        for (int i = 0; i < mComponents; i++) {
            sumComponentHeight += parent.getComponent(i).getHeight();
        }

        int startY = (ph - (sumComponentHeight + gap * (mComponents + 1))) / 2;

        for (int i = 0; i < mComponents; i++) {
            Component component = parent.getComponent(i);
            int xPos = (pw - component.getWidth()) / 2;
            int yPos = startY + gap;
            component.setLocation(xPos, yPos);

            startY += component.getHeight() + gap;
        }

    }
}
