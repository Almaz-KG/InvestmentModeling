package com.investment.modeling

data class Investment(
        val investmentTool: InvestmentTools,
        val startDate: Int,
        val endDate: Int,
        val maxProfitPercentage: Double,
        val maxLossPercentage: Double,
        val balance: Int
        ){

        override fun toString(): String {
                return "Инвестиция($investmentTool, " +
                        "Начало =$startDate, " +
                        "Конец=$endDate, " +
                        "Максимальная прибыль=$maxProfitPercentage%, " +
                        "Максимальный убыток=$maxLossPercentage%, " +
                        "Баланс=$balance)"
        }
}