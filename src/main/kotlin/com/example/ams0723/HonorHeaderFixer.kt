package com.example.ams0723

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.ui.Messages
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiRecursiveElementWalkingVisitor
import com.intellij.psi.XmlElementFactoryImpl
import com.intellij.psi.html.HtmlTag
import com.intellij.psi.impl.source.tree.HtmlFileElement
import com.intellij.psi.impl.source.xml.XmlTokenImpl
import com.intellij.psi.util.elementType
import com.intellij.psi.xml.XmlElementType.HTML_DOCUMENT
import com.intellij.psi.xml.XmlElementType.HTML_TAG
import com.intellij.psi.xml.XmlText
import com.intellij.psi.xml.XmlToken
import com.intellij.psi.xml.XmlTokenType
import com.intellij.xml.util.XmlTagUtil
import com.intellij.xml.util.XmlUtil


class HonorHeaderFixer : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        // TODO: insert action logic here
        val project = e.getData(PlatformDataKeys.PROJECT)

        val selectedEditor =  FileEditorManager.getInstance(project!!).getSelectedTextEditor()
        val psi_file = PsiDocumentManager.getInstance(project!!).getPsiFile(selectedEditor!!.getDocument())

        System.out.println(" Information ")


        val mHtmlFileElement:HtmlFileElement
        val mHtmlTag:HtmlTag
        val mXmlToken:XmlToken
        val mXmlText:XmlText



        psi_file?.accept(object : PsiRecursiveElementWalkingVisitor() { // visitor implementation ...
            override fun visitElement(element: PsiElement) {
                super.visitElement(element)


               // System.out.println("----****************--------"  + element.elementType+ " \n " + element.getText() + " \n ")

                if (element.elementType === HTML_DOCUMENT) {
                    val txt =  element.getText()
                  Messages.showMessageDialog(
                        project,
                        "Hi, 修改文本." + "\n" + txt,
                        "HTML_DOCUMENT",
                        Messages.getInformationIcon())

                    System.out.println("----****************-------- VisitElement: " + " \n " + element.toString() + " \n " + element.getText() + " \n ")
                    //element.add(XmlTokenImpl(XmlTokenType.XML_NAME, "h1") )
                    val fac: XmlElementFactoryImpl= XmlElementFactoryImpl(project)
                    //element.add(fac.createTagFromText("<a></a>", element.language))


                    WriteCommandAction.runWriteCommandAction(project, Runnable {
                        val fac: XmlElementFactoryImpl= XmlElementFactoryImpl(project)
                        element.add(fac.createTagFromText("<a></a>", element.language))
                    })

                    //element.replace(GoElementFactory.createExpression(project, element.getText() + "()") )
                }


                if (element.elementType === HTML_TAG) {

                    val txt =  element.getText()
                    Messages.showMessageDialog(
                        project,
                        "Hi, 修改文本." + "\n" + txt,
                        "HTML_TAG",
                        Messages.getInformationIcon())


                }
                System.out.println("------------ VisitElement: " + " \n " + element.toString() + " \n " + element.getText() + " \n ")


//                if (element.elementType === XmlTokenType.XML_NAME) {
//                    val txt =  element.getText()
//                    Messages.showMessageDialog(
//                        project,
//                        "Hi, 修改文本." + "\n" + txt,
//                        "HTML_TAG",
//                        Messages.getInformationIcon())
//                    val xmlToken = element as XmlTokenImpl
//                    xmlToken.replace(XmlTokenImpl(XmlTokenType.XML_NAME, "h1"))
//                }




            }

            override fun visitFile(file: PsiFile) {
                super.visitFile(file)
                System.out.println("------------ VisitFile: " + " \n " +  file.toString() + " \n " + file.name + " \n ")

            }

            override fun elementFinished(element: PsiElement?) {
                super.elementFinished(element)
                System.out.println("------------ ElementFinished: " + " \n " +  element.toString() + " \n " + element.elementType + " \n ")
            }

            override fun stopWalking() {
                super.stopWalking()
                System.out.println("stopWalking")
            }
        })


    }


}
