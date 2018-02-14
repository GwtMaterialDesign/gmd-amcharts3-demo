package gwt.material.amcharts.demo.shared.model.column;

import gwt.material.amcharts.demo.shared.model.ChartData;
import gwt.material.design.amcharts.client.ui.chart.GanttChart;
import gwt.material.design.amcharts.client.ui.chart.base.constants.ValueAxisType;
import gwt.material.design.amcharts.client.ui.chart.options.*;
import gwt.material.design.client.constants.Color;

public class DateGanttChart extends ChartData {

    public DateGanttChart() {
        super("#gantt-chart-with-dates", "GANTT Chart with dates", "This version of GANTT chart shows how you can use absolute dates to define start and end for segments, regardless of where the other segment in the same category ended. The config uses startDateField and endDateField to point to a specific fields in segment data that hold their start and end dates respectively. Please note that this demo also takes advantage of brightnessStep setting. It allows increasing brightness with each subsequent segment gradually.");

        GanttChart chart = new GanttChart();
        chart.setWidth("100%");
        chart.setHeight("400px");
        chart.setMarginRight(70);

        DataLoader loader = new DataLoader();
        loader.setUrl("/data/column/gantt-date-data.json");
        loader.setFormat("json");
        chart.setDataLoader(loader);

        chart.setPeriod("DD");
        chart.setDataDateFormat("YYYY-MM-DD");
        chart.setColumnWidth(0.5);

        ValueAxis valueAxis = new ValueAxis();
        valueAxis.setType(ValueAxisType.DATE);
        chart.setValueAxis(valueAxis);

        chart.setBrightnessStep(7);

        Graph graph = new Graph();
        graph.setFillAlphas(1);
        graph.setLineAlpha(1);
        graph.setLineColor(Color.WHITE);
        graph.setFillAlphas(0.85);
        graph.setBalloonText("<b>[[task]]</b>:<br />[[open]] -- [[value]]");
        chart.setGraph(graph);

        chart.setRotate(true);
        chart.setCategoryField("category");
        chart.setSegmentsField("segments");
        chart.setColorField("color");
        chart.setStartDateField("start");
        chart.setEndDateField("end");

        ChartScrollbar chartScrollbar = new ChartScrollbar();
        chartScrollbar.setAutoGridCount(true);
        chart.setChartScrollbar(chartScrollbar);

        ChartCursor chartCursor = new ChartCursor();
        chartCursor.setCursorColor("#55bb76");
        chartCursor.setValueBalloonsEnabled(false);
        chartCursor.setCursorAlpha(0);
        chartCursor.setValueLineAlpha(0.5);
        chartCursor.setValueLineBalloonEnabled(true);
        chartCursor.setValueLineEnabled(true);
        chartCursor.setZoomable(false);
        chartCursor.setValueZoomable(true);
        chart.setChartCursor(chartCursor);

        setChart(chart);
    }
}
