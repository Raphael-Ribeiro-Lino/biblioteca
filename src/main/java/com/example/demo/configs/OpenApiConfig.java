package com.example.demo.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI springShopOpenAPI() {
		OpenAPI openApi = new OpenAPI();
		Info info = new Info();
		info.title("Acelera G&P");
		info.version("v.0.0.1");
		info.description("<h2>Descreve a documentação do sistema de biblioteca</h2>");
		openApi.info(info);
		openApi.addTagsItem(new Tag().name("Autor").description("Gerencia os autores do sistema"));
		openApi.addTagsItem(new Tag().name("Livro").description("Gerencia os livros do sistema"));
		
		return openApi;
	}
}
