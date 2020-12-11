package com.example.demo.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {
    private String uploadDir;

    
    public FileStorageProperties() {
		super();
	}

	public FileStorageProperties(String uploadDir) {
		super();
		this.uploadDir = uploadDir;
	}

	public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }
}