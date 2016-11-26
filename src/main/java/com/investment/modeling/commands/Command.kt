package main.java.com.investment.modeling.commands

abstract class Command(protected val commandDef: Array<String>, val description: String) {
    abstract fun execute(args: List<String>)
    abstract fun getDetailedInfo(): Array<String>

    fun support(command: String): Boolean{
        return !commandDef.
                filter { it.equals(command.toLowerCase()) }.isEmpty()
    }


}
