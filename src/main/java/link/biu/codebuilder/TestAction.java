package link.biu.codebuilder;

import com.intellij.codeInsight.actions.ReformatCodeProcessor;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.util.PsiTreeUtil;

public class TestAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        Editor editor = anActionEvent.getData(CommonDataKeys.EDITOR);
        Project project = editor.getProject();
        PsiFile psiFile = anActionEvent.getData(CommonDataKeys.PSI_FILE);

        int offset = editor.getCaretModel().getOffset();
        PsiElement psiElement = psiFile.findElementAt(offset);

        PsiMethod containingMethod = PsiTreeUtil.getParentOfType(psiElement, PsiMethod.class);
        if (containingMethod != null) {
            TextRange textRange = containingMethod.getTextRange();

            WriteCommandAction.runWriteCommandAction(project, () -> {
                        editor.getSelectionModel().setSelection(textRange.getStartOffset(), textRange.getEndOffset());
                    }
            );

            new ReformatCodeProcessor(psiFile, editor.getSelectionModel()).run();

        }

    }

    @Override
    public void update(AnActionEvent e) {
        Editor editor = e.getData(CommonDataKeys.EDITOR);
        PsiFile psiFile = e.getData(CommonDataKeys.PSI_FILE);
        e.getPresentation().setEnabled(editor != null && psiFile != null);
    }

}
