package us.deathmarine.luyten.util;

import com.strobel.assembler.metadata.TypeDefinition;
import us.deathmarine.luyten.Model;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.jar.JarFile;

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
    
}
