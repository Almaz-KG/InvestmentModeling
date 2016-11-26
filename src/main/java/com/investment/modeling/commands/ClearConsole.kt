package com.investment.modeling.commands

import com.investment.modeling.ConsoleInteraction
import main.java.com.investment.modeling.commands.Command

class ClearConsole(val cr: ConsoleInteraction) : Command(arrayOf("clear", "cls"), "CLEAR or CLS \t Очистка экрана"){
    override fun getDetailedInfo(): Array<String> {
        return arrayOf("", "Очищает содержимое экрана", "", "CLEAR or CLS", "")
    }

    override fun execute(args: List<String>) {
        cr.clearConsole()
    }
}