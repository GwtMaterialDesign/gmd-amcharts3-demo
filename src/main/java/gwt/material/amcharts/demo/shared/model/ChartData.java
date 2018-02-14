package gwt.material.amcharts.demo.shared.model;


import gwt.material.design.amcharts.client.ui.chart.base.AbstractChart;

public abstract class ChartData {

    private String name;
    private String description;
    private String demoUrl;
    private AbstractChart chart;

    public ChartData(String url, String name, String description) {
        setDemoUrl(url);
        setName(name);
        setDescription(description);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AbstractChart getChart() {
        return chart;
    }

    public void setChart(AbstractChart chart) {
        this.chart = chart;
    }

    public String getDemoUrl() {
        return demoUrl;
    }

    public void setDemoUrl(String demoUrl) {
        this.demoUrl = demoUrl;
    }
}
