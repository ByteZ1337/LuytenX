package us.deathmarine.luyten.ui;

import us.deathmarine.luyten.OpenFile;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

public class CellRenderer extends DefaultTreeCellRenderer {
    private static final long serialVersionUID = -5691181006363313993L;
    Icon pack;
    Icon classIcon;
    Icon htmlIcon;
    Icon cssIcon;
    Icon textIcon;
    Icon imageIcon;
    Icon fileIcon;
    
    public CellRenderer() {
        this.pack = new ImageIcon(
            Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/resources/package_obj.png")));
        this.classIcon = new ImageIcon(
            Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/resources/java.png")));
        this.htmlIcon = new ImageIcon(
            Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/resources/html.png")));
        this.cssIcon = new ImageIcon(
            Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/resources/css.png")));
        this.textIcon = new ImageIcon(
            Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/resources/text.png")));
        this.imageIcon = new ImageIcon(
            Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/resources/image.png")));
        this.fileIcon = new ImageIcon(
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
                setIcon(this.classIcon);
            } else if (ext.equals(".html") || ext.equals(".htm")) {
                setIcon(this.htmlIcon);
            } else if (ext.equals(".css")) {
                setIcon(this.cssIcon);
            } else if (OpenFile.WELL_KNOWN_IMAGE_FILE_EXTENSIONS.contains(ext)) {
                setIcon(this.imageIcon);
            } else if (OpenFile.WELL_KNOWN_TEXT_FILE_EXTENSIONS.contains(ext)) {
                setIcon(this.textIcon);
            } else {
                setIcon(this.fileIcon);
            }
        } else {
            setIcon(this.fileIcon);
        }
        putClientProperty("html.disable", true);
        return this;
    }
    
    public String getFileName(DefaultMutableTreeNode node) {
        return ((TreeNodeUserObject) node.getUserObject()).getOriginalName();
    }
    
}
