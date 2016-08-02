package stockmarket;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;




public class StockAndTradeMethodsImplementation implements InterfaceForStockAndTradeMethods {

	  private static StockAndTradeMethodsImplementation instance = null;
	

	  public static StockAndTradeMethodsImplementation getInstance() {
	    if (instance == null) {
	      instance = new StockAndTradeMethodsImplementation();
	    }
	    return instance;
	  }

	  private DataAceessObjectForStockAndTrade dataAceessObjectForStockAndTrade = new DataInMemoryForStockAndTrade();

	  public void addStock(Stock stock) {
		  dataAceessObjectForStockAndTrade.addStock(stock);
	  }


	  public Stock getStock(String symbol) {
	    return dataAceessObjectForStockAndTrade.getStock(symbol);
	  }


	  public double getDividendYield(Stock stock, double price) {
	    if (EnumValueForStockType.PREFERRED.equals(stock.getType())) {
	      return (stock.getFixedDividend() * stock.getParValue()) / price;
	    }
	    double result = stock.getLastDividend() / price;
	    return round(result, 2);
	  }


	  public double getPERatio(Stock stock, double price) {
	    double result = price / stock.getLastDividend();
	    return round(result, 2);
	  }


	  public double getVolumeWeightedStockPrice(List<Trade> trades) {
	       int sumOfQuantity = 0;
	       double sumOfPriceQuantity = 0;
	       //Date date=new Date();
	 	   //Date startTime=new Date(date.getTime()-(05*60*1000));
	       //SortedMap<Date,Trade>trade=trades12.tailMap(startTime);
	    for (Trade trade : trades) {
	      sumOfPriceQuantity = sumOfPriceQuantity + (trade.getPrice() * trade.getQuantity());
	      sumOfQuantity = sumOfQuantity + trade.getQuantity();
	    }
	    double result = sumOfPriceQuantity / sumOfQuantity;
	    return round(result, 2);
	  }
	  

	  public double getGBCE(List<Trade> trades) {
	    double total = 1;
	    for (Trade trade : trades) {
	      total = total * trade.getPrice();
	    }
	    double result = Math.pow(total, (1D / trades.size()));
	    return round(result, 2);
	  }


	  private static double round(double value, int places) {
	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	  }
	 
	  
	  public void recordTrade(Trade trade) {
	    if (trade != null && trade.getStock() != null) {
	    	dataAceessObjectForStockAndTrade.addTrade(trade);
	    }
	  }

	  public List<Trade> getTrades(Stock stock, int minutes) {
	    return dataAceessObjectForStockAndTrade.getTrades(stock, minutes);
	  }

	  public List<Trade> getAllTrades() {
	    return dataAceessObjectForStockAndTrade.getAllTrades();
	  }

	}

