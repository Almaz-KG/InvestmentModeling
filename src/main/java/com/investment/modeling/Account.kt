package com.investment.modeling

import java.util.*

class Account(val userName: String, var balance: Int){
    val initBalance = balance
    var currentInvestmentList: ArrayList<Investment> = ArrayList()
    var allInvestmentList: ArrayList<Investment> = ArrayList()

    fun investmentsToClose(month: Int): ArrayList<Investment>{
        var result = ArrayList<Investment>()

        currentInvestmentList.forEach { e ->
            if(e.endDate == month)
                result.add(e)
        }

        return result
    }


    override fun toString(): String {
        return "${langResourceProvider.getText("account.account")}" +
                "(${langResourceProvider.getText("account.owner")}='$userName', " +
                "${langResourceProvider.getText("account.balance")}=$balance, " +
                "${langResourceProvider.getText("account.investment-count")}=${currentInvestmentList.size})"
    }
}