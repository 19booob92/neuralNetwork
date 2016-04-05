package org.pwr.molczak.model;

public class ClassifiedFile {
	
	private String path;
	private Character clazz;
	
	public ClassifiedFile(String path, Character clazz) {
		this.path = path;
		this.clazz = clazz;
	}

	public Character getClazz() {
		return clazz;
	}

	public void setClazz(Character clazz) {
		this.clazz = clazz;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
