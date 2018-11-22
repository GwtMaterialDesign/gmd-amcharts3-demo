/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2017 GwtMaterialDesign
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package gwt.material.amcharts.demo.client.application.home;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import gwt.material.design.amcharts.client.ui.Am4Charts;
import gwt.material.design.amcharts.client.ui.Am4Core;
import gwt.material.design.amcharts.client.ui.chart.*;
import gwt.material.design.amcharts.client.ui.chart.axis.CategoryAxis;
import gwt.material.design.amcharts.client.ui.chart.axis.DateAxis;
import gwt.material.design.amcharts.client.ui.chart.axis.ValueAxis;
import gwt.material.design.amcharts.client.ui.chart.base.*;
import gwt.material.design.amcharts.client.ui.chart.constants.ContainerLayout;
import gwt.material.design.amcharts.client.ui.chart.cursor.XYCursor;
import gwt.material.design.amcharts.client.ui.chart.dataitem.AxisDataItem;
import gwt.material.design.amcharts.client.ui.chart.export.ExportMenu;
import gwt.material.design.amcharts.client.ui.chart.resources.ChartClientBundle;
import gwt.material.design.amcharts.client.ui.chart.scrollbar.XYChartScrollbar;
import gwt.material.design.amcharts.client.ui.chart.series.*;
import gwt.material.design.amcharts.client.ui.chart.state.SpriteState;
import gwt.material.design.amcharts.client.ui.chart.theme.AnimatedTheme;
import gwt.material.design.amcharts.client.ui.chart.theme.MaterialTheme;
import gwt.material.design.amcharts.client.ui.map.MapChart;
import gwt.material.design.amcharts.client.ui.map.base.MapPolygon;
import gwt.material.design.amcharts.client.ui.map.geodata.WorldLow;
import gwt.material.design.amcharts.client.ui.map.projections.Miller;
import gwt.material.design.amcharts.client.ui.map.series.MapPolygonSeries;
import gwt.material.design.client.MaterialDesignBase;
import gwt.material.design.client.ui.MaterialCard;
import gwt.material.design.client.ui.MaterialToast;

import javax.inject.Inject;
import java.util.Date;

public class HomeView extends ViewImpl implements HomePresenter.MyView {
    interface Binder extends UiBinder<Widget, HomeView> {
    }

    @UiField
    MaterialCard pieChartPanel, xyChartPanel, colorsPanel, colorSetPanel, radarChartPanel, columnChartPanel, mapChartPanel,
            treeMapPanel, sankeyDiagramPanel, gaugeChartPanel, chordDiagramPanel, slicedChartPanel, slicedPyramidChartPanel,
            slicedPictorialChartPanel;

    @Inject
    HomeView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    protected void onAttach() {
        super.onAttach();

        // Themes
        Am4Core.useTheme(new AnimatedTheme());
        Am4Core.useTheme(new MaterialTheme());

        createPieChart();
        createLineChart();
        createNewSVGRenderer();
        createColumnChart();
        createMapChart();
        createColorSetChart();
        createRadarChart();
        //createTreeMap();
        createSankeyDiagram();
        createGaugeChart();
        createChordDiagram();
        createSlicedChart();
    }

    protected void createMapChart() {
        //TODO: Move this internally
        MaterialDesignBase.injectDebugJs(ChartClientBundle.INSTANCE.mapJs());
        MaterialDesignBase.injectDebugJs(ChartClientBundle.INSTANCE.worldLowJs());

        // Create map instance
        MapChart mapChart = MapChart.create(mapChartPanel);

        // Set map definition
        mapChart.geodata = WorldLow.get();

        // Set projection
        mapChart.projection = new Miller();

        MapPolygonSeries polygonSeries = new MapPolygonSeries();
        polygonSeries.useGeodata = true;

        // Configure Series
        MapPolygon polygonTemplate = polygonSeries.mapPolygons.template;
        polygonTemplate.tooltipText = "{name}";
        polygonTemplate.fill = new Color("#74B266");
        mapChart.series.push(polygonSeries);

        SpriteState state = polygonTemplate.states.create("hover");
        state.properties.fill = new Color("red");
    }

    protected void createColumnChart() {
        // XYChart Demo
        XYChart xyChart = XYChart.create(columnChartPanel);
        // DateAxis
        DateAxis dateAxis = (DateAxis) xyChart.xAxes.push(new DateAxis());
        // ValueAxis
        ValueAxis valueAxis = (ValueAxis) xyChart.yAxes.push(new ValueAxis());
        // Number Formatter
        valueAxis.numberFormatter.numberFormat = "'$' #.a";
        // Series
        ColumnSeries columnSeries = (ColumnSeries) xyChart.series.push(new ColumnSeries());
        columnSeries.dataFields.valueY = "value";
        columnSeries.dataFields.dateX = "day";
        columnSeries.strokeWidth = 1.8;
        xyChart.dataSource.url = "data/large-data.json";
        // Scrollbar
        XYChartScrollbar scrollbarX = new XYChartScrollbar();
        scrollbarX.series.push(columnSeries);
        xyChart.scrollbarX = scrollbarX;
        xyChart.scrollbarX.updateWhileMoving = false;
        // TODO : Just a workaround to hide the chart
        //xyChart.height = 60;
        // Cursor
        xyChart.cursor = new XYCursor();
        // Events implementation
        xyChart.events.on("hit", param1 -> {
            MaterialToast.fireToast("Event Fired: click");
        }, this);
        // Zooming
        xyChart.zoomToIndexes(0, 23);
        xyChart.events.on("datavalidated", param1 -> {
            Date firstDecember = new Date(117, 11, 1);
            Date lastDecember = new Date(117, 11, 31);
            dateAxis.zoomToDates(firstDecember, lastDecember);
            MaterialToast.fireToast("Event Fired: datavalidated");
        }, this);
    }

    protected void createNewSVGRenderer() {
        // Colors & SVG Renderer
        Container container = (Container) Am4Core.create(colorsPanel, Am4Core.Container);
        container.width = new Percent(100);
        container.height = new Percent(100);

        Circle circle = (Circle) container.createChild(Am4Core.Circle);
        circle.fill = new Color("#A1C084");
        circle.height = 200;
        circle.width = new Percent(100);
        circle.align = "center";

    }

    protected void createColorSetChart() {
        ColorSet colorSet = new ColorSet();
        Container container = (Container) Am4Core.create(colorSetPanel, Am4Core.Container);
        container.width = new Percent(100);
        container.height = new Percent(100);
        container.layout = ContainerLayout.GRID;
        for (int i = 1; i <= 8; i++) {
            Rectangle rectangle = (Rectangle) container.createChild(Am4Core.Rectangle);
            rectangle.width = 100;
            rectangle.height = 100;
            rectangle.fill = colorSet.next();
        }

    }

    private void createRadarChart() {
        RadarChart chart = (RadarChart) Am4Core.create(radarChartPanel, Am4Charts.RadarChart);
        CategoryAxis categoryAxis = (CategoryAxis) chart.xAxes.push(new CategoryAxis());
        categoryAxis.dataFields.category = "country";
        ValueAxis valueAxis = (ValueAxis) chart.yAxes.push(new ValueAxis());
        RadarSeries series = (RadarSeries) chart.series.push(new RadarSeries());
        series.dataFields.valueY = "litres";
        series.dataFields.categoryX = "country";


        chart.dataSource.url = "data/basic.json";
    }

    protected void createLineChart() {
        // XYChart Demo
        XYChart xyChart = (XYChart) Am4Core.create(xyChartPanel, Am4Charts.XYChart);
        // DateAxis
        DateAxis dateAxis = (DateAxis) xyChart.xAxes.push(new DateAxis());
        // ValueAxis
        ValueAxis valueAxis = (ValueAxis) xyChart.yAxes.push(new ValueAxis());
        // Number Formatter
        valueAxis.numberFormatter.numberFormat = "'$' #.a";
        // Series
        LineSeries lineSeries = (LineSeries) xyChart.series.push(new LineSeries());
        lineSeries.dataFields.valueY = "value";
        lineSeries.dataFields.dateX = "day";
        lineSeries.strokeWidth = 1.8;
        xyChart.dataSource.url = "data/large-data.json";
        // Scrollbar
        XYChartScrollbar scrollbarX = new XYChartScrollbar();
        scrollbarX.series.push(lineSeries);
        xyChart.scrollbarX = scrollbarX;
        xyChart.scrollbarX.updateWhileMoving = false;
        // Cursor
        xyChart.cursor = new XYCursor();
        // Events implementation
        xyChart.events.on("hit", param1 -> {
            MaterialToast.fireToast("Event Fired: click");
        }, this);
        // Zooming
        xyChart.zoomToIndexes(0, 23);
        xyChart.events.on("datavalidated", param1 -> {
            Date firstDecember = new Date(117, 11, 1);
            Date lastDecember = new Date(117, 11, 31);
            dateAxis.zoomToDates(firstDecember, lastDecember);
            MaterialToast.fireToast("Event Fired: datavalidated");
        }, this);
    }

    protected void createPieChart() {
        // Pie Chart Demo
        PieChart pieChart = (PieChart) Am4Core.create(pieChartPanel, Am4Charts.PieChart);
        PieSeries series = pieChart.series.push(new PieSeries());
        series.dataFields.value = "litres";
        series.dataFields.category = "country";
        pieChart.dataSource.url = "data/basic.json";
        // Export feature
        pieChart.exporting.menu = new ExportMenu();
    }

    private void createTreeMap() {
        TreeMap treeMap = (TreeMap) Am4Core.create(treeMapPanel, Am4Charts.TreeMap);

        treeMap.dataFields.value = "litres";
        treeMap.dataFields.name = "country";
        treeMap.dataSource.url = "data/basic.json";
    }

    private void createSankeyDiagram() {
        SankeyDiagram sankeyDiagram = (SankeyDiagram) Am4Core.create(sankeyDiagramPanel, Am4Charts.SankeyDiagram);
        sankeyDiagram.dataFields.fromName = "from";
        sankeyDiagram.dataFields.toName = "to";
        sankeyDiagram.dataFields.value  = "value";
        sankeyDiagram.dataSource.url = "data/sankey-diagram.json";
    }

    private void createGaugeChart() {
        GaugeChart gaugeChart = (GaugeChart) Am4Core.create(gaugeChartPanel, Am4Charts.GaugeChart);
        gaugeChart.innerRadius = -15;

        ValueAxis valueAxis = (ValueAxis) gaugeChart.xAxes.push(new ValueAxis());
        valueAxis.min = 0;
        valueAxis.max = 100;
        valueAxis.strictMinMax = true;

        ColorSet colorSet = new ColorSet();

        AxisDataItem range = valueAxis.axisRanges.create();
        range.value = 0;
        range.endValue = 50;
        range.axisFill.fillOpacity = 1;
        range.axisFill.fill = colorSet.getIndex(0);

        AxisDataItem range2 = valueAxis.axisRanges.create();
        range2.value = 50;
        range2.endValue = 80;
        range2.axisFill.fillOpacity = 1;
        range2.axisFill.fill = colorSet.getIndex(2);

        AxisDataItem range3 = valueAxis.axisRanges.create();
        range3.value = 80;
        range3.endValue = 100;
        range3.axisFill.fillOpacity = 1;
        range3.axisFill.fill = colorSet.getIndex(4);

        ClockHand hand = gaugeChart.hands.push(new ClockHand());
        hand.showValue(20);
    }

    private void createChordDiagram() {
        ChordDiagram chart = (ChordDiagram) Am4Core.create(chordDiagramPanel, Am4Charts.ChordDiagram);
        chart.dataFields.fromName = "from";
        chart.dataFields.toName = "to";
        chart.dataFields.value  = "value";
        chart.dataSource.url = "data/sankey-diagram.json";
    }

    private void createSlicedChart() {
        // Funnel
        SlicedChart funnel = (SlicedChart) Am4Core.create(slicedChartPanel, Am4Charts.SlicedChart);
        FunnelSeries series = (FunnelSeries) funnel.series.push(new FunnelSeries());
        series.dataFields.value = "litres";
        series.dataFields.category = "country";
        series.alignLabels = true;
        funnel.dataSource.url = "data/basic.json";

        // Pyramid
        SlicedChart pyramid = (SlicedChart) Am4Core.create(slicedPyramidChartPanel, Am4Charts.SlicedChart);
        PyramidSeries pySeries = (PyramidSeries) pyramid.series.push(new PyramidSeries());
        pySeries.dataFields.value = "litres";
        pySeries.dataFields.category = "country";
        pySeries.alignLabels = true;
        pyramid.dataSource.url = "data/basic.json";

        // Pictorial
        String test = "M53.5,476c0,14,6.833,21,20.5,21s20.5-7,20.5-21V287h21v189c0,14,6.834,21,20.5,21 c13.667,0,20.5-7,20.5-21V154h10v116c0,7.334,2.5,12.667,7.5,16s10.167,3.333,15.5,0s8-8.667,8-16V145c0-13.334-4.5-23.667-13.5-31 s-21.5-11-37.5-11h-82c-15.333,0-27.833,3.333-37.5,10s-14.5,17-14.5,31v133c0,6,2.667,10.333,8,13s10.5,2.667,15.5,0s7.5-7,7.5-13 V154h10V476 M61.5,42.5c0,11.667,4.167,21.667,12.5,30S92.333,85,104,85s21.667-4.167,30-12.5S146.5,54,146.5,42 c0-11.335-4.167-21.168-12.5-29.5C125.667,4.167,115.667,0,104,0S82.333,4.167,74,12.5S61.5,30.833,61.5,42.5z";

        SlicedChart chart = (SlicedChart) Am4Core.create(slicedPictorialChartPanel, Am4Charts.SlicedChart);
        PictorialStackedSeries picSeries = (PictorialStackedSeries) chart.series.push(new PictorialStackedSeries());
        picSeries.maskSprite.path = test;
        picSeries.dataFields.value = "litres";
        picSeries.dataFields.category = "country";
        picSeries.alignLabels = true;
        chart.dataSource.url = "data/basic.json";
    }
}
