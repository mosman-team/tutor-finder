package com.mosman.tutorfinderapp.services.file_upload;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface FilesStorageService {
  public void init();

  public void save(MultipartFile file, String resultFileName);

  public Resource load(String filename);

  public void deleteAll();

  public Stream<Path> loadAll();

  public Path getRoot();

  public void delete(String filename) throws IOException;
}
