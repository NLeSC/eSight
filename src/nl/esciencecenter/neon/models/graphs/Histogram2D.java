package nl.esciencecenter.neon.models.graphs;

import javax.media.opengl.GL3;

import nl.esciencecenter.neon.exceptions.UninitializedException;
import nl.esciencecenter.neon.math.Color4;
import nl.esciencecenter.neon.math.Float3Vector;
import nl.esciencecenter.neon.math.Float4Matrix;
import nl.esciencecenter.neon.math.FloatMatrixMath;
import nl.esciencecenter.neon.models.LeftBottomQuad;
import nl.esciencecenter.neon.shaders.ShaderProgram;
import nl.esciencecenter.neon.text.MultiColorText;
import nl.esciencecenter.neon.text.jogampexperimental.Font;
import nl.esciencecenter.neon.text.jogampexperimental.FontFactory;

/* Copyright 2013 Netherlands eScience Center
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * Convenience class to create a 2D Histogram graph model.
 * 
 * @author Maarten van Meersbergen <m.van.meersbergen@esciencecenter.nl>
 * 
 */
public class Histogram2D {
    private final float[] data;
    private final LeftBottomQuad[] bars;
    private final Color4[] colors;
    private final MultiColorText[] barLabels;
    private final String[] barLabelTexts;

    private static final float DEFAULT_WIDTH = 1f;
    private static final float DEFAULT_HEIGHT = 1f;

    private final Float3Vector leftBottomCoordinates;

    /** Ubuntu fontset is used for HUD elements */
    private static final int fontSet = FontFactory.UBUNTU;
    /** font is used for HUD elements @see fontSet */
    private final Font font;
    private final int FONTSIZE = 20;

    public Histogram2D(Color4[] barColors, String[] labels) {
        this.leftBottomCoordinates = new Float3Vector();

        int numBars = barColors.length;

        float widthPerQuad = DEFAULT_WIDTH / numBars;

        data = new float[numBars];
        bars = new LeftBottomQuad[numBars];
        for (int i = 0; i < numBars; i++) {
            data[i] = 0f;

            Float3Vector newLeftBottom = leftBottomCoordinates.add(new Float3Vector(i * widthPerQuad, 0f, 0f));
            bars[i] = new LeftBottomQuad(DEFAULT_HEIGHT, widthPerQuad, newLeftBottom);
        }

        this.colors = barColors;

        this.font = FontFactory.get(fontSet).getDefault();
        this.barLabels = new MultiColorText[numBars];
        this.barLabelTexts = labels;
    }

    public void init(GL3 gl) {
        for (LeftBottomQuad q : bars) {
            q.init(gl);
        }

        int numBars = data.length;
        for (int i = 0; i < numBars; i++) {
            if (barLabels[i] == null) {
                barLabels[i] = new MultiColorText(gl, font, barLabelTexts[i], Color4.WHITE, FONTSIZE);
                barLabels[i].setString(gl, barLabelTexts[i], Color4.WHITE, FONTSIZE);
            }
        }
    }

    public void drawBars(GL3 gl, ShaderProgram program) throws UninitializedException {
        for (int i = 0; i < bars.length; i++) {
            LeftBottomQuad q = bars[i];

            // Set the color for the current Quad
            program.setUniformVector("Color", colors[i]);

            // Load all staged variables into the GPU, check for errors and
            // omissions.
            program.use(gl);
            q.draw(gl, program);
        }
    }

    public void drawLabels(GL3 gl, Float4Matrix mv, ShaderProgram program) throws UninitializedException {
        float widthPerQuad = DEFAULT_WIDTH / bars.length;

        float scale = .0025f;

        Float4Matrix scaleMatrix = FloatMatrixMath.scale(scale);
        Float4Matrix scaledRotationMatrix = scaleMatrix.mul(FloatMatrixMath.rotationZ(-90f));

        for (int i = 0; i < bars.length; i++) {
            MultiColorText label = barLabels[i];
            Float3Vector newLeftBottom = leftBottomCoordinates.add(new Float3Vector(0.5f, ((widthPerQuad / scale) * i)
                    + ((.2f * widthPerQuad / scale)), 0f));

            Float4Matrix scaledRotationTranslatedMatrix = scaledRotationMatrix.mul(FloatMatrixMath
                    .translate(newLeftBottom));

            program.setUniformMatrix("MVMatrix", mv.mul(scaledRotationTranslatedMatrix));

            label.draw(gl, program);
        }
    }

    public void setValues(GL3 gl, float[] newData) throws IllegalArgumentException {
        int numBars = data.length;
        float widthPerQuad = DEFAULT_WIDTH / numBars;

        if (newData.length != numBars) {
            throw new IllegalArgumentException("The size of this histogram was " + numBars
                    + " while you gave me data for a size " + newData.length);
        }

        for (int i = 0; i < bars.length; i++) {
            bars[i].delete(gl);

            Float3Vector newLeftBottom = leftBottomCoordinates.add(new Float3Vector(i * widthPerQuad, 0f, 0f));
            bars[i] = new LeftBottomQuad(newData[i] * DEFAULT_HEIGHT, widthPerQuad, newLeftBottom);
            bars[i].init(gl);
        }
    }

}
