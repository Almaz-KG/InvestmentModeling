package com.investment.modeling

import jline.console.ConsoleReader
import main.java.com.investment.modeling.commands.Command
import java.util.*

class ConsoleInteraction(private val consoleReader : ConsoleReader) {

    var commands = ArrayList<Command>()

    fun printColumns(values: List<String>){
        consoleReader.printColumns(values)
    }

    fun printMessage(message: String){
        consoleReader.print(message)
        consoleReader.flush()
    }
    fun printlnMessage(message: String){
        printMessage(message)
        consoleReader.println()
    }
    private fun printlnMessageWithCursor(message: String){
        printMessage(message)
        consoleReader.println()
        printMessage("->")
    }

    fun getUserAnswerString(message: String): String{
        printlnMessageWithCursor(message)

        var result = consoleReader.readLine()

        if(result.isEmpty())
            return getUserAnswerString(message)
        if(isCommand(result)) {
            executeCommand(result)
            return getUserAnswerString(message)
        }
        return result
    }
    fun getUserAnswerInt(message: String): Int{
        try {
            val answer = getUserAnswerString(message)
            return Integer.parseInt(answer)
        } catch (e: NumberFormatException){
            printlnMessage(langResourceProvider.getText("console.interaction.cannot-convert"))

            if (message.endsWith(langResourceProvider.getText("console.interaction.whole-number"), true))
                return getUserAnswerInt(message)
            else
                return getUserAnswerInt(message + " " + langResourceProvider.getText("console.interaction.whole-number"))
        }
    }

    fun isCommand(answer: String): Boolean {
        var command = answer.split(" ")[0]
        return commands.any { it.support(command) }
    }

    fun executeCommand(command: String) {
        var cmd = command.split(" ")[0]
        commands
                .filter { it.support(cmd) }
                .forEach { it.execute(command.split(" ")) }
    }

    fun clearConsole() {
        consoleReader.clearScreen()
    }

    fun readKey(){
        var command = consoleReader.readLine()
        if(isCommand(command))
            executeCommand(command)
    }
}