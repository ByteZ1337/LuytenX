package us.deathmarine.luyten.util;

import com.strobel.assembler.metadata.TypeDefinition;
import us.deathmarine.luyten.Model;

import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ProcyonUtils {
    
    public static byte[] getContent(TypeDefinition type, Model model) {
        try (JarFile jarFile = new JarFile(model.getOpenedFile())) {
            JarEntry entry = jarFile.getJarEntry(type.getInternalName() + ".class");
            if (entry != null) {
                try (InputStream inputStream = jarFile.getInputStream(entry)) {
                    byte[] content = new byte[(int) entry.getSize()];
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
