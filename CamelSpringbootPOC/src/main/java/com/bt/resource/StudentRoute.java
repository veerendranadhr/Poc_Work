package com.bt.resource;

import java.util.concurrent.atomic.AtomicLong;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bt.pojo.Student;
import com.bt.service.OrderService;

@Component
class StudentRoute extends RouteBuilder {

	/*
	@Autowired
	private OrderService service;
	
	*/
	@Override
	public void configure() {
		restConfiguration()
		.component("servlet")
		.bindingMode(RestBindingMode.json);

		
		rest("/student").produces("application/json")
		.get("/hello/{name}")
		.route().transform().simple("Hello ${header.name}, Welcome")
		.endRest()
		.get("/records/{name}").to("direct:records");

		from("direct:records")
		.process(new Processor() {

			final AtomicLong counter = new AtomicLong();

			@Override
			public void process(Exchange exchange) throws Exception {

				final String name = exchange.getIn().getHeader("name",String.class);
				exchange.getIn().setBody(new Student(counter.incrementAndGet(),name,"Camel + SpringBoot"));
			}
		});
	}
}
