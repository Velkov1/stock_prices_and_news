# stock_prices_and_news
A RESTful API built with Spring Boot that combines real-time data about stock prices using Finnhub and financial news using Marketaux.

1. What does the project do?
- this program is a REST API used for simple financial analysis and news about stocks. The stock prices and data are fetched using the Finnhub API for the financial data and Marketaux for the relevant news about the selected stocks.

2. Getting started
   -get your own API keys for Finnhub and Marketaux. Go to their websites: https://finnhub.io; https://www.marketaux.com

- copy the properties template and fill in your own API keys
  cp src/main/resources/application.properties.example src/main/resources/application.properties

After that edit the application.properties and set your API keys

- run the app: ./mvnw spring-boot:run

- the API is available on http://localhost:8080

- the H2 console is available at /h2-console while the app is running. The database is in-memory and resets on every restart.


API ENDPOINTS:

STOCKS (/api/stocks)
- /{symbol}/price : GET method that gives the current price for the given ticker
- /{symbol}/profile : GET method that gives the company profile: ticker, company name, country, currency, market capitalisation, logo url, website url
- /{symbol}/peers : GET method that gives the peers companies
- /{symbol}/insider-transactions : GET method that gives the insider transactions for the given ticker

NEWS (/api/news)
- /{symbol} : GET method that gives news for a ticker (served from the database if the news for this ticker were already fetched within the last 30 minutes, otherwise the data is fetched from the API. The idea here is, because the free tier is limited in how many calls could be made, calls could be saved)
- ?symbols= : GET method that gives news for multiple tickers
- /{symbol}/positive : GET method that gives positive sentiment news for a ticker
- /{symbol}/negative : GET method that gives negative sentiment news for a ticker
- /search?keyword= : GET method that searches already stored news in the database by headline
- /search/{symbol}/published-at?date= : GET method that gives already stored news for a ticker saved at an exact timestamp
- /search/published-at?date= : GET method that gives already stored news saved at an exact timestamp
- /search/published-after?date= : GET method that gives already stored news saved after a timestamp
- /search/{symbol}/stored : GET method that gives all stored news for a ticker


3. Frontend
- the frontend is built with React, TypeScript and Vite (completely built with Claude Code) and is located in the frontend folder
- start the backend first, then run:
  cd frontend
  npm install
  npm run dev

- the frontend is available on http://localhost:5173 and sends all /api requests to the backend on port 8080



 
