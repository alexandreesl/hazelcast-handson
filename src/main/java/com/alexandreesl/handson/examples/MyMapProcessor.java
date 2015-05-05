package com.alexandreesl.handson.examples;

import java.util.Map.Entry;

import com.alexandreesl.handson.model.Client;
import com.hazelcast.map.AbstractEntryProcessor;

public class MyMapProcessor extends AbstractEntryProcessor<String, Client> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8890058180314253853L;

	@Override
	public Object process(Entry<String, Client> entry) {

		Client client = entry.getValue();

		client.setPhone(888888888l);

		entry.setValue(client);

		System.out.println("Processing the client: " + client.getName());

		return null;
	}

}
