/*
 * The MIT License
 *
 * Copyright 2014-2017 TweetWallFX
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.tweetwallfx.config;

import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

/**
 * Configures Logging. It does this exactly once.
 */
public class LoggingConfigurator {

    private static final AtomicBoolean ALREADY_CONFIGURED = new AtomicBoolean(false);

    private LoggingConfigurator() {
        // prevent instantiation
    }

    /**
     * Configures Logging in case it has not been configured via this method
     * before.
     */
    public static void configure() {
        if (ALREADY_CONFIGURED.compareAndSet(false, true)) {
            final File log4jFile = new File("log4j2.xml");

            if (log4jFile.isFile()) {
                LoggerContext context = (LoggerContext) LogManager.getContext(false);
                context.setConfigLocation(log4jFile.toURI());
            } else {
                final Logger logger = LogManager.getLogger(LoggingConfigurator.class);
                logger.info("log4j configuration file ('" + log4jFile.getAbsolutePath() + "') not found.");
            }
        }
    }
}
