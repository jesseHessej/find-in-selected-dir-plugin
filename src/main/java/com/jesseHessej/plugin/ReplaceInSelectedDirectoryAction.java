package com.jesseHessej.plugin;

import com.intellij.find.FindManager;
import com.intellij.find.FindModel;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

public class ReplaceInSelectedDirectoryAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Project project = e.getProject();
        if (project == null) return;

        VirtualFile[] files = e.getData(CommonDataKeys.VIRTUAL_FILE_ARRAY);
        if (files == null || files.length == 0) return;

        String targetPath = files[0].isDirectory()
                ? files[0].getPath()
                : files[0].getParent().getPath();

        FindManager findManager = FindManager.getInstance(project);
        FindModel findModel = (FindModel) findManager.getFindInProjectModel().clone();

        // 设置为替换状态
        findModel.setReplaceState(true);

        findModel.setDirectoryName(targetPath);
        findModel.setProjectScope(false);
        findModel.setCustomScope(false);
        findModel.setSearchInProjectFiles(false);

        // 弹出“替换”对话框
        findManager.showFindDialog(findModel, () -> {
        });
    }
}