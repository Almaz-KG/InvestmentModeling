package com.investment.modeling.commands

import com.investment.modeling.ConsoleInteraction
import com.investment.modeling.Investment
import com.investment.modeling.imr
import main.java.com.investment.modeling.commands.Command

class ShowCommand(val ci: ConsoleInteraction) : Command(
        arrayOf("show"),
        "SHOW\t\t Показать детальную информацию параметре"){

    override fun getDetailedInfo(): Array<String> {
        return arrayOf(
                "",
                "Отображает детализированную информацию",
                "",
                "SHOW",
                "\t\tДетализированная информация об аккаунте",
                "",
                "SHOW INVESTMENT",
                "",
                "\t\tОтображает список всех открытых инвестиций",
                "")
    }

    override fun execute(args: List<String>) {
        if(args.size == 0)
            throw IllegalArgumentException("Unknown command")
        if(false == "show".equals(args[0].toLowerCase()))
            throw IllegalArgumentException("Illegal command ${args[0]}")

        if(args.size == 1)
            printAccountInfo()
        else {
            if(false == "investment".equals(args[1].toLowerCase())){
                ci.printlnMessage("Illegal command argument ${args[0]}")
                return
            }
            printInvestmentInfo()
        }
    }

    private fun printInvestmentInfo() {
        val list = imr!!.account.currentInvestmentList
        if(list.size == 0){
            ci.printlnMessage("Активных инвестиций нет")
            return
        } else{
            val column = arrayListOf("ID",
                                     "Акция",
                                     "Начало",
                                     "Конец",
                                     "Прибыль",
                                     "Убыток",
                                     "Сумма",
                                     "")
            list.forEach { e -> (column.addAll(getValuesList(e))) }

            ci.printColumns(column)
        }
    }

    private fun getValuesList(e: Investment): List<String> {
        return arrayListOf(
                e.id,
                e.investmentTool.toString(),
                e.startDate.toString(),
                e.endDate.toString(),
                e.maxProfitPercentage.toString(),
                e.maxLossPercentage.toString(),
                e.balance.toString(),
                "")
    }

    private fun printAccountInfo() {
        ci.printlnMessage(imr!!.account.toString())
    }
}