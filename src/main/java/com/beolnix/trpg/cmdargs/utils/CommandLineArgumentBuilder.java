/* The MIT License
 *
 * Copyright (c) 2010-2015 Danila Atmakin (atmakin.dv@gmail.com)
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
 *
 */

package com.beolnix.trpg.cmdargs.utils;

import com.beolnix.trpg.cmdargs.model.CommandLineArgument;

/**
 * Builder for CommandLineArgument - simplifies arguments creation a bit.
 * Created by beolnix on 11/2/2015.
 */
public class CommandLineArgumentBuilder {
    private String description;
    private String example;
    private String[] flags;

    public CommandLineArgumentBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public CommandLineArgumentBuilder withExample(String example) {
        this.example = example;
        return this;
    }

    public CommandLineArgumentBuilder withFlags(String... flags) {
        this.flags = flags;
        return this;
    }

    public CommandLineArgument build() {
        return new CommandLineArgument(description, example, flags);
    }

}
