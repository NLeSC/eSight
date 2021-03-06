package nl.esciencecenter.neon.models;

import java.util.ArrayList;
import java.util.List;

import nl.esciencecenter.neon.math.Float3Vector;
import nl.esciencecenter.neon.math.Float4Vector;
import nl.esciencecenter.neon.math.FloatVectorMath;

/* Copyright [2013] [Netherlands eScience Center]
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
 * Sphere implementation (based on the code in the OpenGL red book) of the Model
 * class implemented by subdivision of a regular isocahedron. Minimum number of
 * vertices is therefore 20. Allows for multiple Levels of Detail, each level
 * multiplying the amount of vertices by 4 as compared to the previous level.
 * 
 * nr_of_vertices = 20 * (divisions ^ 4) * 3
 * 
 * @author Maarten van Meersbergen <m.vanmeersbergen@esciencecenter.nl>
 */
public class Sphere extends Model {
    private static final float X = 0.525731112119133606f;
    private static final float Z = 0.850650808352039932f;

    private static final Float3Vector[] vdata = { new Float3Vector(-X, 0f, Z), new Float3Vector(X, 0f, Z), new Float3Vector(-X, 0f, -Z),
            new Float3Vector(X, 0f, -Z), new Float3Vector(0f, Z, X), new Float3Vector(0f, Z, -X), new Float3Vector(0f, -Z, X),
            new Float3Vector(0f, -Z, -X), new Float3Vector(Z, X, 0f), new Float3Vector(-Z, X, 0f), new Float3Vector(Z, -X, 0f),
            new Float3Vector(-Z, -X, 0f) };

    private static final int[][] tindices = { { 1, 4, 0 }, { 4, 9, 0 }, { 4, 5, 9 }, { 8, 5, 4 }, { 1, 8, 4 },
            { 1, 10, 8 }, { 10, 3, 8 }, { 8, 3, 5 }, { 3, 2, 5 }, { 3, 7, 2 }, { 3, 10, 7 }, { 10, 6, 7 },
            { 6, 11, 7 }, { 6, 0, 11 }, { 6, 1, 0 }, { 10, 1, 6 }, { 11, 0, 9 }, { 2, 11, 9 }, { 5, 2, 9 },
            { 11, 2, 7 } };

    /**
     * Basic constructor for Sphere. Allows for multiple levels of detail.
     * 
     * @param divisions
     *            The number of divisions for the isocahedron. nr_of_vertices =
     *            20 * (divisions ^ 4) * 3
     */
    public Sphere(int divisions, boolean texCoordsIn3D) {
        super(VertexFormat.TRIANGLES);

        List<Float3Vector> points3List = new ArrayList<Float3Vector>();

        for (int i = 0; i < tindices.length; i++) {
            makeVertices(points3List, vdata[tindices[i][0]], vdata[tindices[i][1]], vdata[tindices[i][2]], divisions);
        }

        List<Float4Vector> points4List = new ArrayList<Float4Vector>();

        for (int i = 0; i < points3List.size(); i++) {
            points4List.add(new Float4Vector(points3List.get(i), 1f));
        }

        setNormals(FloatVectorMath.vec3ListToBuffer(points3List));
        if (texCoordsIn3D) {
            setTexCoords(FloatVectorMath.vec3ListToBuffer(points3List));
        } else {
            ArrayList<Float3Vector> texCoords2D = new ArrayList<Float3Vector>();
            float minPhi = 0f, maxPhi = 0f;
            float maxTheta = 0f;
            for (Float3Vector point : points3List) {
                double x = point.getX();
                double y = point.getY();
                double z = point.getZ();

                float phi = (float) ((Math.atan(x / z) + (0.5 * Math.PI)) / Math.PI);
                float theta = (float) (Math.atan(Math.sqrt(z * z + x * x) / y) / Math.PI);

                if (phi < minPhi) {
                    minPhi = phi;
                }
                if (phi > maxPhi) {
                    maxPhi = phi;
                }
                if (theta < 0) {
                    theta = 1.0f + theta;
                }
                if (theta > maxTheta) {
                    maxTheta = theta;
                }
                texCoords2D.add(new Float3Vector(phi, theta, 0f));
            }

            setTexCoords(FloatVectorMath.vec3ListToBuffer(texCoords2D));
        }
        setVertices(FloatVectorMath.vec4ListToBuffer(points4List));

        setNumVertices(points3List.size());
    }

    /**
     * Helper method to produce the vertices for a triangular part of the
     * sphere.
     * 
     * @param pointsList
     *            _output_ array for the vertex data.
     * @param a
     *            Point A of the triangle.
     * @param b
     *            Point B of the triangle.
     * @param c
     *            Point C of the triangle.
     * @param div
     *            The The number of divisions for this triangle.
     */
    private void makeVertices(List<Float3Vector> pointsList, Float3Vector a, Float3Vector b, Float3Vector c, int div) {
        if (div <= 0) {
            pointsList.add(a);
            pointsList.add(b);
            pointsList.add(c);
        } else {
            Float3Vector ab = new Float3Vector();
            Float3Vector ac = new Float3Vector();
            Float3Vector bc = new Float3Vector();

            ab.setX((a.getX() + b.getX()));
            ac.setX((a.getX() + c.getX()));
            bc.setX((b.getX() + c.getX()));

            ab.setY((a.getY() + b.getY()));
            ac.setY((a.getY() + c.getY()));
            bc.setY((b.getY() + c.getY()));

            ab.setZ((a.getZ() + b.getZ()));
            ac.setZ((a.getZ() + c.getZ()));
            bc.setZ((b.getZ() + c.getZ()));

            ab = FloatVectorMath.normalize(ab);
            ac = FloatVectorMath.normalize(ac);
            bc = FloatVectorMath.normalize(bc);

            makeVertices(pointsList, a, ab, ac, div - 1);
            makeVertices(pointsList, b, bc, ab, div - 1);
            makeVertices(pointsList, c, ac, bc, div - 1);
            makeVertices(pointsList, ab, bc, ac, div - 1);
        }
    }
}
