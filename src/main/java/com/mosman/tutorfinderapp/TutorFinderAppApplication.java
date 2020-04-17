package com.mosman.tutorfinderapp;

import com.mosman.tutorfinderapp.services.file_upload.FilesStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.nio.file.Files;

@SpringBootApplication
public class TutorFinderAppApplication implements CommandLineRunner {

	@Autowired
	FilesStorageService storageService;

	public static void main(String[] args) {
		SpringApplication.run(TutorFinderAppApplication.class, args);
	}

	@Override
	public void run(String... arg) throws Exception {
//    storageService.deleteAll();
//    storageService.init();
		if (!Files.exists(storageService.getRoot())) {
			storageService.init();
		}
	}
}
