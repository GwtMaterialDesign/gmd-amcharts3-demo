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
import gwt.material.design.amcharts.client.*;
import gwt.material.design.amcharts.client.axis.*;
import gwt.material.design.amcharts.client.bullet.CircleBullet;
import gwt.material.design.amcharts.client.column.Column;
import gwt.material.design.amcharts.client.cursor.XYCursor;
import gwt.material.design.amcharts.client.dataitem.AxisDataItem;
import gwt.material.design.amcharts.client.legend.HeatLegend;
import gwt.material.design.amcharts.client.legend.Legend;
import gwt.material.design.amcharts.client.scrollbar.XYChartScrollbar;
import gwt.material.design.amcharts.client.series.*;
import gwt.material.design.amcharts.client.states.CircleBulletProperties;
import gwt.material.design.amcharts.client.tick.ClockHand;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.base.Container;
import gwt.material.design.amcore.client.base.Percent;
import gwt.material.design.amcore.client.base.Sprite;
import gwt.material.design.amcore.client.color.Color;
import gwt.material.design.amcore.client.color.ColorSet;
import gwt.material.design.amcore.client.constants.Align;
import gwt.material.design.amcore.client.constants.ContainerLayout;
import gwt.material.design.amcore.client.constants.VerticalAlign;
import gwt.material.design.amcore.client.export.ExportMenu;
import gwt.material.design.amcore.client.language.Locale;
import gwt.material.design.amcore.client.properties.HeatRule;
import gwt.material.design.amcore.client.properties.Range;
import gwt.material.design.amcore.client.state.SpriteState;
import gwt.material.design.amcore.client.theme.AnimatedTheme;
import gwt.material.design.amcore.client.theme.MaterialTheme;
import gwt.material.design.amcore.client.ui.Circle;
import gwt.material.design.amcore.client.ui.Rectangle;
import gwt.material.design.amcore.client.ui.RoundedRectangle;
import gwt.material.design.ammaps.client.MapChart;
import gwt.material.design.ammaps.client.base.MapPolygon;
import gwt.material.design.ammaps.client.dataitem.MapDataContext;
import gwt.material.design.ammaps.client.geodata.WorldLow;
import gwt.material.design.ammaps.client.projections.Miller;
import gwt.material.design.ammaps.client.properties.MapPolygonProperties;
import gwt.material.design.ammaps.client.series.MapPolygonSeries;
import gwt.material.design.client.ui.MaterialCard;
import gwt.material.design.client.ui.MaterialToast;

import javax.inject.Inject;
import java.util.Date;

public class HomeView extends ViewImpl implements HomePresenter.MyView {
    interface Binder extends UiBinder<Widget, HomeView> {
    }

    @UiField
    MaterialCard customPanel, pieChartPanel, xyChartPanel, colorsPanel, colorSetPanel, radarChartPanel, columnChartPanel, mapChartPanel,
            treeMapPanel, sankeyDiagramPanel, gaugeChartPanel, chordDiagramPanel, slicedChartPanel, slicedPyramidChartPanel,
            slicedPictorialChartPanel, dataPanel, heatLegendPanel, heatDaysPanel, cursorPanel, dataSourcePanel, configFieldPanel;

    @Inject
    HomeView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    protected void onAttach() {
        super.onAttach();

        // Add Themes
        Am4Core.addTheme(new AnimatedTheme());
        Am4Core.addTheme(new MaterialTheme());

        // Add Language
        Am4Core.addLanguage(Locale.ru_RU);

        // Global Language Definition
        Am4Core.options.defaultLocale = Locale.ru_RU.getValue();

        createCustom();
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
        createDataPanel();
        createHeatLegend();
        createHeatDays();
        createCursor();
        createDataSourcePanel();
        createConfigFieldPanel();
    }

    protected void createConfigFieldPanel() {
        PieChart chart = (PieChart) Am4Core.create(configFieldPanel, Am4Charts.PieChart);

        // Let's cut a hole in our Pie chart the size of 40% the radius
        chart.innerRadius = new Percent(40);
        // Add and configure Series
        PieSeries pieSeries = chart.series.push(new PieSeries());
        pieSeries.dataFields.value = "litres";
        pieSeries.dataFields.category = "country";
        pieSeries.slices.template.stroke = new Color("#fff");
        pieSeries.slices.template.strokeWidth = 2;
        pieSeries.slices.template.strokeOpacity = 1;
        pieSeries.slices.template.fillOpacity = 1;

        // Disabling labels and ticks on inner circle
        pieSeries.labels.template.disabled = true;
        pieSeries.ticks.template.disabled = true;

        pieSeries.slices.template.configField = "config";

        pieSeries.dataSource.url = "data/config-field.json";
    }

    protected void createDataSourcePanel() {
        // Create chart instance
        XYChart chart = XYChart.create(dataSourcePanel);

        // Create axes
        DateAxis categoryAxis = (DateAxis) chart.xAxes.push(new DateAxis());
        categoryAxis.renderer.grid.template.location = 0;
        categoryAxis.renderer.minGridDistance = 30;

        ValueAxis valueAxis = (ValueAxis) chart.yAxes.push(new ValueAxis());

        // Create series
        LineSeries series = (LineSeries) chart.series.push(new LineSeries());
        series.dataFields.valueY = "value";
        series.dataFields.dateX = "date";
        series.stroke = new Color("#96BBBB");
        series.strokeWidth = 3;
        series.fill = new Color("#96BBBB");
        series.fillOpacity = 0.7;
        series.tensionX = 0.8;

        series.propertyFields.fill = "color";
        series.propertyFields.stroke = "color";
        series.propertyFields.strokeDasharray = "dash";

        chart.cursor = new XYCursor();

        //TODO:
        chart.adapter.add("data", param1 -> {
            Object data = param1;
            return data;
        });

        // Creating Popup
        chart.openPopup("Can be opened multiple, You can still interact into the chart", "Popup");

        chart.openModal("Can be opened once, You cant interact into the chart", "Modal");

        chart.dataSource.url = "data/unordered.json";
    }

    protected void createCursor() {
        XYChart chart = XYChart.create(cursorPanel);
        CategoryAxis categoryAxis = (CategoryAxis) chart.xAxes.push(new CategoryAxis());
        categoryAxis.dataFields.category = "year";

        /* Create value axis */
        ValueAxis valueAxis = (ValueAxis) chart.yAxes.push(new ValueAxis());

        /* Create series */
        LineSeries series1 = (LineSeries) chart.series.push(new LineSeries());
        series1.dataFields.valueY = "cars";
        series1.dataFields.categoryX = "year";
        series1.name = "Cars";
        series1.strokeWidth = 3;
        series1.tensionX = 0.7;
        CircleBullet bullet = (CircleBullet) series1.bullets.push(new CircleBullet());
        SpriteState<CircleBulletProperties> bullet1hover = bullet.states.create("hover");
        bullet1hover.properties.scale = 2;
        series1.tooltipText = "{categoryX}: [bold]{valueY}[/]";

        LineSeries series2 = (LineSeries) chart.series.push(new LineSeries());
        series2.dataFields.valueY = "motorcycles";
        series2.dataFields.categoryX = "year";
        series2.name = "Motorcycles";
        series2.strokeWidth = 3;
        series2.tensionX = 0.7;
        CircleBullet bullet2 = (CircleBullet) series2.bullets.push(new CircleBullet());
        SpriteState<CircleBulletProperties> bullet2hover = bullet2.states.create("hover");
        bullet2hover.properties.scale = 2;
        series2.tooltipText = "{categoryX}: [bold]{valueY}[/]";

        LineSeries series3 = (LineSeries) chart.series.push(new LineSeries());
        series3.dataFields.valueY = "bicycles";
        series3.dataFields.categoryX = "year";
        series3.name = "Bicycles";
        series3.strokeWidth = 3;
        series3.tensionX = 0.7;
        CircleBullet bullet3 = (CircleBullet) series3.bullets.push(new CircleBullet());
        SpriteState<CircleBulletProperties> bullet3hover = bullet3.states.create("hover");
        bullet3hover.properties.scale = 2;
        series3.tooltipText = "{categoryX}: [bold]{valueY}[/]";

        /* Add legend */
        chart.legend = new Legend();

        /* Create a cursor */
        chart.cursor = new XYCursor();

        // Changing cursor appearance
        chart.cursor.lineX.stroke = new Color("#8F3985");
        chart.cursor.lineX.strokeWidth = 4;
        chart.cursor.lineX.strokeOpacity = 0.2;
        chart.cursor.lineX.strokeDasharray = "";

        chart.cursor.lineY.stroke = new Color("#8F3985");
        chart.cursor.lineY.strokeWidth = 4;
        chart.cursor.lineY.strokeOpacity = 0.2;
        chart.cursor.lineY.strokeDasharray = "";

        // Disabling Cursor Line
        chart.cursor.lineY.disabled = true;

        // Enabling full-width Cursor Lines
        chart.cursor.xAxis = categoryAxis;
        chart.cursor.fullWidthLineX = true;
        chart.cursor.lineX.strokeWidth = 0;
        chart.cursor.lineX.fill = new Color("#8F3985");
        chart.cursor.lineX.fillOpacity = 0.1;

        chart.cursor.behavior = "selectX";
        chart.cursor.events.on("selectended", param1 -> {
            XYCursor cursor = param1.target;
            Range range = cursor.xRange;
            Axis axis = ((XYChart) cursor.chart).xAxes.getIndex(0);
            String from = axis.getPositionLabel(range.start);
            String to = axis.getPositionLabel(range.end);
            MaterialToast.fireToast("Selected from " + from + " to " + to);
        });

        chart.dataSource.url = "data/cursor.json";
    }

    protected void createHeatDays() {
        XYChart chart = XYChart.create(heatDaysPanel);
        chart.maskBullets = false;

        CategoryAxis xAxis = (CategoryAxis) chart.xAxes.push(new CategoryAxis());
        CategoryAxis yAxis = (CategoryAxis) chart.yAxes.push(new CategoryAxis());

        xAxis.dataFields.category = "weekday";
        yAxis.dataFields.category = "hour";

        xAxis.renderer.grid.template.disabled = true;
        xAxis.renderer.minGridDistance = 40;

        yAxis.renderer.grid.template.disabled = true;
        yAxis.renderer.inversed = true;
        yAxis.renderer.minGridDistance = 30;

        ColumnSeries series = (ColumnSeries) chart.series.push(new ColumnSeries());
        series.dataFields.categoryX = "weekday";
        series.dataFields.categoryY = "hour";
        series.dataFields.value = "value";
        series.sequencedInterpolation = true;
        //series.defaultState.transitionDuration = 3000;

        Column columnTemplate = series.columns.template;
        columnTemplate.strokeWidth = 2;
        columnTemplate.strokeOpacity = 1;
        columnTemplate.stroke = new Color("#ffffff");
        columnTemplate.tooltipText = "{weekday}, {hour}: {value.workingValue.formatNumber('#.')}";
        columnTemplate.width = new Percent(100);
        columnTemplate.height = new Percent(100);

        HeatRule heatRule = new HeatRule();
        heatRule.target = columnTemplate;
        heatRule.property = "fill";
        heatRule.min = new Color("#ffffff");
        heatRule.max = new Color("#692155");
        series.heatRules.push(heatRule);

        // heat legend
        HeatLegend heatLegend = (HeatLegend) chart.createChild(Am4Charts.HeatLegend);
        heatLegend.width = new Percent(100);
        heatLegend.series = series;
        heatLegend.valueAxis.renderer.labels.template.fontSize = 9;
        heatLegend.valueAxis.renderer.minGridDistance = 30;

        chart.dataSource.url = "data/heat-days.json";
    }

    protected void createHeatLegend() {
        XYChart chart = XYChart.create(heatLegendPanel);
        /* Create axes */
        CategoryAxis categoryAxis = (CategoryAxis) chart.xAxes.push(new CategoryAxis());
        categoryAxis.dataFields.category = "category";
        categoryAxis.renderer.grid.template.location = 0;
        categoryAxis.renderer.minGridDistance = 30;

        ValueAxis valueAxis = (ValueAxis) chart.yAxes.push(new ValueAxis());

        /* Create series */
        ColumnSeries series = (ColumnSeries) chart.series.push(new ColumnSeries());
        series.dataFields.value = "value";
        series.dataFields.valueY = "value";
        series.dataFields.categoryX = "category";
        series.columns.template.strokeWidth = 0;


        HeatRule heatRule = new HeatRule();
        heatRule.target = series.columns.template;
        heatRule.property = "fill";
        heatRule.min = new Color("#F5DBCB");
        heatRule.max = new Color("#ED7B84");
        series.heatRules.push(heatRule);

        /* Create a heat legend */
        HeatLegend heatLegend = (HeatLegend) chart.createChild(Am4Charts.HeatLegend);
        heatLegend.series = series;
        heatLegend.width = new Percent(100);
        heatLegend.valueAxis.renderer.labels.template.fontSize = 9;
        heatLegend.valueAxis.renderer.minGridDistance = 30;
        // Change orientation
        //heatLegend.orientation = Orientation.VERTICAL;

        // Color steps
        heatLegend.markerCount = 10;

        //Adding interactivity
        series.columns.template.events.on("over", param1 -> {
            Column column = param1.target;
            if (column.dataItem.value != null) {
                heatLegend.valueAxis.showTooltipAt(column.dataItem.value);
            } else {
                heatLegend.valueAxis.hideTooltip();
            }
        });

        series.columns.template.events.on("out", param1 -> {
            heatLegend.valueAxis.hideTooltip();
        });

        chart.dataSource.url = "data/heat.json";
    }

    protected void createDataPanel() {
        XYChart chart = XYChart.create(dataPanel);

        chart.dataSource.url = "data/stacked.json";

        chart.cursor = new XYCursor();
        chart.legend = new Legend();
        chart.legend.useDefaultMarker = true;

        RoundedRectangle marker = (RoundedRectangle) chart.legend.markers.template.children.getIndex(0);
        marker.cornerRadius(12, 12, 12, 12);
        marker.strokeWidth = 2;
        marker.strokeOpacity = 1;
        //marker.width = 80;
        //marker.height = 80;
        marker.stroke = new Color("#ccc");

        chart.legend.itemContainers.template.events.on("hit", param1 -> {
            MaterialToast.fireToast("Clicked on" + param1);
        });

        //Custom Marker (SVG)
        /* Remove square from marker template */
        /*Container marker = chart.legend.markers.template;
        marker.disposeChildren();
        marker.width = 40;
        marker.height = 40;*/

        /* Add custom image instead */
        /*Image dollar = (Image) marker.createChild(Am4Core.Image);
        dollar.width = 40;
        dollar.height = 40;
        dollar.verticalCenter = VerticalCenter.TOP;
        dollar.horizontalCenter = HorizontalCenter.LEFT;
        dollar.href = "data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiA/PjxzdmcgaGVpZ2h0PSIyMXB4IiB2ZXJzaW9uPSIxLjEiIHZpZXdCb3g9IjAgMCAyMSAyMSIgd2lkdGg9IjIxcHgiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgeG1sbnM6eGxpbms9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkveGxpbmsiPjx0aXRsZS8+PGRlc2MvPjxkZWZzLz48ZyBmaWxsPSJub25lIiBmaWxsLXJ1bGU9ImV2ZW5vZGQiIGlkPSJCdXNpbmVzcy1JY29uIiBzdHJva2U9Im5vbmUiIHN0cm9rZS13aWR0aD0iMSIgdHJhbnNmb3JtPSJ0cmFuc2xhdGUoLTI3Ny4wMDAwMDAsIC0yMjcuMDAwMDAwKSI+PGcgaWQ9IkNvaW4iIHRyYW5zZm9ybT0idHJhbnNsYXRlKDI3NS4wMDAwMDAsIDIyNS4wMDAwMDApIj48cmVjdCBoZWlnaHQ9IjI1IiBpZD0iUmVjdGFuZ2xlLTQ5IiB3aWR0aD0iMjUiIHg9IjAiIHk9IjAiLz48ZyBpZD0iR3JvdXAtMzQiIHRyYW5zZm9ybT0idHJhbnNsYXRlKDIuMDAwMDAwLCAyLjAwMDAwMCkiPjxwYXRoIGQ9Ik0xMC41LDIxIEM0LjcwMTAxMDEzLDIxIDAsMTYuMjk4OTg5OSAwLDEwLjUgQzAsNC43MDEwMTAxMyA0LjcwMTAxMDEzLDAgMTAuNSwwIEMxNi4yOTg5ODk5LDAgMjEsNC43MDEwMTAxMyAyMSwxMC41IEMyMSwxNi4yOTg5ODk5IDE2LjI5ODk4OTksMjEgMTAuNSwyMSBaIE0xMC41LDE4IEMxNC42NDIxMzU2LDE4IDE4LDE0LjY0MjEzNTYgMTgsMTAuNSBDMTgsNi4zNTc4NjQzOCAxNC42NDIxMzU2LDMgMTAuNSwzIEM2LjM1Nzg2NDM4LDMgMyw2LjM1Nzg2NDM4IDMsMTAuNSBDMywxNC42NDIxMzU2IDYuMzU3ODY0MzgsMTggMTAuNSwxOCBaIiBmaWxsPSIjM0I1QUZCIiBmaWxsLXJ1bGU9Im5vbnplcm8iIGlkPSJDb21iaW5lZC1TaGFwZSIvPjxwYXRoIGQ9Ik0xMC41LDE3IEM2LjkxMDE0OTEzLDE3IDQsMTQuMDg5ODUwOSA0LDEwLjUgQzQsNi45MTAxNDkxMyA2LjkxMDE0OTEzLDQgMTAuNSw0IEMxNC4wODk4NTA5LDQgMTcsNi45MTAxNDkxMyAxNywxMC41IEMxNywxNC4wODk4NTA5IDE0LjA4OTg1MDksMTcgMTAuNSwxNyBaIE0xMSw4IEwxMSw3LjUgQzExLDcuMjIzODU3NjMgMTAuNzc2MTQyNCw3IDEwLjUsNyBDMTAuMjIzODU3Niw3IDEwLDcuMjIzODU3NjMgMTAsNy41IEwxMCw4IEw5LjUxMzk0NTIsOCBDOC42ODU1MTgwNyw4IDguMDEzOTQ1Miw4LjY3MTU3Mjg4IDguMDEzOTQ1Miw5LjUgTDguMDEzOTQ1MiwxMC4wMDA3NTY3IEM4LjAxMzk0NTIsMTAuODI5MTgzOCA4LjY4NTUxODA3LDExLjUwMDc1NjcgOS41MTM5NDUyLDExLjUwMDc1NjcgTDExLjUwMDAwNDksMTEuNTAwNzU2NyBDMTEuNzc2MTQ3MywxMS41MDA3NTY3IDEyLjAwMDAwNDksMTEuNzI0NjE0MyAxMi4wMDAwMDQ5LDEyLjAwMDc1NjcgTDEyLjAwMDAwNDksMTIuNSBDMTIuMDAwMDA0OSwxMi43NzYxNDI0IDExLjc3NjE0NzMsMTMgMTEuNTAwMDA0OSwxMyBMOS4wMTIyNTUyNSwxMyBDOC45OTE2NzA0LDEyLjc0OTgzOSA4Ljc4NTk3ODE2LDEyLjU0OTY2OCA4LjUyOTE0NDQyLDEyLjU0MTg1NyBDOC4yNTMxMjk2NiwxMi41MzM0NjI3IDguMDIyNTcwNTcsMTIuNzUwNDEyIDguMDE0MTc2MjcsMTMuMDI2NDI2OCBMOC4wMDAyMzU5NywxMy40ODQ4MDA4IEM3Ljk5MTY2MDY0LDEzLjc2Njc2ODIgOC4yMTc5MDcwOCwxNCA4LjUwMDAwNDksMTQgTDEwLDE0IEwxMCwxNC40OTEyNDE1IEMxMCwxNC43NjczODM4IDEwLjIyMzg1NzYsMTQuOTkxMjQxNSAxMC41LDE0Ljk5MTI0MTUgQzEwLjc3NjE0MjQsMTQuOTkxMjQxNSAxMSwxNC43NjczODM4IDExLDE0LjQ5MTI0MTUgTDExLDE0IEwxMS41MDAwMDQ5LDE0IEMxMi4zMjg0MzIsMTQgMTMuMDAwMDA0OSwxMy4zMjg0MjcxIDEzLjAwMDAwNDksMTIuNSBMMTMuMDAwMDA0OSwxMi4wMDA3NTY3IEMxMy4wMDAwMDQ5LDExLjE3MjMyOTYgMTIuMzI4NDMyLDEwLjUwMDc1NjcgMTEuNTAwMDA0OSwxMC41MDA3NTY3IEw5LjUxMzk0NTIsMTAuNTAwNzU2NyBDOS4yMzc4MDI4MiwxMC41MDA3NTY3IDkuMDEzOTQ1MiwxMC4yNzY4OTkxIDkuMDEzOTQ1MiwxMC4wMDA3NTY3IEw5LjAxMzk0NTIsOS41IEM5LjAxMzk0NTIsOS4yMjM4NTc2MyA5LjIzNzgwMjgyLDkgOS41MTM5NDUyLDkgTDExLjk2Nzk1NTksOSBDMTIuMDIyMjIwMyw5LjIxNzQ0NzMzIDEyLjIxODg0ODEsOS4zNzg1NDAwNCAxMi40NTMxMDEsOS4zNzg1NDAwNCBDMTIuNzI5MjQzNCw5LjM3ODU0MDA0IDEyLjk1MzEwMSw5LjE1NDY4MjQxIDEyLjk1MzEwMSw4Ljg3ODU0MDA0IEwxMi45NTMxMDEsOC41IEMxMi45NTMxMDEsOC4yMjM4NTc2MyAxMi43MjkyNDM0LDggMTIuNDUzMTAxLDggTDExLDggWiIgZmlsbD0iIzQ0RDVFOSIgaWQ9IkNvbWJpbmVkLVNoYXBlIi8+PC9nPjwvZz48L2c+PC9zdmc+";
        */

        CategoryAxis categoryAxis = (CategoryAxis) chart.xAxes.push(new CategoryAxis());
        categoryAxis.dataFields.category = "category";
        categoryAxis.renderer.grid.template.location = 0;

        ValueAxis valueAxis = (ValueAxis) chart.yAxes.push(new ValueAxis());
        valueAxis.min = 0;
        valueAxis.renderer.minWidth = 35;

        ColumnSeries series1 = (ColumnSeries) chart.series.push(new ColumnSeries());
        series1.columns.template.width = new Percent(80);
        series1.columns.template.tooltipText = "{name}: {valueY.value}";
        series1.name = "Series 1";
        series1.dataFields.categoryX = "category";
        series1.dataFields.valueY = "value1";
        series1.stacked = true;

        ColumnSeries series2 = (ColumnSeries) chart.series.push(new ColumnSeries());
        series2.columns.template.width = new Percent(80);
        series2.columns.template.tooltipText = "{name}: {valueY.value}";
        series2.name = "Series 2";
        series2.dataFields.categoryX = "category";
        series2.dataFields.valueY = "value2";
        series2.stacked = true;

        ColumnSeries series3 = (ColumnSeries) chart.series.push(new ColumnSeries());
        series3.columns.template.width = new Percent(80);
        series3.columns.template.tooltipText = "{name}: {valueY.value}";
        series3.name = "Series 3";
        series3.dataFields.categoryX = "category";
        series3.dataFields.valueY = "value3";
        series3.stacked = true;

        ColumnSeries series4 = (ColumnSeries) chart.series.push(new ColumnSeries());
        series4.columns.template.width = new Percent(80);
        series4.columns.template.tooltipText = "{name}: {valueY.value}";
        series4.name = "Series 4";
        series4.dataFields.categoryX = "category";
        series4.dataFields.valueY = "value4";
        series4.stacked = true;
    }

    protected void createCustom() {
        // XYChart Demo
        XYChart xyChart = XYChart.create(customPanel);
        // DateAxis
        DateAxis dateAxis = (DateAxis) xyChart.xAxes.push(new DateAxis());

        // Axis Line
        dateAxis.renderer.line.strokeOpacity = 1;
        dateAxis.renderer.line.strokeWidth = 2;
        dateAxis.renderer.line.stroke = new Color("#3787ac");

        // POsition or Location
        //dateAxis.renderer.grid.template.location = 0;

        // Disabling certain elements
        //dateAxis.renderer.grid.template.disabled = true;

        // ValueAxis
        ValueAxis valueAxis = (ValueAxis) xyChart.yAxes.push(new ValueAxis());
        // Number Formatter
        valueAxis.numberFormatter.numberFormat = "'$' #.a";

        // Titles
        valueAxis.title.text = "Turnover ($M)";
        valueAxis.title.fontWeight = "bold";

        // Grid
        valueAxis.renderer.grid.template.strokeOpacity = 1;
        valueAxis.renderer.grid.template.stroke = new Color("#A0CA92");
        valueAxis.renderer.grid.template.strokeWidth = 2;

        // Labels
        valueAxis.renderer.labels.template.fill = new Color("#A0CA92");
        valueAxis.renderer.labels.template.fontSize = 20;

        // Ticks
        valueAxis.renderer.ticks.template.strokeOpacity = 1;
        valueAxis.renderer.ticks.template.stroke = new Color("#495C43");
        valueAxis.renderer.ticks.template.strokeWidth = 2;
        valueAxis.renderer.ticks.template.length = 10;

        // AxisBreaks
        AxisBreak axisBreak = valueAxis.axisBreaks.create();
        axisBreak.startValue = 15000;
        axisBreak.endValue = 10000;
        axisBreak.breakSize = 0.05;

        //TODO: Issue with Events
        /*axisBreak.events.on("over", param1 -> {

            SpriteAnimationOptions options = new SpriteAnimationOptions();
            options.property = "breakSize";
            options.to = 1;

            SpriteAnimationOptions options2 = new SpriteAnimationOptions();
            options2.property = "opacity";
            options2.to = 0.1;

            axisBreak.animate(new SpriteAnimationOptions[]{options, options2}, 1500);
        });

        axisBreak.events.on("out", param1 -> {

            SpriteAnimationOptions options = new SpriteAnimationOptions();
            options.property = "breakSize";
            options.to = 0.05;

            SpriteAnimationOptions options2 = new SpriteAnimationOptions();
            options2.property = "opacity";
            options2.to = 1;

            axisBreak.animate(new SpriteAnimationOptions[]{options, options2}, 1500);
        });*/

        // Series
        ColumnSeries columnSeries = (ColumnSeries) xyChart.series.push(new ColumnSeries());
        columnSeries.dataFields.valueY = "value";
        columnSeries.dataFields.dateX = "day";
        columnSeries.strokeWidth = 1.8;
        columnSeries.columns.template.tooltipText = "THIS IS A TOOLTIP: {name}\nCategory: {categoryX}\nValue: {valueY}";
        xyChart.dataSource.url = "data/large-data.json";
        // Scrollbar
        XYChartScrollbar scrollbarX = new XYChartScrollbar();
        scrollbarX.series.push(columnSeries);
        xyChart.scrollbarX = scrollbarX;
        xyChart.scrollbarX.updateWhileMoving = false;

        xyChart.scrollbarY = new XYChartScrollbar();
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

        // Legends
        xyChart.legend = new Legend();
        xyChart.legend.useDefaultMarker = true;
        Sprite marker = xyChart.legend.markers.template.children.getIndex(0);
        marker.strokeWidth = 2;
        marker.strokeOpacity = 1;
        marker.stroke = new Color("#ccc");

        xyChart.legend.markers.template.width = 40;
        xyChart.legend.markers.template.height = 40;

        // Disabling Legend Marker
        xyChart.legend.markers.template.disabled = true;

        // Legend Label
        columnSeries.legendSettings.labelText = "Series: [bold {stroke}]{name}[/]";
        columnSeries.legendSettings.valueText = "{valueY.close}";

        // Interacting with cursor
        columnSeries.legendSettings.itemValueText = "[bold]{valueY}[/bold]";

        // Legend Spacing
        xyChart.legend.itemContainers.template.paddingTop = 5;
        xyChart.legend.itemContainers.template.paddingBottom = 5;
    }

    protected void createMapChart() {
        /*// Create map instance
        MapChart chart = (MapChart) Am4Core.create(mapChartPanel, Am4Maps.MapChart);

        // Set map definition
        chart.geodata = WorldLow.get();

        // Set projection
        chart.projection = new Miller();

        // Create map polygon series
        MapPolygonSeries polygonSeries = new MapPolygonSeries();

        // Make map load polygon (like country names) data from GeoJSON
        polygonSeries.useGeodata = true;

        // Exclude Antarctica
        polygonSeries.exclude = new String[]{"AQ"};

        // Configure series
        MapPolygon polygonTemplate = polygonSeries.mapPolygons.template;
        polygonTemplate.tooltipText = "{name}";
        polygonTemplate.fill = new Color("#74B266");

        // Create hover state and set alternative fill color
        SpriteState<MapPolygonProperties> hs = polygonTemplate.states.create("hover");
        hs.properties.fill = new Color("#367B25");

       *//* polygonTemplate.events.on("hit", param1 -> {
            chart.closeAllPopups();
            chart.openPopup("We clicked on <strong>" + param1.target.dataItem.dataContext + "</strong>");
        });*/
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

        SpriteState<MapPolygonProperties> state = polygonTemplate.states.create("hover");
        state.properties.fill = new Color("red");

        polygonTemplate.events.on("hit", param1 -> {
            mapChart.closeAllPopups();
            MapDataContext context = (MapDataContext) param1.target.dataItem.dataContext;
            mapChart.openPopup("We clicked on <strong>" + context.name + "</strong>");
        });
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

        // Titles
        valueAxis.title.text = "Turnover ($M)";
        valueAxis.title.fontWeight = "bold";

        // Grid
        valueAxis.renderer.grid.template.strokeOpacity = 1;
        valueAxis.renderer.grid.template.stroke = new Color("#A0CA92");
        valueAxis.renderer.grid.template.strokeWidth = 2;

        // Labels
        valueAxis.renderer.labels.template.fill = new Color("#A0CA92");
        valueAxis.renderer.labels.template.fontSize = 20;

        // Ticks
        valueAxis.renderer.ticks.template.strokeOpacity = 1;
        valueAxis.renderer.ticks.template.stroke = new Color("#495C43");
        valueAxis.renderer.ticks.template.strokeWidth = 2;
        valueAxis.renderer.ticks.template.length = 10;

        // Series
        ColumnSeries columnSeries = (ColumnSeries) xyChart.series.push(new ColumnSeries());
        columnSeries.dataFields.valueY = "value";
        columnSeries.dataFields.dateX = "day";
        columnSeries.strokeWidth = 1.8;
        columnSeries.columns.template.tooltipText = "THIS IS A TOOLTIP: {name}\nCategory: {categoryX}\nValue: {valueY}";
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
        RadarSeries series = chart.series.push(new RadarSeries());
        series.dataFields.valueY = "litres";
        series.dataFields.categoryX = "country";
        chart.dataSource.url = "data/basic.json";
    }

    protected void createLineChart() {
        // XYChart Demo
        XYChart xyChart = (XYChart) Am4Core.create(xyChartPanel, Am4Charts.XYChart);
        // Language
        xyChart.language.locale = Locale.ru_RU.getValue();
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
        series.tooltip.getFillFromObject = false;
        series.tooltip.background.fill = new Color("#fff");
        series.tooltip.label.fill = new Color("#000");
        pieChart.dataSource.url = "data/basic.json";
        // Export feature
        ExportMenu menu = pieChart.exporting.menu = new ExportMenu();
        menu.align = Align.LEFT;
        menu.verticalAlign = VerticalAlign.TOP;

        // Root TODO: Custom export items
       /* ExportMenuItem root = new ExportMenuItem();
        root.label = "...";

        // RootChild - PNG
        ExportMenuItem png = new ExportMenuItem();
        png.type = "png";
        png.label = "PNG";

        // RootChild - JSON
        ExportMenuItem json = new ExportMenuItem();
        json.type = "json";
        json.label = "JSON";

        // RootChild - Print
        ExportMenuItem print = new ExportMenuItem();
        print.type = "print";
        print.label = "Print";

        root.menu = new ExportMenuItem[]{png, json, print};
        menu.items = new ExportMenuItem[]{root};*/
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
        sankeyDiagram.dataFields.value = "value";
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
        chart.dataFields.value = "value";
        chart.dataSource.url = "data/sankey-diagram.json";
    }

    private void createSlicedChart() {
        // Funnel
        SlicedChart funnel = (SlicedChart) Am4Core.create(slicedChartPanel, Am4Charts.SlicedChart);
        FunnelSeries series = funnel.series.push(new FunnelSeries());
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
