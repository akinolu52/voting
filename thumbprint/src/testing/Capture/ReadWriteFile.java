/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testing.Capture;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 *
 * @author Oginni
 */
public class ReadWriteFile {

    final static Charset ENCODING = StandardCharsets.UTF_8;

    String read(String aFileName) throws IOException {
        Path path = Paths.get(aFileName);
        String eachLine = "";
        try {
            Scanner scanner = new Scanner(path, ENCODING.name());
            while (scanner.hasNextLine()) {
                eachLine = scanner.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return eachLine;
    }

     void write(String aFileName, Byte[] text) throws IOException {
        try {
            PrintWriter writer = new PrintWriter(aFileName, "UTF-8");
            writer.println(text);
            writer.flush();
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     void write(String aFileName, String text) throws IOException {
        try {
            PrintWriter writer = new PrintWriter(aFileName, "UTF-8");
            writer.println(text);
            writer.flush();
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
