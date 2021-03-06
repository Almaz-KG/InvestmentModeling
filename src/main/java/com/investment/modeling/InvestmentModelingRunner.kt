package com.investment.modeling

import java.util.*

class InvestmentModelingRunner(var account: Account, var investValuePerMonth: Int) {
    var id = 0

    fun run() {
        io.printlnMessage(langResourceProvider.getText("modeling.runner.start"))
        var month: Int = 1
        var iog = InvestmentOpportunityGenerator()
        var isAlive: Boolean = true

        while (isAlive) {
            io.printlnMessage("${langResourceProvider.getText("modeling.runner.month")}: $month $account")

            val investmentsToClose = account.investmentsToClose(month)

            if (investmentsToClose.size != 0)
                investmentsToClose.forEach { e -> closeInvestment(e, account) }

            val opportunity = iog.getOpportunity()
            if (opportunity != null) {
                io.printlnMessage(opportunity.toString())
                val userAnswer = io.getUserAnswerString(langResourceProvider.getText("modeling.runner.do-invest"))
                if (isPositiveAnswer(userAnswer)) {
                    val balance = account.balance
                    if (balance > 0) {
                        val investmentPercentage = io.getUserAnswerInt(langResourceProvider.getText("modeling.runner.part-of-investments"))
                        if (investmentPercentage > 0 && investmentPercentage <= 100) {
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

                            io.printlnMessage(langResourceProvider.getText("modeling.runner.invest-success"))
                            io.printlnMessage("\t" + investment.toString())
                        } else
                            io.printlnMessage(langResourceProvider.getText("modeling.runner.invest-rejected-wrong-part"))
                    } else
                        io.printlnMessage(langResourceProvider.getText("modeling.runner.invest-rejected-no-free-money"))

                } else
                    io.printlnMessage(langResourceProvider.getText("modeling.runner.invest-rejected-by-investor"))
            }

            account.balance += investValuePerMonth
            month++

            io.readKey()
        }
        closeAllInvestments(account)

        io.printlnMessage("\n\n=============================")
        io.printlnMessage("\t\t${langResourceProvider.getText("modeling.runner.finished")}")
        io.printlnMessage("${langResourceProvider.getText("modeling.runner.invest.start-balance")}: ${account.initBalance}")
        io.printlnMessage("${langResourceProvider.getText("modeling.runner.invest.total-money-added")}: " +
                "${investValuePerMonth * month}")
        io.printlnMessage("${langResourceProvider.getText("modeling.runner.invest.finish-balance")}: ${account.balance}")

        var res = if(account.initBalance != 0) account.balance / account.initBalance * 100
            else
                account.balance
        io.printlnMessage("${langResourceProvider.getText("modeling.runner.invest.average-year-revenue")}: ${res / (month / 12.0)}")
        io.printlnMessage("${langResourceProvider.getText("modeling.runner.invest.total-investments")}: ${account.allInvestmentList.size}")
    }


    fun closeAllInvestments(account: Account) {
        var investmentList: ArrayList<Investment> = ArrayList(account.currentInvestmentList)

        investmentList.forEach { e -> closeInvestment(e, account) }
    }

    private fun closeInvestment(investment: Investment, account: Account) {
        val max = investment.maxProfitPercentage
        val min = investment.maxLossPercentage
        val resultPercentage = min + rnd.nextDouble() * (max - min + 1)

        val resultValue = investment.balance * (1 + resultPercentage / 100)
        account.balance += resultValue.toInt()

        account.currentInvestmentList.remove(investment)
        account.allInvestmentList.add(investment)

        io.printlnMessage("${langResourceProvider.getText("modeling.runner.invest.closed-by-result")}: ${resultPercentage.format(2)}%")
        io.printlnMessage("\t$investment")
        io.printlnMessage(account.toString())
    }

    private fun isPositiveAnswer(answer: String) : Boolean {
        return "${langResourceProvider.getText("modeling.runner.positive-answer-contains")}".equals(answer.toLowerCase())
    }
}