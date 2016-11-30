package main.java.com.investment.modeling.commands

import com.investment.modeling.ConsoleInteraction
import com.investment.modeling.langResourceProvider

class ExitCommand(val consoleInteraction: ConsoleInteraction) : Command(
        langResourceProvider.getText("command.exit.command-enter"),
        langResourceProvider.getText("command.exit.command") + "\t\t" +
                langResourceProvider.getText("command.exit.info")) {

    override fun getDetailedInfo(): Array<String> {
        return arrayOf(
                "",
                langResourceProvider.getText("command.exit.info-short"),
                "",
                langResourceProvider.getText("command.exit.command")+
                        "  " +
                            langResourceProvider.getText("command.exit.exit-code"),
                "",
                "\t\t" +
                        langResourceProvider.getText("command.exit.exit-code")+
                        "\t" +
                        langResourceProvider.getText("command.exit.exit-code-desc")+
                "")

    }

    override fun execute(args: List<String>) {
        consoleInteraction.printMessage(langResourceProvider.getText("command.exit.info.goal"))
        if(args.size == 1){
            System.exit(0)
        } else{
            System.exit(Integer.parseInt(args[1]))
        }
    }
}