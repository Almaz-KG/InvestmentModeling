package main.java.com.investment.modeling.commands

abstract class Command(protected var commandDef: String, var description: String) {
    abstract fun execute(args: List<String>)
    abstract fun getDetailedInfo(): Array<String>

    fun support(command: String): Boolean{
        return commandDef.toLowerCase().equals(command.toLowerCase())
    }
}
