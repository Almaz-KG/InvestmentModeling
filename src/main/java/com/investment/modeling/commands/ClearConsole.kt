package com.investment.modeling.commands

import com.investment.modeling.ConsoleInteraction
import main.java.com.investment.modeling.commands.Command

class ClearConsole(val cr: ConsoleInteraction) : Command(arrayOf("clear", "cls"), "CLEAR or CLS \t Очистка экрана"){
    override fun execute(command: String) {
        cr.clearConsole()
    }
}