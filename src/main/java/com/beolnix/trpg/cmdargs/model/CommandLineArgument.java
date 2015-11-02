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

package com.beolnix.trpg.cmdargs.model;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Model describes details of supported Command Line Argument
 * Created by beolnix on 29/08/15.
 */
public class CommandLineArgument {

    private final String description;
    private final String example;
    private final String[] flags;

    public CommandLineArgument(String description, String example, String[] flags) {
        this.description = description;
        this.example = example;
        this.flags = flags;
    }

    public boolean consistFlag(String flagStr) {
        return Stream.of(this.flags)
                .anyMatch(flag -> flag.equals(flagStr));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommandLineArgument that = (CommandLineArgument) o;

        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (example != null ? !example.equals(that.example) : that.example != null) return false;
        if (!Arrays.equals(flags, that.flags)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = description != null ? description.hashCode() : 0;
        result = 31 * result + (example != null ? example.hashCode() : 0);
        result = 31 * result + (flags != null ? Arrays.hashCode(flags) : 0);
        return result;
    }

    public String getDescription() {
        return description;
    }

    public String getExample() {
        return example;
    }

    public String[] getFlags() {
        return flags;
    }

}
