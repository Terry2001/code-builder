package link.biu.codebuilder.settings;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import lombok.Data;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * 保存配置中填写的各个参数值，并持久化
 */
@State(name = "codebuildersettingform", storages = {@Storage("codebuildersetting.xml")})
@Data
public class SettingValue implements PersistentStateComponent<SettingValue> {

    /**
     * mapper relative path
     */
    public String mapperRelativePath;


    @Override
    public SettingValue getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull SettingValue state) {
        XmlSerializerUtil.copyBean(state, this);
    }

    @Nullable
    public static SettingValue getInstance() {
        return ServiceManager.getService(SettingValue.class);
    }
}
