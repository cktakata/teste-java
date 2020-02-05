package com.example.api.utils;

import java.security.cert.X509Certificate;

import javax.annotation.PostConstruct;
import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.TrustStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class RestServer {

	private static final Logger LOGGER = LoggerFactory.getLogger(RestServer.class);
	
	private RestTemplate restTemplate;
	
	private HttpComponentsClientHttpRequestFactory requestFactory;
	
	public RestServer() {
		this.requestFactory = new HttpComponentsClientHttpRequestFactory();
		this.restTemplate = new RestTemplate(this.requestFactory);
		
		try {
			TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

			SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
			        .loadTrustMaterial(null, acceptingTrustStrategy)
			        .build();

			SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

			CloseableHttpClient httpClient = HttpClients.custom()
			        .setSSLSocketFactory(csf)
			        .build();

			// HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

			requestFactory.setHttpClient(httpClient);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
	
	@PostConstruct
	public void initialize() {

	}
	
	public ResponseEntity getMap( String reqUrl, String method, HttpEntity<?> requestEntity, String JsonData ) {
		ResponseEntity<?> mapResponse;
		if (method.equalsIgnoreCase("POST")) {
			mapResponse = restTemplate.exchange(reqUrl, HttpMethod.POST, requestEntity, String.class, JsonData);
		} else if (method.equalsIgnoreCase("PUT")) {
			mapResponse = restTemplate.exchange(reqUrl, HttpMethod.PUT, requestEntity, String.class, JsonData);
		} else if (method.equalsIgnoreCase("GET")) {
			mapResponse = restTemplate.exchange(reqUrl, HttpMethod.GET, requestEntity, String.class, JsonData);
		} else if (method.equalsIgnoreCase("DELETE")) {
			mapResponse = restTemplate.exchange(reqUrl, HttpMethod.DELETE, requestEntity, String.class, JsonData);
		} else {
			mapResponse = restTemplate.postForEntity(reqUrl, requestEntity, String.class);
		}
		return mapResponse;
	}

}