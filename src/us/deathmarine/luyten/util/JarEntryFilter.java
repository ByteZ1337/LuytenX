package us.deathmarine.luyten.util;

import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JarEntryFilter {
    
    private JarFile jfile;
    
    public JarEntryFilter() {
    }
    
    public JarEntryFilter(JarFile jfile) {
        this.jfile = jfile;
    }
    
    public List<String> getAllEntriesFromJar() {
        List<String> mass = new ArrayList<>();
        Enumeration<JarEntry> entries = jfile.entries();
        while (entries.hasMoreElements()) {
            JarEntry e = entries.nextElement();
            if (!e.isDirectory()) {
                mass.add(e.getName());
            }
        }
        return mass;
    }
    
    public List<String> getEntriesWithoutInnerClasses() {
        List<String> mass = new ArrayList<>();
        Enumeration<JarEntry> entries = jfile.entries();
        Set<String> possibleInnerClasses = new HashSet<>();
        Set<String> baseClasses = new HashSet<>();
        
        while (entries.hasMoreElements()) {
            JarEntry e = entries.nextElement();
            if (!e.isDirectory()) {
                String entryName = e.getName();
                
                if (entryName.trim().length() > 0) {
                    entryName = entryName.trim();
                    
                    if (!entryName.endsWith(".class")) {
                        mass.add(entryName);
                        
                        // com/acme/Model$16.class
                    } else if (entryName.matches(".*[^(/|\\\\)]+\\$[^(/|\\\\)]+$")) {
                        possibleInnerClasses.add(entryName);
                        
                    } else {
                        baseClasses.add(entryName);
                        mass.add(entryName);
                    }
                }
            }
        }
        
        // keep Badly$Named but not inner classes
        for (String inner : possibleInnerClasses) {
            
            // com/acme/Connection$Conn$1.class -> com/acme/Connection
            String innerWithoutTail = inner.replaceAll("\\$[^(/|\\\\)]+\\.class$", "");
            if (!baseClasses.contains(innerWithoutTail + ".class")) {
                mass.add(inner);
            }
        }
        return mass;
    }
    
    public JarFile getJfile() {
        return jfile;
    }
    
    public void setJfile(JarFile jfile) {
        this.jfile = jfile;
    }
}
