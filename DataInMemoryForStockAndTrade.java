package stockmarket;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Collections;
import java.util.Date;

public class DataInMemoryForStockAndTrade implements DataAceessObjectForStockAndTrade {

	private Map<String, Stock> StockDetailsInMap = new HashMap<String, Stock>();
	private Map<String, List<Trade>> tradDetailsInMap = new HashMap<String, List<Trade>>(); 
    
  public void addTrade(Trade trade) {
    List<Trade> trades = new ArrayList<Trade>();
    if (tradDetailsInMap.containsKey(trade.getStock().getSymbol())) {
        trades = tradDetailsInMap.get(trade.getStock().getSymbol());
    }
    trades.add(trade);
    tradDetailsInMap.put(trade.getStock().getSymbol(), trades);
  }
 
  public List<Trade> getTrades(Stock stock, int minutes) {
    List<Trade> result = new ArrayList<Trade>();
    Date afterDate = getDateXMinutesEarlier(minutes);
    List<Trade> trades = tradDetailsInMap.get(stock.getSymbol());
    Collections.sort(trades); 
    Iterator<Trade> it = trades.iterator();
    while (it.hasNext()) {
      Trade trade = it.next();
      if (trade.getTimestamp().before(afterDate)) { 
        break;
      }
      result.add(trade);
    }
    return result;
  }

  public List<Trade> getAllTrades() {
    List<Trade> result = new ArrayList<Trade>();
    for (String stockSymbol: tradDetailsInMap.keySet()) {
      result.addAll(tradDetailsInMap.get(stockSymbol));
    }
    return result;
  }


  private Date getDateXMinutesEarlier(int minutes){
    Calendar c = Calendar.getInstance();
    c.add(Calendar.MINUTE, -minutes);
    return c.getTime();
  }


  public void addStock(Stock stock) {
	    StockDetailsInMap.put(stock.getSymbol(), stock);
	  }

  public Stock getStock(String symbol) {
	    return StockDetailsInMap.get(symbol);
	  }

}
