package main.java.com.investment.modeling.commands

import com.investment.modeling.ConsoleInteraction
import java.util.*

class HelpCommand(val io: ConsoleInteraction, val commands: ArrayList<Command>) :
        Command(arrayOf("help"),
                "HELP \t\t Выводит справочную информацию о командах") {
    override fun getDetailedInfo(): Array<String> {
        return arrayOf(
                "",
                "Вывод справочных сведений о командах",
                "",
                "\tHELP",
                "\t\tотображение списка всех доступных команд",
                "\tHELP [<команда>]",
                "\t\t<команда> - команда, интересующая пользователя",
                "")
    }


    /**
     *  Expected command line:
     *      HELP ARG1 ARG2 ... ARG_N
     */
    override fun execute(args: List<String>) {
        if(args.size == 0)
            throw IllegalArgumentException("Unknown command")
        if(false == "help".equals(args[0].toLowerCase()))
            throw IllegalArgumentException("Illegal command ${args[0]}")

        if(args.size == 1)
            printGlobalHelp()
        else {
            val args = args.distinct()

            for (i in 1..args.size - 1)
                printHelpForCommand(args[i])

        }
    }

    private fun printHelpForCommand(command: String){
        val availableCommand = commands.filter { c -> c.support(command) }
        if(availableCommand.size == 0) {
            io.printlnMessage("Данная команда не поддерживается")
        }

        availableCommand.forEach { c -> c.getDetailedInfo()
                        .forEach { e -> io.printlnMessage(e)} }
    }

    private fun printGlobalHelp() {
        for (i in 0..commands.size - 1)
            io.printlnMessage("${i + 1}. ${commands[i].description}")
    }
}