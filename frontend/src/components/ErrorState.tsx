export default function ErrorState({ message }: { message: string }) {
  return (
    <div className="card" style={{ borderColor: 'var(--negative)', background: 'var(--negative-bg)' }}>
      <strong style={{ color: 'var(--negative)' }}>Something went wrong</strong>
      <p style={{ margin: '4px 0 0' }}>{message}</p>
    </div>
  )
}
