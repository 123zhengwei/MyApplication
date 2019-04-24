package  com.example.administrator.myapplication;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.MapView;
import com.example.administrator.myapplication.R;


public class LebenFragment extends Fragment {
    ImageButton btA;
    ImageButton btB;
    ImageButton btC;
    private boolean isVisible = true;
    MapView mMapView = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        SDKInitializer.initialize(getActivity().getApplicationContext());
        View view = inflater.inflate(R.layout.tab01, container, false);
        mMapView = (MapView)view.findViewById(R.id.bmapView);
        return view;

    }

    //百度地图的生命周期
    @Override
    public void onResume() {
        super.onResume();
        //在Fragment执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        //在<span style="font-family: 微软雅黑, 'Microsoft YaHei', sans-serif;">Fragment</span>执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //在Fragment执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }

    //按钮活动
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ImageButton btA = (ImageButton) getActivity().findViewById(R.id.btA);
        final ImageButton btB = (ImageButton) getActivity().findViewById(R.id.btB);
        final ImageButton btC = (ImageButton) getActivity().findViewById(R.id.btC);


        btB.setVisibility(View.INVISIBLE);
        btC.setVisibility(View.INVISIBLE);
        btA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isVisible) {
                    btB.setVisibility(View.VISIBLE);
                    btC.setVisibility(View.VISIBLE);
                    isVisible = false;
                } else {
                    btB.setVisibility(View.INVISIBLE);
                    btC.setVisibility(View.INVISIBLE);
                    isVisible = true;
                }
            }
        });
    }


}



