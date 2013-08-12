package nl.esciencecenter.esight.util;

import javax.media.opengl.GLProfile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
 * Holder class for {@link GLProfile} related static functions.
 * 
 * @author Maarten van Meersbergen <m.van.meersbergen@esciencecenter.nl>
 * 
 */
public final class GLProfileSelector {
    private static final Logger LOGGER = LoggerFactory.getLogger(GLProfileSelector.class);

    private GLProfileSelector() {
        // Utility class
    }

    /**
     * Prints the available GLProfiles (hardware defined) to STDOUT
     */
    static final void printAvailable() {
        LOGGER.info(GLProfile.glAvailabilityToString());
    }
}
