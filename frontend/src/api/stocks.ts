import { apiGet } from './client'
import type { InsiderTransactionsResponse, StockPriceResponse, StockProfileResponse, SummaryTickerDTO } from './types'

export function getStockPrice(symbol: string) {
  return apiGet<StockPriceResponse>(`/api/stocks/${symbol}/price`)
}

export function getStockProfile(symbol: string) {
  return apiGet<StockProfileResponse>(`/api/stocks/${symbol}/profile`)
}

export function getPeers(symbol: string) {
  return apiGet<SummaryTickerDTO[]>(`/api/stocks/${symbol}/peers`)
}

export function getInsiderTransactions(symbol: string) {
  return apiGet<InsiderTransactionsResponse[]>(`/api/stocks/${symbol}/insider-transactions`)
}
