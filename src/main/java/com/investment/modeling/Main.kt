package com.investment.modeling

import com.investment.modeling.commands.ClearConsole
import com.investment.modeling.commands.ShowCommand
import jline.console.ConsoleReader
import main.java.com.investment.modeling.commands.Command
import main.java.com.investment.modeling.commands.ExitCommand
import main.java.com.investment.modeling.commands.HelpCommand
import java.util.*

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

    io.printlnMessage("Добро пожаловать в Investment Modeling v.0.0.1")
    io.printlnMessage("Эта программа моделирует состояние инвестиционного")
    io.printlnMessage("портфеля в зависимости от действий инвестора")

    val userName = io.getUserAnswerString("Введите Ваше имя")

    val initBalance = io.getUserAnswerInt("Базовый капиталл составляет")
    val investValue = io.getUserAnswerInt("Сколько ежемесячно Вы можете инвестировать в портфель")
    val account = Account(userName, initBalance)

    imr = InvestmentModelingRunner(account, investValue)
    imr!!.run()

}

fun Double.format(digits: Int) = String.format("%.${digits}f", this)