package main.java.com.investment.modeling.commands

import com.investment.modeling.ConsoleInteraction

class ExitCommand(val io: ConsoleInteraction) : Command(
        arrayOf("exit", "quit"), "EXIT or QUIT \t Завершение работы программы") {

    override fun getDetailedInfo(): Array<String> {
        return arrayOf(
                "",
                "Завершает программу",
                "",
                "EXIT or QUIT [exitCode]",
                "",
                "\t\texitCode\tКод завершения программы",
                "")

    }

    override fun execute(args: List<String>) {
        io.printMessage("Работа программы завершена. Благодарим за использование!")
        if(args.size == 1){
            System.exit(0)
        } else{
            System.exit(Integer.parseInt(args[1]))
        }
    }
}