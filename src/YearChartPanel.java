import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class YearChartPanel extends JPanel{
	private DefaultCategoryDataset yeargetDataset() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		String series = "profit";
		String type1 = "2015";
		String type2 = "2016";
		String type3 = "2017";

		dataset.addValue(4000000, series, type1);
		dataset.addValue(3000000, series, type2);
		dataset.addValue(8000000, series, type3);

		return dataset;
	}
	private JFreeChart yearChart = ChartFactory.createLineChart(
			"",
			"YEAR",
			"Profit",
			yeargetDataset(),
			PlotOrientation.VERTICAL,
			false,
			true,
			false
			);
	public YearChartPanel() {
		ChartPanel yearCP = new ChartPanel(yearChart);
		this.add(yearCP);
	}

}
