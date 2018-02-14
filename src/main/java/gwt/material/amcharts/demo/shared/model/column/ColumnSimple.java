package gwt.material.amcharts.demo.shared.model.column;

import gwt.material.amcharts.demo.shared.model.ChartData;
import gwt.material.design.amcharts.client.ui.chart.SerialChart;
import gwt.material.design.amcharts.client.ui.chart.base.constants.GraphType;
import gwt.material.design.amcharts.client.ui.chart.base.constants.GridPosition;
import gwt.material.design.amcharts.client.ui.chart.base.constants.TickPosition;
import gwt.material.design.amcharts.client.ui.chart.options.*;
import gwt.material.design.client.constants.Color;

public class ColumnSimple extends ChartData {

    public ColumnSimple() {
        super("#simple-column", "Simple Column Chart", "If you take a close look at this chart, you will notice that grid lines are above the columns – equally dividing them into segments. This is an optional feature, of course. It allows you creating the charts close to the ones described by famous data visualization guru, Edward Tufte, in his book “The Visual Display of Quantitative Information”.");

        SerialChart chart = new SerialChart();
        chart.setWidth("100%");
        chart.setHeight("400px");
        chart.setMarginRight(70);

        DataLoader loader = new DataLoader();
        loader.setUrl("/data/column/line-data-basic.json");
        loader.setFormat("json");
        chart.setDataLoader(loader);

        ValueAxis valueAxis = new ValueAxis();
        valueAxis.setGridColor(Color.WHITE);
        valueAxis.setGridAlpha(0.2);
        valueAxis.setDashLength(0);
        chart.setValueAxes(valueAxis);

        chart.setGridAboveGraphs(true);
        chart.setStartDuration(1);

        Graph graph = new Graph();
        graph.setBalloonText("[[category]]: <b>[[value]]</b>");
        graph.setFillAlphas(0.8);
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
        categoryAxis.setGridAlpha(0);
        categoryAxis.setTickPosition(TickPosition.START);
        categoryAxis.setTickLength(20);
        chart.setCategoryAxis(categoryAxis);
        setChart(chart);
    }
}
