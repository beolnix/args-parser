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

package com.beolnix.trpg.cmdargs;

import com.beolnix.trpg.cmdargs.error.UnknownFlag;
import com.beolnix.trpg.cmdargs.model.CommandLineArgument;

import java.util.Map;
import java.util.Set;

/**
 * Arguments Parser interface.
 * Parser is used to parse provided command lines parameters into the Passed Arguments objects
 * It also provides functionality to nicely print supported command line arguments.
 * Created by beolnix on 29/08/15.
 */
public interface ArgumentsParser {

    /**
     * Parses passed command line arguments. Returns a map with provided command line arguments.
     * Throws exception if unsupported argument found.
     *
     * @param args array of command line arguments
     * @return not null Map of parsed command like arguments
     * @throws UnknownFlag if unsupported argument found.
     */
    public Map<CommandLineArgument, String> parse(String[] args) throws UnknownFlag;

    /**
     * Return supported command line arguments
     * @return supported command line arguments
     */
    public Set<CommandLineArgument> getSupportedCommandLineArguments();

    /**
     * Generates help message based on supported arguments.
     * @return help message based on supported arguments.
     */
    public String getHelpMessage();

}
