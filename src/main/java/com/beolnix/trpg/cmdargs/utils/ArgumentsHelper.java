package com.beolnix.trpg.cmdargs.utils;

import com.beolnix.trpg.cmdargs.model.CommandLineArgument;


import java.util.Map;

import static com.beolnix.trpg.cmdargs.utils.DefaultArguments.helpCommandLineArgument;

/**
 * Helper is used to identify whether specific command line arguments are provided or not.
 * Created by beolnix on 29/08/15.
 */
public class ArgumentsHelper {

    public ArgumentsHelper() {
    }

    /**
     * Method returns true if help argument was provided
     * @param passedArgs
     * @return
     */
    public boolean consistOfHelp(Map<CommandLineArgument, String> passedArgs) {
        return passedArgs.containsKey(helpCommandLineArgument);
    }

}
