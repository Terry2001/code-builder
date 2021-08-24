package link.biu.codebuilder;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.codeInsight.intention.PsiElementBaseIntentionAction;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

public class TestIntention extends PsiElementBaseIntentionAction implements IntentionAction {
    @Override
    public void invoke(@NotNull Project project, Editor editor, @NotNull PsiElement psiElement) throws IncorrectOperationException {

//        int lineNumber = editor.getDocument().getLineNumber(editor.getCaretModel().getOffset());
//
//        String text = editor.getDocument().getText(new TextRange(
//                editor.getDocument().getLineStartOffset(lineNumber),
//                editor.getDocument().getLineEndOffset(lineNumber)
//        ));
//
//        WriteCommandAction.runWriteCommandAction(project, () ->
//                editor.getDocument().replaceString(
//                        editor.getDocument().getLineStartOffset(lineNumber),
//                        editor.getDocument().getLineEndOffset(lineNumber),
//                        String.format("\"%s\"", text)
//                )
//        );

        PsiMethod containingMethod = PsiTreeUtil.getParentOfType(psiElement, PsiMethod.class);
        if (containingMethod != null) {
            int textOffset = containingMethod.getStartOffsetInParent();
            int textLength = containingMethod.getTextLength();

            WriteCommandAction.runWriteCommandAction(project, () ->
                    editor.getSelectionModel().setSelection(textOffset, textOffset + textLength)
            );
        }
    }

    @Override
    public boolean isAvailable(@NotNull Project project, Editor editor, @NotNull PsiElement element) {


//        VirtualFile projectFile = project.getProjectFile();
//        System.out.println(projectFile.getCanonicalPath());
//
//        String projectFilePath = project.getProjectFilePath();
//        System.out.println(projectFilePath);
//
//        String lang = element.getLanguage().getID();
//        System.out.println(lang);
//
//        if (lang.equals("JAVA")) {
//            int lineNumber = editor.getDocument().getLineNumber(editor.getCaretModel().getOffset());
//            String text = editor.getDocument().getText(new TextRange(
//                    editor.getDocument().getLineStartOffset(lineNumber),
//                    editor.getDocument().getLineEndOffset(lineNumber)
//            ));
//
//            boolean matches = Pattern.compile("^\\d+$").matcher(text).matches();
//            return matches;
//        }
//
//        return false;

        return true;
    }

    @Nls(capitalization = Nls.Capitalization.Sentence)
    @NotNull
    @Override
    public String getFamilyName() {
        return "my intention test";
    }

    @NotNull
    @Override
    public String getText() {
        return "test intention";
    }

    @Override
    public boolean startInWriteAction() {
        return false;
    }
}