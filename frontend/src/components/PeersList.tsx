import { Link } from 'react-router-dom'
import type { SummaryTickerDTO } from '../api/types'

export default function PeersList({ peers }: { peers: SummaryTickerDTO[] }) {
  if (peers.length === 0) return <p className="muted">No peers found.</p>

  return (
    <div style={{ display: 'flex', flexWrap: 'wrap', gap: 8 }}>
      {peers.map((peer) => (
        <Link key={peer.tickerName} to={`/ticker/${peer.tickerName}`} className="pill">
          {peer.tickerName} <span className="muted">{peer.companyName}</span>
        </Link>
      ))}
    </div>
  )
}
