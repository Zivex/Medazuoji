package com.capinfo.omp.model;

public class Worker {
	protected int id;
	protected String name;
	protected String age;
	protected String post;
	protected String number;
	protected String jobNumber;

	public Worker() {
		super();
	}

	public Worker(int id, String name, String age, String post, String number, String jobNumber) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.post = post;
		this.number = number;
		this.jobNumber = jobNumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getJobNumber() {
		return jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	
}
