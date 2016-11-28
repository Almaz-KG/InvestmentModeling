package com.investment.modeling.commands

import com.investment.modeling.ConsoleInteraction
import com.investment.modeling.Investment
import com.investment.modeling.imr
import com.investment.modeling.langResourceProvider
import main.java.com.investment.modeling.commands.Command

class ShowCommand(val consoleInteraction: ConsoleInteraction) : Command(
        langResourceProvider.getText("command.show.command-enter"),
        langResourceProvider.getText("command.show.command") +
                "\t\t " +
                langResourceProvider.getText("command.show.info")){

    override fun getDetailedInfo(): Array<String> {
        return arrayOf(
                "",
                langResourceProvider.getText("command.show.investment.open.list"),
                "",
                langResourceProvider.getText("command.show.command"),
                "\t\t " +
                        langResourceProvider.getText("command.show.info.detailed"),
                "",
                langResourceProvider.getText("command.show.investment.short"),
                "",
                "\t\t " +
                        langResourceProvider.getText("command.show.investment.open.list"),
                "")
    }

    override fun execute(args: List<String>) {
        if(args.size == 0)
            throw IllegalArgumentException(langResourceProvider.getText("command.show.error.unknown-command"))
        if(false == "${langResourceProvider.getText("command.show.command-enter")}".equals(args[0].toLowerCase()))
            throw IllegalArgumentException("${langResourceProvider.getText("command.show.error.illegal-argument-command")} " +
                    "${args[0]}")

        if(args.size == 1)
            printAccountInfo()
        else {
            if(false == "${langResourceProvider.getText("command.show.investment")}".equals(args[1].toLowerCase())){
                consoleInteraction.printlnMessage("${langResourceProvider.getText("command.show.error.illegal-command-argument")} " +
                        "${args[0]}")
                return
            }
            printInvestmentInfo()
        }
    }

    private fun printInvestmentInfo() {
        val list = imr!!.account.currentInvestmentList
        if(list.size == 0){
            consoleInteraction.printlnMessage(langResourceProvider.getText("command.show.investment.info.no-open-investments"))
            return
        } else{
            val column = arrayListOf(
                    langResourceProvider.getText("command.show.investment.id"),
                    langResourceProvider.getText("command.show.investment.share"),
                    langResourceProvider.getText("command.show.investment.start"),
                    langResourceProvider.getText("command.show.investment.end"),
                    langResourceProvider.getText("command.show.investment.revenue"),
                    langResourceProvider.getText("command.show.investment.loss"),
                    langResourceProvider.getText("command.show.investment.balance"),
                    "")
            list.forEach { e -> (column.addAll(getValuesList(e))) }

            consoleInteraction.printColumns(column)
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
        consoleInteraction.printlnMessage(imr!!.account.toString())
    }
}
