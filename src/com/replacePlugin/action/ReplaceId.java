package com.replacePlugin.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.replacePlugin.ui.HomeDialog;

import java.awt.*;
import java.io.File;

public class ReplaceId extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = e.getData(PlatformDataKeys.PROJECT);

        String srcPath = project.getBasePath() + File.separator +
                "app" + File.separator +
                "src" + File.separator +
                "main" + File.separator +
                "java" + File.separator;
        String resPath = project.getBasePath() + File.separator +
                "app" + File.separator +
                "src" + File.separator +
                "main" + File.separator +
                "res" + File.separator ;
        HomeDialog dialog = new HomeDialog(srcPath, resPath,project);
        dialog.setMinimumSize(new Dimension(800,300));
        dialog.setVisible(true);

    }
}
