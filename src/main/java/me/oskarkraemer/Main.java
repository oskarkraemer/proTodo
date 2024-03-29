package me.oskarkraemer;

import com.formdev.flatlaf.FlatDarculaLaf;
import me.oskarkraemer.TodoList.TodoList;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        FlatDarculaLaf.setup();
        UIManager.getLookAndFeelDefaults().put("defaultFont", new Font("Consolas", Font.PLAIN, 12));

        MainUI mainUI = new MainUI();
        UIController controller = new UIController(new ArrayList<>(), mainUI);
    }
}