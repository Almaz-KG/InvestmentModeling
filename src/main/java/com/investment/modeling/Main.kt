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

fun main(args: Array<String>){
    commands.add(HelpCommand(io, commands))
    commands.add(ClearConsole(io))
    commands.add(ExitCommand(io))
    io.commands = commands


    println("Добро пожаловать в Investment Modeling v.0.0.1")
    println("Эта программа моделирует состояние инвестиционного\nпортфеля в зависимости от действий инвестора")

    val userName = io.getUserAnswerString("Введите Ваше имя")

    val initBalance = io.getUserAnswerInt("Базовый капиталл составляет")
    val investValue = io.getUserAnswerInt("Сколько ежемесячно Вы можете инвестировать в портфель")
    val account = Account(userName, initBalance)

    startModeling(account, investValue)
}

fun startModeling(account: Account, investValuePerMonth: Int){
    io.printlnMessage("Начинаю процесс моделирования")
    var month: Int = 1
    var iog = InvestmentOpportunityGenerator()
    var isAlive: Boolean = true

    while (isAlive){
        io.printlnMessage("Месяц: $month $account")

        val investmentsToClose = account.investmentsToClose(month)

        if(investmentsToClose.size != 0)
            investmentsToClose.forEach { e -> closeInvestment(e, account)}

        val opportunity = iog.getOpportunity()
        if(opportunity != null){
            io.printlnMessage(opportunity.toString())
            val userAnswer = io.getUserAnswerString("Инвестировать?")
            if(isPositiveAnswer(userAnswer)) {
                val balance = account.balance
                if(balance > 0) {
                    val investmentPercentage = io.getUserAnswerInt("Какую часть инвестировать (в % от свободного баланса, не более 100%)")
                    if(investmentPercentage > 0 && investmentPercentage <= 100){
                        val investmentValue = balance * investmentPercentage / 100

                        val investment = Investment(
                                opportunity.investmentTool,
                                month,
                                month + opportunity.duration,
                                opportunity.maxProfitPercentage,
                                opportunity.maxLossPercentage,
                                investmentValue)

                        account.currentInvestmentList.add(investment)
                        account.balance -= investmentValue
                    } else
                        io.printlnMessage("Инвестиция отклонена: Неправильно введена часть от баланса для инвестиций")
                } else
                    io.printlnMessage("Инвестиция отклонена: Недостаточно свободных средств")

            } else
                io.printlnMessage("Инвестиция отклонена инвестором")
        }

        account.balance += investValuePerMonth
        month++

        io.readKey()
    }
    closeAllInvestments(account)

    io.printlnMessage("\n\n=============================")
    io.printlnMessage("\t\tСимуляция завершена")
    io.printlnMessage("Базовый капиталл: ${account.initBalance}")
    io.printlnMessage("Всего доинвестировано: ${investValuePerMonth * month}")
    io.printlnMessage("Конечный капиталл: ${account.balance}")
    var res = account.balance / account.initBalance * 100
    io.printlnMessage("Средняя ежегодная прибыль: ${res /(month / 12.0)}")
    io.printlnMessage("Всего инвестиций: ${account.allInvestmentList.size}")

}

fun closeAllInvestments(account: Account) {
    var investmentList: ArrayList<Investment> = ArrayList(account.currentInvestmentList)

    investmentList.forEach {  e -> closeInvestment(e, account)}
}

private fun closeInvestment(investment: Investment, account: Account){
    val max = investment.maxProfitPercentage
    val min = investment.maxLossPercentage
    val resultPercentage = min + rnd.nextDouble() * (max - min + 1)

    val resultValue = investment.balance * (1 + resultPercentage / 100)
    account.balance += resultValue.toInt()

    account.currentInvestmentList.remove(investment)
    account.allInvestmentList.add(investment)

    io.printlnMessage("Инвестиция закрыта с результатом: ${resultPercentage.format(2)}%")
    io.printlnMessage("\t$investment")
    io.printlnMessage(account.toString())
}
private fun isPositiveAnswer(answer: String) = "да".equals(answer.toLowerCase())

fun Double.format(digits: Int) = String.format("%.${digits}f", this)