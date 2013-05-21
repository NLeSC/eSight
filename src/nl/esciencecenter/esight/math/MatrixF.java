package nl.esciencecenter.esight.math;

import java.nio.FloatBuffer;

/* Copyright [2013] [Netherlands eScience Center]
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
 * Abstract class for all Matrices that provides several utility functions.
 * 
 * @author Maarten van Meersbergen <m.van.meersbergen@esciencecenter.nl>
 * 
 */
public abstract class MatrixF {
    /** The main storage array for this matrix. */
    protected float m[];

    /** The same storage, but in FloatBuffer form. */
    protected FloatBuffer buf;

    protected int size;

    /**
     * Basic constructor for MatrixF.
     * 
     * @param size
     *            the number of floats to be stored in this matrix.
     */
    protected MatrixF(int size) {
        this.size = size;
        m = new float[size];
        buf = FloatBuffer.wrap(m);
        buf.rewind();
    }

    /**
     * Returns the flattened Array associated with this matrix.
     * 
     * @return This matrix as a flat Array.
     */
    public float[] asArray() {
        return m;
    }

    /**
     * Returns the FloatBuffer associated with this matrix.
     * 
     * @return This matrix as a FloatBuffer.
     */
    public FloatBuffer asBuffer() {
        buf.rewind();
        return buf;
    }

    /**
     * Returns the value of this matrix at position i,j.
     * 
     * @param i
     *            The column.
     * @param j
     *            The row.
     * @return The value at index i,j.
     */
    public float get(int i, int j) {
        int rowSize = (int) Math.sqrt(m.length);
        return m[i * rowSize + j];
    }

    /**
     * Returns the value of this matrix at position i.
     * 
     * @param i
     *            The index.
     * @return The value at index i.
     */
    public float get(int i) {
        return m[i];
    }

    /**
     * Sets the value of this matrix at position i,j.
     * 
     * @param i
     *            The column.
     * @param j
     *            The row.
     * @param f
     *            The new value.
     */
    public void set(int i, int j, float f) {
        int rowSize = (int) Math.sqrt(m.length);
        m[i * rowSize + j] = f;
    }

    /**
     * Sets the value of this matrix at position i.
     * 
     * @param i
     *            The column.
     * @param f
     *            The new value.
     */
    public void set(int i, float f) {
        m[i] = f;
    }

    @Override
    public String toString() {
        int rowSize = (int) Math.sqrt(m.length);
        String result = "";

        for (int i = 0; i < m.length; i++) {
            if (i != 0 && i % rowSize == 0)
                result += "\n";

            result += m[i] + " ";
        }

        return result;
    }

    @Override
    public int hashCode() {
        int rowsAndCols = (int) Math.sqrt(size);

        int hashCode = 1;
        for (int i = 0; i < rowsAndCols; ++i) {
            for (int j = 0; j < rowsAndCols; ++j) {
                int v = Float.floatToIntBits(m[i * rowsAndCols + j]);
                int valHash = v ^ (v >>> 32);
                hashCode = 31 * hashCode + valHash;
            }
        }
        return hashCode;
    }

    @Override
    public boolean equals(Object thatObject) {
        if (this == thatObject)
            return true;
        if (!(thatObject instanceof MatrixF))
            return false;

        // cast to native object is now safe
        MatrixF that = (MatrixF) thatObject;

        // now a proper field-by-field evaluation can be made
        boolean same = true;
        for (int i = 0; i < size; i++) {
            if (m[i] < that.m[i] - MatrixFMath.EPSILON || m[i] > that.m[i] + MatrixFMath.EPSILON) {
                same = false;
            }
        }
        return same;
    }
}
