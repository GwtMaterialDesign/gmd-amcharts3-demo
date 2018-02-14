package gwt.material.amcharts.demo.client.application.cards;

/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2016 GwtMaterialDesign
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


import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.amcharts.demo.shared.model.ChartData;
import gwt.material.design.client.ui.MaterialColumn;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialPanel;

public class DemoCard extends Composite {

    private static DemoCardUiBinder uiBinder = GWT.create(DemoCardUiBinder.class);
    @UiField
    MaterialLabel title, description;
    @UiField
    MaterialPanel chartPanel;
    @UiField
    MaterialColumn container;

    public DemoCard() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    public void setModel(ChartData data) {
        title.setText(data.getName());
        description.setText(data.getDescription());
        chartPanel.add(data.getChart());
        container.setScrollspy(data.getDemoUrl().replace("#", ""));
    }

    interface DemoCardUiBinder extends UiBinder<Widget, DemoCard> {
    }
}
