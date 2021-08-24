package link.biu.codebuilder.settings;

import com.intellij.openapi.options.SearchableConfigurable;
import lombok.Data;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Objects;

/**
 * 参数设置管理类，用于在设置中显示、编辑 和 保存 配置信息
 *
 */
@Data
public class ProjectSettingConfig implements SearchableConfigurable {

    ProjectSettingUI mainGui;

    private SettingValue settingValue;

    public ProjectSettingConfig() {
        settingValue = SettingValue.getInstance();
    }

    @NotNull
    @Override
    public String getId() {
        return getDisplayName();
    }

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return "Code Builder";
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        mainGui = new ProjectSettingUI(settingValue);
        return mainGui.getRootPanel();
    }

    @Nullable
    @Override
    public String getHelpTopic() {
        return null;
    }

    @Override
    public boolean isModified() {
        return ifEdited();
    }

    @Nullable
    @Override
    public Runnable enableSearch(String option) {
        return null;
    }

    @Override
    public void disposeUIResources() {
        mainGui = null;
    }

    @Override
    public void reset() {
        mainGui.getMapperRelativePath().setText(settingValue.getMapperRelativePath());
    }

    @Override
    public void apply() {
        settingValue.setMapperRelativePath(mainGui.getMapperRelativePath().getText());
    }

    private boolean ifEdited() {

        return !Objects.equals(settingValue.getMapperRelativePath(), mainGui.getMapperRelativePath().getText());
    }
}
