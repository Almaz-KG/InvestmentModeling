package com.investment.modeling.commands

import com.investment.modeling.ConsoleInteraction
import com.investment.modeling.langResourceProvider
import main.java.com.investment.modeling.commands.Command

class ClearConsole(val consoleInteraction: ConsoleInteraction) : Command(
                langResourceProvider.getText("command.clear-console.command-enter"),
                langResourceProvider.getText("command.clear-console.command") + "\t\t" +
                langResourceProvider.getText("command.clear-console.info-short")){

    override fun getDetailedInfo(): Array<String> {
        return arrayOf("",
                langResourceProvider.getText("command.clear-console.info"),
                "",
                langResourceProvider.getText("command.clear-console.command"),
                "")
    }

    override fun execute(args: List<String>) {
        consoleInteraction.clearConsole()
    }
}