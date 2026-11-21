import type { NewsItemDTO } from '../api/types'

export default function NewsCard({ item }: { item: NewsItemDTO }) {
  return (
    <article className="card">
      <a href={item.url} target="_blank" rel="noreferrer" style={{ color: 'var(--text)', fontWeight: 600 }}>
        {item.headline}
      </a>
      <p className="muted" style={{ margin: '4px 0 0', fontSize: '0.85rem' }}>
        {item.source} · {new Date(item.publishedAt).toLocaleString()}
        {item.ticker?.ticker && ` · ${item.ticker.ticker}`}
      </p>
    </article>
  )
}
