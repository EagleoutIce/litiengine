package de.gurkenlabs.utiliti.swing.panels;

import java.awt.LayoutManager;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JToggleButton;

import com.github.weisj.darklaf.ui.togglebutton.DarkToggleButtonUI;

import de.gurkenlabs.litiengine.environment.tilemap.IMapObject;
import de.gurkenlabs.litiengine.environment.tilemap.MapObjectProperty;
import de.gurkenlabs.litiengine.graphics.Spritesheet;
import de.gurkenlabs.litiengine.graphics.emitters.xml.EmitterData;
import de.gurkenlabs.litiengine.resources.Resources;
import de.gurkenlabs.utiliti.swing.LabelListCellRenderer;

@SuppressWarnings("serial")
public class EmitterSpritePanel extends PropertyPanel {
  private final JComboBox<JLabel> spritesheet;
  private final JToggleButton animateSprite;

  public EmitterSpritePanel() {
    super();
    spritesheet = new JComboBox<>();
    spritesheet.setRenderer(new LabelListCellRenderer());
    animateSprite = new JToggleButton();
    animateSprite.putClientProperty("JToggleButton.variant", DarkToggleButtonUI.VARIANT_SLIDER);
    setLayout(createLayout());
    setupChangedListeners();
  }

  @Override
  protected void clearControls() {
    spritesheet.removeAllItems();
    spritesheet.setSelectedItem(null);
    animateSprite.setSelected(EmitterData.DEFAULT_ANIMATE_SPRITE);

  }

  @Override
  protected void setControlValues(IMapObject mapObject) {
    loadSpritesheets();
    selectSpriteSheet(spritesheet, mapObject);
    animateSprite.setSelected(mapObject.getBoolValue(MapObjectProperty.Particle.ANIMATESPRITE, EmitterData.DEFAULT_ANIMATE_SPRITE));

  }

  protected LayoutManager createLayout() {
    LayoutItem[] layoutItems = new LayoutItem[] { new LayoutItem("particle_spritesheet", spritesheet), new LayoutItem("particle_animatesprite", animateSprite) };
    return this.createLayout(layoutItems);
  }

  private void setupChangedListeners() {
    setupL(spritesheet, MapObjectProperty.SPRITESHEETNAME);
    setup(animateSprite, MapObjectProperty.Particle.ANIMATESPRITE);
  }

  private void loadSpritesheets() {
    spritesheet.removeAllItems();
    spritesheet.setSelectedItem(null);
    for (Spritesheet s : Resources.spritesheets().getAll()) {
      JLabel label = new JLabel();
      label.setText(s.getName());
      label.setIcon(new ImageIcon(s.getPreview(CONTROL_HEIGHT)));
      spritesheet.addItem(label);
    }
  }
}
