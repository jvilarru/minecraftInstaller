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
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 *
 * @author uri
 */
public class fileCopy implements Runnable {

    private InputStream inputZip;
    private File installDir;
    private long actual;
    private javax.swing.JProgressBar barra;
    private String separator;

    public fileCopy(File installDir, InputStream inputZip, javax.swing.JProgressBar barra, String separator) {
        this.inputZip = inputZip;
        this.installDir = installDir;
        this.barra = barra;
        this.separator = separator;
    }

    public long getActual() {
        return actual;
    }

    @Override
    public void run() {
//        try {
//            //        length = inputZip.length();
//            //        barra.setMaximum((int) length);
//            //        Principal.addText("Decompressing " + inputZip.getName() + " with a total length of " + length + " bytes");
//            //        barra.setMinimum(0);
//            ////        fileDecompresser(inputZip, installDir);
//            //        Principal.addText("Finished!!");
////            zipDecompresser(inputZip, installDir);
////        } catch (FileNotFoundException ex) {
////            Logger.getLogger(fileCopy.class.getName()).log(Level.SEVERE, null, ex);
////        } catch (IOException ex) {
////            Logger.getLogger(fileCopy.class.getName()).log(Level.SEVERE, null, ex);
//        }
        System.out.println("hola");
    }

//    private void fileDecompresser(File zipOrigin, File dirDest) {
//        FileInputStream in;
//        FileOutputStream out;
//        File output;
//        byte[] data;
//        data = new byte[1024];
//        int i = 0;
//        dirDest.mkdir();
//        for (File input : zipOrigin.listFiles()) {
//            output = new File(installDir, input.getName());
//            if (input.isDirectory()) {
//                fileDecompresser(input, output);
//            } else {
//                try {
//                    in = new FileInputStream(input);
//                    out = new FileOutputStream(output);
//
//                    int len;
//                    while ((len = in.read(data)) > 0) {
//                        out.write(data, 0, len);
//                    }
//                    in.close();
//                    out.close();
//                    Principal.addText(input.getName() + "Copied");
//                    i += input.length();
//                    barra.setValue(i);
//                } catch (FileNotFoundException ex) {
//                    Principal.addText("Error en la copia d'arxius");
//                    Principal.addText("Fitxer Error = " + input.getAbsolutePath());
//                    Principal.addText("Directori d'instalació = " + installDir.getAbsolutePath());
//                    Principal.addText("Directori source = " + inputZip.getAbsolutePath());
//                    Principal.addText(ex.getMessage());
//                } catch (IOException ex) {
//                    Principal.addText("Error d'entrada/sortida");
//                    Principal.addText("Fitxer Error = " + input.getAbsolutePath());
//                    Principal.addText("Directori d'instalació = " + installDir.getAbsolutePath());
//                    Principal.addText("Directori source = " + inputZip.getAbsolutePath());
//                    Principal.addText(ex.getMessage());
//                }
//            }
//        }
//    }
    private void zipDecompresser(InputStream input, File output) throws FileNotFoundException, IOException {
        ZipInputStream zis = new ZipInputStream(input);
        ZipEntry entrada;
        while (null != (entrada = zis.getNextEntry())) {
            System.out.println(entrada.getName());
            if (entrada.isDirectory()) {
                File f = new File(output, entrada.getName());
                f.mkdirs();
            } else {
                FileOutputStream fos = new FileOutputStream(output.getAbsolutePath() + separator + entrada.getName());
                int leido;
                byte[] buffer = new byte[1024];
                while (0 < (leido = zis.read(buffer))) {
                    fos.write(buffer, 0, leido);
                }
                fos.close();
            }
            zis.closeEntry();
        }
    }
}
