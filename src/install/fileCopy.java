/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package install;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author uri
 */
public class fileCopy implements Runnable {

    private File inputDir;
    private File installDir;
    private long actual;
    private long length;
    private int numOfFiles;
    private javax.swing.JProgressBar barra;

    public fileCopy(File inputDir, File installDir, javax.swing.JProgressBar barra) {
        this.inputDir = inputDir;
        this.installDir = installDir;
        this.barra = barra;
        this.length = 0;
        this.numOfFiles = 0;
    }

    public long getActual() {
        return actual;
    }

    @Override
    public void run() {
        howLength(inputDir);
        barra.setMaximum((int) length);
        Principal.addText("Copying " + numOfFiles + " with a total length of " + length + " bytes");
        barra.setMinimum(0);
//        fileCopy(inputDir, installDir);
        Principal.addText("Finished!!");
    }

    private void fileCopy(File dirOrigin, File dirDest) {
        FileInputStream in;
        FileOutputStream out;
        File output;
        byte[] data;
        data = new byte[1024];
        int i = 0;
        dirDest.mkdir();
        for (File input : dirOrigin.listFiles()) {
            output = new File(installDir, input.getName());
            if (input.isDirectory()) {
                fileCopy(input, output);
            } else {
                try {
                    in = new FileInputStream(input);
                    out = new FileOutputStream(output);

                    int len;
                    while ((len = in.read(data)) > 0) {
                        out.write(data, 0, len);
                    }
                    in.close();
                    out.close();
                    Principal.addText(input.getName() + "Copied");
                    i += input.length();
                    barra.setValue(i);
                } catch (FileNotFoundException ex) {
                    Principal.addText("Error en la copia d'arxius");
                    Principal.addText("Fitxer Error = " + input.getAbsolutePath());
                    Principal.addText("Directori d'instalació = " + installDir.getAbsolutePath());
                    Principal.addText("Directori source = " + inputDir.getAbsolutePath());
                    Principal.addText(ex.getMessage());
                } catch (IOException ex) {
                    Principal.addText("Error d'entrada/sortida");
                    Principal.addText("Fitxer Error = " + input.getAbsolutePath());
                    Principal.addText("Directori d'instalació = " + installDir.getAbsolutePath());
                    Principal.addText("Directori source = " + inputDir.getAbsolutePath());
                    Principal.addText(ex.getMessage());
                }
            }
        }
    }

    private void howLength(File f) {
        if (f.isFile()) {
            numOfFiles++;
            length += f.length();
            return;
        }
        for (File file : f.listFiles()) {
            howLength(file);
        }
    }
}
