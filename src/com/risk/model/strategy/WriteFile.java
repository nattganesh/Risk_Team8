package com.risk.model.strategy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class WriteFile {
	public static void writeFile(String path, String content) {
        File writefile;
        try {
            writefile = new File(path);
            if (!writefile.exists()) {
                writefile.createNewFile();
                writefile = new File(path);
            }

            FileOutputStream txt = new FileOutputStream(writefile, true);
            Writer out = new OutputStreamWriter(txt, "utf-8");
            out.write(content);
            String newline = System.getProperty("line.separator");
            out.write(newline);
            out.close();
            txt.flush();
            txt.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
