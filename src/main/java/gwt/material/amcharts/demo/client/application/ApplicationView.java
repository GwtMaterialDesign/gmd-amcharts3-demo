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
package gwt.material.amcharts.demo.client.application;

import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import gwt.material.amcharts.demo.client.place.NameTokens;
import gwt.material.amcharts.demo.client.pwa.AppServiceWorkerManager;
import gwt.material.design.client.pwa.PwaManager;
import gwt.material.design.client.ui.MaterialContainer;

public class ApplicationView extends ViewImpl implements ApplicationPresenter.MyView {
    interface Binder extends UiBinder<Widget, ApplicationView> {
    }

    @UiField
    MaterialContainer container;

    @Inject
    ApplicationView(
            Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));

        bindSlot(ApplicationPresenter.SLOT_MAIN, container);

        PwaManager.getInstance()
                .setServiceWorker(new AppServiceWorkerManager("service-worker.js"))
                .setWebManifest("manifest.json")
                .setThemeColor("#2196f3")
                .load();

    }

    @UiHandler("charts")
    void charts(ClickEvent e) {
        Window.Location.replace("#" + NameTokens.CHART);
    }

    @UiHandler("maps")
    void maps(ClickEvent e) {
        Window.Location.replace("#" + NameTokens.MAPS);
    }

    @UiHandler("stock")
    void stock(ClickEvent e) {
        Window.Location.replace("#" + NameTokens.STOCK);
    }


    @UiHandler("api")
    void api(ClickEvent e) {
        Window.Location.replace("#" + NameTokens.API);
    }

    @Override
    protected void onAttach() {
        super.onAttach();

        Document.get().getElementById("splashscreen").removeFromParent();
    }
}
