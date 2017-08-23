import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class MonthChartPanel extends JPanel{
	private DefaultCategoryDataset monthgetDataset() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		String series = "profit";
		String type1 = "jan";
		String type2 = "Feb";
		String type3 = "Mar";
		String type4 = "Apr";
		String type5 = "May";
		String type6 = "June";
		String type7 = "Jul";
		String type8 = "Aug";
		String type9 = "Sept";
		String type10 = "Oct";
		String type11 = "Nov";
		String type12 = "Dec";

		dataset.addValue(150000, series, type1);
		dataset.addValue(500000, series, type2);
		dataset.addValue(300000, series, type3);
		dataset.addValue(600000, series, type4);
		dataset.addValue(900000, series, type5);
		dataset.addValue(850000, series, type6);
		dataset.addValue(700000, series, type7);
		dataset.addValue(800000, series, type8);
		dataset.addValue(950000, series, type9);
		dataset.addValue(1100000, series, type10);
		dataset.addValue(1400000, series, type11);
		dataset.addValue(1200000, series, type12);

		return dataset;
	}
	private JFreeChart monthChart = ChartFactory.createLineChart(
			"",
			"Month",
			"Profit",
			monthgetDataset(),
			PlotOrientation.VERTICAL,
			false,
			true,
			false
			);
	public MonthChartPanel() {
		ChartPanel monthCP = new ChartPanel(monthChart);
		this.add(monthCP);
	}

}
