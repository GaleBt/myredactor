package com.mycompany.myredactor.utils;

import com.mycompany.myredactor.exception.RepositoryException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Helper class for validation of files
 */
public class FileUtils {
    
    public static void validate(String filePath) {
        Path path = Paths.get(filePath);
        if(!Files.exists(path)) {
            throw new RepositoryException("File not found: " + filePath);
        }
        if(Files.isDirectory(path)) {
            throw new RepositoryException("File should'n be directory: " + filePath);
        }
        if(!Files.isReadable(path)) {
            throw new RepositoryException("File not readable: " + filePath);
        }
        if(!Files.isWritable(path)) {
            throw new RepositoryException("File not writeable: " + filePath);
        }
    }
    
}
