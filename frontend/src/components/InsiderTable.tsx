import type { InsiderTransactionsResponse } from '../api/types'

export default function InsiderTable({ transactions }: { transactions: InsiderTransactionsResponse[] }) {
  if (transactions.length === 0) return <p className="muted">No recent insider transactions.</p>

  return (
    <div style={{ overflowX: 'auto' }}>
      <table style={{ width: '100%', borderCollapse: 'collapse', fontSize: '0.9rem' }}>
        <thead>
          <tr style={{ textAlign: 'left', borderBottom: '1px solid var(--border)' }}>
            <th style={{ padding: '6px 8px' }}>Member</th>
            <th style={{ padding: '6px 8px' }}>Change</th>
            <th style={{ padding: '6px 8px' }}>Shares held</th>
            <th style={{ padding: '6px 8px' }}>Price</th>
            <th style={{ padding: '6px 8px' }}>Transaction date</th>
          </tr>
        </thead>
        <tbody>
          {transactions.map((t, i) => (
            <tr key={i} style={{ borderBottom: '1px solid var(--border)' }}>
              <td style={{ padding: '6px 8px' }}>{t.memberName}</td>
              <td
                style={{
                  padding: '6px 8px',
                  color: t.change >= 0 ? 'var(--positive)' : 'var(--negative)',
                  fontWeight: 600,
                }}
              >
                {t.change >= 0 ? '+' : ''}
                {t.change.toLocaleString()}
              </td>
              <td style={{ padding: '6px 8px' }}>{t.share.toLocaleString()}</td>
              <td style={{ padding: '6px 8px' }}>{t.transactionPrice.toFixed(2)}</td>
              <td style={{ padding: '6px 8px' }}>{t.transactionDate}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  )
}
