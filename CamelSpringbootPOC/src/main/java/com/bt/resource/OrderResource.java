package com.bt.resource;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.bt.service.OrderService;

@Component
public class OrderResource extends RouteBuilder {

	@Autowired
	private OrderService service;
	
	@Override
	public void configure() {
		restConfiguration()
		.component("servlet")
		.bindingMode(RestBindingMode.json);

		rest().get("/testorder").produces(MediaType.APPLICATION_JSON_VALUE).route().setBody(constant("welcome to veera application"));

		rest().get("/getorders").produces(MediaType.APPLICATION_JSON_VALUE).route().setBody(constant(service.getOrders().toString()));

	}
	
}
