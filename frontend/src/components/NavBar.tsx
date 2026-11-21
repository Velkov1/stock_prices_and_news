import { Link } from 'react-router-dom'
import TickerSearchBox from './TickerSearchBox'

export default function NavBar() {
  return (
    <header
      style={{
        borderBottom: '1px solid var(--border)',
      }}
    >
      <div
        className="container"
        style={{
          display: 'flex',
          alignItems: 'center',
          justifyContent: 'space-between',
          gap: 16,
          padding: '16px',
          flexWrap: 'wrap',
        }}
      >
        <div style={{ display: 'flex', alignItems: 'center', gap: 24 }}>
          <Link to="/" style={{ color: 'var(--text)', fontWeight: 700, fontSize: '1.05rem' }}>
            Stocks &amp; News
          </Link>
          <Link to="/news/search" className="muted">
            News Search
          </Link>
        </div>
        <TickerSearchBox />
      </div>
    </header>
  )
}
