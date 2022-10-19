package us.deathmarine.luyten.util;

import com.strobel.assembler.metadata.TypeDefinition;
import us.deathmarine.luyten.Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;

public class ProcyonUtils {
    
    public static byte[] getContent(TypeDefinition type, Model model) {
        var file = model.getOpenedFile();
        if (file.getName().endsWith(".class")) {
            try (var inputStream = new FileInputStream(file)) {
                var content = new byte[(int) file.length()];
                inputStream.read(content);
                return content;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            try (var jarFile = new JarFile(file)) {
                var entry = jarFile.getJarEntry(type.getInternalName() + ".class");
                if (entry != null) {
                    try (var inputStream = jarFile.getInputStream(entry)) {
                        var content = new byte[(int) entry.getSize()];
                        inputStream.read(content);
                        return content;
                    }
                } else {
                    return null;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    
    public static File createSingletonTempJar(String entryName, byte[] content) {
        try {
            var file = File.createTempFile("luyten", ".jar");
            file.deleteOnExit();
            try (var jos = new JarOutputStream(new FileOutputStream(file))) {
                var je = new JarEntry(entryName);
                je.setSize(content.length);
                jos.putNextEntry(je);
                jos.write(content);
                jos.closeEntry();
            }
            return file;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
}
