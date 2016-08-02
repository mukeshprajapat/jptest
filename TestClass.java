package stockmarket;


import java.util.Scanner;
import java.util.Calendar;
import java.util.List;


public class TestClass {

  private static InterfaceForStockAndTradeMethods interfaceForStockAndTradeMethods = StockAndTradeMethodsImplementation.getInstance();
  private static Scanner scanner;

  public static void main(String[] args) {
    initializStocks();
    displayRequirementsWithOptions();
    /*---------------------------------------------------------------------------------------------------------------------------*/
    scanner = new Scanner(System.in);
    String options= null;
    while (true) {
      options = scanner.nextLine();
      if ("6".equals(options)) {
        scanner.close();
        System.exit(0);
      } else {
        try {
          int option = Integer.parseInt(options);
          Stock stockDetails;
          double price;

          switch (option) {
            case 1:
              stockDetails = getStockDetails();
              price = getStockPrice();
              getDividendYield(stockDetails, price);
              break;
            case 2:
              stockDetails = getStockDetails();
              price = getStockPrice();
              getPERatio(stockDetails, price);
              break;
            case 3:
              stockDetails = getStockDetails();
              int quantity = getQuantity();
              EnumValueForTradeType tradeType = getTradeType();
              price = getStockPrice();
              recordTrade(stockDetails, quantity, tradeType, price);
              break;
            case 4:
              stockDetails = getStockDetails();
              getVolumeWeightedStockPrice(stockDetails);
              break;
            case 5:
              calculateGBCE();
              break;
            default:
              break;
          }
        } catch (NumberFormatException e) {
        	System.out.println("Invalid Option");
        } catch (StockMarketException e1) {
        	System.out.println(e1.getMessage());
        }
         displayRequirementsWithOptions();
      }
    }
  }
  /*---------------------------------------------------------------------------------------------------------------------------*/
  private static Stock getStockDetails() throws StockMarketException {
    System.out.println("Please input stock symbol");
    String stockSymbol = scanner.nextLine();
    Stock stock = interfaceForStockAndTradeMethods.getStock(stockSymbol);
    if (stock == null) {
      throw new StockMarketException("Stock not found");
    }
    return stock;
  }
  /*---------------------------------------------------------------------------------------------------------------------------*/
  private static double getStockPrice() throws StockMarketException {
    System.out.println("Please input stock price");
    String stockPrice = scanner.nextLine();
    try {
      double result = Double.parseDouble(stockPrice);
      if (result <= 0) {
        throw new StockMarketException("Invalid price: Must be greated than 0");
      }
      return result;
    } catch (NumberFormatException e) {
      throw new StockMarketException("Invalid price: Not a number");
    }
  }
  /*---------------------------------------------------------------------------------------------------------------------------*/
  private static EnumValueForTradeType getTradeType() throws StockMarketException {
    System.out.println("Please input trade type (BUY/SELL)");
    String type = scanner.nextLine();
    try {
      return EnumValueForTradeType.valueOf(type.toUpperCase());
    } catch (IllegalArgumentException e) {
      throw new StockMarketException("Invalid trade type: Must be BUY or SELL");
    }
  }
  /*---------------------------------------------------------------------------------------------------------------------------*/
  private static int getQuantity() throws StockMarketException {
    System.out.println("Please input quantity");
    String quantity = scanner.nextLine();
    try {
      int result = Integer.parseInt(quantity);
      if (result <= 0) {
        throw new StockMarketException("Invalid quantity: Must be greated than 0");
      }
      return result;
    } catch (NumberFormatException e) {
      throw new StockMarketException("Invalid quantity: Not a number");
    }
  }

  /*---------------------------------------------------------------------------------------------------------------------------*/
  private static void getDividendYield(Stock stock, double price) {
    double result = interfaceForStockAndTradeMethods.getDividendYield(stock, price);
    System.out.println("Dividend Yield: " + result);
  }
  /*---------------------------------------------------------------------------------------------------------------------------*/
  private static void getPERatio(Stock stock, double price) {
     double result = interfaceForStockAndTradeMethods.getPERatio(stock, price);
     System.out.println("PE Ratio: " + result);
  }
  /*---------------------------------------------------------------------------------------------------------------------------*/

  private static void getVolumeWeightedStockPrice(Stock stock) {
    List<Trade> trades = interfaceForStockAndTradeMethods.getTrades(stock, 05);
    if (trades == null || trades.isEmpty()) {
    	System.out.println("Volume Weighted Stock Price: No trades");
    } else {
      double result = interfaceForStockAndTradeMethods.getVolumeWeightedStockPrice(trades);
      System.out.println("Volume Weighted Stock Price: " + result);
    }
  }
  /*---------------------------------------------------------------------------------------------------------------------------*/
 
  private static void recordTrade(Stock stock, int quantity, EnumValueForTradeType type, double price) {
	  interfaceForStockAndTradeMethods.recordTrade(new Trade(stock, Calendar.getInstance().getTime(),quantity, type, price));
    System.out.println("Trade recorded");
  }
  /*---------------------------------------------------------------------------------------------------------------------------*/
  private static void calculateGBCE() {
    List<Trade> allTrades = interfaceForStockAndTradeMethods.getAllTrades();
    if (allTrades == null || allTrades.isEmpty()) {
    	System.out.println("Unable to calculate GBCE: No trades");
    } else {
    	System.out.println("GBCE: " + interfaceForStockAndTradeMethods.getGBCE(allTrades));
    }
  }
  /*---------------------------------------------------------------------------------------------------------------------------*/
  private static void initializStocks() {
    interfaceForStockAndTradeMethods.addStock(new Stock("TEA", EnumValueForStockType.COMMON, 0, 0, 100));
    interfaceForStockAndTradeMethods.addStock(new Stock("POP", EnumValueForStockType.COMMON, 8, 0, 100));
    interfaceForStockAndTradeMethods.addStock(new Stock("ALE", EnumValueForStockType.COMMON, 23, 0, 60));
    interfaceForStockAndTradeMethods.addStock(new Stock("GIN", EnumValueForStockType.PREFERRED, 8, 2, 100));
    interfaceForStockAndTradeMethods.addStock(new Stock("JOE", EnumValueForStockType.PREFERRED, 13, 0, 250));
  }  
  /*---------------------------------------------------------------------------------------------------------------------------*/
private static void displayRequirementsWithOptions() {
	    System.out.println("Enter 1 to calculate dividend yield");
	    System.out.println("Enter 2 to calculate P/E ratio");
	    System.out.println("Enter 3 to record a trade");
	    System.out.println("Enter 4 to  Volume Weighted Stock Price");
	    System.out.println("Enter 5 to calculate GBCE All Share Index");
	    System.out.println("Enter 6 to  Exit");
}
}
/*---------------------------------------------------------------------------------------------------------------------------*/
