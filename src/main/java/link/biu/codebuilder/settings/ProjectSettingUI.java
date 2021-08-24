package link.biu.codebuilder.settings;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.swing.*;

/**
 * 参数设置界面的 UI 类
 *
 */
@Data
@NoArgsConstructor
public class ProjectSettingUI {
    private JPanel panel;
    private JTextField mapperRelativePath;


    private SettingValue settingValue;

    ProjectSettingUI(SettingValue settingValue) {
        this.settingValue = settingValue;
    }

    private void setShowParams() {
        mapperRelativePath.setText(settingValue.getMapperRelativePath());
    }

    JPanel getRootPanel() {
        setShowParams();
        return panel;
    }

}
