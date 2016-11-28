package com.investment.modeling

import com.investment.modeling.commands.ClearConsole
import com.investment.modeling.commands.ShowCommand
import jline.console.ConsoleReader
import main.java.com.investment.modeling.commands.Command
import main.java.com.investment.modeling.commands.ExitCommand
import main.java.com.investment.modeling.commands.HelpCommand
import java.util.*

//val initFileName = "config/init.properties"
//val initProperties = ResourceProvider(initFileName)
val langResourceProvider = LanguageResourceProvider(LanguageResourceProvider.LANGUAGES.RUS)
val rnd = Random()
var cr = ConsoleReader(System.`in`, System.out)
val io = ConsoleInteraction(cr)
val commands = ArrayList<Command>()
var imr:InvestmentModelingRunner? = null

fun main(args: Array<String>){
    commands.add(ShowCommand(io))
    commands.add(HelpCommand(io, commands))
    commands.add(ClearConsole(io))
    commands.add(ExitCommand(io))
    io.commands = commands

    io.printlnMessage(langResourceProvider.getText("welcome-message"))
    io.printlnMessage(langResourceProvider.getText("program-info-1"))
    io.printlnMessage(langResourceProvider.getText("program-info-2"))

    val userName = io.getUserAnswerString(langResourceProvider.getText("enter-your-name"))

    val initBalance = io.getUserAnswerInt(langResourceProvider.getText("initial-capital"))
    val investValue = io.getUserAnswerInt(langResourceProvider.getText("how-money-every-month"))
    val account = Account(userName, initBalance)

    imr = InvestmentModelingRunner(account, investValue)
    imr!!.run()

}

fun Double.format(digits: Int) = String.format("%.${digits}f", this)