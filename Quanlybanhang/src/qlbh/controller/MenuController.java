package qlbh.controller;


import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import qlbh.bean.ChoBean;
import qlbh.bean.HoaDonBean;
import qlbh.service.MenuService;
import qlbh.service.MenuServiceImpl;
/**
 *
 * @author congl
 */
public class MenuController {
    
    private MenuService menuService = null;

    public MenuController() {
        this.menuService = new MenuServiceImpl();
    }

    public void setDataToChart1(JPanel jpnItem) {
        List<HoaDonBean> listItem = menuService.getListByHoaDon();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if (listItem != null) {
            for (HoaDonBean item : listItem) {
                dataset.addValue(item.getTongTien(), "Tổng hóa đơn", item.getNgayLapHD());
            }
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "Biểu đồ thống kê số lượng hóa đơn trong từng ngày".toUpperCase(),
                "Thời gian", "Số lượng",
                dataset, PlotOrientation.VERTICAL, false, true, false);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(1000, 400));

        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();
    }
    
    public void setDataToChart2(JPanel jpnItem) {
        List<ChoBean> listItem = menuService.getListByCho();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if (listItem != null) {
            for (ChoBean item : listItem) {
                dataset.addValue(item.getSoLuongCho(), "Tổng", item.getMaDanhMuc());
            }
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "Biểu đồ thống kê số lượng chó".toUpperCase(),
                "Tên danh mục", "Số lượng",
                dataset, PlotOrientation.VERTICAL, false, true, false);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(500, 300));

        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();
    }
}
