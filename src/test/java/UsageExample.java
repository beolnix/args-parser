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

import com.beolnix.trpg.cmdargs.utils.ArgumentsHelper;
import com.beolnix.trpg.cmdargs.ArgumentsParser;
import com.beolnix.trpg.cmdargs.utils.CommandLineArgumentBuilder;
import com.beolnix.trpg.cmdargs.error.UnknownFlag;
import com.beolnix.trpg.cmdargs.impl.DefaultArgumentsParser;
import com.beolnix.trpg.cmdargs.model.CommandLineArgument;

import java.util.Map;

/**
 * Created by beolnix on 29/08/15.
 */
public class UsageExample {

    public static void main(String[] args) throws UnknownFlag {
        // define supported arguments
        CommandLineArgument someArg = new CommandLineArgumentBuilder()
                .withDescription("Description of the argument. " +
                        "The description will be automatically sliced if it longer then 30 chars.")
                .withExample("-s ./path/to/file")
                .withFlags("-s", "--source")
                .build();

        // initialize parser
        ArgumentsParser argsParser = new DefaultArgumentsParser(someArg);

        //parse passed arguments
        Map<CommandLineArgument, String> passedArgs = argsParser.parse(args);

        // check if arg has been passed
        if (passedArgs.containsKey(someArg)) {
            //process some arg passed to the program here
            System.out.println("you passed " + someArg.getFlags()[0] + " " +
                    "with " + passedArgs.get(someArg) + " value");
        }

        // check if help message has been requested
        if (new ArgumentsHelper().consistOfHelp(passedArgs)) {
            //print help message here
            System.out.println(argsParser.getHelpMessage());
        }
    }

}
