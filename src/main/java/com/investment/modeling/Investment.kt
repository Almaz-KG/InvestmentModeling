package com.investment.modeling

data class Investment(
        val id: String,
        val investmentTool: InvestmentTools,
        val startDate: Int,
        val endDate: Int,
        val maxProfitPercentage: Double,
        val maxLossPercentage: Double,
        val balance: Int
        ){

        override fun toString(): String {
                return "${langResourceProvider.getText("investment-opportunity.investment")}(" +
                        "${langResourceProvider.getText("investment.id")} = $id, " +
                        "${langResourceProvider.getText("investment.share")} = $investmentTool, " +
                        "${langResourceProvider.getText("investment.start")} = $startDate, " +
                        "${langResourceProvider.getText("investment.end")} = $endDate, " +
                        "${langResourceProvider.getText("investment.revenue")} = $maxProfitPercentage%, " +
                        "${langResourceProvider.getText("investment.loss")} = $maxLossPercentage%, " +
                        "${langResourceProvider.getText("investment.balance")} = $balance)"
        }
}