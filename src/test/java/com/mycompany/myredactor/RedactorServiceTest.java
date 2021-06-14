package com.mycompany.myredactor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import com.mycompany.myredactor.service.RedactorService;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;

public class RedactorServiceTest {

    private final RedactorService redactorService = new RedactorService();

    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(output));
    }
    
    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

    @Test
    public void testDeleteFirstPos() {
        List<String> list = new ArrayList<>(Arrays.asList("aaa", "bbb", "ccc", "ddd"));
        redactorService.setList(list);
        redactorService.deleteLine(0);
        List<String> expected = new ArrayList<>(Arrays.asList("bbb", "ccc", "ddd"));
        Assert.assertEquals(expected, list);
    }

    @Test
    public void testDeleteLastPos() {
        List<String> list = new ArrayList<>(Arrays.asList("aaa", "bbb", "ccc", "ddd"));
        redactorService.setList(list);
        redactorService.deleteLine(3);
        List<String> expected = new ArrayList<>(Arrays.asList("aaa", "bbb", "ccc"));
        Assert.assertEquals(expected, list);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteNegativeNum() {
        List<String> list = new ArrayList<>(Arrays.asList("aaa", "bbb", "ccc", "ddd"));
        redactorService.setList(list);
        redactorService.deleteLine(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteOversizeNum() {
        List<String> list = new ArrayList<>(Arrays.asList("aaa", "bbb", "ccc", "ddd"));
        redactorService.setList(list);
        redactorService.deleteLine(100);
    }

    @Test
    public void testInsertFirstPos() {
        List<String> list = new ArrayList<>(Arrays.asList("aaa", "bbb", "ccc", "ddd"));
        redactorService.setList(list);
        List<String> expected = new ArrayList<>(Arrays.asList("000", "aaa", "bbb", "ccc", "ddd"));
        redactorService.insertLine(0, "000");
        Assert.assertEquals(expected, list);
    }

    @Test
    public void testInsertLastPosrt() {
        List<String> list = new ArrayList<>(Arrays.asList("aaa", "bbb", "ccc", "ddd"));
        redactorService.setList(list);
        List<String> expected = new ArrayList<>(Arrays.asList("aaa", "bbb", "ccc", "ddd", "444"));
        redactorService.insertLine(4, "444");
        Assert.assertEquals(expected, list);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInsertNegativeNum() {
        List<String> list = new ArrayList<>(Arrays.asList("aaa", "bbb", "ccc", "ddd"));
        redactorService.setList(list);
        redactorService.insertLine(-1, "-1-1-1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInsertOversizeNum() {
        List<String> list = new ArrayList<>(Arrays.asList("aaa", "bbb", "ccc", "ddd"));
        redactorService.setList(list);
        redactorService.insertLine(100, "100, 100, 100");
    }

    @Test
    public void testString() {
        List<String> list = new ArrayList<>(Arrays.asList("qqq", "www"));
        redactorService.setList(list);
        redactorService.readAllLine();
        String str = "1: qqq\r\n2: www\r\n";
        Assert.assertEquals(str, output.toString());
    }

}
