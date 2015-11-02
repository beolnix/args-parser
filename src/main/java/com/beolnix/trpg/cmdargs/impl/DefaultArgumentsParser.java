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

package com.beolnix.trpg.cmdargs.impl;

import com.beolnix.trpg.cmdargs.ArgumentsParser;
import com.beolnix.trpg.cmdargs.utils.HelpPrinter;
import com.beolnix.trpg.cmdargs.error.UnknownFlag;
import com.beolnix.trpg.cmdargs.model.CommandLineArgument;

import java.util.*;

import static com.beolnix.trpg.cmdargs.utils.DefaultArguments.helpCommandLineArgument;



/**
 * Default implementation of ArgumentsParser interface
 * Created by beolnix on 29/08/15.
 */
public class DefaultArgumentsParser implements ArgumentsParser {

    private final Set<CommandLineArgument> supportedCommandLineArguments = new HashSet<>();
    private final HelpPrinter helpPrinter = new HelpPrinter();

    public DefaultArgumentsParser(CommandLineArgument... supportedCommandLineArguments) {
        this.supportedCommandLineArguments.addAll(Arrays.asList(supportedCommandLineArguments));
        this.supportedCommandLineArguments.add(helpCommandLineArgument);
    }

    @Override
    public Map<CommandLineArgument, String> parse(String[] args) throws UnknownFlag {
        if (args.length == 0) {
            return Collections.emptyMap();
        }

        LinkedList<String> argsList = new LinkedList<>(Arrays.asList(args));
        Map<CommandLineArgument, String> passedArgumentMap = new HashMap<>();
        while(!argsList.isEmpty()) {
            String flagStr = argsList.pop();
            CommandLineArgument commandLineArgument = parseArg(flagStr);
            if (!argsList.isEmpty()) {
                passedArgumentMap.put(commandLineArgument, argsList.pop());
            } else {
                passedArgumentMap.put(commandLineArgument, null);
            }

        }

        return passedArgumentMap;
    }

    @Override
    public Set<CommandLineArgument> getSupportedCommandLineArguments() {
        return supportedCommandLineArguments;
    }

    @Override
    public String getHelpMessage() {
        return helpPrinter.printHelp(supportedCommandLineArguments);
    }

    /**
     * Method searches for the corresponding commandLineArgument which describes provided argument
     * @param arg provided argument
     * @return commandLineArgument instance in case of success
     * @throws UnknownFlag in case corresponding commandLineArgument not found
     */
    private CommandLineArgument parseArg(String arg) throws UnknownFlag {
        for (CommandLineArgument argument : supportedCommandLineArguments) {
            if (argument.consistFlag(arg)) {
                return argument;
            }
        }

        throw new UnknownFlag(arg);
    }
}
