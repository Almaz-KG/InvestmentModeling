package com.investment.modeling

import java.util.*

class InvestmentOpportunityGenerator(){
    val probabilityInvestmentOpportunityMaxIndex = 10
    val probabilityInvestmentOpportunityIndex = 6
    val rnd: Random = Random()


    fun getOpportunity(): InvestmentOpportunity? =
        if (rnd.nextInt(probabilityInvestmentOpportunityMaxIndex) > probabilityInvestmentOpportunityIndex)
            generateOpportunity()
        else
            null

    private fun generateOpportunity(): InvestmentOpportunity{
        val it =  getTool(rnd.nextInt(InvestmentTools.values().size))
        val maxProfitPercentage = rnd.nextInt(40) * 1.0
        val maxLossPercentage = rnd.nextInt(40) * 1.0
        val time = rnd.nextInt(36)

        return InvestmentOpportunity(it, maxProfitPercentage, maxLossPercentage, time)
    }
}