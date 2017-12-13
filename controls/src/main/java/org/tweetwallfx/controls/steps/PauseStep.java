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
package org.tweetwallfx.controls.steps;

import java.time.Duration;
import org.tweetwallfx.controls.stepengine.Step;
import org.tweetwallfx.controls.stepengine.StepEngine.MachineContext;

/**
 * @author Sven Reimers
 */
public class PauseStep implements Step {

    private final Duration pause;

    private PauseStep(final Duration pause) {
        this.pause = pause;
    }

    @Override
    public Duration preferredStepDuration(final MachineContext context) {
        return this.pause;
    }

    @Override
    public void doStep(final MachineContext context) {
        context.proceed();
    }

    /**
     * Implementation of {@link Step.Factory} as Service implementation creating
     * {@link PauseStep}.
     */
    public static final class Factory implements Step.Factory {

        @Override
        public PauseStep create() {
            return new PauseStep(Duration.ofSeconds(5));
        }

        @Override
        public Class<PauseStep> getStepClass() {
            return PauseStep.class;
        }
    }
}
