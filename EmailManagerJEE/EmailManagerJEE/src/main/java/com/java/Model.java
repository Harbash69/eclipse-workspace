package com.java;

import java.util.Objects;

public class Model {

	private  String email;

	public Model(String a) {
		super();
		// TODO Auto-generated constructor stub
		this.email = a;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Model other = (Model) obj;
		return Objects.equals(email, other.email);
	}

	@Override
	public String toString() {
		return  email ;
	}
}
