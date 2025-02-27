import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StocksPortfolio  {
    private List<Stock> stocks = new ArrayList<>();
    private IStockmarketService stockmarket;

    public StocksPortfolio(IStockmarketService stockmarket) {
        this.stockmarket = stockmarket;

    }
    public void addStock(Stock stock) {
        this.stocks.add(stock);
    }
    public double getTotalValue() {
        double total = 0.0;
        for (Stock stock : stocks) {
            total += stockmarket.lookUpPrice(stock.getLabel()) * stock.getQuantity();
        }
        return total;
    }

    /**
     * Returns the top N most valuable stocks in the portfolio.
     *
     * @param topN the number of most valuable stocks to return
     * @return a list with the top N most valuable stocks
     */
    public List<Stock> mostValuableStocks(int topN) {
        return stocks.stream()
                .sorted((s1, s2) -> Double.compare(s2.getQuantity() * stockmarket.lookUpPrice(s2.getLabel()),
                        s1.getQuantity() * stockmarket.lookUpPrice(s1.getLabel())))
                .limit(topN)
                .toList();
    }



}
