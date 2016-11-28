package main.java.com.investment.modeling.commands

abstract class Command(protected val commandDef: String, val description: String) {
    abstract fun execute(args: List<String>)
    abstract fun getDetailedInfo(): Array<String>

    fun support(command: String): Boolean{
        return commandDef.toLowerCase().equals(command.toLowerCase())
    }
}
