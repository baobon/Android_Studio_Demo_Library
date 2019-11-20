package com.example.demo_mpandroidchart;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
//import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.components.AxisBase;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity  implements  OnChartGestureListener,OnChartValueSelectedListener{

    private static final String TAG = "MainActivity";
    private LineChart mcChart;
    int datademo1;
    int datademo2;

    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        Log.i(TAG,"onChartGestureStart: X: "+me.getX() + "Y: " + me.getY());
    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        Log.i(TAG,"onChartGestureEnd: " + lastPerformedGesture);
    }

    @Override
    public void onChartLongPressed(MotionEvent me) {
        Log.i(TAG,"onChartLongPressed:");
    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {
        Log.i(TAG,"onChartDoubleTapped:");
    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {
        Log.i(TAG,"onChartSingleTapped:");
    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {
        Log.i(TAG, "onChartFling: velocX: " + velocityX +"  velocY: " +velocityY);
    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {
        Log.i(TAG, "onChartScale: ScaleX: " + scaleX +"   ScaleY: "+scaleY);
    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {
        Log.i(TAG, "onChartTranslate: dX: " + dX +"dy: "+dY);
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        Log.i(TAG, "onValueSelected: "+e.toString());
    }

    @Override
    public void onNothingSelected() {
        Log.i(TAG, "onNothingSelected: ");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ và cấu hình
        mcChart = findViewById(R.id.linechart);

        mcChart.setOnChartGestureListener(MainActivity.this);
        mcChart.setOnChartValueSelectedListener(MainActivity.this);

        mcChart.setDragEnabled(true);
        mcChart.setScaleEnabled(false);

        // Định đường danh giới cảnh báo mức cao
        LimitLine upper_limit = new LimitLine(90f,"Danger");
        upper_limit.setLineWidth(4f);
        upper_limit.enableDashedLine(10f,10f,0f);
        upper_limit.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        upper_limit.setTextSize(15f);

        // Định đường danh giới cảnh báo mức thấp
        LimitLine lower_limit = new LimitLine(30f,"Too low");
        lower_limit.setLineWidth(4f);
        lower_limit.enableDashedLine(10f,10f,0f);
        lower_limit.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        lower_limit.setTextSize(15f);


        // Hiển thị đường danh giới cảnh báo ra
        YAxis leftAxis = mcChart.getAxisLeft();
        leftAxis.removeAllLimitLines();
        leftAxis.addLimitLine(upper_limit);
        leftAxis.addLimitLine(lower_limit);
        leftAxis.setAxisMaximum(100f);
        leftAxis.setAxisMinimum(25f);
        leftAxis.enableAxisLineDashedLine(10f,10f,0f);
        leftAxis.setDrawLimitLinesBehindData(true);

        mcChart.getAxisRight().setEnabled(false);




        /*
            Tạo một biến ảo yValues
            Tạo mới 1 LineData để đưa dữ liệu vào
            set dữ liệu trên data đó
            Tạo ra 1 ArrayList chứ ILineDataSet
            Dataset này sẽ add linedataset vào
            Tạo ra 1 LineData để chứa datasets;
            đưa linedata này vào mChart tạo lức đầu
         */
        ArrayList<Entry> yValues = new ArrayList<>();
        Random random = new Random(100);
        yValues.add(new Entry(1,random.nextInt(100)));
        yValues.add(new Entry(2,random.nextInt(100)));
        yValues.add(new Entry(3,random.nextInt(100)));
        yValues.add(new Entry(4,random.nextInt(100)));
        yValues.add(new Entry(5,random.nextInt(100)));
        yValues.add(new Entry(6,random.nextInt(100)));
//        yValues.add(new Entry(7,random.nextInt(100)));
//        yValues.add(new Entry(8,random.nextInt(100)));
//        yValues.add(new Entry(9,random.nextInt(100)));
//        yValues.add(new Entry(10,random.nextInt(100)));
//        yValues.add(new Entry(11,random.nextInt(100)));
//        yValues.add(new Entry(12,random.nextInt(100)));

        LineDataSet set1 = new LineDataSet(yValues,"Data Set 1");
        set1.setColor(Color.RED);
        set1.setLineWidth(3f);
        set1.setValueTextSize(10f);
        set1.setValueTextColor(Color.GREEN);
        set1.setFillAlpha(110);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);
        LineData data = new LineData(dataSets);
        mcChart.setData(data);

        String[] values = new String[] {"Jan","Feb","Mar","Apr","May","Jun"};

        XAxis xAxis = mcChart.getXAxis();
        xAxis.setValueFormatter(new MyXAxisValueFormatter(values));
//        xAxis.setGranularity(1f);


    }

    public class MyXAxisValueFormatter implements IAxisValueFormatter {

        private String[] mValues;

        public MyXAxisValueFormatter(String[] values) {
            this.mValues = values;
        }
        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return mValues[(int) value];
        }

    }
}
