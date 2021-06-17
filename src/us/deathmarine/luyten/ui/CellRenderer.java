package us.deathmarine.luyten.ui;

import us.deathmarine.luyten.OpenFile;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

public class CellRenderer extends DefaultTreeCellRenderer {
    private static final long serialVersionUID = -5691181006363313993L;
    Icon pack;
    Icon java_image;
    Icon text_image;
    Icon file_image;
    
    public CellRenderer() {
        this.pack = new ImageIcon(
                Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/resources/package_obj.png")));
        this.java_image = new ImageIcon(
                Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/resources/java.png")));
        this.text_image = new ImageIcon(
                Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/resources/text.png")));
        this.file_image = new ImageIcon(
                Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/resources/file.png")));
    }
    
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf,
                                                  int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
        String name = getFileName(node);
        if (node.getChildCount() > 0) {
            setIcon(this.pack);
        } else if (name.contains(".")) {
            String ext = name.substring(name.lastIndexOf('.'));
            if (ext.equals(".class") || ext.equals(".java")) {
                setIcon(this.java_image);
            } else if (OpenFile.WELL_KNOWN_TEXT_FILE_EXTENSIONS.contains(ext)) {
                setIcon(this.text_image);
            } else {
                setIcon(this.file_image);
            }
        } else {
            setIcon(this.file_image);
        }
        putClientProperty("html.disable", true);
        return this;
    }
    
    public String getFileName(DefaultMutableTreeNode node) {
        return ((TreeNodeUserObject) node.getUserObject()).getOriginalName();
    }
    
}
