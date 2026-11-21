import type { NewsItemDTO } from '../api/types'
import NewsCard from './NewsCard'

export default function NewsList({ items }: { items: NewsItemDTO[] }) {
  if (items.length === 0) return <p className="muted">No news found.</p>

  return (
    <div style={{ display: 'flex', flexDirection: 'column', gap: 8 }}>
      {items.map((item) => (
        <NewsCard key={item.id} item={item} />
      ))}
    </div>
  )
}
