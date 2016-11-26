package com.investment.modeling

import main.java.com.investment.modeling.commands.Command
import java.util.*

class ConsoleInteraction(private val scanner : Scanner) {
    var commands = ArrayList<Command>()

    fun printMessage(message: String){
        print(message)
    }
    private fun printlnMessage(message: String){
        printMessage(message+"\n")
    }
    private fun printlnMessageWithCursor(message: String){
        printMessage(message+"\n->")
    }

    fun getUserAnswerString(message: String): String{
        printlnMessageWithCursor(message)

        var result = scanner.nextLine()
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
}