package com.alexandreesl.handson.examples;

import com.alexandreesl.handson.model.Client;
import com.hazelcast.core.EntryEvent;
import com.hazelcast.core.EntryListener;
import com.hazelcast.core.MapEvent;

public class MyMapEntryListener implements EntryListener<Long, Client> {

	public void entryAdded(EntryEvent<Long, Client> event) {

		System.out.println("thread:" + Thread.currentThread().getName());
		System.out.println("entryAdded:" + event);

	}

	public void entryRemoved(EntryEvent<Long, Client> event) {
		System.out.println("entryRemoved:" + event);

	}

	public void entryUpdated(EntryEvent<Long, Client> event) {

		System.out.println("thread:" + Thread.currentThread().getName());
		System.out.println("entryUpdated:" + event);

	}

	public void entryEvicted(EntryEvent<Long, Client> event) {
		System.out.println("entryEvicted:" + event);

	}

	public void mapEvicted(MapEvent event) {
		System.out.println("mapEvicted:" + event);

	}

	public void mapCleared(MapEvent event) {
		System.out.println("mapCleared:" + event);

	}

}
