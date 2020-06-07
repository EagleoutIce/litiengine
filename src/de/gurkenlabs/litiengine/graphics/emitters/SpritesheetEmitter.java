package de.gurkenlabs.litiengine.graphics.emitters;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.concurrent.ThreadLocalRandom;

import de.gurkenlabs.litiengine.graphics.Spritesheet;
import de.gurkenlabs.litiengine.graphics.emitters.particles.Particle;
import de.gurkenlabs.litiengine.graphics.emitters.particles.SpriteParticle;

public abstract class SpritesheetEmitter extends Emitter {
  private final Spritesheet spriteSheet;

  public SpritesheetEmitter(final Spritesheet spriteSheet, final Point2D origin) {
    super(origin);
    this.spriteSheet = spriteSheet;
    this.setSize(this.getSpritesheet().getSpriteWidth(), this.getSpritesheet().getSpriteHeight());
  }

  public Spritesheet getSpritesheet() {
    return this.spriteSheet;
  }

  protected BufferedImage getRandomSprite() {
    return this.getSpritesheet().getSprite(ThreadLocalRandom.current().nextInt(this.getSpritesheet().getTotalNumberOfSprites()));
  }

  @Override
  protected Particle createNewParticle() {
    return new SpriteParticle(this.spriteSheet);
  }
}
