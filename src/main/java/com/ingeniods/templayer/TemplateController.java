package com.ingeniods.templayer;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/template/")
public class TemplateController {
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@PostMapping("/apply/{templateName}")
	public String generate(@PathVariable String templateName, @RequestBody String value) throws JsonProcessingException {
		try{
			JsonNode jsonNode = new ObjectMapper().readValue(value, JsonNode.class);
			return templateEngine.generate(templateName, jsonNode);
		}catch(JsonParseException e){}
		return templateEngine.generate(templateName, value);
	}
	
}
