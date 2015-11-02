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

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Help message printer is used to get nicely formatted help message.
 * update description size column size and cellPadding to improve look&feel
 * Created by beolnix on 29/08/15.
 */
public class HelpPrinter {

    /**
     * Space between columns
     */
    private int cellPadding = 5;

    /**
     * Too long descriptions are sliced automatically.
     * Description Column Size - is the number of characters allowed on a line in description column.
     */
    private int descriptionColumnSize = 30;

    public HelpPrinter() {
    }

    /**
     * Method provides nicely formatted in a table manner help message for provided arguments
     * @param commandLineArguments arguments to be presented in the help message
     * @return nicely formatted help message
     */
    public String printHelp(Set<CommandLineArgument> commandLineArguments) {

        int argsOffset = getArgsOffset(commandLineArguments);
        int exampleOffset = getExampleOffset(commandLineArguments);

        String mainFormat = "%-" + argsOffset + "s %-" + exampleOffset + "s %s\n";
        int descirptionOffset = argsOffset + exampleOffset + 1;

        StringBuilder builder = new StringBuilder();

        builder.append("The following command line arguments are supported:\n\n");
        builder.append(formatHelp(commandLineArguments, mainFormat, descirptionOffset));

        return builder.toString();
    }

    /**
     * Method generates help message as a table using provided formats
     * @param commandLineArguments arguments to be displayed in the table
     * @param format main format for the row in the table
     * @param descirptionOffset offset required to nicely format description field if it was split
     * @return
     */
    private String formatHelp(Set<CommandLineArgument> commandLineArguments, String format, int descirptionOffset) {
        StringBuilder builder = new StringBuilder();

        builder.append(String.format(format + "\n", "Flags", "Example", "Description"));

        commandLineArguments.stream()
                .forEach(flag -> {

                    String flags = Stream.of(flag.getFlags()).collect(Collectors.joining(","));
                    LinkedList<String> descriptionsSplited = splitByN(flag.getDescription(), descriptionColumnSize);

                    builder.append(String.format(format, flags, flag.getExample(), descriptionsSplited.pop()));
                    descriptionsSplited.stream().forEach(line ->
                                    builder.append(String.format("%" + (line.length() + descirptionOffset) + "s\n", line))
                    );
                    builder.append("\n");
                });

        return builder.toString();
    }

    /**
     * Method split provided lineToSplit by lines trying to make each not more then limit.
     * Lines are split by words so, if there will be long word, the resulting line will also big.
     * @param lineToSplit line to split
     * @param limit limit for the size of each part of the line
     * @return
     */
    private LinkedList<String> splitByN(String lineToSplit, int limit) {
        // better use guava here but according to the limitations i'm not allowed ..

        LinkedList<String> lines = new LinkedList<>();
        StringBuilder currentString = new StringBuilder();
        boolean cutOneNextSpace = false;
        for (int i = 0; i < lineToSplit.length(); i++) {
            char currentChar = lineToSplit.charAt(i);
            if (cutOneNextSpace && Character.isSpaceChar(currentChar)) {
                lines.add(currentString.toString());
                currentString = new StringBuilder();
                cutOneNextSpace = false;
            }

            currentString.append(lineToSplit.charAt(i));
            if (currentString.length() > limit) {
                cutOneNextSpace = true;
            }

        }

        lines.add(currentString.toString());
        return lines;
    }

    /**
     * Method calculates offset required for the args field of the table in help output
     * @param commandLineArguments
     * @return
     */
    private int getArgsOffset(Set<CommandLineArgument> commandLineArguments) {
        return getLargestSize(
                commandLineArguments.stream()
                        .map(flag ->
                                        Stream.of(flag.getFlags())
                                                .collect(Collectors.joining(","))
                        )
        );
    }

    /**
     * Method calculates offset required for the examples field of the table in help output
     * @param commandLineArguments
     * @return
     */
    private int getExampleOffset(Set<CommandLineArgument> commandLineArguments) {
        return getLargestSize(
                commandLineArguments.stream()
                        .map(flag -> flag.getExample())
        );
    }

    /**
     * returns size of the largest string from the input stream
     * @param stream input stream
     * @return size of the largest string
     */
    private int getLargestSize(Stream<String> stream) {
        return stream.collect(
                Collectors.maxBy(
                        Comparator.comparingInt(arg -> arg.length())
                )
            ).get().length() + cellPadding;
    }

    public int getCellPadding() {
        return cellPadding;
    }

    public void setCellPadding(int cellPadding) {
        this.cellPadding = cellPadding;
    }

    public int getDescriptionColumnSize() {
        return descriptionColumnSize;
    }

    public void setDescriptionColumnSize(int descriptionColumnSize) {
        this.descriptionColumnSize = descriptionColumnSize;
    }
}
