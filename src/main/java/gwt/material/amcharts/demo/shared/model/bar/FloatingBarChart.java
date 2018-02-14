package gwt.material.amcharts.demo.shared.model.bar;

import gwt.material.amcharts.demo.shared.model.ChartData;
import gwt.material.design.amcharts.client.ui.chart.SerialChart;
import gwt.material.design.amcharts.client.ui.chart.base.constants.GraphType;
import gwt.material.design.amcharts.client.ui.chart.base.constants.GridPosition;
import gwt.material.design.amcharts.client.ui.chart.base.constants.Position;
import gwt.material.design.amcharts.client.ui.chart.options.CategoryAxis;
import gwt.material.design.amcharts.client.ui.chart.options.DataLoader;
import gwt.material.design.amcharts.client.ui.chart.options.Graph;

public class FloatingBarChart extends ChartData {

    public FloatingBarChart() {
        super("#floating-bar-chart", "Floating Bar Chart", "As you can see, the bars (also columns) in our charts can start and end at any place, not necessary from axis or zero-coordinate. All you need to do is set openField for column graph and set the open values in data. For a floating-columns check Waterfall chart demo.");

        SerialChart chart = new SerialChart();
        chart.setWidth("100%");
        chart.setHeight("400px");
        chart.setMarginRight(70);

        DataLoader loader = new DataLoader();
        loader.setUrl("/data/bar/floating-bar-data.json");
        loader.setFormat("json");
        chart.setDataLoader(loader);

        chart.setStartDuration(1);

        Graph graph = new Graph();
        graph.setBalloonText("<b>[[category]]</b><br>starts at [[startTime]]<br>ends at [[endTime]]");
        graph.setColorField("color");
        graph.setFillAlphas(0.8);
        graph.setLineAlpha(0);
        graph.setOpenField("startTime");
        graph.setType(GraphType.COLUMN);
        graph.setValueField("endTime");
        chart.setGraphs(graph);

        chart.setRotate(true);
        chart.setColumnWidth(1);
        chart.setCategoryField("name");

        CategoryAxis categoryAxis = new CategoryAxis();
        categoryAxis.setGridPosition(GridPosition.START);
        categoryAxis.setAxisAlpha(0);
        categoryAxis.setGridAlpha(0.1);
        categoryAxis.setPosition(Position.LEFT);
        chart.setCategoryAxis(categoryAxis);

        setChart(chart);
    }
}
