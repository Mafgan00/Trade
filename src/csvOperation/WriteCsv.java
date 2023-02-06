package csvOperation;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * CSV書き込みクラス
 */
public class WriteCsv {

	/**
	 * CSVに累積損益を書き込む
	 * @param List<Double> 累積損益のリスト
	 */
	public void write(List<Double> profitAndLoss) {
		try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("profitAndLoss.csv", false), StandardCharsets.UTF_8))) {
			
			for (Double pal : profitAndLoss) {
				bw.write(String.valueOf(pal));
				bw.newLine();
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("ファイルが存在しません");
		} catch (IOException e) {
			System.out.println("エラーが発生しました");
		}
	}
}
