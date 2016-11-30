package main.java.com.investment.modeling.commands.set

import com.investment.modeling.ConsoleInteraction
import com.investment.modeling.langResourceProvider
import main.java.com.investment.modeling.commands.Command
import java.util.*

open class SetCommand(val io: ConsoleInteraction) : Command(
                                langResourceProvider.getText("command.set.command-enter"),
                                langResourceProvider.getText("command.set.command") + "\t\t" +
                                langResourceProvider.getText("command.set.info-short")){
    val commands = ArrayList<SetCommand>()

    override fun getDetailedInfo(): Array<String> {
        return arrayOf(
                "",
                langResourceProvider.getText("command.set.info"),
                "",
                "\t" + langResourceProvider.getText("command.set.command") + " " +
                        langResourceProvider.getText("command.set.command.argument"),
                "")
    }

    override fun execute(args: List<String>) {
        when(args.size){
            0 -> throw IllegalArgumentException(langResourceProvider.getText("command.set.error.unknown-command"))
            else -> checkParams(args)
        }
    }
    private fun checkParams(args: List<String>) {
        if(isSetCommand(args[0])){
            when(args.size){
                1 -> executeWithOneParams()
                2 -> executeWithTwoParams(args)
                else -> executeWithWellFormedParams(args)
            }
        } else
            throw IllegalArgumentException(langResourceProvider.getText("command.set.error.unknown-command") + " $args[0]")
    }

    private fun executeWithOneParams() {
        io.printlnMessage(this.description)
    }

    private fun executeWithTwoParams(args: List<String>){
        getSubCommand(args[1]).getDetailedInfo().forEach { e -> io.printlnMessage(e) }
    }

    private fun executeWithWellFormedParams(args: List<String>){
        val command = getSubCommand(args[1])
        command.execute(args)
    }
    private fun getSubCommand(subCommand: String): SetCommand {
        val list = this.commands.filter { e -> e.support(subCommand) }

        if (list.size == 0)
            throw IllegalArgumentException(langResourceProvider.getText("command.set.error.property-not-find"))

        return list[0]
    }

    fun isSetCommand(command: String) = langResourceProvider.getText("command.set.command-enter").equals(command.toLowerCase())
}