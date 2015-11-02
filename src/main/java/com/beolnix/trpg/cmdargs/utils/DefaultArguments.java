package com.beolnix.trpg.cmdargs.utils;

import com.beolnix.trpg.cmdargs.model.CommandLineArgument;
import com.beolnix.trpg.cmdargs.utils.CommandLineArgumentBuilder;


/**
 * Default command line attributes supported by Arguments Parser
 * Created by beolnix on 29/08/15.
 */
public class DefaultArguments {

    public final static CommandLineArgument helpCommandLineArgument = new CommandLineArgumentBuilder()
            .withDescription("Prints this help.")
            .withExample("-h")
            .withFlags("-h", "--help")
            .build();

    private DefaultArguments() {
    }

}
