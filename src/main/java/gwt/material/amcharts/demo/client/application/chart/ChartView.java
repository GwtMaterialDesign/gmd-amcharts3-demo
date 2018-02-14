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
package gwt.material.amcharts.demo.client.application.chart;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import gwt.material.amcharts.demo.client.application.cards.DemoCard;
import gwt.material.amcharts.demo.shared.DataHelper;
import gwt.material.amcharts.demo.shared.model.ChartData;
import gwt.material.design.client.ui.MaterialColumn;
import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialScrollspy;

import javax.inject.Inject;

public class ChartView extends ViewImpl implements ChartPresenter.MyView {

    @UiField
    MaterialColumn chartPanel;
    @UiField
    MaterialScrollspy scrollspyPanel;

    @Inject
    ChartView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void load() {
        scrollspyPanel.clear();
        chartPanel.clear();
        for (ChartData data : DataHelper.getAllLineCharts()) {
            DemoCard card = new DemoCard();
            card.setModel(data);
            chartPanel.add(card);

            MaterialLink link = new MaterialLink();
            link.setText(data.getName());
            link.setHref(data.getDemoUrl());
            scrollspyPanel.add(link);
        }
    }

    interface Binder extends UiBinder<Widget, ChartView> {
    }
}
