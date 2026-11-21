import { BrowserRouter, Route, Routes } from 'react-router-dom'
import NavBar from './components/NavBar'
import HomePage from './pages/HomePage'
import TickerPage from './pages/TickerPage'
import NewsSearchPage from './pages/NewsSearchPage'

export default function App() {
  return (
    <BrowserRouter>
      <NavBar />
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/ticker/:symbol" element={<TickerPage />} />
        <Route path="/news/search" element={<NewsSearchPage />} />
      </Routes>
    </BrowserRouter>
  )
}
