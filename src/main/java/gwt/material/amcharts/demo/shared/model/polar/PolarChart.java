package gwt.material.amcharts.demo.shared.model.polar;

import gwt.material.amcharts.demo.shared.model.ChartData;
import gwt.material.design.amcharts.client.ui.chart.GanttChart;
import gwt.material.design.amcharts.client.ui.chart.base.constants.GraphBullet;
import gwt.material.design.amcharts.client.ui.chart.base.constants.GridType;
import gwt.material.design.amcharts.client.ui.chart.base.constants.Position;
import gwt.material.design.amcharts.client.ui.chart.js.options.Guide;
import gwt.material.design.amcharts.client.ui.chart.options.DataLoader;
import gwt.material.design.amcharts.client.ui.chart.options.Graph;
import gwt.material.design.amcharts.client.ui.chart.options.ValueAxis;
import gwt.material.design.client.constants.Color;

public class PolarChart extends ChartData {

    public PolarChart() {
        super("#basic-gantt-chart", "GANTT Chart", "GANTT chart is a modification of a bar chart, showing several segments, not necessarily attached to each other. It can be used to mark spans of time, date or values. The most obvious and wide use for GANTT chart is to show person’s or team’s tasks span over time as well as relationships between those tasks. This particular demo uses hours as segment duration parameter. The units used in it are defined by period parameter.");

        GanttChart chart = new GanttChart();
        chart.setWidth("100%");
        chart.setHeight("400px");
        chart.setMarginRight(70);

        DataLoader loader = new DataLoader();
        loader.setUrl("/data/polar/polar-chart.json");
        loader.setFormat("json");
        chart.setDataLoader(loader);

        ValueAxis valueAxis = new ValueAxis();
        valueAxis.setGridType(GridType.CIRCLE);
        valueAxis.setMinimum(0);
        valueAxis.setAutoGridCount(false);
        valueAxis.setAxisAlpha(0.2);
        valueAxis.setFillAlpha(0.05);
        valueAxis.setFillColor(Color.WHITE);
        valueAxis.setGridAlpha(0.08);

        Guide guide1 = new Guide();
        guide1.setAngle(225);
        guide1.setFillAlpha(0.3);
        guide1.setFillColor("#0066CC");
        guide1.setTickLength(0);
        guide1.setToAngle(315);
        guide1.setToValue(14);
        guide1.setValue(0);
        guide1.setLineAlpha(0);

        Guide guide2 = new Guide();
        guide2.setAngle(45);
        guide2.setFillAlpha(0.3);
        guide2.setFillColor("#CC3333");
        guide2.setTickLength(0);
        guide2.setToAngle(135);
        guide2.setToValue(14);
        guide2.setValue(0);
        guide2.setLineAlpha(0);

        valueAxis.setPosition(Position.LEFT);
        valueAxis.setGuides(guide1, guide2);
        chart.setValueAxis(valueAxis);

        chart.setStartDuration(1);

        Graph graph = new Graph();
        graph.setBalloonText("[[category]]: [[value]] m/s");
        graph.setBullet(GraphBullet.ROUND);
        graph.setFillAlphas(0.3);
        graph.setValueField("value");
        chart.setGraph(graph);

        chart.setCategoryField("direction");

        setChart(chart);
    }
}
