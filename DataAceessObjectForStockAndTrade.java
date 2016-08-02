package stockmarket;

import java.util.List;


/**
 * Interface for Trade and Stock data
*/
public interface DataAceessObjectForStockAndTrade {

  
  void addStock(Stock stock);
  Stock getStock(String symbol);
  void addTrade(Trade trade);
  List<Trade> getTrades(Stock stock, int minutes);
  List<Trade> getAllTrades();
  
  

}