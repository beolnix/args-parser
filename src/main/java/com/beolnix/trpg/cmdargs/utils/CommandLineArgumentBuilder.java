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
