package link.biu.codebuilder.service;

import com.intellij.openapi.components.ServiceManager;
import freemarker.cache.StringTemplateLoader;
import org.jetbrains.annotations.Nullable;

import freemarker.template.*;
import java.util.*;
import java.io.*;

public class TemplateService {

    private Configuration cfg;

    public TemplateService() {

        cfg = new Configuration(Configuration.VERSION_2_3_31);

        // Recommended settings for new projects:
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);
        cfg.setFallbackOnNullLoopVariable(false);

        StringTemplateLoader loader = new StringTemplateLoader();
        cfg.setTemplateLoader(loader);

        loader.putTemplate("model", "public class ${name}");
    }

    public String generateCode(String name, Map<String, Object> map) throws IOException, TemplateException {

        Template temp = cfg.getTemplate(name);

        Writer out = new StringWriter();
        temp.process(map, out);

        return out.toString();
    }

    @Nullable
    public static TemplateService getInstance() {
        return ServiceManager.getService(TemplateService.class);
    }

}
