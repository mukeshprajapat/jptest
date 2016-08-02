package stockmarket;


public class Stock {

  private String symbol;
  private EnumValueForStockType type;
  private double lastDividend;
  private double fixedDividend;
  private double parValue;

  public Stock(String symbol, EnumValueForStockType type, double lastDividend,
      double fixedDividend, double parValue) {
    super();
    this.symbol = symbol;
    this.type = type;
    this.lastDividend = lastDividend;
    this.fixedDividend = fixedDividend;
    this.parValue = parValue;
  }

public String getSymbol() {
	return symbol;
}

public void setSymbol(String symbol) {
	this.symbol = symbol;
}

public EnumValueForStockType getType() {
	return type;
}

public void setType(EnumValueForStockType type) {
	this.type = type;
}

public double getLastDividend() {
	return lastDividend;
}

public void setLastDividend(double lastDividend) {
	this.lastDividend = lastDividend;
}

public double getFixedDividend() {
	return fixedDividend;
}

public void setFixedDividend(double fixedDividend) {
	this.fixedDividend = fixedDividend;
}

public double getParValue() {
	return parValue;
}

public void setParValue(double parValue) {
	this.parValue = parValue;
}

  
}
