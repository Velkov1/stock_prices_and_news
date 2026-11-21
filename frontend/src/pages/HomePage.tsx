import { useState } from 'react'
import { useNavigate } from 'react-router-dom'

const EXAMPLES = ['AAPL', 'MSFT', 'TSLA', 'NVDA']

export default function HomePage() {
  const [symbol, setSymbol] = useState('')
  const navigate = useNavigate()

  function handleSubmit(e: React.FormEvent) {
    e.preventDefault()
    const trimmed = symbol.trim()
    if (trimmed) navigate(`/ticker/${trimmed.toUpperCase()}`)
  }

  return (
    <div className="container" style={{ textAlign: 'center', paddingTop: 80 }}>
      <h1 style={{ fontSize: '2rem' }}>Stock prices &amp; news</h1>
      <p className="muted">Look up a ticker for its price, profile, peers, insider activity, and recent news.</p>
      <form
        onSubmit={handleSubmit}
        style={{ display: 'flex', gap: 8, justifyContent: 'center', margin: '24px 0' }}
      >
        <input
          type="text"
          autoFocus
          placeholder="Ticker symbol, e.g. AAPL"
          value={symbol}
          onChange={(e) => setSymbol(e.target.value)}
          style={{ width: 240 }}
        />
        <button type="submit">Search</button>
      </form>
      <p className="muted" style={{ fontSize: '0.85rem' }}>
        Try:{' '}
        {EXAMPLES.map((ex, i) => (
          <span key={ex}>
            <a
              href={`/ticker/${ex}`}
              onClick={(e) => {
                e.preventDefault()
                navigate(`/ticker/${ex}`)
              }}
            >
              {ex}
            </a>
            {i < EXAMPLES.length - 1 && ', '}
          </span>
        ))}
      </p>
    </div>
  )
}
