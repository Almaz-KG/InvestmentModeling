package com.investment.modeling

import com.investment.modeling.commands.ClearConsole
import com.investment.modeling.commands.ShowCommand
import jline.console.ConsoleReader
import main.java.com.investment.modeling.LANGUAGES
import main.java.com.investment.modeling.commands.Command
import main.java.com.investment.modeling.commands.ExitCommand
import main.java.com.investment.modeling.commands.HelpCommand
import main.java.com.investment.modeling.commands.set.LangSetCommand
import main.java.com.investment.modeling.commands.set.SetCommand
import java.io.PrintStream
import java.util.*

val langResourceProvider = LanguageResourceProvider(LANGUAGES.RUS)
val rnd = Random()
val prStr = PrintStream(System.out, true, "UTF-8")
var cr = ConsoleReader(System.`in`, prStr)
val io = ConsoleInteraction(cr)
val commands = ArrayList<Command>()
var imr:InvestmentModelingRunner? = null

fun main(args: Array<String>){
    commands.add(ShowCommand(io))

    val setCommand = SetCommand(io)
    setCommand.commands.add(LangSetCommand(io))
    commands.add(setCommand)
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