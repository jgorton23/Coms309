package iastate.cs309.myexpenses;

import android.view.View;

import com.github.mikephil.charting.charts.BarChart;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class BarGraphFragmentTest {

    @Mock
    private View view;
    @Mock
    private BarChart barChart;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test() {
        when(view.findViewById(anyInt())).thenReturn(barChart);

        BarGraphFragment barGraphFragment = new BarGraphFragment();

        BarChart newBarChart = barGraphFragment.initChart(view);

        assertEquals(barChart, newBarChart);

        verify(view, only()).findViewById(anyInt());
        verifyNoMoreInteractions(view);
    }

}