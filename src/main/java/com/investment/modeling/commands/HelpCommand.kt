package main.java.com.investment.modeling.commands

import com.investment.modeling.ConsoleInteraction
import com.investment.modeling.langResourceProvider
import java.util.*


class HelpCommand(val consoleInteraction: ConsoleInteraction, val commands: ArrayList<Command>) :
        Command(langResourceProvider.getText("command.help.command-enter"),
                langResourceProvider.getText("command.help.command") + "\t\t" +
                        langResourceProvider.getText("command.help.info")) {
    override fun getDetailedInfo(): Array<String> {
        return arrayOf(
                "",
                langResourceProvider.getText("command.help.detailed.m1"),
                "",
                "\t" +
                        langResourceProvider.getText("command.help.command"),
                "\t\t" +
                        langResourceProvider.getText("command.help.detailed.m2"),
                "\t" +
                        langResourceProvider.getText("command.help.command") +
                        " " +
                        langResourceProvider.getText("command.help.detailed.m3"),
                "\t\t" +
                        langResourceProvider.getText("command.help.detailed.m3") +
                        " - " +
                        langResourceProvider.getText("command.help.detailed.m4"),
                "")
    }


    /**
     *  Expected command line:
     *      HELP ARG1 ARG2 ... ARG_N
     */
    override fun execute(args: List<String>) {
        if (args.size == 0)
            throw IllegalArgumentException(langResourceProvider.getText("command.help.error.unknown-command"))
        if (false == "${langResourceProvider.getText("command.help.command-enter")}".equals(args[0].toLowerCase()))
            throw IllegalArgumentException("${langResourceProvider.getText("command.help.error.illegal-argument-command")} ${args[0]}")

        if (args.size == 1)
            printGlobalHelp()
        else {
            val args = args.distinct()

            for (i in 1..args.size - 1)
                printHelpForCommand(args[i])

        }
    }

    private fun printHelpForCommand(command: String) {
        val availableCommand = commands.filter { c -> c.support(command) }
        if (availableCommand.size == 0) {
            consoleInteraction.printlnMessage(langResourceProvider.getText("command.help.error.unsupported-command"))
            return
        }

        availableCommand.forEach { c ->
            c.getDetailedInfo()
                    .forEach { e -> consoleInteraction.printlnMessage(e) }
        }
    }

    private fun printGlobalHelp() {
        for (i in 0..commands.size - 1)
            consoleInteraction.printlnMessage("${i + 1}. ${commands[i].description}")
    }
}