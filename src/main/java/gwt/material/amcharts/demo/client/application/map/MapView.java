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
package gwt.material.amcharts.demo.client.application.map;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import gwt.material.design.amcharts.client.ui.maps.Map;
import gwt.material.design.amcharts.client.ui.maps.base.constants.MapLayer;
import gwt.material.design.amcharts.client.ui.maps.js.options.AreasSettings;
import gwt.material.design.amcharts.client.ui.maps.js.options.MapData;
import gwt.material.design.client.constants.Color;

import javax.inject.Inject;

public class MapView extends ViewImpl implements MapPresenter.MyView {
    @UiField
    Map map, mapCanada;

    @Inject
    MapView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));

        buildMap(map, MapLayer.WORLD_LOW);
        buildMap(mapCanada, MapLayer.CANADA_LOW);
    }

    protected void buildMap(Map map, MapLayer layer) {
        MapData dataProvider = new MapData();
        dataProvider.setMap(layer);
        dataProvider.setGetAreasFromMap(true);
        map.setDataProvider(dataProvider);

        AreasSettings areasSettings = new AreasSettings();
        areasSettings.setAutoZoom(true);
        areasSettings.setSelectedColor(Color.BLUE_DARKEN_3);
        areasSettings.setUnlistedAreasColor(Color.GREY_LIGHTEN_2);
        areasSettings.setRollOverOutlineColor(Color.BLUE_DARKEN_3);
        areasSettings.setColor(Color.BLUE_ACCENT_1);
        map.setAreasSettings(areasSettings);
    }

    interface Binder extends UiBinder<Widget, MapView> {
    }
}
