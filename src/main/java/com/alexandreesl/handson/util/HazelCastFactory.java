package com.alexandreesl.handson.util;

import com.alexandreesl.handson.examples.MyMapProcessor;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientNetworkConfig;
import com.hazelcast.core.HazelcastInstance;

public class HazelCastFactory {

	private static HazelcastInstance cluster;

	private static boolean shutDown = true;

	public static HazelcastInstance getInstance() {

		if (shutDown) {
			ClientConfig clientConfig = new ClientConfig();
			clientConfig.setClassLoader(MyMapProcessor.class.getClassLoader());
			ClientNetworkConfig clientNetworkConfig = new ClientNetworkConfig();
			clientNetworkConfig.addAddress("127.0.0.1:5701");
			clientConfig.setNetworkConfig(clientNetworkConfig);
			cluster = HazelcastClient.newHazelcastClient(clientConfig);
			shutDown = false;

		}

		return cluster;

	}

	public static void shutDown() {
		cluster.shutdown();
		shutDown = true;
	}

}
