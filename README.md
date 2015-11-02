# Simple command line argumentes parser

## Project details
Stable version - [args-parser-0.3.jar](http://nexus.beolnix.com/service/local/repositories/releases/content/com/beolnix/args-parser/0.3/args-parser-0.3.jar)

## Adding parser to your project
### Maven
To use binaries just add the following lines into the pom.xml file of your maven project:
```xml
<repositories>
    <repository>
        <id>beolnix-releases</id>
        <name>releases repo</name>
        <url>http://nexus.beolnix.com/content/repositories/releases/</url>
    </repository>
</repositories>

<dependencies>
     <dependency>
        <groupId>com.beolnix</groupId>
        <artifactId>args-parser</artifactId>
        <version>0.3</version>
     </dependency>
</dependencies>
```

### Gradle
Or if you are using Gradle just add the following into the build.gradle of your project:
```groovy
repositories {
    maven {
        url "http://nexus.beolnix.com/content/repositories/releases/"
    }
}
dependencies {
    compile "com.beolnix:args-parser:0.3"
}
```

### Binaries
You may also simply download [args-parser-0.3.jar](http://nexus.beolnix.com/service/local/repositories/releases/content/com/beolnix/args-parser/0.3/args-parser-0.3.jar) and just add it to your classpath.

### Build from sources
Project build prerequirements:
1. JDK 1.8+
2. Maven 3.2+

Simply execute the following to build the project and may the force be with you
```bash
mvn clean install
```

## Usage
Java usage example:
```java
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
        if (passedArgs != null && passedArgs.containsKey(someArg)) {
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
```

Please find help message example below:
```bash
The following command line arguments are supported:

Flags             Example                  Description

-v,--version      -v                       Prints version.

-h,--help         -h                       Prints this help.

-g,--game         -g ./saved_game.txt      Path to the file with saved game.
                                           If not provided starts new game
                                           and saves the progress to the current
                                           dir.
```