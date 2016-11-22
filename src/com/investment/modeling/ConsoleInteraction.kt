package com.investment.modeling

import java.util.*

class ConsoleInteraction(){
    val scanner : Scanner

    init {
        scanner = Scanner(System.`in`)
    }

    private fun printMessage(message: String){
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
        return scanner.nextLine()
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
}