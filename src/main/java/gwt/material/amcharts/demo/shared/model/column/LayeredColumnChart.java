package gwt.material.amcharts.demo.shared.model.column;

import gwt.material.amcharts.demo.shared.model.ChartData;
import gwt.material.design.amcharts.client.ui.chart.SerialChart;
import gwt.material.design.amcharts.client.ui.chart.base.constants.GraphType;
import gwt.material.design.amcharts.client.ui.chart.base.constants.GridPosition;
import gwt.material.design.amcharts.client.ui.chart.base.constants.Position;
import gwt.material.design.amcharts.client.ui.chart.options.CategoryAxis;
import gwt.material.design.amcharts.client.ui.chart.options.DataLoader;
import gwt.material.design.amcharts.client.ui.chart.options.Graph;
import gwt.material.design.amcharts.client.ui.chart.options.ValueAxis;

public class LayeredColumnChart extends ChartData {

    public LayeredColumnChart() {
        super("#layered-column-chart", "Layered Column Chart", "With amCharts library you can easily produce layered column or bar chart with any number of layers. The trick is simple, you should set clustered property of a graph to false. Besides that you might want to make columns of the graph more narrow – set columnWidth to a smaller than default value, like 0.5.");

        SerialChart chart = new SerialChart();
        chart.setWidth("100%");
        chart.setHeight("400px");
        chart.setMarginRight(70);

        DataLoader loader = new DataLoader();
        loader.setUrl("/data/column/layered-column-chart.json");
        loader.setFormat("json");
        chart.setDataLoader(loader);

        ValueAxis valueAxis = new ValueAxis();
        valueAxis.setUnit("%");
        valueAxis.setPosition(Position.LEFT);
        valueAxis.setTitle("GDP growth rate");
        chart.setValueAxes(valueAxis);

        chart.setStartDuration(1);

        Graph graph1 = new Graph();
        graph1.setBalloonText("GDP grow in [[category]] (2004): <b>[[value]]</b>");
        graph1.setFillAlphas(0.9);
        graph1.setLineAlpha(0.2);
        graph1.setTitle("2004");
        graph1.setType(GraphType.COLUMN);
        graph1.setValueField("year2004");

        Graph graph2 = new Graph();
        graph2.setBalloonText("GDP grow in [[category]] (2005): <b>[[value]]</b>");
        graph2.setFillAlphas(0.9);
        graph2.setLineAlpha(0.2);
        graph2.setTitle("2005");
        graph2.setType(GraphType.COLUMN);
        graph2.setClustered(false);
        graph2.setColumnWidth(0.5);
        graph2.setValueField("year2005");

        chart.setGraphs(graph1, graph2);

        chart.setPlotAreaFillAlphas(0.1);
        chart.setCategoryField("country");

        CategoryAxis categoryAxis = new CategoryAxis();
        categoryAxis.setGridPosition(GridPosition.START);
        chart.setCategoryAxis(categoryAxis);

        setChart(chart);
    }
}
