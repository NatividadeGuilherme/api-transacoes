package br.com.natividade.apitransacoes.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.plugin.core.SimplePluginRegistry;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build()
				.globalResponseMessage(RequestMethod.GET, responseMessageForGET())
				.globalResponseMessage(RequestMethod.POST, responseMessageForPOST())
		.globalResponseMessage(RequestMethod.DELETE, responseMessageForDELETE());
	}

	@Primary
	@Bean
	public LinkDiscoverers discoverers() {
		List<LinkDiscoverer> plugins = new ArrayList<>();
		plugins.add(new CollectionJsonLinkDiscoverer());
		return new LinkDiscoverers(SimplePluginRegistry.create(plugins));
	}

	private List<ResponseMessage> responseMessageForPOST() {
		return new ArrayList<ResponseMessage>() {
			{
				add(new ResponseMessageBuilder().code(400).message("Bad request!").build());
				add(new ResponseMessageBuilder().code(422).message("UNPROCESSABLE_ENTITY!").build());
			}
		};
	}

	private List<ResponseMessage> responseMessageForGET() {
		return new ArrayList<ResponseMessage>() {
			{
				add(new ResponseMessageBuilder().code(404).message("Not found!").build());
				add(new ResponseMessageBuilder().code(422).message("UNPROCESSABLE_ENTITY!").build());
			}
		};
	}
	
	private List<ResponseMessage> responseMessageForDELETE() {
		return new ArrayList<ResponseMessage>() {
			{
				add(new ResponseMessageBuilder().code(404).message("Not found!").build());
			}
		};
	}
}
