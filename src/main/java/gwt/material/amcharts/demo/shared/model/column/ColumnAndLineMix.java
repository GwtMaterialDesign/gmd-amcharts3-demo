package gwt.material.amcharts.demo.shared.model.column;

import gwt.material.amcharts.demo.shared.model.ChartData;
import gwt.material.design.amcharts.client.ui.chart.SerialChart;
import gwt.material.design.amcharts.client.ui.chart.base.constants.GraphBullet;
import gwt.material.design.amcharts.client.ui.chart.base.constants.GraphType;
import gwt.material.design.amcharts.client.ui.chart.base.constants.GridPosition;
import gwt.material.design.amcharts.client.ui.chart.options.Balloon;
import gwt.material.design.amcharts.client.ui.chart.options.CategoryAxis;
import gwt.material.design.amcharts.client.ui.chart.options.DataLoader;
import gwt.material.design.amcharts.client.ui.chart.options.Graph;

public class ColumnAndLineMix extends ChartData {

    public ColumnAndLineMix() {
        super("#column-and-line-mix", "Column and Line Mix", "Our serial chart supports a lot of graph types: line, area, smoothed line, step, column, candlestick, OHLC. All these types can go together in one chart – line with columns, columns with smoothed lines and similar. The real difference of these graphs is one property – type, except candlestick and OHLC graphs, of course, as they require 4 data fields instead of one.");

        SerialChart chart = new SerialChart();
        chart.setWidth("100%");
        chart.setHeight("400px");
        chart.setMarginRight(70);

        DataLoader loader = new DataLoader();
        loader.setUrl("/data/column/column-and-line-data.json");
        loader.setFormat("json");
        chart.setDataLoader(loader);

        chart.setAutoMargins(false);
        chart.setMarginLeft(30);
        chart.setMarginRight(8);
        chart.setMarginTop(10);
        chart.setMarginBottom(26);

        Balloon balloon = new Balloon();
        balloon.setAdjustBorderColor(false);
        balloon.setHorizontalPadding(10);
        balloon.setVerticalPadding(8);
        balloon.setColor("#ffffff");
        chart.setBalloon(balloon);

        chart.setStartDuration(1);

        Graph graph = new Graph();
        graph.setAlphaField("alpha");
        graph.setBalloonText("<span style='font-size:12px;'>[[title]] in [[category]]:<br><span style='font-size:20px;'>[[value]]</span> [[additional]]</span>");
        graph.setFillAlphas(1);
        graph.setTitle("Income");
        graph.setType(GraphType.COLUMN);
        graph.setValueField("income");
        graph.setDashLengthField("dashLengthField");

        Graph graph2 = new Graph();
        graph2.setId("graph2");
        graph2.setBalloonText("<span style='font-size:12px;'>[[title]] in [[category]]:<br><span style='font-size:20px;'>[[value]]</span> [[additional]]</span>");
        graph2.setBullet(GraphBullet.ROUND);
        graph2.setLineThickness(3);
        graph2.setBulletBorderAlpha(1);
        graph2.setBulletColor("#ffffff");
        graph2.setUseLineColorForBulletBorder(true);
        graph2.setBulletBorderThickness(3);
        graph2.setFillAlphas(0);
        graph2.setLineAlpha(1);
        graph2.setTitle("Expenses");
        graph2.setValueField("expenses");
        graph2.setDashLengthField("dashLengthLine");

        chart.setGraphs(graph, graph2);

        chart.setCategoryField("year");

        CategoryAxis categoryAxis = new CategoryAxis();
        categoryAxis.setGridPosition(GridPosition.START);
        categoryAxis.setAxisAlpha(0);
        categoryAxis.setTickLength(0);
        chart.setCategoryAxis(categoryAxis);

        setChart(chart);
    }
}
