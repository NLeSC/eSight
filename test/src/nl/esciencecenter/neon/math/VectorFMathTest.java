package nl.esciencecenter.neon.math;

import static org.junit.Assert.assertEquals;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/* Copyright 2013 Netherlands eScience Center
 * 
 * Licensed under the Apache License, Version 2.0 (the "License")
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
 * Expected values for assertions were generated by Wolfram Alpha
 * http://www.wolframalpha.com/
 * 
 * @author Maarten van Meersbergen <m.van.meersbergen@esciencecenter.nl>
 * 
 */
public class VectorFMathTest {
    float EPSILON = 0.000001f;

    @Test
    public final void testDotFloat2VectorFloat2Vector() {
        Float2Vector input1 = new Float2Vector(0.3f, 0.2f);
        Float2Vector input2 = new Float2Vector(0.6f, 0.2f);
        float expected = 0.22f;
        assertEquals(expected, FloatVectorMath.dot(input1, input2), EPSILON);

        input1 = new Float2Vector(-0.3f, -0.2f);
        input2 = new Float2Vector(0.6f, 0.2f);
        expected = -0.22f;
        assertEquals(expected, FloatVectorMath.dot(input1, input2), EPSILON);

        input1 = new Float2Vector(1f, 1f);
        input2 = new Float2Vector(0.6f, 0.2f);
        expected = 0.8f;
        assertEquals(expected, FloatVectorMath.dot(input1, input2), EPSILON);

        input1 = new Float2Vector(1f, 1f);
        input2 = new Float2Vector(1f, 1f);
        expected = 2f;
        assertEquals(expected, FloatVectorMath.dot(input1, input2), EPSILON);

        input1 = new Float2Vector(0f, 0f);
        input2 = new Float2Vector(1f, 1f);
        expected = 0f;
        assertEquals(expected, FloatVectorMath.dot(input1, input2), EPSILON);

        input1 = new Float2Vector(Float.NaN, 0f);
        input2 = new Float2Vector(1f, 1f);
        expected = Float.NaN;
        assertEquals(expected, FloatVectorMath.dot(input1, input2), EPSILON);

    }

    @Test
    public final void testDotFloat3VectorFloat3Vector() {
        Float3Vector input1 = new Float3Vector(0.3f, 0.2f, 0.5f);
        Float3Vector input2 = new Float3Vector(0.6f, 0.2f, 0.8f);
        float expected = 0.62f;
        assertEquals(expected, FloatVectorMath.dot(input1, input2), EPSILON);

        input1 = new Float3Vector(-0.3f, -0.2f, -0.5f);
        input2 = new Float3Vector(0.6f, 0.2f, 0.8f);
        expected = -0.62f;
        assertEquals(expected, FloatVectorMath.dot(input1, input2), EPSILON);

        input1 = new Float3Vector(1f, 1f, 1f);
        input2 = new Float3Vector(0.6f, 0.2f, 0.8f);
        expected = 1.6f;
        assertEquals(expected, FloatVectorMath.dot(input1, input2), EPSILON);

        input1 = new Float3Vector(1f, 1f, 1f);
        input2 = new Float3Vector(1f, 1f, 1f);
        expected = 3f;
        assertEquals(expected, FloatVectorMath.dot(input1, input2), EPSILON);

        input1 = new Float3Vector(0f, 0f, 0f);
        input2 = new Float3Vector(1f, 1f, 1f);
        expected = 0f;
        assertEquals(expected, FloatVectorMath.dot(input1, input2), EPSILON);

        input1 = new Float3Vector(Float.NaN, 0f, 0f);
        input2 = new Float3Vector(1f, 1f, 1f);
        expected = Float.NaN;
        assertEquals(expected, FloatVectorMath.dot(input1, input2), EPSILON);
    }

    @Test
    public final void testDotFloat4VectorFloat4Vector() {
        Float4Vector input1 = new Float4Vector(0.3f, 0.2f, 0.5f, 0.1f);
        Float4Vector input2 = new Float4Vector(0.6f, 0.2f, 0.8f, 1.0f);
        float expected = 0.72f;
        assertEquals(expected, FloatVectorMath.dot(input1, input2), EPSILON);

        input1 = new Float4Vector(-0.3f, -0.2f, -0.5f, -0.1f);
        input2 = new Float4Vector(0.6f, 0.2f, 0.8f, 1.0f);
        expected = -0.72f;
        assertEquals(expected, FloatVectorMath.dot(input1, input2), EPSILON);

        input1 = new Float4Vector(1f, 1f, 1f, 1f);
        input2 = new Float4Vector(0.6f, 0.2f, 0.8f, 1.0f);
        expected = 2.6f;
        assertEquals(expected, FloatVectorMath.dot(input1, input2), EPSILON);

        input1 = new Float4Vector(1f, 1f, 1f, 1f);
        input2 = new Float4Vector(1f, 1f, 1f, 1f);
        expected = 4f;
        assertEquals(expected, FloatVectorMath.dot(input1, input2), EPSILON);

        input1 = new Float4Vector(0f, 0f, 0f, 0f);
        input2 = new Float4Vector(1f, 1f, 1f, 1f);
        expected = 0f;
        assertEquals(expected, FloatVectorMath.dot(input1, input2), EPSILON);

        input1 = new Float4Vector(Float.NaN, 0f, 0f, 0f);
        input2 = new Float4Vector(1f, 1f, 1f, 1f);
        expected = Float.NaN;
        assertEquals(expected, FloatVectorMath.dot(input1, input2), EPSILON);
    }

    @Test
    public final void testLengthFloat2Vector() {
        Float2Vector input1 = new Float2Vector(0.3f, 0.2f);
        float expected = 0.360555f;
        assertEquals(expected, FloatVectorMath.length(input1), EPSILON);

        input1 = new Float2Vector(-0.3f, -0.2f);
        expected = 0.360555f;
        assertEquals(expected, FloatVectorMath.length(input1), EPSILON);

        input1 = new Float2Vector(1f, 1f);
        expected = (float) Math.sqrt(2.0);
        assertEquals(expected, FloatVectorMath.length(input1), EPSILON);

        input1 = new Float2Vector(1f, 0f);
        expected = 1f;
        assertEquals(expected, FloatVectorMath.length(input1), EPSILON);

        input1 = new Float2Vector(0f, 0f);
        expected = 0f;
        assertEquals(expected, FloatVectorMath.length(input1), EPSILON);

        input1 = new Float2Vector(Float.NaN, 0f);
        expected = Float.NaN;
        assertEquals(expected, FloatVectorMath.length(input1), EPSILON);
    }

    @Test
    public final void testLengthFloat3Vector() {
        Float3Vector input1 = new Float3Vector(0.3f, 0.2f, 0.5f);
        float expected = 0.616441f;
        assertEquals(expected, FloatVectorMath.length(input1), EPSILON);

        input1 = new Float3Vector(-0.3f, -0.2f, -0.5f);
        expected = 0.616441f;
        assertEquals(expected, FloatVectorMath.length(input1), EPSILON);

        input1 = new Float3Vector(1f, 1f, 1f);
        expected = (float) Math.sqrt(3.0);
        assertEquals(expected, FloatVectorMath.length(input1), EPSILON);

        input1 = new Float3Vector(1f, 0f, 0f);
        expected = 1f;
        assertEquals(expected, FloatVectorMath.length(input1), EPSILON);

        input1 = new Float3Vector(0f, 0f, 0f);
        expected = 0f;
        assertEquals(expected, FloatVectorMath.length(input1), EPSILON);

        input1 = new Float3Vector(Float.NaN, 0f, 0f);
        expected = Float.NaN;
        assertEquals(expected, FloatVectorMath.length(input1), EPSILON);
    }

    @Test
    public final void testLengthFloat4Vector() {
        Float4Vector input1 = new Float4Vector(0.3f, 0.2f, 0.5f, 0.1f);
        float expected = 0.6245f;
        assertEquals(expected, FloatVectorMath.length(input1), EPSILON);

        input1 = new Float4Vector(-0.3f, -0.2f, -0.5f, -0.1f);
        expected = 0.6245f;
        assertEquals(expected, FloatVectorMath.length(input1), EPSILON);

        input1 = new Float4Vector(1f, 1f, 1f, 1f);
        expected = (float) Math.sqrt(4.0);
        assertEquals(expected, FloatVectorMath.length(input1), EPSILON);

        input1 = new Float4Vector(1f, 0f, 0f, 0f);
        expected = 1f;
        assertEquals(expected, FloatVectorMath.length(input1), EPSILON);

        input1 = new Float4Vector(0f, 0f, 0f, 0f);
        expected = 0f;
        assertEquals(expected, FloatVectorMath.length(input1), EPSILON);

        input1 = new Float4Vector(Float.NaN, 0f, 0f, 0f);
        expected = Float.NaN;
        assertEquals(expected, FloatVectorMath.length(input1), EPSILON);
    }

    @Test
    public final void testNormalizeFloat2Vector() {
        Float2Vector input1 = new Float2Vector(0.3f, 0.2f);
        Float2Vector expected = new Float2Vector(0.8320503f, 0.5547002f);
        assertEquals(expected, FloatVectorMath.normalize(input1));

        input1 = new Float2Vector(-0.3f, -0.2f);
        expected = new Float2Vector(-0.8320503f, -0.5547002f);
        assertEquals(expected, FloatVectorMath.normalize(input1));

        input1 = new Float2Vector(1f, 1f);
        expected = new Float2Vector(0.70710677f, 0.70710677f);
        assertEquals(expected, FloatVectorMath.normalize(input1));

        input1 = new Float2Vector(1f, 0f);
        expected = new Float2Vector(1f, 0f);
        assertEquals(expected, FloatVectorMath.normalize(input1));

        input1 = new Float2Vector(0f, 0f);
        expected = new Float2Vector(0f, 0f);
        assertEquals(expected, FloatVectorMath.normalize(input1));

        input1 = new Float2Vector(Float.NaN, 0f);
        expected = new Float2Vector(Float.NaN, Float.NaN);
        assertEquals(expected, FloatVectorMath.normalize(input1));
    }

    @Test
    public final void testNormalizeFloat3Vector() {
        Float3Vector input1 = new Float3Vector(0.3f, 0.2f, 0.5f);
        Float3Vector expected = new Float3Vector(0.48666432f, 0.32444286f, 0.81110716f);
        assertEquals(expected, FloatVectorMath.normalize(input1));

        input1 = new Float3Vector(-0.3f, -0.2f, -0.5f);
        expected = new Float3Vector(-0.48666432f, -0.32444286f, -0.81110716f);
        assertEquals(expected, FloatVectorMath.normalize(input1));

        input1 = new Float3Vector(1f, 1f, 1f);
        expected = new Float3Vector(0.57735026f, 0.57735026f, 0.57735026f);
        assertEquals(expected, FloatVectorMath.normalize(input1));

        input1 = new Float3Vector(1f, 0f, 0f);
        expected = new Float3Vector(1f, 0f, 0f);
        assertEquals(expected, FloatVectorMath.normalize(input1));

        input1 = new Float3Vector(0f, 0f, 0f);
        expected = new Float3Vector(0f, 0f, 0f);
        assertEquals(expected, FloatVectorMath.normalize(input1));

        input1 = new Float3Vector(Float.NaN, 0f, 0f);
        expected = new Float3Vector(Float.NaN, Float.NaN, Float.NaN);
        assertEquals(expected, FloatVectorMath.normalize(input1));
    }

    @Test
    public final void testNormalizeFloat4Vector() {
        Float4Vector input1 = new Float4Vector(0.3f, 0.2f, 0.5f, 0.1f);
        Float4Vector expected = new Float4Vector(0.48038447f, 0.32025632f, 0.80064076f, 0.16012816f);
        assertEquals(expected, FloatVectorMath.normalize(input1));

        input1 = new Float4Vector(-0.3f, -0.2f, -0.5f, -0.1f);
        expected = new Float4Vector(-0.48038447f, -0.32025632f, -0.80064076f, -0.16012816f);
        assertEquals(expected, FloatVectorMath.normalize(input1));

        input1 = new Float4Vector(1f, 1f, 1f, 1f);
        expected = new Float4Vector(0.5f, 0.5f, 0.5f, 0.5f);
        assertEquals(expected, FloatVectorMath.normalize(input1));

        input1 = new Float4Vector(1f, 0f, 0f, 0f);
        expected = new Float4Vector(1f, 0f, 0f, 0f);
        assertEquals(expected, FloatVectorMath.normalize(input1));

        input1 = new Float4Vector(0f, 0f, 0f, 0f);
        expected = new Float4Vector(0f, 0f, 0f, 0f);
        assertEquals(expected, FloatVectorMath.normalize(input1));

        input1 = new Float4Vector(Float.NaN, 0f, 0f, 0f);
        expected = new Float4Vector(Float.NaN, Float.NaN, Float.NaN, Float.NaN);
        assertEquals(expected, FloatVectorMath.normalize(input1));
    }

    @Test
    public final void testCrossFloat3VectorFloat3Vector() {
        Float3Vector input1 = new Float3Vector(0.3f, 0.2f, 0.5f);
        Float3Vector input2 = new Float3Vector(0.6f, 0.2f, 0.8f);
        Float3Vector expected = new Float3Vector(0.06000001f, 0.060000002f, -0.060000002f);
        assertEquals(expected, FloatVectorMath.cross(input1, input2));

        input1 = new Float3Vector(-0.3f, -0.2f, -0.5f);
        input2 = new Float3Vector(0.6f, 0.2f, 0.8f);
        expected = new Float3Vector(-0.06000001f, -0.060000002f, 0.060000002f);
        assertEquals(expected, FloatVectorMath.cross(input1, input2));

        input1 = new Float3Vector(1f, 1f, 1f);
        input2 = new Float3Vector(0.6f, 0.2f, 0.8f);
        expected = new Float3Vector(0.6f, -0.19999999f, -0.40000004f);
        assertEquals(expected, FloatVectorMath.cross(input1, input2));

        input1 = new Float3Vector(1f, 1f, 1f);
        input2 = new Float3Vector(1f, 1f, 1f);
        expected = new Float3Vector();
        assertEquals(expected, FloatVectorMath.cross(input1, input2));

        input1 = new Float3Vector(0f, 0f, 0f);
        input2 = new Float3Vector(1f, 1f, 1f);
        expected = new Float3Vector();
        assertEquals(expected, FloatVectorMath.cross(input1, input2));

        input1 = new Float3Vector(Float.NaN, 0f, 0f);
        input2 = new Float3Vector(1f, 1f, 1f);
        expected = new Float3Vector(0.0f, Float.NaN, Float.NaN);
        assertEquals(expected, FloatVectorMath.cross(input1, input2));
    }

    @Test
    public final void testCrossFloat4VectorFloat4Vector() {
        Float4Vector input1 = new Float4Vector(0.3f, 0.2f, 0.5f, 0.1f);
        Float4Vector input2 = new Float4Vector(0.6f, 0.2f, 0.8f, 1.0f);
        Float4Vector expected = new Float4Vector(0.06000001f, 0.060000002f, -0.060000002f, 0.0f);
        assertEquals(expected, FloatVectorMath.cross(input1, input2));

        input1 = new Float4Vector(-0.3f, -0.2f, -0.5f, -0.1f);
        input2 = new Float4Vector(0.6f, 0.2f, 0.8f, 1.0f);
        expected = new Float4Vector(-0.06000001f, -0.060000002f, 0.060000002f, 0.0f);
        assertEquals(expected, FloatVectorMath.cross(input1, input2));

        input1 = new Float4Vector(1f, 1f, 1f, 1f);
        input2 = new Float4Vector(0.6f, 0.2f, 0.8f, 1.0f);
        expected = new Float4Vector(0.6f, -0.19999999f, -0.40000004f, 0.0f);
        assertEquals(expected, FloatVectorMath.cross(input1, input2));

        input1 = new Float4Vector(1f, 1f, 1f, 1f);
        input2 = new Float4Vector(1f, 1f, 1f, 1f);
        expected = new Float4Vector();
        assertEquals(expected, FloatVectorMath.cross(input1, input2));

        input1 = new Float4Vector(0f, 0f, 0f, 0f);
        input2 = new Float4Vector(1f, 1f, 1f, 1f);
        expected = new Float4Vector();
        assertEquals(expected, FloatVectorMath.cross(input1, input2));

        input1 = new Float4Vector(Float.NaN, 0f, 0f, 0f);
        input2 = new Float4Vector(1f, 1f, 1f, 1f);
        expected = new Float4Vector(0.0f, Float.NaN, Float.NaN, 0.0f);
        assertEquals(expected, FloatVectorMath.cross(input1, input2));
    }

    @Test
    public final void testToBufferFloatArray() {
        float[] input = new float[] { 0f, 1f, 2f, 3f, 4f, 5f, 6f };

        FloatBuffer expected = FloatBuffer.allocate(input.length);
        expected.put(input);
        expected.rewind();

        assertEquals(expected, FloatVectorMath.toBuffer(input));
    }

    @Test
    public final void testToBufferFloat2VectorArray() {
        Float2Vector[] input = new Float2Vector[] { new Float2Vector(0f, 1f), new Float2Vector(2f, 3f),
                new Float2Vector(4f, 5f) };

        float[] expectedArray = new float[] { 0f, 1f, 2f, 3f, 4f, 5f };
        FloatBuffer expected = FloatBuffer.allocate(expectedArray.length);
        expected.put(expectedArray);
        expected.rewind();

        assertEquals(expected, FloatVectorMath.toBuffer(input));
    }

    @Test
    public final void testToBufferFloat3VectorArray() {
        Float3Vector[] input = new Float3Vector[] { new Float3Vector(0f, 1f, 2f), new Float3Vector(3f, 4f, 5f) };

        float[] expectedArray = new float[] { 0f, 1f, 2f, 3f, 4f, 5f };
        FloatBuffer expected = FloatBuffer.allocate(expectedArray.length);
        expected.put(expectedArray);
        expected.rewind();

        assertEquals(expected, FloatVectorMath.toBuffer(input));
    }

    @Test
    public final void testToBufferFloat4VectorArray() {
        Float4Vector[] input = new Float4Vector[] { new Float4Vector(0f, 1f, 2f, 3f), new Float4Vector(3f, 4f, 5f, 6f) };

        float[] expectedArray = new float[] { 0f, 1f, 2f, 3f, 3f, 4f, 5f, 6f };
        FloatBuffer expected = FloatBuffer.allocate(expectedArray.length);
        expected.put(expectedArray);
        expected.rewind();

        assertEquals(expected, FloatVectorMath.toBuffer(input));
    }

    @Test
    public final void testListToBuffer() {
        List<Float> input = new ArrayList<Float>();
        input.add(0f);
        input.add(1f);
        input.add(2f);
        input.add(3f);
        input.add(4f);
        input.add(5f);
        input.add(6f);

        float[] expectedArray = new float[] { 0f, 1f, 2f, 3f, 4f, 5f, 6f };

        FloatBuffer expected = FloatBuffer.allocate(expectedArray.length);
        expected.put(expectedArray);
        expected.rewind();

        assertEquals(expected, FloatVectorMath.listToBuffer(input));
    }

    @Test
    public final void testVec2ListToBuffer() {
        List<Float2Vector> input = new ArrayList<Float2Vector>();
        input.add(new Float2Vector(0f, 0f));
        input.add(new Float2Vector(1f, 0f));
        input.add(new Float2Vector(2f, 0f));
        input.add(new Float2Vector(3f, 0f));
        input.add(new Float2Vector(4f, 0f));
        input.add(new Float2Vector(5f, 0f));
        input.add(new Float2Vector(6f, 0f));

        float[] expectedArray = new float[] { 0f, 0f, 1f, 0f, 2f, 0f, 3f, 0f, 4f, 0f, 5f, 0f, 6f, 0f };

        FloatBuffer expected = FloatBuffer.allocate(expectedArray.length);
        expected.put(expectedArray);
        expected.rewind();

        assertEquals(expected, FloatVectorMath.vec2ListToBuffer(input));
    }

    @Test
    public final void testVec3ListToBuffer() {
        List<Float3Vector> input = new ArrayList<Float3Vector>();
        input.add(new Float3Vector(0f, 0f, 0f));
        input.add(new Float3Vector(1f, 0f, 0f));
        input.add(new Float3Vector(2f, 0f, 0f));
        input.add(new Float3Vector(3f, 0f, 0f));
        input.add(new Float3Vector(4f, 0f, 0f));
        input.add(new Float3Vector(5f, 0f, 0f));
        input.add(new Float3Vector(6f, 0f, 0f));

        float[] expectedArray = new float[] { 0f, 0f, 0f, 1f, 0f, 0f, 2f, 0f, 0f, 3f, 0f, 0f, 4f, 0f, 0f, 5f, 0f, 0f,
                6f, 0f, 0f };

        FloatBuffer expected = FloatBuffer.allocate(expectedArray.length);
        expected.put(expectedArray);
        expected.rewind();

        assertEquals(expected, FloatVectorMath.vec3ListToBuffer(input));
    }

    @Test
    public final void testVec4ListToBuffer() {
        List<Float4Vector> input = new ArrayList<Float4Vector>();
        input.add(new Float4Vector(0f, 0f, 0f, 0f));
        input.add(new Float4Vector(1f, 0f, 0f, 0f));
        input.add(new Float4Vector(2f, 0f, 0f, 0f));
        input.add(new Float4Vector(3f, 0f, 0f, 0f));
        input.add(new Float4Vector(4f, 0f, 0f, 0f));
        input.add(new Float4Vector(5f, 0f, 0f, 0f));
        input.add(new Float4Vector(6f, 0f, 0f, 0f));

        float[] expectedArray = new float[] { 0f, 0f, 0f, 0f, 1f, 0f, 0f, 0f, 2f, 0f, 0f, 0f, 3f, 0f, 0f, 0f, 4f, 0f,
                0f, 0f, 5f, 0f, 0f, 0f, 6f, 0f, 0f, 0f };

        FloatBuffer expected = FloatBuffer.allocate(expectedArray.length);
        expected.put(expectedArray);
        expected.rewind();

        assertEquals(expected, FloatVectorMath.vec4ListToBuffer(input));
    }

    @Test
    public final void testBezierCurve() {
        Float4Vector startPoint = new Float4Vector(0f, 0f, 0f, 0f);
        Float3Vector startControl = new Float3Vector(1f, 0f, 0f);
        Float3Vector endControl = new Float3Vector(0f, 0f, 1f);
        Float4Vector endPoint = new Float4Vector(10f, 10f, 10f, 0f);

        Float4Vector step1 = new Float4Vector(0.0f, 0.0f, 0.0f, 0.0f);
        Float4Vector step2 = new Float4Vector(0.523f, 0.28f, 0.25300002f, 0.0f);
        Float4Vector step3 = new Float4Vector(1.4240001f, 1.0400001f, 0.94400007f, 0.0f);
        Float4Vector step4 = new Float4Vector(2.6010003f, 2.16f, 1.9710002f, 0.0f);
        Float4Vector step5 = new Float4Vector(3.9520004f, 3.52f, 3.2320006f, 0.0f);
        Float4Vector step6 = new Float4Vector(5.375f, 5.0f, 4.6250005f, 0.0f);
        Float4Vector step7 = new Float4Vector(6.768f, 6.48f, 6.0480003f, 0.0f);
        Float4Vector step8 = new Float4Vector(8.029f, 7.84f, 7.399f, 0.0f);
        Float4Vector step9 = new Float4Vector(9.056001f, 8.96f, 8.576f, 0.0f);
        Float4Vector step10 = new Float4Vector(9.747001f, 9.719999f, 9.476999f, 0.0f);

        Float4Vector[] bezierPoints = FloatVectorMath.bezierCurve(10, startPoint, startControl, endControl, endPoint);

        assertEquals(step1, bezierPoints[0]);
        assertEquals(step2, bezierPoints[1]);
        assertEquals(step3, bezierPoints[2]);
        assertEquals(step4, bezierPoints[3]);
        assertEquals(step5, bezierPoints[4]);
        assertEquals(step6, bezierPoints[5]);
        assertEquals(step7, bezierPoints[6]);
        assertEquals(step8, bezierPoints[7]);
        assertEquals(step9, bezierPoints[8]);
        assertEquals(step10, bezierPoints[9]);

    }

    @Test
    public final void testDegreesBezierCurve() {
        Float3Vector startPoint = new Float3Vector(0f, 0f, 0f);
        Float3Vector startControl = new Float3Vector(1f, 0f, 0f);
        Float3Vector endControl = new Float3Vector(0f, 0f, 1f);
        Float3Vector endPoint = new Float3Vector(10f, 10f, 10f);

        Float3Vector[] expected = { new Float3Vector(360.0f, 360.0f, 360.0f),
                new Float3Vector(262.96298f, 262.72f, 262.693f), new Float3Vector(185.74397f, 185.36f, 185.26399f),
                new Float3Vector(126.08098f, 125.64f, 125.451004f), new Float3Vector(81.71199f, 81.28f, 80.99202f),
                new Float3Vector(50.374992f, 49.999996f, 49.625034f),
                new Float3Vector(29.807993f, 29.51999f, 29.088047f),
                new Float3Vector(17.748993f, 17.559984f, 17.11906f),
                new Float3Vector(11.935992f, 11.839972f, 11.456075f),
                new Float3Vector(10.106991f, 10.079955f, 9.83709f) };

        Float3Vector[] bezierPoints = FloatVectorMath.degreesBezierCurve(10, startPoint, startControl, endControl,
                endPoint);

        for (int i = 0; i < 10; i++) {
            assertEquals(expected[i], bezierPoints[i]);
        }

    }

    @Test
    public final void testInterpolateColors() {
        Color4 startColor = new Color4(0f, 0f, 0f, 1f);
        Color4 endColor = new Color4(1f, 1f, 1f, 1f);

        Color4[] expected = { new Color4(0.0f, 0.0f, 0.0f, 1.0f), new Color4(0.1f, 0.1f, 0.1f, 1.0f),
                new Color4(0.2f, 0.2f, 0.2f, 1.0f), new Color4(0.3f, 0.3f, 0.3f, 1.0f),
                new Color4(0.4f, 0.4f, 0.4f, 1.0f), new Color4(0.5f, 0.5f, 0.5f, 1.0f),
                new Color4(0.6f, 0.6f, 0.6f, 1.0f), new Color4(0.7f, 0.7f, 0.7f, 1.0f),
                new Color4(0.8f, 0.8f, 0.8f, 1.0f), new Color4(0.90000004f, 0.90000004f, 0.90000004f, 1.0f) };

        Color4[] result = FloatVectorMath.interpolateColors(10, startColor, endColor);

        for (int i = 0; i < 10; i++) {
            assertEquals(expected[i], result[i]);
        }
    }

}
