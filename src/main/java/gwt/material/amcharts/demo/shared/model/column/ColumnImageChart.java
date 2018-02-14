package gwt.material.amcharts.demo.shared.model.column;

import gwt.material.amcharts.demo.shared.model.ChartData;
import gwt.material.design.amcharts.client.ui.chart.SerialChart;
import gwt.material.design.amcharts.client.ui.chart.base.constants.GraphType;
import gwt.material.design.amcharts.client.ui.chart.options.CategoryAxis;
import gwt.material.design.amcharts.client.ui.chart.options.DataLoader;
import gwt.material.design.amcharts.client.ui.chart.options.Graph;

public class ColumnImageChart extends ChartData {

    public ColumnImageChart() {
        super("#image-column", "Column chart with images on top", "Our charts do support custom bullets, this means you can have them on column chart too. It’s quite often scenario that a company logo or some other image is to be placed above the column – check this example, it’s quite easy to do.");

        SerialChart chart = new SerialChart();
        chart.setWidth("100%");
        chart.setHeight("400px");
        chart.setMarginRight(70);

        DataLoader loader = new DataLoader();
        loader.setUrl("/data/column/column-image-data.json");
        loader.setFormat("json");
        chart.setDataLoader(loader);

        chart.setStartDuration(1);

        Graph graph = new Graph();
        graph.setBalloonText("<span style='font-size:13px;'>[[category]]: <b>[[value]]</b></span>");
        graph.setBulletOffset(10);
        graph.setBulletSize(52);
        graph.setColorField("color");
        graph.setCornerRadiusTop(8);
        graph.setCustomBulletField("bullet");
        graph.setFillAlphas(0.8);
        graph.setLineAlpha(0);
        graph.setType(GraphType.COLUMN);
        graph.setValueField("points");
        chart.setGraphs(graph);

        chart.setMarginTop(0);
        chart.setMarginRight(0);
        chart.setMarginLeft(0);
        chart.setMarginBottom(0);
        chart.setAutoMargins(false);
        chart.setCategoryField("name");

        CategoryAxis categoryAxis = new CategoryAxis();
        categoryAxis.setAxisAlpha(0);
        categoryAxis.setGridAlpha(0);
        categoryAxis.setInside(true);
        categoryAxis.setTickLength(0);
        chart.setCategoryAxis(categoryAxis);

        setChart(chart);
    }
}
