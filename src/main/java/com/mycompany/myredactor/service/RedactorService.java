package com.mycompany.myredactor.service;

/**
 * handling  List<String>
 */
import java.util.List;
import com.mycompany.myredactor.repository.FileDao;

public class RedactorService {

    private final FileDao filedao = new FileDao();

    private List<String> list;

    /**
     * insert string
     */
    public void setList(List<String> list) {
        this.list = list;
    }

    /**
     * getting List<String>
     */
    public void openFile(String namefile) {
        list = filedao.getText(namefile);
    }

    /**
     * output List<String>r to the console
     */
    public void readAllLine() {

        int i = 0;
        for (String s : list) {
            i++;
            System.out.println(i + ": " + s);
        }
    }

    /**
     * deleting string
     */
    public void deleteLine(int num) {
        if (list.size() - 1 < num || num < 0) {
            throw new IllegalArgumentException("line number is not valid");
        }
        list.remove(num);

    }

    /**
     * insert string
     */
    public void insertLine(int num, String str) {
        if (list.size() < num || num < 0) {
            throw new IllegalArgumentException("line number is not valid");
        }
        list.add(num, str);

    }

    /**
     * save strings
     */
    public void saveList() {
        filedao.saveText(list);
    }

}
