package us.deathmarine.luyten.config;

import us.deathmarine.luyten.Luyten;
import us.deathmarine.luyten.config.LuytenPreferences;

import javax.swing.*;
import java.io.File;

public class DirPreferences {
    private LuytenPreferences luytenPrefs;
    
    public DirPreferences(LuytenPreferences luytenPrefs) {
        this.luytenPrefs = luytenPrefs;
    }
    
    public void retrieveOpenDialogDir(JFileChooser fc) {
        try {
            String currentDirStr = luytenPrefs.getFileOpenCurrentDirectory();
            if (currentDirStr != null && currentDirStr.trim().length() > 0) {
                File currentDir = new File(currentDirStr);
                if (currentDir.exists() && currentDir.isDirectory()) {
                    fc.setCurrentDirectory(currentDir);
                }
            }
        } catch (Exception e) {
            Luyten.showExceptionDialog("Exception!", e);
        }
    }
    
    public void saveOpenDialogDir(JFileChooser fc) {
        try {
            File currentDir = fc.getCurrentDirectory();
            if (currentDir != null && currentDir.exists() && currentDir.isDirectory()) {
                luytenPrefs.setFileOpenCurrentDirectory(currentDir.getAbsolutePath());
            }
        } catch (Exception e) {
            Luyten.showExceptionDialog("Exception!", e);
        }
    }
    
    public void retrieveSaveDialogDir(JFileChooser fc) {
        try {
            String currentDirStr = luytenPrefs.getFileSaveCurrentDirectory();
            if (currentDirStr != null && currentDirStr.trim().length() > 0) {
                File currentDir = new File(currentDirStr);
                if (currentDir.exists() && currentDir.isDirectory()) {
                    fc.setCurrentDirectory(currentDir);
                }
            }
        } catch (Exception e) {
            Luyten.showExceptionDialog("Exception!", e);
        }
    }
    
    public void saveSaveDialogDir(JFileChooser fc) {
        try {
            File currentDir = fc.getCurrentDirectory();
            if (currentDir != null && currentDir.exists() && currentDir.isDirectory()) {
                luytenPrefs.setFileSaveCurrentDirectory(currentDir.getAbsolutePath());
            }
        } catch (Exception e) {
            Luyten.showExceptionDialog("Exception!", e);
        }
    }
}