package com.mycompany.myredactor.repository;

import com.mycompany.myredactor.exception.RepositoryException;
import java.io.IOException;
import java.nio.file.Files;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Repository for text file
 */
public class FileDao {

    public FileDao() {

    }

    public FileDao(Path path) {
        this.path = path;
    }

    private Path path;

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    /**
     * Extracts strings from a file
     */
    public List<String> getText(String namefile) {
        path = Paths.get(namefile);
        try {
            return Files.lines(path).collect(Collectors.toList());
        } catch (IOException e) {
            throw new RepositoryException(e);
        }
    }

    /**
     * Writs strings to file
     */
    public void saveText(List<String> list) {
        try {
            Files.write(path, list);
        } catch (IOException e) {
            throw new RepositoryException(e);
        }
    }
}
