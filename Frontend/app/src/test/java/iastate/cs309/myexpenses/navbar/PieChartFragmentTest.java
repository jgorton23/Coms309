package iastate.cs309.myexpenses.navbar;

import android.view.View;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class PieChartFragmentTest {

    @Mock
    private View view;
    @Mock
    private PieChart pieChart;
    @Mock
    private Description description;
    @Mock
    private Legend legend;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test() {
        when(view.findViewById(anyInt())).thenReturn(pieChart);
        when(pieChart.getDescription()).thenReturn(description);
        when(pieChart.getLegend()).thenReturn(legend);

        PieChartFragment pieChartFragment = new PieChartFragment();

        PieChart newPieChart = pieChartFragment.initChart(view);

        assertEquals(pieChart, newPieChart);

        verify(view, only()).findViewById(anyInt());
        verify(pieChart, times(1)).getDescription();
        verify(pieChart, times(1)).getLegend();
        verifyNoMoreInteractions(view);
    }
}