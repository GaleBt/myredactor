package com.mycompany.myredactor;

import com.mycompany.myredactor.exception.RepositoryException;
import com.mycompany.myredactor.repository.FileDao;

import java.nio.file.Paths;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.assertArrayEquals;
import org.junit.Test;

public class FileDaoTest {

    private final File actual = new File("src/test/resources/actual.txt");
    private final Path testpath = Paths.get("src/test/resources/actual.txt");
    private final FileDao filedao = new FileDao(testpath);

    private final Path invtestpath = Paths.get("src/test/resources/xx/xxx.txt");
    private final FileDao invfiledao = new FileDao(invtestpath);

    private final Path testpath2 = Paths.get("src/test/resources/actual2.txt");
    private final FileDao filedao2 = new FileDao(testpath2);

    @After
    public void deleteFile() {
        actual.delete();
    }

    @Test
    public void testSaveText() throws IOException {
        List<String> expectedList = new ArrayList<>(Arrays.asList("bbb", "ccc", "ddd"));
        filedao.saveText(expectedList);
        byte[] expectedbyte = Files.readAllBytes(Paths.get("src/test/resources/expectedfile.txt"));
        byte[] actualbyte = Files.readAllBytes(Paths.get("src/test/resources/actual.txt"));
        System.out.println("'" + new String(expectedbyte) + "'");
        System.out.println("'" + new String(actualbyte) + "'");
        assertArrayEquals(expectedbyte, actualbyte);
    }

    @Test(expected = RuntimeException.class)
    public void testSaveTextInvalid() throws IOException {
        List<String> expectedList = new ArrayList<>(Arrays.asList("bbb", "ccc", "ddd"));
        invfiledao.saveText(expectedList);
        byte[] expectedbyte = Files.readAllBytes(Paths.get("src/test/resources/expectedfile.txt"));
        byte[] actualbyte = Files.readAllBytes(Paths.get("src/test/resources/actual.txt"));
        assertArrayEquals(expectedbyte, actualbyte);
    }

    @Test
    public void testGetText() {
        List<String> expectedList = new ArrayList<>(Arrays.asList("bbb", "ccc", "ddd"));
        Assert.assertEquals(expectedList, filedao2.getText("src/test/resources/actual2.txt"));
    }

    @Test(expected = RepositoryException.class)
    public void testGetTextInvalidName() {
        List<String> expectedList = null;
        Assert.assertEquals(expectedList, filedao2.getText("xxx.txt"));
    }

}
