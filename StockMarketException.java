package stockmarket;

/**
 * custom exception class for any exceptions.
 * 
 */
public class StockMarketException extends Exception {

  private static final long serialVersionUID = 1L;

  public StockMarketException(String message) {
    super(message);
  }

}