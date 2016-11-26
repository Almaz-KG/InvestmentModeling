package main.java.com.investment.modeling.commands

import com.investment.modeling.ConsoleInteraction
import java.util.*

class HelpCommand(val io: ConsoleInteraction, val commands: ArrayList<Command>) :
        Command(arrayOf("help"),
                "HELP \t\t Выводит справочную информацию о командах") {

    override fun execute(command: String) {
        for (i in 0..commands.size - 1)
            io.printMessage("${i + 1}. ${commands.get(i).description}\n")
    }
}