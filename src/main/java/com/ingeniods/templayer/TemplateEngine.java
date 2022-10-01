package com.ingeniods.templayer;

@FunctionalInterface
public interface TemplateEngine {
		
	public String generate(String templateName, Object value);

}
