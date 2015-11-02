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
