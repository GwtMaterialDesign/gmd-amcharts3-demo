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
import gwt.material.design.amcharts.client.ui.chart.axis.CategoryAxis;
import gwt.material.design.amcharts.client.ui.chart.axis.ValueAxis;
import gwt.material.design.amcharts.client.ui.chart.resources.ChartClientBundle;
import gwt.material.design.amcharts.client.ui.chart.scrollbar.XYChartScrollbar;
import gwt.material.design.amcharts.client.ui.chart.series.LineSeries;
import gwt.material.design.amcharts.client.ui.chart.series.PieSeries;
import gwt.material.design.client.MaterialDesignBase;
import gwt.material.design.client.ui.MaterialCard;

import javax.inject.Inject;

public class HomeView extends ViewImpl implements HomePresenter.MyView {
    interface Binder extends UiBinder<Widget, HomeView> {
    }

    @UiField
    MaterialCard pieChartPanel, xyChartPanel;

    @Inject
    HomeView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    protected void onAttach() {
        super.onAttach();

        // Inject the necessary javascript resources
        MaterialDesignBase.injectDebugJs(ChartClientBundle.INSTANCE.coreJs());
        MaterialDesignBase.injectDebugJs(ChartClientBundle.INSTANCE.chartsJs());

        // Pie Chart Demo
        PieChart pieChart = (PieChart) Am4Core.create(pieChartPanel, Am4Charts.PieChart);
        PieSeries series = pieChart.series.push(new PieSeries());
        series.dataFields.value = "litres";
        series.dataFields.category = "country";
        pieChart.dataSource.url = "data/basic.json";

        // XYChart Demo
        XYChart xyChart = (XYChart) Am4Core.create(xyChartPanel, Am4Charts.XYChart);
        CategoryAxis categoryAxis = (CategoryAxis) xyChart.xAxes.push(new CategoryAxis());
        categoryAxis.dataFields.category = "country";
        categoryAxis.title.text = "Countries";

        ValueAxis valueAxis = (ValueAxis) xyChart.yAxes.push(new ValueAxis());
        valueAxis.title.text = "Litres sold (M)";

        LineSeries lineSeries = (LineSeries) xyChart.series.push(new LineSeries());
        lineSeries.dataFields.valueY = "litres";
        lineSeries.dataFields.categoryX = "country";
        xyChart.dataSource.url = "data/basic.json";

        XYChartScrollbar scrollbarX = new XYChartScrollbar();
        scrollbarX.series.push(lineSeries);
        xyChart.scrollbarX = scrollbarX;

        // Hiding the scrollbar
        /*scrollbarX.scrollbarChart.seriesContainer.hide();*/
    }
}
