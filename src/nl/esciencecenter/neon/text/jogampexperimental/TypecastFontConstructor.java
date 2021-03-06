package nl.esciencecenter.neon.text.jogampexperimental;

/**
 * Copyright 2011 JogAmp Community. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are
 * permitted provided that the following conditions are met:
 *
 *    1. Redistributions of source code must retain the above copyright notice, this list of
 *       conditions and the following disclaimer.
 *
 *    2. Redistributions in binary form must reproduce the above copyright notice, this list
 *       of conditions and the following disclaimer in the documentation and/or other materials
 *       provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY JogAmp Community ``AS IS'' AND ANY EXPRESS OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL JogAmp Community OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * The views and conclusions contained in the software and documentation are those of the
 * authors and should not be interpreted as representing official policies, either expressed
 * or implied, of JogAmp Community.
 */

import java.io.File;
import java.io.IOException;
import java.net.URLConnection;
import java.security.AccessController;
import java.security.PrivilegedAction;

import javax.media.opengl.GLException;

import jogamp.graph.font.typecast.ot.OTFontCollection;
import nl.esciencecenter.neon.exceptions.FontException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jogamp.common.util.IOUtil;

public class TypecastFontConstructor {
    private static final Logger logger = LoggerFactory.getLogger(TypecastFontConstructor.class);

    public Font create(final File ffile) throws IOException {
        return AccessController.doPrivileged(new PrivilegedAction<Font>() {
            @Override
            public Font run() {
                OTFontCollection fontset;
                try {
                    fontset = OTFontCollection.create(ffile);
                    return new TypecastFont(fontset);
                } catch (IOException e) {
                    logger.error(e.getMessage());
                } catch (FontException e) {
                    logger.error(e.getMessage());
                }
                return null;
            }
        });
    }

    public Font create(final URLConnection furl) throws IOException {
        return AccessController.doPrivileged(new PrivilegedAction<Font>() {
            @Override
            public Font run() {
                File tf = null;
                int len = 0;
                Font f = null;
                try {
                    tf = IOUtil.createTempFile("joglfont", ".ttf", false);
                    len = IOUtil.copyURLConn2File(furl, tf);
                    if (len == 0) {
                        if (!tf.delete()) {
                            throw new GLException("Font of stream " + furl + " was zero bytes");
                        }
                    }
                    f = create(tf);
                    tf.delete();
                } catch (IOException e) {
                    logger.error(e.getMessage());
                }
                return f;
            }
        });
    }

}