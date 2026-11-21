import type { StockPriceResponse, StockProfileResponse } from '../api/types'

function formatMarketCap(value: number): string {
  if (value >= 1_000_000) return `${(value / 1_000_000).toFixed(1)}T`
  if (value >= 1_000) return `${(value / 1_000).toFixed(1)}B`
  return `${value.toFixed(0)}M`
}

export default function TickerHeader({
  profile,
  price,
}: {
  profile: StockProfileResponse
  price: StockPriceResponse
}) {
  return (
    <div className="card" style={{ display: 'flex', gap: 16, alignItems: 'center', flexWrap: 'wrap' }}>
      {profile.logoUrl && (
        <img
          src={profile.logoUrl}
          alt=""
          width={56}
          height={56}
          style={{ borderRadius: 8, objectFit: 'contain', background: '#fff' }}
        />
      )}
      <div style={{ flex: 1, minWidth: 200 }}>
        <h1 style={{ fontSize: '1.4rem' }}>
          {profile.name} <span className="muted">({profile.ticker})</span>
        </h1>
        <p className="muted" style={{ margin: 0 }}>
          {profile.country} · Market cap {formatMarketCap(profile.marketCap)} {profile.currency}
          {profile.webUrl && (
            <>
              {' · '}
              <a href={profile.webUrl} target="_blank" rel="noreferrer">
                Website
              </a>
            </>
          )}
        </p>
      </div>
      <div style={{ textAlign: 'right' }}>
        <div style={{ fontSize: '1.6rem', fontWeight: 700 }}>
          {price.price.toFixed(2)} {profile.currency}
        </div>
        <div className="muted" style={{ fontSize: '0.8rem' }}>
          as of {new Date(price.date).toLocaleString()}
        </div>
      </div>
    </div>
  )
}
