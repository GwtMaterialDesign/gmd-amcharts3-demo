package gwt.material.amcharts.demo.shared.model.bar;

import gwt.material.amcharts.demo.shared.model.ChartData;
import gwt.material.design.amcharts.client.ui.chart.SerialChart;
import gwt.material.design.amcharts.client.ui.chart.base.constants.GraphBullet;
import gwt.material.design.amcharts.client.ui.chart.base.constants.GraphType;
import gwt.material.design.amcharts.client.ui.chart.base.constants.GridPosition;
import gwt.material.design.amcharts.client.ui.chart.base.constants.Position;
import gwt.material.design.amcharts.client.ui.chart.options.*;

public class BarAndLineMix extends ChartData {

    public BarAndLineMix() {
        super("#bar-and-line-mix", "Bar and Line Mix", "Minor grid lines can be displayed both on value and category axes of the chart.");

        SerialChart chart = new SerialChart();
        chart.setWidth("100%");
        chart.setHeight("400px");
        chart.setMarginRight(70);

        DataLoader loader = new DataLoader();
        loader.setUrl("/data/bar/bar-and-line-data.json");
        loader.setFormat("json");
        chart.setDataLoader(loader);

        chart.setHandDrawn(true);
        chart.setHandDrawScatter(3);

        Legend legend = new Legend();
        legend.setUseGraphSettings(true);
        legend.setMarkerSize(12);
        legend.setValueWidth(0);
        legend.setVerticalGap(0);
        chart.setLegend(legend);

        ValueAxis valueAxis = new ValueAxis();
        valueAxis.setMinorGridAlpha(0.08);
        valueAxis.setMinorGridEnabled(true);
        valueAxis.setPosition(Position.TOP);
        valueAxis.setAxisAlpha(0);
        chart.setValueAxes(valueAxis);

        chart.setStartDuration(1);

        Graph graph1 = new Graph();
        graph1.setBalloonText("<span style='font-size:13px;'>[[title]] in [[category]]:<b>[[value]]</b></span>");

        graph1.setBulletBorderAlpha(1);
        graph1.setTitle("Income");
        graph1.setValueField("income");
        graph1.setType(GraphType.COLUMN);
        graph1.setFillAlphas(0.8);

        Graph graph2 = new Graph();
        graph2.setBalloonText("<span style='font-size:13px;'>[[title]] in [[category]]:<b>[[value]]</b></span>");
        graph2.setBullet(GraphBullet.ROUND);
        graph2.setBulletBorderAlpha(1);
        graph2.setUseLineColorForBulletBorder(true);
        graph2.setFillAlphas(0);
        graph2.setLineThickness(2);
        graph2.setLineAlpha(1);
        graph2.setBulletSize(7);
        graph2.setTitle("Expenses");
        graph2.setValueField("expenses");


        chart.setGraphs(graph1, graph2);

        chart.setRotate(true);
        chart.setCategoryField("year");

        CategoryAxis categoryAxis = new CategoryAxis();
        categoryAxis.setGridPosition(GridPosition.START);
        chart.setCategoryAxis(categoryAxis);

        setChart(chart);
    }
}
