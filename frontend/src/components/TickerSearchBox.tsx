import { useState } from 'react'
import { useNavigate } from 'react-router-dom'

export default function TickerSearchBox() {
  const [symbol, setSymbol] = useState('')
  const navigate = useNavigate()

  function handleSubmit(e: React.FormEvent) {
    e.preventDefault()
    const trimmed = symbol.trim()
    if (trimmed) {
      navigate(`/ticker/${trimmed.toUpperCase()}`)
      setSymbol('')
    }
  }

  return (
    <form onSubmit={handleSubmit} style={{ display: 'flex', gap: 8 }}>
      <input
        type="text"
        placeholder="Ticker symbol, e.g. AAPL"
        value={symbol}
        onChange={(e) => setSymbol(e.target.value)}
      />
      <button type="submit">Go</button>
    </form>
  )
}
