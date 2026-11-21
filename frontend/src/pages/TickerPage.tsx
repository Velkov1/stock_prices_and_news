import { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'
import { getInsiderTransactions, getPeers, getStockPrice, getStockProfile } from '../api/stocks'
import { getNewsForTicker, type NewsSentiment } from '../api/news'
import { ApiError } from '../api/client'
import type {
  InsiderTransactionsResponse,
  NewsItemDTO,
  StockPriceResponse,
  StockProfileResponse,
  SummaryTickerDTO,
} from '../api/types'
import TickerHeader from '../components/TickerHeader'
import PeersList from '../components/PeersList'
import InsiderTable from '../components/InsiderTable'
import NewsList from '../components/NewsList'
import LoadingState from '../components/LoadingState'
import ErrorState from '../components/ErrorState'

interface DashboardData {
  price: StockPriceResponse
  profile: StockProfileResponse
  peers: SummaryTickerDTO[]
  insiderTransactions: InsiderTransactionsResponse[]
}

const TABS: { key: NewsSentiment; label: string }[] = [
  { key: 'all', label: 'All' },
  { key: 'positive', label: 'Positive' },
  { key: 'negative', label: 'Negative' },
]

export default function TickerPage() {
  const { symbol } = useParams<{ symbol: string }>()
  const [data, setData] = useState<DashboardData | null>(null)
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState<string | null>(null)

  const [sentiment, setSentiment] = useState<NewsSentiment>('all')
  const [news, setNews] = useState<NewsItemDTO[]>([])
  const [newsLoading, setNewsLoading] = useState(true)

  useEffect(() => {
    if (!symbol) return
    let cancelled = false

    setLoading(true)
    setError(null)
    setSentiment('all')

    Promise.all([
      getStockPrice(symbol),
      getStockProfile(symbol),
      getPeers(symbol),
      getInsiderTransactions(symbol),
    ])
      .then(([price, profile, peers, insiderTransactions]) => {
        if (cancelled) return
        setData({ price, profile, peers, insiderTransactions })
      })
      .catch((err) => {
        if (cancelled) return
        setError(err instanceof ApiError ? err.message : 'Failed to load ticker data.')
        setData(null)
      })
      .finally(() => {
        if (!cancelled) setLoading(false)
      })

    return () => {
      cancelled = true
    }
  }, [symbol])

  useEffect(() => {
    if (!symbol) return
    let cancelled = false

    setNewsLoading(true)
    getNewsForTicker(symbol, sentiment)
      .then((items) => {
        if (!cancelled) setNews(items)
      })
      .catch(() => {
        if (!cancelled) setNews([])
      })
      .finally(() => {
        if (!cancelled) setNewsLoading(false)
      })

    return () => {
      cancelled = true
    }
  }, [symbol, sentiment])

  if (loading) {
    return (
      <div className="container">
        <LoadingState label={`Loading ${symbol}…`} />
      </div>
    )
  }

  if (error || !data) {
    return (
      <div className="container">
        <ErrorState message={error ?? 'No data available.'} />
      </div>
    )
  }

  return (
    <div className="container" style={{ display: 'flex', flexDirection: 'column', gap: 24 }}>
      <TickerHeader profile={data.profile} price={data.price} />

      <section>
        <h2>Peers</h2>
        <PeersList peers={data.peers} />
      </section>

      <section>
        <h2>News</h2>
        <div style={{ display: 'flex', gap: 8, marginBottom: 12 }}>
          {TABS.map((tab) => (
            <button
              key={tab.key}
              onClick={() => setSentiment(tab.key)}
              style={
                sentiment === tab.key
                  ? undefined
                  : { background: 'var(--bg)', color: 'var(--text)', borderColor: 'var(--border)' }
              }
            >
              {tab.label}
            </button>
          ))}
        </div>
        {newsLoading ? <LoadingState /> : <NewsList items={news} />}
      </section>

      <section>
        <h2>Insider transactions</h2>
        <InsiderTable transactions={data.insiderTransactions} />
      </section>
    </div>
  )
}
