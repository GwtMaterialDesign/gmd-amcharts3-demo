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
import gwt.material.design.amcharts.client.ui.chart.PieChart;
import gwt.material.design.amcharts.client.ui.chart.XYChart;
import gwt.material.design.amcharts.client.ui.chart.axis.DateAxis;
import gwt.material.design.amcharts.client.ui.chart.axis.ValueAxis;
import gwt.material.design.amcharts.client.ui.chart.base.Color;
import gwt.material.design.amcharts.client.ui.chart.base.Container;
import gwt.material.design.amcharts.client.ui.chart.base.Percent;
import gwt.material.design.amcharts.client.ui.chart.base.Rectangle;
import gwt.material.design.amcharts.client.ui.chart.constants.Align;
import gwt.material.design.amcharts.client.ui.chart.constants.Valign;
import gwt.material.design.amcharts.client.ui.chart.cursor.XYCursor;
import gwt.material.design.amcharts.client.ui.chart.export.ExportMenu;
import gwt.material.design.amcharts.client.ui.chart.scrollbar.XYChartScrollbar;
import gwt.material.design.amcharts.client.ui.chart.series.ColumnSeries;
import gwt.material.design.amcharts.client.ui.chart.series.LineSeries;
import gwt.material.design.amcharts.client.ui.chart.series.PieSeries;
import gwt.material.design.amcharts.client.ui.chart.theme.AnimatedTheme;
import gwt.material.design.amcharts.client.ui.chart.theme.MaterialTheme;
import gwt.material.design.client.ui.MaterialCard;
import gwt.material.design.client.ui.MaterialToast;

import javax.inject.Inject;
import java.util.Date;

public class HomeView extends ViewImpl implements HomePresenter.MyView {
    interface Binder extends UiBinder<Widget, HomeView> {
    }

    @UiField
    MaterialCard pieChartPanel, xyChartPanel, colorsPanel, columnChartPanel;

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
    }

    protected void createColumnChart() {
        // XYChart Demo
        XYChart xyChart = (XYChart) Am4Core.create(columnChartPanel, Am4Charts.XYChart);
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
        Rectangle rectangle = (Rectangle) container.createChild(Am4Core.Rectangle);
        rectangle.width = new Percent(50);
        rectangle.height = new Percent(50);
        rectangle.align = Align.CENTER;
        rectangle.valign = Valign.MIDDLE;
        rectangle.strokeWidth = 3;
        rectangle.fill = new Color("green").lighten(0.5);
        rectangle.stroke = new Color("red").lighten(-0.5);
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
}
