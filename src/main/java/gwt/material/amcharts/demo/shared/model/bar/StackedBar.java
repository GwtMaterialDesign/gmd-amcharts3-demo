package gwt.material.amcharts.demo.shared.model.bar;

import gwt.material.amcharts.demo.shared.model.ChartData;
import gwt.material.design.amcharts.client.ui.chart.SerialChart;
import gwt.material.design.amcharts.client.ui.chart.base.constants.*;
import gwt.material.design.amcharts.client.ui.chart.options.*;

public class StackedBar extends ChartData {

    public StackedBar() {
        super("#stacked-bar-chart", "Stacked Bar Chart", "Technically, this chart is exactly the same as Stacked Column Chart with a single property, rotate set to true. Our charts do rotate that easily!");

        SerialChart chart = new SerialChart();
        chart.setWidth("100%");
        chart.setHeight("400px");
        chart.setMarginRight(70);

        DataLoader loader = new DataLoader();
        loader.setUrl("/data/column/stacked-data.json");
        loader.setFormat("json");
        chart.setDataLoader(loader);

        chart.setRotate(true);

        Legend legend = new Legend();
        legend.setHorizontalGap(10);
        legend.setMaxColumns(1);
        legend.setPosition(LegendPosition.RIGHT);
        legend.setUseGraphSettings(true);
        legend.setMarkerSize(10);

        ValueAxis valueAxis = new ValueAxis();
        valueAxis.setStackType(StackType.REGULAR);
        valueAxis.setAxisAlpha(0.3);
        valueAxis.setGridAlpha(0);
        chart.setValueAxes(valueAxis);

        Graph graph1 = new Graph();
        graph1.setBalloonText("<b>[[title]]</b><br><span style='font-size:14px'>[[category]]: <b>[[value]]</b></span>");
        graph1.setFillAlphas(0.8);
        graph1.setLabelText("[[value]]");
        graph1.setLineAlpha(0.3);
        graph1.setTitle("Europe");
        graph1.setType(GraphType.COLUMN);
        graph1.setColor("#000000");
        graph1.setValueField("europe");

        Graph graph2 = new Graph();
        graph2.setBalloonText("<b>[[title]]</b><br><span style='font-size:14px'>[[category]]: <b>[[value]]</b></span>");
        graph2.setFillAlphas(0.8);
        graph2.setLabelText("[[value]]");
        graph2.setLineAlpha(0.3);
        graph2.setTitle("North America");
        graph2.setType(GraphType.COLUMN);
        graph2.setColor("#000000");
        graph2.setValueField("namerica");

        Graph graph3 = new Graph();
        graph3.setBalloonText("<b>[[title]]</b><br><span style='font-size:14px'>[[category]]: <b>[[value]]</b></span>");
        graph3.setFillAlphas(0.8);
        graph3.setLabelText("[[value]]");
        graph3.setLineAlpha(0.3);
        graph3.setTitle("Asia Pacific");
        graph3.setType(GraphType.COLUMN);
        graph3.setColor("#000000");
        graph3.setValueField("asia");

        Graph graph4 = new Graph();
        graph4.setBalloonText("<b>[[title]]</b><br><span style='font-size:14px'>[[category]]: <b>[[value]]</b></span>");
        graph4.setFillAlphas(0.8);
        graph4.setLabelText("[[value]]");
        graph4.setLineAlpha(0.3);
        graph4.setTitle("Latin America");
        graph4.setType(GraphType.COLUMN);
        graph4.setColor("#000000");
        graph4.setValueField("lamerica");

        Graph graph5 = new Graph();
        graph5.setBalloonText("<b>[[title]]</b><br><span style='font-size:14px'>[[category]]: <b>[[value]]</b></span>");
        graph5.setFillAlphas(0.8);
        graph5.setLabelText("[[value]]");
        graph5.setLineAlpha(0.3);
        graph5.setTitle("Middle-East");
        graph5.setType(GraphType.COLUMN);
        graph5.setColor("#000000");
        graph5.setValueField("meast");

        Graph graph6 = new Graph();
        graph6.setBalloonText("<b>[[title]]</b><br><span style='font-size:14px'>[[category]]: <b>[[value]]</b></span>");
        graph6.setFillAlphas(0.8);
        graph6.setLabelText("[[value]]");
        graph6.setLineAlpha(0.3);
        graph6.setTitle("Africa");
        graph6.setType(GraphType.COLUMN);
        graph6.setColor("#000000");
        graph6.setValueField("africa");

        Graph graph7 = new Graph();
        graph7.setBalloonText("<b>[[title]]</b><br><span style='font-size:14px'>[[category]]: <b>[[value]]</b></span>");
        graph7.setFillAlphas(0.8);
        graph7.setLabelText("[[value]]");
        graph7.setLineAlpha(0.3);
        graph7.setTitle("Africa");
        graph7.setType(GraphType.COLUMN);
        graph7.setColor("#000000");//
        graph7.setValueField("africa");


        chart.setGraphs(graph1, graph2, graph3, graph4, graph5, graph6, graph7);

        chart.setCategoryField("year");

        CategoryAxis categoryAxis = new CategoryAxis();
        categoryAxis.setGridPosition(GridPosition.START);
        categoryAxis.setAxisAlpha(0);
        categoryAxis.setGridAlpha(0);
        categoryAxis.setPosition(Position.LEFT);
        chart.setCategoryAxis(categoryAxis);
        setChart(chart);
    }
}
