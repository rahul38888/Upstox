package com.app.main;

public class PersonScore {
	String name;
	String role;
	String manager;
	Integer score;

	public PersonScore(String name, String role, String manager, Integer score) {
		this.name = name;
		this.role = role;
		this.manager = manager;
		this.score = score;
	}

	@Override
	public String toString() {
		return "{" +
				"name='" + name + '\'' +
				", role='" + role + '\'' +
				", manager='" + manager + '\'' +
				'}';
	}
}
