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
import com.beolnix.trpg.cmdargs.impl.DefaultArgumentsParser;
import com.beolnix.trpg.cmdargs.model.CommandLineArgument;
import com.beolnix.trpg.cmdargs.utils.ArgumentsHelper;
import com.beolnix.trpg.cmdargs.utils.CommandLineArgumentBuilder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Map;

/**
 * Created by beolnix on 29/08/15.
 */
public class ArgumentsParserTestCase {

    private CommandLineArgument someArg = new CommandLineArgumentBuilder()
            .withDescription("Description of the argument. " +
                    "The description will be automatically splitted if it longer then 30 chars.")
            .withExample("-s ./path/to/file")
            .withFlags("-s", "--source")
            .build();


    private String expectedCaptionHelpMessage = "" +
            "The following command line arguments are supported:";


    private String expectedSourceHelpMessage = "" +
            "-s,--source      -s ./path/to/file      Description of the argument. The\n" +
            "                                        description will be automatically\n" +
            "                                        splitted if it longer then 30 chars.\n";


    private String expectedHelpMessage = "" +
            "-h,--help        -h                     Prints this help.\n";


    @Test
    public void testTransform() throws UnknownFlag {
        ArgumentsParser argsParser = new DefaultArgumentsParser(getSupportedArgs());
        String[] args = new String[]{"-s", "path"};
        Map<CommandLineArgument, String> passedArgs = argsParser.parse(args);

        assertNotNull(passedArgs);
        assertTrue(passedArgs.containsKey(someArg));
        assertNotNull(passedArgs.get(someArg));
        assertEquals(args[1], passedArgs.get(someArg));
    }

    @Test
    public void testConsistOfHelp() throws UnknownFlag {
        ArgumentsParser argsParser = new DefaultArgumentsParser(getSupportedArgs());
        String[] args = new String[]{"-h"};
        Map<CommandLineArgument, String> passedArgs = argsParser.parse(args);

        assertTrue(new ArgumentsHelper().consistOfHelp(passedArgs));
    }

    @Test
    public void testHelp() throws UnknownFlag {
        ArgumentsParser argsParser = new DefaultArgumentsParser(getSupportedArgs());

        String helpMsg = argsParser.getHelpMessage();

        assertTrue(helpMsg.contains(expectedCaptionHelpMessage));
        assertTrue(helpMsg.contains(expectedSourceHelpMessage));
        assertTrue(helpMsg.contains(expectedHelpMessage));
    }

    @Test(expected = UnknownFlag.class)
    public void testUnkownArgumentException() throws UnknownFlag {
        ArgumentsParser argsParser = new DefaultArgumentsParser(getSupportedArgs());
        String[] args = new String[]{"-vava"};
        Map<CommandLineArgument, String> passedArgs = argsParser.parse(args);
    }

    public CommandLineArgument[] getSupportedArgs() {
        return new CommandLineArgument[] {someArg};
    }

}
