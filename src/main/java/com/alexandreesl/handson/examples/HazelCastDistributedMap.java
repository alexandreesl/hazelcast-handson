package com.alexandreesl.handson.examples;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.alexandreesl.handson.model.Client;
import com.alexandreesl.handson.util.HazelCastFactory;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

public class HazelCastDistributedMap {

	public static void main(String[] args) {

		HazelcastInstance client = HazelCastFactory.getInstance();

		IMap<Long, Client> map = client.getMap("customers");

		map.addEntryListener(new MyMapEntryListener(), true);

		Client clientData = new Client();

		clientData.setName("Alexandre Eleuterio Santos Lourenco");
		clientData.setPhone(33455676l);
		clientData.setSex("M");

		map.put(clientData.getPhone(), clientData, 5, TimeUnit.MINUTES);

		clientData = new Client();

		clientData.setName("Lucebiane Santos Lourenco");
		clientData.setPhone(456782387l);
		clientData.setSex("F");

		map.put(clientData.getPhone(), clientData, 2, TimeUnit.MINUTES);

		clientData = new Client();

		clientData.setName("Ana Carolina Fernandes do Sim");
		clientData.setPhone(345622189l);
		clientData.setSex("F");

		map.put(clientData.getPhone(), clientData, 120, TimeUnit.SECONDS);

		clientData = (Client) map.get(33455676l);

		clientData.setName("Alexandre Eleuterio Santos Lourenco UPDATED!");

		map.put(clientData.getPhone(), clientData, 5, TimeUnit.MINUTES);

		HazelCastFactory.shutDown();

		client = HazelCastFactory.getInstance();

		Map<Long, Client> mapPostShutDown = client.getMap("customers");

		for (Long phone : mapPostShutDown.keySet()) {
			Client cli = mapPostShutDown.get(phone);

			System.out.println(cli.getName());

		}

		System.out.println(mapPostShutDown.size());

		IMap<Long, Client> mapProcessors = client.getMap("customers");

		mapProcessors.executeOnEntries(new MyMapProcessor());

		HazelCastFactory.shutDown();

		client = HazelCastFactory.getInstance();

		mapProcessors = client.getMap("customers");

		for (Long phone : mapProcessors.keySet()) {
			Client cli = mapProcessors.get(phone);

			System.out.println(cli.getName() + " " + cli.getPhone());

		}

		HazelCastFactory.shutDown();

	}

}
