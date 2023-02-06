package backTest;

import java.util.ArrayList;
import java.util.List;

import data.Account;
import data.HistoricalData;

/**
 * ペアトレード戦略クラス
 */
public class PairTradeStrategy {
	
	//買いトリガーの閾値
	final double upperThreshold = 1.09;
	//売りトリガーの閾値
	final double lowerThreshold = 1.05;
	
	Account account = new Account();
	
	List<Double> profitAndLossList = new ArrayList<>();
	
	public List<Double> pairTrade(HistoricalData data1, HistoricalData data2) {
		
		//data2の終値/data1の終値(閾値判定に使用)
		double data2Perdata1 = 0;
		//ポジション保有の有無
		boolean position = false;
		//ポジションがData1の買いポジションか売りポジションか
		boolean buyData1position = false;
		//ポジション保有時のData1の価格
		double positionData1Price = 0;
		//ポジション保有時のData2の価格
		double positionData2Price = 0;
		
		for(int i = 0; i < data1.getHistoricalData().size(); i++) {
			
			double data1Price = data1.getHistoricalData().get(i).getClosePrice();
			double data2Price = data2.getHistoricalData().get(i).getClosePrice();
			data2Perdata1 = data2Price/data1Price;
			
			//Position保有時かつData1買いData2売り
			if(position && buyData1position) {
				
				//ポジションクローズ時の閾値判定
				if(data2Perdata1 <= upperThreshold - 0.01) {
					double profitAndLoss =  data1Price - positionData1Price + positionData2Price - data2Price;
					account.setProfitAndLoss(account.getProfitAndLoss()+profitAndLoss);
				}
				
			//Position保有時かつData1売りData2買い
			}else if(position && !buyData1position) {
				
				//ポジションクローズ時の閾値判定
				if(data2Perdata1 >= lowerThreshold + 0.01) {
					double profitAndLoss =  data2Price - positionData2Price + positionData1Price - data1Price;
					account.setProfitAndLoss(account.getProfitAndLoss()+profitAndLoss);
				}
				
			}
			
			//Position未保有時
			//Data1買いData2売り時の閾値判定
			if(!position && (data2Perdata1 >= upperThreshold)) {
				
				positionData1Price = data1Price;
				positionData2Price = data2Price;
				position = true;
				buyData1position = true;
				
			//Position未保有時
			//Data1売りData2買い時の閾値判定
			}else if(!position && (data2Perdata1 <= lowerThreshold)) {
				
				positionData1Price = data1.getHistoricalData().get(i).getClosePrice();
				positionData2Price = data2.getHistoricalData().get(i).getClosePrice();
				position = true;
				buyData1position = false;
				
			}
			profitAndLossList.add(account.getProfitAndLoss());
			System.out.println(account.getProfitAndLoss());
		}
		return profitAndLossList;
	}
}
