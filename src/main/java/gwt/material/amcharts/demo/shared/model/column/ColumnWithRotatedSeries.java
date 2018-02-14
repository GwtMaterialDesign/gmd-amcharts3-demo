package gwt.material.amcharts.demo.shared.model.column;

import gwt.material.amcharts.demo.shared.model.ChartData;
import gwt.material.design.amcharts.client.ui.chart.SerialChart;
import gwt.material.design.amcharts.client.ui.chart.base.constants.GraphType;
import gwt.material.design.amcharts.client.ui.chart.base.constants.GridPosition;
import gwt.material.design.amcharts.client.ui.chart.base.constants.Position;
import gwt.material.design.amcharts.client.ui.chart.options.*;

public class ColumnWithRotatedSeries extends ChartData {

    public ColumnWithRotatedSeries() {
        super("#column-with-rotated-series", "Column With Rotated Series", "As you might have a lot of series on the axis, labels can be rotated so that they would all fit. You can rotate them by any degree you want. In case you don’t want rotated labels, they can wrap or some of the labels might be omitted.");

        SerialChart chart = new SerialChart();
        chart.setWidth("100%");
        chart.setHeight("400px");
        chart.setMarginRight(70);

        DataLoader loader = new DataLoader();
        loader.setUrl("/data/column/line-data-basic.json");
        loader.setFormat("json");
        chart.setDataLoader(loader);

        ValueAxis valueAxis = new ValueAxis();
        valueAxis.setAxisAlpha(0);
        valueAxis.setPosition(Position.LEFT);
        valueAxis.setTitle("Visitors from country");
        chart.setValueAxes(valueAxis);

        chart.setStartDuration(1);

        Graph graph = new Graph();
        graph.setBalloonText("<b>[[category]]: [[value]]</b>");
        graph.setFillColorsField("color");
        graph.setFillAlphas(0.9);
        graph.setLineAlpha(0.2);
        graph.setType(GraphType.COLUMN);
        graph.setValueField("visits");
        chart.setGraphs(graph);

        ChartCursor chartCursor = new ChartCursor();
        chartCursor.setCategoryBalloonEnabled(false);
        chartCursor.setCursorAlpha(0);
        chartCursor.setZoomable(false);
        chart.setChartCursor(chartCursor);

        chart.setCategoryField("country");

        CategoryAxis categoryAxis = new CategoryAxis();
        categoryAxis.setGridPosition(GridPosition.START);
        categoryAxis.setLabelRotation(45);
        chart.setCategoryAxis(categoryAxis);
        chart.setCategoryAxis(categoryAxis);
        setChart(chart);
    }
}
