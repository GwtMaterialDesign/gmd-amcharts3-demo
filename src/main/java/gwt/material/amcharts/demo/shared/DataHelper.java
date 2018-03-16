package gwt.material.amcharts.demo.shared;

import gwt.material.amcharts.demo.shared.model.ChartData;
import gwt.material.amcharts.demo.shared.model.bar.*;
import gwt.material.amcharts.demo.shared.model.column.*;
import gwt.material.amcharts.demo.shared.model.line.DateBasedData;
import gwt.material.amcharts.demo.shared.model.polar.PolarChart;

import java.util.ArrayList;
import java.util.List;

public class DataHelper {

    public static List<ChartData> getAllLineCharts() {
        List<ChartData> list = new ArrayList<>();
        list.add(new ColumnWithRotatedSeries());
        list.add(new ColumnSimple());
        list.add(new StackedColumn());
        list.add(new BasicGanttChart());
        list.add(new DateGanttChart());
        list.add(new ColumnAndLineMix());
        list.add(new StackedBarChartNegative());
        list.add(new LayeredColumnChart());
        list.add(new ClusteredBarChart());
        list.add(new BarAndLineMix());
        list.add(new StackedBar());
        list.add(new FloatingBarChart());
        list.add(new ColumnImageChart());
        list.add(new DateBasedData());
        list.add(new PolarChart());
        return list;
    }
}
