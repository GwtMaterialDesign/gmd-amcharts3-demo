package gwt.material.amcharts.demo.shared.model.bar;

import gwt.material.amcharts.demo.shared.model.ChartData;
import gwt.material.design.amcharts.client.ui.chart.SerialChart;
import gwt.material.design.amcharts.client.ui.chart.base.constants.GraphType;
import gwt.material.design.amcharts.client.ui.chart.base.constants.GridPosition;
import gwt.material.design.amcharts.client.ui.chart.base.constants.Position;
import gwt.material.design.amcharts.client.ui.chart.options.CategoryAxis;
import gwt.material.design.amcharts.client.ui.chart.options.DataLoader;
import gwt.material.design.amcharts.client.ui.chart.options.Graph;
import gwt.material.design.amcharts.client.ui.chart.options.ValueAxis;

public class ClusteredBarChart extends ChartData {

    public ClusteredBarChart() {
        super("#clustered-bar-chart", "Clustered Bar Chart", "Clustered bar chart is a chart when bars of a different graphs are placed next to each another, and not stacked one on another, so basically it’s the most simple bar chart with two graphs in it.");

        SerialChart chart = new SerialChart();
        chart.setWidth("100%");
        chart.setHeight("400px");
        chart.setMarginRight(70);

        DataLoader loader = new DataLoader();
        loader.setUrl("/data/bar/clustered-bar-chart.json");
        loader.setFormat("json");
        chart.setDataLoader(loader);

        chart.setCategoryField("year");
        chart.setRotate(true);
        chart.setStartDuration(1);

        CategoryAxis categoryAxis = new CategoryAxis();
        categoryAxis.setGridPosition(GridPosition.START);
        categoryAxis.setPosition(Position.LEFT);
        chart.setCategoryAxis(categoryAxis);

        Graph graph1 = new Graph();
        graph1.setBalloonText("Income:[[value]]");
        graph1.setFillAlphas(0.8);
        graph1.setId("AmGraph-1");
        graph1.setLineAlpha(0.2);
        graph1.setTitle("Income");
        graph1.setType(GraphType.COLUMN);
        graph1.setValueField("income");

        Graph graph2 = new Graph();
        graph2.setBalloonText("Expenses:[[value]]");
        graph2.setFillAlphas(0.8);
        graph2.setId("AmGraph-2");
        graph2.setLineAlpha(0.2);
        graph2.setTitle("Expenses");
        graph2.setType(GraphType.COLUMN);
        graph2.setValueField("expenses");

        chart.setGraphs(graph1, graph2);

        ValueAxis valueAxis = new ValueAxis();
        valueAxis.setId("ValueAxis-1");
        valueAxis.setPosition(Position.TOP);
        valueAxis.setAxisAlpha(0);
        chart.setValueAxes(valueAxis);

        setChart(chart);
    }
}
