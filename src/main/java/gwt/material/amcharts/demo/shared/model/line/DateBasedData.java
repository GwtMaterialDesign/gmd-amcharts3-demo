package gwt.material.amcharts.demo.shared.model.line;

import gwt.material.amcharts.demo.shared.model.ChartData;
import gwt.material.design.amcharts.client.ui.chart.SerialChart;
import gwt.material.design.amcharts.client.ui.chart.base.constants.GraphBullet;
import gwt.material.design.amcharts.client.ui.chart.base.constants.Position;
import gwt.material.design.amcharts.client.ui.chart.options.*;
import gwt.material.design.client.constants.Color;

public class DateBasedData extends ChartData {

    public DateBasedData() {
        super("#date-based-data", "Date Based Data", "Our serial chart can accept data as date strings, date objects or time stamps. Dates on category axis are placed at logical intervals using any date format you want. Period beginning can be marked as bold and use a different date format. Your data can be yearly, monthly, daily, hourly, up to milliseconds – the chart will handle any time span. The chart can be zoomed-in or panned, Chart scrollbar can shows a rough graph of all your data and you can use it both to scroll or to zoom the chart to desired position.");

        SerialChart chart = new SerialChart();
        chart.setWidth("100%");
        chart.setHeight("400px");
        chart.setMarginRight(70);

        DataLoader loader = new DataLoader();
        loader.setUrl("/data/line/line-date-based-data.json");
        loader.setFormat("json");
        chart.setDataLoader(loader);

        chart.setMarginRight(40);
        chart.setMarginLeft(40);
        chart.setAutoMarginOffset(20);
        chart.setMouseWheelZoomEnabled(true);
        chart.setDataDateFormat("YYYY-MM-DD");

        ValueAxis valueAxis = new ValueAxis();
        valueAxis.setId("v1");
        valueAxis.setAxisAlpha(0);
        valueAxis.setPosition(Position.LEFT);
        valueAxis.setIgnoreAxisWidth(true);
        chart.setValueAxes(valueAxis);

        Balloon balloon = new Balloon();
        balloon.setBorderThickness(1);
        balloon.setShadowAlpha(0);
        chart.setBalloon(balloon);

        Graph graph1 = new Graph();
        graph1.setId("g1");

        Balloon balloon1 = new Balloon();
        balloon1.setDrop(true);
        balloon1.setAdjustBorderColor(false);
        balloon1.setColor(Color.WHITE);
        graph1.setBalloon(balloon1);

        Graph graph2 = new Graph();
        graph2.setBullet(GraphBullet.ROUND);
        graph2.setBulletBorderAlpha(1);
        graph2.setBulletColor(Color.WHITE);
        graph2.setBulletSize(5);
        graph2.setHideBulletsCount(50);
        graph2.setLineThickness(2);
        graph2.setTitle("red line");
        graph2.setUseLineColorForBulletBorder(true);
        graph2.setValueField("value");
        graph2.setBalloonText("<span style='font-size:18px;'>[[value]]</span>");

        chart.setGraphs(graph1, graph2);

        ChartScrollbar chartScrollbar = new ChartScrollbar();
        chartScrollbar.setGraph("g1");
        chartScrollbar.setOppositeAxis(false);
        chartScrollbar.setOffset(30);
        chartScrollbar.setScrollbarHeight(80);
        chartScrollbar.setBackgroundAlpha(0);
        chartScrollbar.setSelectedBackgroundAlpha(0.1);
        chartScrollbar.setSelectedBackgroundColor("#888888");
        chartScrollbar.setGraphFillAlpha(0);
        chartScrollbar.setGraphLineAlpha(0.5);
        chartScrollbar.setSelectedGraphFillAlpha(0);
        chartScrollbar.setSelectedGraphLineAlpha(1);
        chartScrollbar.setAutoGridCount(true);
        chartScrollbar.setColor("#AAAAAA");
        chart.setChartScrollbar(chartScrollbar);

        ChartCursor chartCursor = new ChartCursor();
        chartCursor.setPan(true);
        chartCursor.setValueLineEnabled(true);
        chartCursor.setValueLineBalloonEnabled(true);
        chartCursor.setCursorAlpha(1);
        chartCursor.setCursorColor("#258cbb");
        chartCursor.setLimitToGraph("g1");
        chartCursor.setValueLineAlpha(0.2);
        chartCursor.setValueZoomable(true);
        chart.setChartCursor(chartCursor);

        ChartScrollbar valueScrollbar = new ChartScrollbar();
        valueScrollbar.setOppositeAxis(false);
        valueScrollbar.setOffset(50);
        valueScrollbar.setScrollbarHeight(10);
        chart.setValueScrollbar(valueScrollbar);

        chart.setCategoryField("date");

        CategoryAxis categoryAxis = new CategoryAxis();
        categoryAxis.setParseDates(true);
        categoryAxis.setDashLength(1);
        categoryAxis.setMinorGridEnabled(true);
        chart.setCategoryAxis(categoryAxis);

        setChart(chart);
    }
}
