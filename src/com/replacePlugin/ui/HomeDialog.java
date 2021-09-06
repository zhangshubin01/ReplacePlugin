package com.replacePlugin.ui;


import com.github.better.replaceId.IdConfiguration;
import com.github.better.replaceId.folder.LayoutReplace;
import com.intellij.openapi.project.Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class HomeDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JEditorPane formattedTextField1;
    private JLabel Label;

    private String srcPath;
    private String resPath;
    private Project project;

    public HomeDialog(String srcPath, String resPath,Project project) {
        this.srcPath = srcPath;
        this.resPath = resPath;
        this.project = project;
        setContentPane(contentPane);
        setModal(true);
        setTitle("修改 布局id ");
        getRootPane().setDefaultButton(buttonOK);
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // 单击 X 时调用 onCancel()
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // 遇到 ESCAPE 时调用 onCancel()
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        setVisible(false);
        // 在此处添加您的代码
        String str = formattedTextField1.getText();
        IdConfiguration workConfig = new IdConfiguration(resPath, srcPath, str);
        try {
            new LayoutReplace(workConfig).replaceThis();
        } catch (IOException e) {
            e.printStackTrace();
        }
        project.getBaseDir().refresh(false, true);
        dispose();
    }

    private void onCancel() {
        // 必要时在此处添加您的代码
        setVisible(false);
    }

    public static void main(String[] args) {
        HomeDialog dialog = new HomeDialog("", "", null);
        dialog.setMinimumSize(new Dimension(800,300));

        dialog.setVisible(true);
        System.exit(0);
    }
}
