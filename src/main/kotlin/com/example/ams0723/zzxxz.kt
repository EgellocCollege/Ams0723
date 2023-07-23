package com.example.ams0723

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages
import com.intellij.pom.Navigatable


class zzxxz : AnAction() {
    override fun actionPerformed(event: AnActionEvent) {
        // TODO: insert action logic here
        // TODO: insert action logic here
        val project = event.getData(PlatformDataKeys.PROJECT)
        // 展示输入框，提示输入名称，并获取名称
        val username = Messages.showInputDialog(project, "你的名称是？", "输入你的名称", Messages.getQuestionIcon())
        // 获取并展示欢迎语
        Messages.showMessageDialog(
            project,
            "Hi, $username!\n Hello world.",
            "Information",
            Messages.getInformationIcon())


        val currentProject: Project? = event.getProject()
        val dlgMsg: StringBuilder = StringBuilder(event.getPresentation().getText() + " Selected!")
        val dlgTitle: String = event.getPresentation().getDescription()
        // If an element is selected in the editor, add info about it.
        // If an element is selected in the editor, add info about it.
        val nav: Navigatable? = event.getData(CommonDataKeys.NAVIGATABLE)
        if (nav != null) {
            dlgMsg.append(String.format("\nSelected Element: %s", nav.toString()))
        }
        Messages.showMessageDialog(currentProject, dlgMsg.toString(), dlgTitle, Messages.getInformationIcon())
    }
}
