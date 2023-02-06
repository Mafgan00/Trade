package data;

import java.util.List;

public class HistoricalData {
	
	private List<OhlcData> historicalData;

	public HistoricalData(List<OhlcData> historicalData) {
		this.historicalData = historicalData;
	}

	public List<OhlcData> getHistoricalData() {
		return historicalData;
	}
	
}