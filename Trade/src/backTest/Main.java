package backTest;

import csvOperation.ReadCsv;
import csvOperation.WriteCsv;
import data.HistoricalData;

public class Main {
	
	public static void main(String[] args) {
		
		ReadCsv rc = new ReadCsv();
		WriteCsv wc = new WriteCsv();
		HistoricalData wtiOilHistoricalData = rc.readCsv("WTI原油2010-2023.csv");
		HistoricalData ukOilHistoricalData = rc.readCsv("ブレント原油2010-2023.csv");
		PairTradeStrategy pairTradeStrategy = new PairTradeStrategy();	
		wc.write(pairTradeStrategy.pairTrade(wtiOilHistoricalData, ukOilHistoricalData));
	}
}
