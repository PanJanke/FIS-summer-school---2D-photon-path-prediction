package pl.jano.Window;

import javax.swing.*;


public class CartesianFrame extends JFrame {
    public CartesianPanel panel;

    public CartesianFrame() {
        panel = new CartesianPanel();
        add(panel);
    }

    public void showUI(String name) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(name);
        setSize(800, 800);
        setVisible(true);
    }
}