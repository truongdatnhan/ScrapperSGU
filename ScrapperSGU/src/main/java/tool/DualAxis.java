package tool;

import java.awt.BorderLayout;

//Đây là thư viện để vẽ biểu đồ (sử dụng jcommon+jfreechart)

import java.awt.Color;
import java.util.ArrayList;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.LegendItem;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.axis.*;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.ui.TextAnchor;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class DualAxis {

	private JFreeChart chart;
	
    public DualAxis(ArrayList<String> hk,ArrayList<Float> dtb4,ArrayList<Float> dtb10) {
        final CategoryDataset dataset1 = createDataset1(hk,dtb4);
        final CategoryDataset dataset2 = createDataset2(hk,dtb10);
        this.chart = createChart(dataset1, dataset2);
    }

    public JFreeChart getChart() {
		return chart;
	}

	public void setChart(JFreeChart chart) {
		this.chart = chart;
	}

	private CategoryDataset createDataset1(ArrayList<String> hk,ArrayList<Float> dtb4) {

        // row keys...
        final String series1 = "Điểm hệ 4";
        final String series2 = "Dummy 1";

        // length
        int n = hk.size();

        // create the dataset...
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.clear();
        for(int i = 0; i<n ; i++)
        {

            dataset.addValue((Float)dtb4.get(i), series1, hk.get(i).toString());
        }

        for(int i = 0; i<n ; i++)
        {

            dataset.addValue(null, series2, hk.get(i).toString());
        }


        return dataset;

    }

    private CategoryDataset createDataset2(ArrayList<String> hk,ArrayList<Float> dtb10) {

        // row keys...
        final String series1 = "Dummy 2";
        final String series2 = "Điểm hệ 10";

        // length
        int n = hk.size();

        // create the dataset...
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.clear();
        for(int i = 0; i<n ; i++)
        {
            dataset.addValue(null, series1, hk.get(i).toString());

        }

        for(int i = 0; i<n ; i++)
        {
            dataset.addValue((Float)dtb10.get(i), series2, hk.get(i).toString());
        }

        return dataset;

    }

    private JFreeChart createChart(final CategoryDataset dataset1, final CategoryDataset dataset2) {

        final CategoryAxis domainAxis = new CategoryAxis("Học kì");
        final NumberAxis rangeAxis = new NumberAxis("Điểm trung bình hệ 4");
        final BarRenderer renderer1 = new BarRenderer();
        final CategoryPlot plot = new CategoryPlot(dataset1, domainAxis, rangeAxis, renderer1) {

            public LegendItemCollection getLegendItems() {

                final LegendItemCollection result = new LegendItemCollection();

                final CategoryDataset data = getDataset();
                if (data != null) {
                    final CategoryItemRenderer r = getRenderer();
                    if (r != null) {
                        final LegendItem item = r.getLegendItem(0, 0);
                        result.add(item);
                    }
                }

                // the JDK 1.2.2 compiler complained about the name of this
                // variable
                final CategoryDataset dset2 = getDataset(1);
                if (dset2 != null) {
                    final CategoryItemRenderer renderer2 = getRenderer(1);
                    renderer2.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
                    renderer2.setDefaultItemLabelsVisible(true);
                    ItemLabelPosition position = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12,
                            TextAnchor.TOP_CENTER);
                    renderer2.setDefaultPositiveItemLabelPosition(position);
                    if (renderer2 != null) {
                        final LegendItem item = renderer2.getLegendItem(1, 1);
                        result.add(item);
                    }
                }

                return result;

            }

        };

        final JFreeChart chart = new JFreeChart("Điểm trung bình qua từng học kì", plot);
        chart.setBackgroundPaint(Color.white);
//        chart.getLegend().setAnchor(Legend.SOUTH);
        plot.setBackgroundPaint(new Color(0xEE, 0xEE, 0xFF));
        plot.setDomainAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);

        plot.setDataset(1, dataset2);

        plot.mapDatasetToRangeAxis(1, 1);
        final ValueAxis axis2 = new NumberAxis("Điểm trung bình hệ 10/100");
        plot.setRangeAxis(1, axis2);
        plot.setRangeAxisLocation(1, AxisLocation.BOTTOM_OR_RIGHT);
        final BarRenderer renderer2 = new BarRenderer();
        plot.setRenderer(1, renderer2);

        CategoryItemRenderer renderer = ((CategoryPlot)chart.getPlot()).getRenderer();
        renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setDefaultItemLabelsVisible(true);
        ItemLabelPosition position = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12,
                TextAnchor.TOP_CENTER);
        renderer.setDefaultPositiveItemLabelPosition(position);

        final CategoryAxis domainAxiss = plot.getDomainAxis();
        domainAxiss.setCategoryLabelPositions(CategoryLabelPositions.UP_45);

        plot.getDomainAxis().setCategoryMargin(0.45);

        return chart;
    }
    
}
