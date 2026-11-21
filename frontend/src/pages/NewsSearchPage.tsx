import { useState } from 'react'
import { searchNews } from '../api/news'
import { ApiError } from '../api/client'
import type { NewsItemDTO } from '../api/types'
import NewsList from '../components/NewsList'
import LoadingState from '../components/LoadingState'
import ErrorState from '../components/ErrorState'

export default function NewsSearchPage() {
  const [keyword, setKeyword] = useState('')
  const [results, setResults] = useState<NewsItemDTO[] | null>(null)
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState<string | null>(null)

  async function handleSubmit(e: React.FormEvent) {
    e.preventDefault()
    const trimmed = keyword.trim()
    if (!trimmed) return

    setLoading(true)
    setError(null)
    try {
      setResults(await searchNews(trimmed))
    } catch (err) {
      setError(err instanceof ApiError ? err.message : 'Failed to search news.')
      setResults(null)
    } finally {
      setLoading(false)
    }
  }

  return (
    <div className="container">
      <h1>News search</h1>
      <p className="muted">Search stored articles by headline keyword.</p>
      <form onSubmit={handleSubmit} style={{ display: 'flex', gap: 8, margin: '16px 0' }}>
        <input
          type="text"
          placeholder="Keyword, e.g. earnings"
          value={keyword}
          onChange={(e) => setKeyword(e.target.value)}
          style={{ flex: 1 }}
        />
        <button type="submit">Search</button>
      </form>

      {loading && <LoadingState label="Searching…" />}
      {error && <ErrorState message={error} />}
      {!loading && !error && results && <NewsList items={results} />}
    </div>
  )
}
