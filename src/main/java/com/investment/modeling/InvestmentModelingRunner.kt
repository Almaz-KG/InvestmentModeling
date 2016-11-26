package com.investment.modeling

import java.util.*

class InvestmentModelingRunner(var account: Account, var investValuePerMonth: Int){
    var id = 0

    fun run(){
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
                                    "" + (id++),
                                    opportunity.investmentTool,
                                    month,
                                    month + opportunity.duration,
                                    opportunity.maxProfitPercentage,
                                    opportunity.maxLossPercentage,
                                    investmentValue)

                            account.currentInvestmentList.add(investment)
                            account.balance -= investmentValue

                            io.printlnMessage("Инвестиция успешно заклюена")
                            io.printlnMessage("\t" + investment.toString())
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
    private fun isPositiveAnswer(answer: String) = "дауes1".contains(answer.toLowerCase())

}