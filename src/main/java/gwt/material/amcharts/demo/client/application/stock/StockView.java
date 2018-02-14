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
package gwt.material.amcharts.demo.client.application.stock;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import gwt.material.design.amcharts.client.ui.chart.base.constants.GraphType;
import gwt.material.design.amcharts.client.ui.stock.StockChart;
import gwt.material.design.amcharts.client.ui.stock.StockGraph;
import gwt.material.design.amcharts.client.ui.stock.StockLegend;
import gwt.material.design.amcharts.client.ui.stock.StockPanel;
import gwt.material.design.amcharts.client.ui.stock.js.*;
import gwt.material.design.client.ui.MaterialToast;

import javax.inject.Inject;

public class StockView extends ViewImpl implements StockPresenter.MyView {
    @UiField
    StockChart stockChart;

    @Inject
    StockView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));

        DataSet dataSet = new DataSet();
        dataSet.setDataProvider(getData());

        FieldMapping f1 = new FieldMapping();
        f1.setFromField("val");
        f1.setToField("value");
        dataSet.setFieldMappings(f1);
        dataSet.setCategoryField("date");
        stockChart.setDataSets(dataSet);

        StockPanel stockPanel = new StockPanel();
        stockPanel.addBuildStartedHandler(event -> MaterialToast.fireToast("Build Started"));
        stockChart.setPanels(stockPanel);

        StockLegend stockLegend = new StockLegend();
        stockLegend.addHideItemHandler(event -> MaterialToast.fireToast("DAS"));
        stockPanel.setStockLegend(stockLegend);

        PanelsSettings panelsSettings = new PanelsSettings();
        panelsSettings.setStartDuration(1);
        stockChart.setPanelsSettings(panelsSettings);

        StockGraph stockGraph = new StockGraph();
        stockGraph.setValueField("value");
        stockGraph.setType(GraphType.COLUMN);
        stockGraph.setTitle("MyGraph");
        stockGraph.setFillAlphas(1);
        stockPanel.addStockGraph(stockGraph);

        CategoryAxesSettings categoryAxesSettings = new CategoryAxesSettings();
        categoryAxesSettings.setDashLength(1);
        stockChart.setCategoryAxesSettings(categoryAxesSettings);

        ValueAxesSettings valueAxesSettings = new ValueAxesSettings();
        valueAxesSettings.setDashLength(5);
        stockChart.setValueAxesSettings(valueAxesSettings);

        ChartScrollbarSettings chartScrollbarSettings = new ChartScrollbarSettings();
        chartScrollbarSettings.setGraph(stockGraph);
        chartScrollbarSettings.setGraphType(GraphType.LINE);
        stockChart.setChartScrollbarSettings(chartScrollbarSettings);

        ChartCursorSettings chartCursorSettings = new ChartCursorSettings();
        chartCursorSettings.setValueBalloonsEnabled(true);
        stockChart.setChartCursorSettings(chartCursorSettings);

        PeriodSelector periodSelector = new PeriodSelector();

        Period oneDay = new Period();
        oneDay.setPeriod("DD");
        oneDay.setCount(1);
        oneDay.setLabel("1 day");

        Period fiveDays = new Period();
        fiveDays.setPeriod("DD");
        fiveDays.setCount(5);
        fiveDays.setSelected(true);
        fiveDays.setLabel("5 days");

        Period oneMonth = new Period();
        oneMonth.setPeriod("MM");
        oneMonth.setCount(1);
        oneMonth.setLabel("1 month");

        Period oneYear = new Period();
        oneYear.setPeriod("YYYY");
        oneYear.setCount(1);
        oneYear.setLabel("1 year");


        Period ytd = new Period();
        ytd.setPeriod("YTD");
        ytd.setLabel("YTD");

        Period max = new Period();
        max.setPeriod("MAX");
        max.setLabel("MAX");

        periodSelector.setPeriods(oneDay, fiveDays, oneMonth, oneYear, ytd, max);
        periodSelector.setHideOutOfScopePeriods(false);
        stockChart.setPeriodSelector(periodSelector);
    }

    public native Object[] getData() /*-{
        var chartData = [
            {date: new Date(2011, 5, 1, 0, 0, 0, 0), val: 10},
            {date: new Date(2011, 5, 2, 0, 0, 0, 0), val: 11},
            {date: new Date(2011, 5, 3, 0, 0, 0, 0), val: 12},
            {date: new Date(2011, 5, 4, 0, 0, 0, 0), val: 11},
            {date: new Date(2011, 5, 5, 0, 0, 0, 0), val: 10},
            {date: new Date(2011, 5, 6, 0, 0, 0, 0), val: 11},
            {date: new Date(2011, 5, 7, 0, 0, 0, 0), val: 13},
            {date: new Date(2011, 5, 8, 0, 0, 0, 0), val: 14},
            {date: new Date(2011, 5, 9, 0, 0, 0, 0), val: 17},
            {date: new Date(2011, 5, 10, 0, 0, 0, 0), val: 13}
        ];
        return chartData;
    }-*/;

    interface Binder extends UiBinder<Widget, StockView> {
    }
}
