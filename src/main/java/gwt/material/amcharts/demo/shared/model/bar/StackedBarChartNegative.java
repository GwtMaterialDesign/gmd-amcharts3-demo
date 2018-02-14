package gwt.material.amcharts.demo.shared.model.bar;

import gwt.material.amcharts.demo.shared.model.ChartData;
import gwt.material.design.amcharts.client.ui.chart.SerialChart;
import gwt.material.design.amcharts.client.ui.chart.base.constants.GraphType;
import gwt.material.design.amcharts.client.ui.chart.base.constants.GridPosition;
import gwt.material.design.amcharts.client.ui.chart.js.options.GraphDataItem;
import gwt.material.design.amcharts.client.ui.chart.js.options.Guide;
import gwt.material.design.amcharts.client.ui.chart.js.options.Label;
import gwt.material.design.amcharts.client.ui.chart.options.*;

public class StackedBarChartNegative extends ChartData {

    public StackedBarChartNegative() {
        super("#stacked-bar-chart-negative", "Stacked bar chart with negative values", "");

        SerialChart chart = new SerialChart();
        chart.setWidth("100%");
        chart.setHeight("400px");
        chart.setMarginRight(70);

        DataLoader loader = new DataLoader();
        loader.setUrl("/data/bar/stacked-bar-chart-negative.json");
        loader.setFormat("json");
        chart.setDataLoader(loader);

        chart.setRotate(true);
        chart.setMarginBottom(50);
        chart.setStartDuration(1);

        Graph graph1 = new Graph();
        graph1.setFillAlphas(0.8);
        graph1.setLineAlpha(0.2);
        graph1.setType(GraphType.COLUMN);
        graph1.setValueField("male");
        graph1.setTitle("Male");
        graph1.setLabelText("[[value]]");
        graph1.setClustered(false);
        graph1.setLabelFunction(item -> {
            GraphDataItem dataItem = (GraphDataItem) item;
            Object value = dataItem.getValues().getValue();
            if (value != null) {
                return Math.abs((double) value) + "";
            }

            return value;
        });

        graph1.setBalloonFunction(item -> {
            GraphDataItem dataItem = (GraphDataItem) item;
            Object value = dataItem.getValues().getValue();
            if (value != null) {
                return dataItem.getCategory() + ": " + Math.abs((double) value) + "%";
            }

            return value;
        });

        Graph graph2 = new Graph();
        graph2.setFillAlphas(0.8);
        graph2.setLineAlpha(0.2);
        graph2.setType(GraphType.COLUMN);
        graph2.setValueField("female");
        graph2.setTitle("Female");
        graph2.setLabelText("[[value]]");
        graph2.setClustered(false);
        graph2.setLabelFunction(item -> {
            GraphDataItem dataItem = (GraphDataItem) item;
            Object value = dataItem.getValues().getValue();
            if (value != null) {
                return Math.abs((double) value) + "";
            }

            return value;
        });

        graph2.setBalloonFunction(item -> {
            GraphDataItem dataItem = (GraphDataItem) item;
            Object value = dataItem.getValues().getValue();
            if (value != null) {
                return dataItem.getCategory() + ": " + Math.abs((double) value) + "%";
            }

            return value;
        });

        chart.setGraphs(graph1, graph2);
        chart.setCategoryField("age");

        CategoryAxis categoryAxis = new CategoryAxis();
        categoryAxis.setGridPosition(GridPosition.START);
        categoryAxis.setGridAlpha(0.2);
        categoryAxis.setAxisAlpha(0);
        chart.setCategoryAxis(categoryAxis);

        ValueAxis valueAxis1 = new ValueAxis();
        valueAxis1.setGridAlpha(0);
        valueAxis1.setIgnoreAxisWidth(true);
        valueAxis1.setLabelFunction(value -> value + "%");

        ValueAxis valueAxis2 = new ValueAxis();
        Guide guide = new Guide();
        guide.setValue(0);
        guide.setLineAlpha(0.2);
        valueAxis2.setGuides(guide);

        chart.setValueAxes(valueAxis1, valueAxis2);

        Balloon balloon = new Balloon();
        balloon.setFixedPosition(true);
        chart.setBalloon(balloon);

        ChartCursor chartCursor = new ChartCursor();
        chartCursor.setValueBalloonsEnabled(false);
        chartCursor.setCursorAlpha(0.05);
        chartCursor.setFullWidth(true);
        chart.setChartCursor(chartCursor);

        Label label = new Label();
        label.setText("Male");
        label.setX("28%");
        label.setY("97%");
        label.setBold(true);
        label.setAlign("middle");

        Label label2 = new Label();
        label2.setText("Female");
        label2.setX("75%");
        label2.setY("97%");
        label2.setBold(true);
        label2.setAlign("middle");
        chart.setAllLabels(label, label2);

        setChart(chart);
    }
}
