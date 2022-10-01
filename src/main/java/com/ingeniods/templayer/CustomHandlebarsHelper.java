package com.ingeniods.templayer;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Options;
import com.github.jknack.handlebars.io.TemplateLoader;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

public final class CustomHandlebarsHelper {
	
	private CustomHandlebarsHelper() {}

	private static String toLower(String texto) {
		if (null == texto) {
			return "";
		}
		return texto.toLowerCase();
	}

	private static CharSequence isNotEmpty(Collection<Object> context, Options options) throws IOException {
		Collection<Object> collection = Optional.ofNullable(context).orElse(Collections.emptyList());
		if(collection.isEmpty()) {
			return "";
		}
		return options.fn();
	}

	private static CharSequence isEquals(Object context, Options options) throws IOException {
		if(context.getClass() != options.params[0].getClass()) {
			return options.inverse();
		}
		if(!context.equals(options.params[0])) {
			return options.inverse();
		}
		return options.fn();
	}

	private static String toUpper(String texto) {
		if (null == texto) {
			return "";
		}
		return texto.toUpperCase();
	}

	private static String upperfy(String value) {
		return String.valueOf(value.charAt(0)).toUpperCase() + value.substring(1);
	}

	private static String lowerfy(String value) {
		return String.valueOf(value.charAt(0)).toLowerCase() + value.substring(1);
	}
	public static Handlebars instance(TemplateLoader loader) {
		Handlebars handlebars = new Handlebars(loader);
		handlebars.registerHelper("tolower", (String context, Options options) -> toLower(context));
		handlebars.registerHelper("upper", (String context, Options options) ->  toUpper(context));
		handlebars.registerHelper("lowerfy", (String context, Options options) -> lowerfy(context));
		handlebars.registerHelper("upperfy", (String context, Options options) -> upperfy(context));
		handlebars.registerHelper("equals", CustomHandlebarsHelper::isEquals);
		handlebars.registerHelper("isNotEmpty",  CustomHandlebarsHelper::isNotEmpty);
		return handlebars;
	}
}
