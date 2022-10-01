package com.ingeniods.templayer;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import com.github.jknack.handlebars.Context;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.JsonNodeValueResolver;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.context.FieldValueResolver;
import com.github.jknack.handlebars.context.JavaBeanValueResolver;
import com.github.jknack.handlebars.context.MapValueResolver;
import com.github.jknack.handlebars.context.MethodValueResolver;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;

public final class TemplateApplyer implements TemplateEngine {

    private final Handlebars handlebars;

    public TemplateApplyer() {
        TemplateLoader loader = new ClassPathTemplateLoader();
        loader.setPrefix("/templates");
        loader.setSuffix(".tpl");
        handlebars = CustomHandlebarsHelper.instance(loader);
    }

    @Override
    public String generate(String templateName, Object value) {
        try {
            Context context = Context
                    .newBuilder(value)
                    .resolver(JsonNodeValueResolver.INSTANCE,
                            JavaBeanValueResolver.INSTANCE,
                            FieldValueResolver.INSTANCE,
                            MapValueResolver.INSTANCE,
                            MethodValueResolver.INSTANCE
                    )
                    .build();
            return handlebars.compile(templateName).apply(context);
        } catch (IOException e) {
            throw new RuntimeException("Cant apply template:" + templateName, e);
        }
    }

}