package fr.isen.java2.db.database;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DatabaseBackupWriterTest extends TestCase {

    private static final List<String[]> dataList = new ArrayList<>();
    private final DatabaseBackupWriter backupWriter = new DatabaseBackupWriter();

    @Before
    private static void initList() {
        String[] header = {"id", "name", "address", "phone"};
        String[] record1 = {"1", "first name", "address 1", "11111"};
        String[] record2 = {"2", "second name", "address 2", "22222"};
        dataList.add(header);
        dataList.add(record1);
        dataList.add(record2);
    }

    @Test
    public void testWriteToCSV() {
        String fileName = "testCSV.csv";
        DatabaseBackupWriter.writeToCSV(dataList, fileName);
        File backupFile = new File(fileName);
        assertTrue(backupFile.exists());

    }
}