package com.alexandreesl.handson.examples;

import com.alexandreesl.handson.model.Client;
import com.alexandreesl.handson.util.HazelCastFactory;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.MultiMap;

public class HazelCastDistributedMultiMap {

	public static void main(String[] args) {

		HazelcastInstance client = HazelCastFactory.getInstance();

		MultiMap<String, Client> map = client.getMultiMap("multicustomers");

		Client clientData = new Client();

		clientData.setName("Alexandre Eleuterio Santos Lourenco");
		clientData.setPhone(33455676l);
		clientData.setSex("M");

		map.put(clientData.getSex(), clientData);

		clientData = new Client();

		clientData.setName("Lucebiane Santos Lourenco");
		clientData.setPhone(456782387l);
		clientData.setSex("F");

		map.put(clientData.getSex(), clientData);

		clientData = new Client();

		clientData.setName("Ana Carolina Fernandes do Sim");
		clientData.setPhone(345622189l);
		clientData.setSex("F");

		map.put(clientData.getSex(), clientData);

		HazelCastFactory.shutDown();

		client = HazelCastFactory.getInstance();

		map = client.getMultiMap("multicustomers");

		for (String key : map.keySet()) {

			for (Client cli : map.get(key)) {

				System.out.println("The Client: " + cli.getName()
						+ " for the Key: " + key);

			}

		}

		HazelCastFactory.shutDown();

	}

}
