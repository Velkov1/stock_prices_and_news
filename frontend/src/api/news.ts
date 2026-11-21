import { apiGet } from './client'
import type { NewsItemDTO } from './types'

export type NewsSentiment = 'all' | 'positive' | 'negative'

export function getNewsForTicker(symbol: string, sentiment: NewsSentiment = 'all') {
  const path =
    sentiment === 'all' ? `/api/news/${symbol}` : `/api/news/${symbol}/${sentiment}`
  return apiGet<NewsItemDTO[]>(path)
}

export function searchNews(keyword: string) {
  return apiGet<NewsItemDTO[]>('/api/news/search', { keyword })
}
