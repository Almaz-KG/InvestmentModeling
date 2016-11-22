package com.investment.modeling

import java.util.*

val io = ConsoleInteraction()
val rnd = Random()

fun main(args: Array<String>){
    println("Добро пожаловать в Investment Modeling v.0.0.1")
    println("Эта программа моделирует состояние инвестиционного портфеля в зависимсоти от действий инвестора")

    val userName = io.getUserAnswerString("Введите Ваше имя")
    val initBalance = io.getUserAnswerInt("Базовый капиталл составляет")
    val investValue = io.getUserAnswerInt("Сколько ежемесячно Вы можете инвестировать в портфель")

    val account = Account(userName, initBalance)

    val answer = io.getUserAnswerString("Начать симуляцию?")
    if(isPositiveAnswer(answer))
        startModeling(account, investValue)
    else
        println("Работа программы завершена. Благодарим за использование!")
}

fun startModeling(account: Account, investValuePerMonth: Int){
    var month: Int = 1
    var iog = InvestmentOpportunityGenerator()
    var isAlive: Boolean = true

    while (isAlive){
        println("Месяц: $month $account")

        val investmentsToClose = account.investmentsToClose(month)

        if(investmentsToClose.size != 0)
            investmentsToClose.forEach { e -> closeInvestment(e, account)}

        val opportunity = iog.getOpportunity()
        if(opportunity != null){
            println(opportunity)
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
                        println("Инвестиция отклонена: Неправильно введена часть от баланса для инвестиций")
                } else
                    println("Инвестиция отклонена: Недостаточно свободных средств")

            } else
                println("Инвестиция отклонена инвестором")
        }

        account.balance += investValuePerMonth
        month++

        if(month % 12 == 0)
            isAlive = isPositiveAnswer(io.getUserAnswerString("Продолжить"))
    }

    closeAllInvestments(account)


    println("\n\n=============================")
    println("\t\tСимуляция завершена")
    println("Базовый капиталл: ${account.initBalance}")
    println("Всего доинвестировано: ${investValuePerMonth * month}")
    println("Конечный капиталл: ${account.balance}")
    var res = account.balance / account.initBalance * 100
    println("Средняя ежегодная прибыль: ${res / (month / 12)}")
    println("Всего инвестиций: ${account.allInvestmentList.size}")

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

    println("Инвестиция закрыта с результатом: ${resultPercentage.format(2)}%\n\t$investment" )
    println(account)
}
private fun isPositiveAnswer(answer: String) = ("да".equals(answer.toLowerCase()))
fun Double.format(digits: Int) = String.format("%.${digits}f", this)

