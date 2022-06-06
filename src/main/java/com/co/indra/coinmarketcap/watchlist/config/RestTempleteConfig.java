package com.co.indra.coinmarketcap.watchlist.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTempleteConfig {
@Autowired
	private RestTemplateBuilder builder;

	@Bean
	public RestTemplate defaultRestTemplate() {
		return builder.build();
	}
}
