export interface TickerDTO {
  id: number
  ticker: string
  companyName: string
  currency: string
  logoUrl: string
  webUrl: string
}

export interface StockPriceResponse {
  ticker: TickerDTO
  price: number
  date: string
}

export interface StockProfileResponse {
  ticker: string
  name: string
  country: string
  currency: string
  marketCap: number
  logoUrl: string
  webUrl: string
}

export interface SummaryTickerDTO {
  tickerName: string
  companyName: string
}

export interface InsiderTransactionsResponse {
  memberName: string
  share: number
  change: number
  fillingDate: string
  transactionDate: string
  transactionCode: string
  transactionPrice: number
}

export interface NewsItemDTO {
  id: number
  ticker: TickerDTO
  headline: string
  body: string
  url: string
  source: string
  publishedAt: string
}

export interface ErrorResponse {
  timestamp: string
  status: number
  error: string
  message: string
}
