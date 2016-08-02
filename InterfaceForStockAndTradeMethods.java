package stockmarket;

import java.util.List;


public interface InterfaceForStockAndTradeMethods {

	public void addStock(Stock stock);
	public Stock getStock(String symbol);
	public double getDividendYield(Stock stock, double price);
	public double getPERatio(Stock stock, double price);
	public double getVolumeWeightedStockPrice(List<Trade> trades);
	public double getGBCE(List<Trade> trades);
  	public void recordTrade(Trade trade);
	public List<Trade> getTrades(Stock stock, int numberOfMinutes);
	public List<Trade> getAllTrades();
  
}
