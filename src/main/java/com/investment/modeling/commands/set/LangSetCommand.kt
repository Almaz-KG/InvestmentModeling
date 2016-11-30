package main.java.com.investment.modeling.commands.set

import com.investment.modeling.ConsoleInteraction
import com.investment.modeling.langResourceProvider
import main.java.com.investment.modeling.LANGUAGES

class LangSetCommand(val consoleInteraction: ConsoleInteraction) : SetCommand(consoleInteraction){

    init {
        commandDef = langResourceProvider.getText("command.set.lang.command-enter")
        description = langResourceProvider.getText("command.set.lang.command") + "\t\t" +
                langResourceProvider.getText("command.set.lang.info")
    }

    fun isLangSetCommand(command: String) = commandDef.equals(command.toLowerCase())
    fun isLangSetCommand(command: List<String>) = (isSetCommand(command[0]) && isLangSetCommand(command[1]))

    /**
     *  Expected command line:
     *      SET LANG ARG2 ... ARG_N
     */
    override fun execute(args: List<String>) {
        assert(args.size > 2)
        when(args.size){
            3 -> executeWellFormedParams(args)
            else -> throw IllegalArgumentException("${langResourceProvider.getText("command.set.lang.exception.illegal-arguments")} ${args[4]}")
        }
    }

    // SET LANG ${LANGUAGE} - command holder
    private fun executeWellFormedParams(args: List<String>) {
        if(isLangSetCommand(args)){
            val language = LANGUAGES.getLanguage(args[2])

            langResourceProvider.reload(language)

            consoleInteraction.printlnMessage(langResourceProvider.getText("command.set.lang.successfully-changed"))
        } else
            throw IllegalArgumentException("${langResourceProvider.getText("command.set.lang.exception.illegal-argument")} ${args[0]}")
    }

    override fun getDetailedInfo(): Array<String> {
        return arrayOf("",
                langResourceProvider.getText("command.set.lang.info"),
                "",
                "\t" + langResourceProvider.getText("command.set.lang.command") + " " +
                        langResourceProvider.getText("command.set.lang.command.argument"),
                "")
    }
}