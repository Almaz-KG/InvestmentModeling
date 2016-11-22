package com.investment.modeling

data class InvestmentOpportunity(
        val investmentTool: InvestmentTools,
        val maxProfitPercentage: Double,
        val maxLossPercentage: Double,
        val duration: Int) {


    override fun toString(): String {
        return "Инвестиция ($investmentTool Максимальная прибыль =$maxProfitPercentage%, " +
                "Максимальный убыток=$maxLossPercentage%, Длительность =$duration месяцев)"
    }
}

