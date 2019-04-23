package com.auto.task.service;

class Pair<K, V> {

	private K first;
	private V second;
	
	public Pair(K k, V v) {
		this.first = k;
		this.second = v;
	}
	
	public K getFirst(){
		return first;
	}
	
	public V getSecond() {
		return second;
	}
}