package csvOperation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import data.HistoricalData;
import data.OhlcData;

public class ReadCsv {


	public HistoricalData readCsv(String fileName) {
		
		Path path = Paths.get(fileName);
		List<OhlcData> ohlcdataList = new ArrayList<>();
		try (BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
			
			String textTradingHistory;
			while ((textTradingHistory = br.readLine()) != null) {
				
				String[] textLine = textTradingHistory.split(",");
				
				String date = textLine[0];
				double closePrice = Double.parseDouble(textLine[1]);
				double openPrice = Double.parseDouble(textLine[2]);
				double highPrice = Double.parseDouble(textLine[3]);
				double lowPrice = Double.parseDouble(textLine[4]);
				
				OhlcData ohlcdata = new OhlcData(date, openPrice, highPrice, lowPrice, closePrice);
				ohlcdataList.add(ohlcdata);
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("ファイルが存在しません");
		} catch (IOException e) {
			System.out.println("エラーが発生しました");
		}
		
		HistoricalData historicalData = new HistoricalData(ohlcdataList);
		return historicalData;
	}
}