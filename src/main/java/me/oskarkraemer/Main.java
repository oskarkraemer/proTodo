package me.oskarkraemer;

import com.formdev.flatlaf.FlatDarculaLaf;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        FlatDarculaLaf.setup();
        UIManager.getLookAndFeelDefaults().put("defaultFont", new Font("Consolas", Font.PLAIN, 12));

        new MainUI();
    }
}