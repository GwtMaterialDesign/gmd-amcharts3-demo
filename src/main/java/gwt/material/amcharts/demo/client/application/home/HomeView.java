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
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import gwt.material.design.amcharts.client.ui.Am4Charts;
import gwt.material.design.amcharts.client.ui.Am4Core;
import gwt.material.design.amcharts.client.ui.chart.PieChart;
import gwt.material.design.amcharts.client.ui.chart.resources.ChartClientBundle;
import gwt.material.design.amcharts.client.ui.chart.series.PieSeries;
import gwt.material.design.client.MaterialDesignBase;

import javax.inject.Inject;

public class HomeView extends ViewImpl implements HomePresenter.MyView {
    interface Binder extends UiBinder<Widget, HomeView> {
    }

    @Inject
    HomeView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    protected void onAttach() {
        super.onAttach();
        
        MaterialDesignBase.injectDebugJs(ChartClientBundle.INSTANCE.coreJs());
        MaterialDesignBase.injectDebugJs(ChartClientBundle.INSTANCE.chartsJs());
        MaterialDesignBase.injectDebugJs(ChartClientBundle.INSTANCE.animatedJs());

        PieChart chart = (PieChart) Am4Core.create("div", Am4Charts.PieChart);

        PieSeries series = chart.series.push(new PieSeries());
        series.dataFields.value = "litres";
        series.dataFields.category = "country";
        chart.dataSource.url = "data/basic.json";
    }
}
