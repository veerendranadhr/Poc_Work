package com.bt.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.bt.pojo.Order;

@Service
public class OrderService {

	
	private List<Order> list=new ArrayList<Order>();
	
	@PostConstruct
	public void initDB() {
		list.add(new Order(67,"mobile",5000));
		list.add(new Order(68,"book",500));

		list.add(new Order(69,"car",500000));

		list.add(new Order(70,"tab",5000));

		list.add(new Order(90,"bag",400));

	}
	
	public Order addOrder(Order order) {
		list.add(order);
		return order;
	}
	
	public List<Order> getOrders(){
		return list;
	}
	
}
