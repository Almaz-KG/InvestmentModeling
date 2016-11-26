package main.java.com.investment.modeling.commands

import com.investment.modeling.ConsoleInteraction

class ExitCommand(val io: ConsoleInteraction) : Command(
        arrayOf("exit", "quit"), "EXIT or QUIT \t Завершение работы программы") {

    override fun execute(command: String) {
        io.printMessage("Работа программы завершена. Благодарим за использование!")
        System.exit(0)
    }
}