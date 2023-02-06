package calculate;

import java.util.ArrayList;
import java.util.List;

/**
 * 計算クラス
 */
public class CalcurateRegressionLine {
	/**
	 * 切片を計算する
	 * @param itemXi 項目リスト（Xi）
	 * @param itemYi 項目リスト（Yi）
	 * @return 結果
	 */
	public Double section(final List<Double> itemsXi, final List<Double> itemsYi) {
		Double xbar = average(itemsXi);
		Double ybar = average(itemsYi);
		Double bata1 = regressionCoefficient(itemsXi, itemsYi);
		return ybar - (bata1 * xbar);
	}

	/**
	 * 回帰係数を計算する
	 * @param itemXi 項目リスト（Xi）
	 * @param itemYi 項目リスト（Yi）
	 * @return 結果
	 */
	public Double regressionCoefficient(final List<Double> itemsXi, final List<Double> itemsYi) {
		Double sxy = deviationSumOfProduct(itemsXi, itemsYi);
		Double sxx = sumOfSquares(itemsXi);
		return sxy / sxx;
	}

	/**
	 * 偏差積和を計算する
	 * @param itemXi 項目リスト（Xi）
	 * @param itemYi 項目リスト（Yi）
	 * @return 結果
	 */
	public Double deviationSumOfProduct(final List<Double> itemsXi, final List<Double> itemsYi) {
		List<Double> itemsXiYi = new ArrayList<>();
		int n = itemsXi.size();

		for (int i = 0; i < n; i++) {
			itemsXiYi.add(itemsXi.get(i) * itemsYi.get(i));
		}
		Double xiyiSum = sum(itemsXiYi);
		Double xiSum = sum(itemsXi);
		Double yiSum = sum(itemsYi);
		return xiyiSum - ((xiSum * yiSum) / n);
	}

	/**
	 * 平方和を計算する
	 * @param items 項目リスト
	 * @return 結果
	 */
	public Double sumOfSquares(final List<Double> items) {
		Double xbar = average(items);
		List<Double> squares = new ArrayList<>();

		for (Double item : items) {
			Double sqare = (item - xbar) * (item - xbar);
			squares.add(sqare);
		}
		return sum(squares);
	}

	/**
	 * 平均値を計算する
	 * @param items 項目リスト
	 * @return 結果
	 */
	public Double average(final List<Double> items) {
		return sum(items) / items.size();
	}

	/**
	 * 総和を計算する
	 * @param items 項目リスト
	 * @return 結果
	 */
	public Double sum(final List<Double> items) {
		Double result = 0.0;

		for (Double item : items) {
			result += item;
		}
		return result;
	}
}
