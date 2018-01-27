package de.gurkenlabs.utiliti.swing;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.util.io.XmlUtilities;
import de.gurkenlabs.utiliti.EditorScreen;

public final class XmlExportDialog {
  private static final Logger log = Logger.getLogger(XmlExportDialog.class.getName());

  private XmlExportDialog() {
  }

  public static <T> void export(T object, String name, String filename) {
    export(object, name, filename, "xml");
  }

  public static <T> void export(T object, String name, String filename, String extension) {
    JFileChooser chooser;
    try {
      String source = EditorScreen.instance().getProjectPath();
      chooser = new JFileChooser(source != null ? source : new File(".").getCanonicalPath());
      chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
      chooser.setDialogType(JFileChooser.SAVE_DIALOG);
      chooser.setDialogTitle("Export " + name);
      FileFilter filter = new FileNameExtensionFilter(name + " XML", extension);
      chooser.setFileFilter(filter);
      chooser.addChoosableFileFilter(filter);
      chooser.setSelectedFile(new File(filename + "." + extension));

      int result = chooser.showSaveDialog(Game.getScreenManager().getRenderComponent());
      if (result == JFileChooser.APPROVE_OPTION) {
        String newFile = XmlUtilities.save(object, chooser.getSelectedFile().toString(), extension);
        log.log(Level.INFO, "exported mapobject {0} to {1}", new Object[] { filename, newFile });
      }
    } catch (IOException e) {
      log.log(Level.SEVERE, e.getMessage(), e);
    }
  }
}
