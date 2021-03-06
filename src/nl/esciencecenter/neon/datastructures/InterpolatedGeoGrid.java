package nl.esciencecenter.neon.datastructures;

import java.util.ArrayList;
import java.util.List;

import nl.esciencecenter.neon.math.Float2Vector;
import nl.esciencecenter.neon.math.FloatVectorMath;

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
 * Experimental class to create an interpolated grid out of points.
 * 
 * @author Maarten van Meersbergen <m.van.meersbergen@esciencecenter.nl>
 * 
 */
public class InterpolatedGeoGrid {

    /**
     * Data storage format for the individual points.
     * 
     * @author Maarten van Meersbergen <m.van.meersbergen@esciencecenter.nl>
     * 
     */
    private class GridPointData {
        float latitude;
        float longitude;
        float[] data;

        public GridPointData(float latitude, float longitude, float[] data) {
            this.latitude = latitude;
            this.longitude = longitude;
            this.data = data;
        }

        public float[] getData() {
            return data;
        }

        public float distanceTo(Float2Vector targetCoordinates) {
            Float2Vector myCoords = new Float2Vector(longitude, latitude);
            float distance = FloatVectorMath.length(myCoords.sub(targetCoordinates));

            return distance;
        }

        public int compareTo(Float2Vector targetCoordinates, GridPointData other) {
            final int BEFORE = -1;
            final int EQUAL = 0;
            final int AFTER = 1;

            if (other == null) {
                return BEFORE;
            }

            if (this == other) {
                return EQUAL;
            }

            float distanceThis = distanceTo(targetCoordinates);
            float distanceOther = other.distanceTo(targetCoordinates);

            if (distanceThis < distanceOther) {
                return BEFORE;
            } else if (distanceThis > distanceOther) {
                return AFTER;
            }

            return EQUAL;
        }
    }

    private final int numVisualGridPoints;

    private final List<GridPointData> dataGrid;
    private final int numberOfLongitudeCoordinates;
    private final int numberOfLatitudeCoordinates;

    public InterpolatedGeoGrid(int width, int height) {
        dataGrid = new ArrayList<GridPointData>();

        numberOfLongitudeCoordinates = width;
        numberOfLatitudeCoordinates = height;

        numVisualGridPoints = width * height;
    }

    public void addData(float lat, float lon, float[] data) {
        dataGrid.add(new GridPointData(lat, lon, data));
    }

    public float[][] calculate() {
        if (dataGrid.size() > 4) {
            int numDataFields = dataGrid.get(0).getData().length;
            float[][] visualGridData = new float[numVisualGridPoints][numDataFields];

            float[] maxes = new float[numDataFields];
            float[] mins = new float[numDataFields];

            for (int dataIndex = 0; dataIndex < numDataFields; dataIndex++) {
                maxes[dataIndex] = Float.MIN_VALUE;
                mins[dataIndex] = Float.MAX_VALUE;

                for (GridPointData gpd : dataGrid) {
                    for (float singleDataPoint : gpd.getData()) {
                        if (singleDataPoint > maxes[dataIndex]) {
                            maxes[dataIndex] = singleDataPoint;
                        }
                        if (singleDataPoint < mins[dataIndex]) {
                            mins[dataIndex] = singleDataPoint;
                        }
                    }
                }
            }

            for (int latIndex = 0; latIndex < numberOfLatitudeCoordinates; latIndex++) {
                float gridPointLatitude = (float) latIndex / (float) numberOfLatitudeCoordinates;

                for (int lonIndex = 0; lonIndex < numberOfLongitudeCoordinates; lonIndex++) {
                    int visualIndex = latIndex * numberOfLatitudeCoordinates + lonIndex;

                    float gridPointLongitude = (float) lonIndex / (float) numberOfLongitudeCoordinates;
                    Float2Vector targetCoordinates = new Float2Vector(gridPointLongitude, gridPointLatitude);

                    GridPointData[] closestDataPoints = determineClosestData(targetCoordinates);

                    float[] weightedAverages = dataWeigthedAverages(targetCoordinates, closestDataPoints);
                    for (int dataIndex = 0; dataIndex < numDataFields; dataIndex++) {
                        float diff = maxes[dataIndex] - mins[dataIndex];
                        visualGridData[visualIndex][dataIndex] = (weightedAverages[dataIndex] / diff) + mins[dataIndex];
                    }
                }
            }

            return visualGridData;
        }

        return null;
    }

    private float[] dataWeigthedAverages(Float2Vector targetCoordinates, GridPointData[] input) {
        int numDataFields = input[0].getData().length;

        float totalValue;
        float totalWeight;
        float weightedAverages[] = new float[numDataFields];

        for (int i = 0; i < numDataFields; i++) {
            totalValue = 0f;
            totalWeight = 0f;

            for (int inputIndex = 0; inputIndex < input.length; inputIndex++) {
                float[] inputData = input[inputIndex].getData();
                float distance = input[inputIndex].distanceTo(targetCoordinates);

                float weight = 1f / (distance * distance);

                totalValue += inputData[i] * weight;
                totalWeight += weight;
            }
            weightedAverages[i] = totalValue / totalWeight;
        }

        return weightedAverages;
    }

    private GridPointData[] determineClosestData(Float2Vector targetCoordinates) {
        GridPointData[] closestPoints = new GridPointData[4];

        // Determine the 4 closest points that we have actual data about
        closestPoints[0] = null;
        closestPoints[1] = null;
        closestPoints[2] = null;
        closestPoints[3] = null;

        for (GridPointData gpd : dataGrid) {
            int compareTo0 = gpd.compareTo(targetCoordinates, closestPoints[0]);
            int compareTo1 = gpd.compareTo(targetCoordinates, closestPoints[1]);
            int compareTo2 = gpd.compareTo(targetCoordinates, closestPoints[2]);
            int compareTo3 = gpd.compareTo(targetCoordinates, closestPoints[3]);

            if (compareTo0 == -1 || compareTo0 == 0) {
                closestPoints[3] = closestPoints[2];
                closestPoints[2] = closestPoints[1];
                closestPoints[1] = closestPoints[0];
                closestPoints[0] = gpd;
            } else if (compareTo1 == -1 || compareTo1 == 0) {
                closestPoints[3] = closestPoints[2];
                closestPoints[2] = closestPoints[1];
                closestPoints[1] = gpd;
            } else if (compareTo2 == -1 || compareTo2 == 0) {
                closestPoints[3] = closestPoints[2];
                closestPoints[2] = gpd;
            } else if (compareTo3 == -1 || compareTo3 == 0) {
                closestPoints[3] = gpd;
            }
        }

        return closestPoints;
    }

}
