package gwt.material.amcharts.demo.shared.model.column;

import gwt.material.amcharts.demo.shared.model.ChartData;
import gwt.material.design.amcharts.client.ui.chart.GanttChart;
import gwt.material.design.amcharts.client.ui.chart.base.constants.ValueAxisType;
import gwt.material.design.amcharts.client.ui.chart.options.*;

import java.sql.Date;

public class BasicGanttChart extends ChartData {

    public BasicGanttChart() {
        super("#basic-gantt-chart", "GANTT Chart", "GANTT chart is a modification of a bar chart, showing several segments, not necessarily attached to each other. It can be used to mark spans of time, date or values. The most obvious and wide use for GANTT chart is to show person’s or team’s tasks span over time as well as relationships between those tasks. This particular demo uses hours as segment duration parameter. The units used in it are defined by period parameter.");

        GanttChart chart = new GanttChart();
        chart.setWidth("100%");
        chart.setHeight("400px");
        chart.setMarginRight(70);

        DataLoader loader = new DataLoader();
        loader.setUrl("/data/column/gantt-data.json");
        loader.setFormat("json");
        chart.setDataLoader(loader);

        chart.setPeriod("hh");
        chart.setDataDateFormat("YYYY-MM-DD");
        chart.setBalloonDateFormat("JJ:NN");
        chart.setColumnWidth(0.5);

        ValueAxis valueAxis = new ValueAxis();
        valueAxis.setType(ValueAxisType.DATE);
        chart.setValueAxis(valueAxis);

        chart.setBrightnessStep(10);

        Graph graph = new Graph();
        graph.setFillAlphas(1);
        graph.setBalloonText("<b>[[task]]</b>: [[open]] [[value]]");
        chart.setGraph(graph);

        chart.setRotate(true);
        chart.setCategoryField("category");
        chart.setSegmentsField("segments");
        chart.setColorField("color");
        chart.setStartDate(new Date(115, 0, 1));
        chart.setStartField("start");
        chart.setEndField("end");
        chart.setDurationField("duration");

        ChartScrollbar chartScrollbar = new ChartScrollbar();
        chartScrollbar.setAutoGridCount(true);
        chart.setValueScrollbar(chartScrollbar);

        ChartCursor chartCursor = new ChartCursor();
        chartCursor.setCursorColor("#55bb76");
        chartCursor.setValueBalloonsEnabled(false);
        chartCursor.setCursorAlpha(0);
        chartCursor.setValueLineAlpha(0.5);
        chartCursor.setValueLineBalloonEnabled(true);
        chartCursor.setZoomable(false);
        chartCursor.setValueZoomable(true);
        chart.setChartCursor(chartCursor);

        setChart(chart);
    }
}
