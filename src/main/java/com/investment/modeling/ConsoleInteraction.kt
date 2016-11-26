package com.investment.modeling

import jline.console.ConsoleReader
import main.java.com.investment.modeling.commands.Command
import java.util.*

class ConsoleInteraction(private val consoleReader : ConsoleReader) {

    var commands = ArrayList<Command>()

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
            printlnMessage("Не удается сконвертировать в целое число")
            return getUserAnswerInt(message + (" (целое число)"))
        }
    }

    fun isCommand(answer: String): Boolean {
        return commands.any { it.support(answer) }
    }

    fun executeCommand(command: String) {
        commands
                .filter { it.support(command) }
                .forEach { it.execute(command) }
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