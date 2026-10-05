# Fractal Tree Rendering

Java OpenGL exercise rendering recursively branching plants in a two-dimensional scene.

## How it works

A Swing window hosts a JOGL canvas. The scene draws a table, pots and a sun, then generates plant branches recursively. Each branch splits at opposing angles, shrinks to eighty percent of its parent length and stops when short enough.

## Usage

Requires a Java Development Kit, a desktop display and a legacy JOGL installation compatible with the `javax.media.opengl` API. Configure the JOGL JARs and native libraries in the Java classpath, then compile `src/shape/FractalTree.java` and run `shape.FractalTree`.

## Notes

Scene dimensions, positions and branch parameters are defined in the source. JOGL dependencies and native libraries are not bundled.
